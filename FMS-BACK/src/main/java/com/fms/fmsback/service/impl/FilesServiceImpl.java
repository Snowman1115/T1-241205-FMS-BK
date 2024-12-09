package com.fms.fmsback.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.fms.fmsback.common.constants.ResultConstants;
import com.fms.fmsback.common.result.Result;
import com.fms.fmsback.common.utils.JwtUtil;
import com.fms.fmsback.entity.Files;
import com.fms.fmsback.entity.PageBean;
import com.fms.fmsback.entity.User;
import com.fms.fmsback.exception.ServiceException;
import com.fms.fmsback.mapper.FilesMapper;
import com.fms.fmsback.service.IFilesService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FilesServiceImpl implements IFilesService {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Autowired
    private FilesMapper filesMapper;

    /**
     * Retrieve All File Based On User UserId / FolderId
     * @param page
     * @param pageSize
     * @param fileName
     * @param userId
     * @param folderId
     */
    @Override
    public PageBean page(Integer page, Integer pageSize, String fileName, String fileType, Long userId, Long folderId) {
        PageHelper.startPage(page, pageSize);

        List<Files> fileList = filesMapper.list(fileName, fileType, userId, folderId);
        Page<Files> files = (Page<Files>) fileList;

        PageBean pageBean = new PageBean(files.getTotal(), files.getResult());
        return pageBean;
    }

    /**
     * Get File By Id
     * @param id
     * @param request
     * @return File
     */
    @Override
    public Files getById(Integer id, HttpServletRequest request) {
        String jwt = request.getHeader("token");
        User user = JwtUtil.getUserFromJwt(jwt);
        Files files = filesMapper.getFileById(id);
        if (user.getId() == files.getUserId()) {
            files.setLastAccess(new Date());
            filesMapper.update(files);
        };
        return files;
    };

    /**
     * Get File By MD5
     * @param md5
     * @return File
     */
    @Override
    public List<Files> getByMd5(String md5) {
        return filesMapper.getFileByMd5(md5);
    }

    /**
     * Create New File
     * @param file
     * @return Boolean
     */
    @Override
    public Boolean save(Files file) {
        return filesMapper.save(file);
    }

    /**
     * Update File By Id
     * @param file
     * @return Boolean
     */
    @Override
    public Boolean update(Files file) {
        return filesMapper.update(file);
    }

    /**
     * Delete File By Id
     * @param id
     * @return Boolean
     */
    @Override
    public Boolean delete(Integer id) {
        Files files = filesMapper.getFileById(id);
        if (files == null) {
            throw new ServiceException(ResultConstants.NOT_FOUND, "Failed To Delete File Id (Not Found): " + id);
        }
        files.setIsDelete(true);
        return filesMapper.update(files);
    }

    /**
     * Delete Files By Ids
     * @param ids
     * @return Boolean
     */
    @Override
    public Boolean batchDelete(List<Integer> ids) {
        for (Integer id : ids) {
            Files files = filesMapper.getFileById(id);
            if (files == null) { continue; };
            files.setIsDelete(true);
            filesMapper.update(files);
        }
        return true;
    }

    /**
     * Upload Method
     * @param file
     * @param userId
     */
    public String upload(MultipartFile file, Long userId) throws IOException {
        if (file.isEmpty()) {
            throw new ServiceException(ResultConstants.BAD_REQUEST,"No File Selected To Upload. Please Try Again.");
        };
        String originalFileName = file.getOriginalFilename();
        String fileType = FileUtil.extName(originalFileName);
        long fileSize = file.getSize();
        File uploadParentFile = new File(fileUploadPath);
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdir(); // Create Directory If Not Exists
        };
        String uuid = UUID.randomUUID().toString();
        String filePath = fileUploadPath + uuid + "." + fileType;
        File uploadFile = new File(filePath);
        file.transferTo(uploadFile);
        String url = filePath;
        String md5 = SecureUtil.md5(uploadFile);
        Files dbFile = getFileByMd5(md5);
        if (dbFile != null) {
            url = dbFile.getUrl();
            uploadFile.delete();
        }
        Files saveFiles = new Files();
        saveFiles.setName(originalFileName);
        saveFiles.setUserId(userId);
        saveFiles.setType(fileType);
        saveFiles.setSize(fileSize/1024);
        saveFiles.setMd5(md5);
        saveFiles.setUrl(url);
        filesMapper.save(saveFiles);
        return url;
    };

    /**
     * Get Files By MD%
     * @return Files
     */
    private Files getFileByMd5(String md5) {
        List<Files> filesList = getByMd5(md5);
        return filesList.size() == 0 ? null : filesList.get(0);
    };

}

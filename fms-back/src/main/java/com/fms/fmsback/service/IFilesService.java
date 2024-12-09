package com.fms.fmsback.service;

import com.fms.fmsback.entity.Files;
import com.fms.fmsback.entity.PageBean;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

public interface IFilesService {

    /**
     * Retrieve All File Based On User UserId / FolderId
     * @param page
     * @param pageSize
     * @param fileName
     * @param userId
     * @param folderId
     */
    PageBean page(Integer page, Integer pageSize, String fileName, String fileType, Long userId, Long folderId);

    /**
     * Get File By Id
     * @param id
     * @param request
     * @return File
     */
    Files getById(Integer id, HttpServletRequest request);

    /**
     * Get File By MD5
     * @param md5
     * @return Files
     */
    List<Files> getByMd5(String md5);

    /**
     * Create New File
     * @param file
     * @return Boolean
     */
    Boolean save(Files file);

    /**
     * Update File By Id
     * @param file
     * @return Boolean
     */
    Boolean update(Files file);

    /**
     * Delete File By Id
     * @param id
     * @return Boolean
     */
    Boolean delete(Integer id);

    /**
     * Delete Files By Ids
     * @param ids
     * @return Boolean
     */
    Boolean batchDelete(List<Integer> ids);

    /**
     * Upload Method
     * @param file
     * @param userId
     */
    String upload(MultipartFile file, Long userId) throws IOException;

}

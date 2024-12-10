package com.fms.fmsback.controller;

import com.fms.fmsback.common.constants.ResultConstants;
import com.fms.fmsback.common.result.Result;
import com.fms.fmsback.common.utils.JwtUtil;
import com.fms.fmsback.entity.Files;
import com.fms.fmsback.entity.PageBean;
import com.fms.fmsback.entity.User;
import com.fms.fmsback.exception.ServiceException;
import com.fms.fmsback.service.IFilesService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private IFilesService iFileService;

    /**
     * Retrieve Files
     * @param page
     * @param pageSize
     * @param fileName
     * @param fileType
     * @param userId
     * @param folderId
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "") String fileName,
                       @RequestParam(defaultValue = "") String fileType,
                       @RequestParam(required = false) Long userId,
                       @RequestParam(required = false) Long folderId,
                       HttpServletRequest request) {
        String jwt = request.getHeader("token");
        User user = JwtUtil.getUserFromJwt(jwt);
        if (user.getIsAdmin() != 1) {
            userId = user.getId();
        };
        log.info("Page Query, Params: {}, {}, {}, {}, {}, {}", page, pageSize, fileName, fileType, userId, folderId);
        PageBean pageBean = iFileService.page(page,pageSize,fileName,fileType,userId,folderId);
        return Result.success(pageBean);
    }

    /**
     * Retrieve User By Id
     * @param id
     * @param request
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id, HttpServletRequest request) {
        log.info("Query User By Id: {}",id);
        Files file = iFileService.getById(id, request);
        return Result.success(file);
    };

    /**
     * Save File
     * @param file
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody Files file) {
        log.info("Save File: {}", file);
        if (iFileService.save(file)) {
            return Result.success(null, "File Added Successfully.");
        };
        log.error("File Creation Failed: {}", file);
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to Save file. Please Try Again.");
    };

    /**
     * Edit File
     * @param file
     * @return Result
     */
    @PutMapping
    public Result update(@RequestBody Files file) {
        log.info("Update File: {}", file);
        if (iFileService.update(file)) {
            return Result.success(null, "File Updated Successfully.");
        };
        log.error("File Update Failed (Not Found): {}", file);
        throw new ServiceException(ResultConstants.NOT_FOUND, "Update Fail, File Not Found. Please Try Again.");
    };

    /**
     * Delete File By Id
     * @param id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("Delete File: {}", id);
        if (iFileService.delete(id)) {
            return Result.success(ResultConstants.OK,"File Removed Successfully");
        };
        log.error("Failed To Remove File (Not Found): {}", id);
        return Result.error(ResultConstants.INTERNAL_SERVER_ERROR,"Server Error, Failed to Delete File. Please Try Again.");
    };

    /**
     * Delete Files By Ids
     * @param ids
     * @return Result
     */
    @DeleteMapping("/del/batch/{ids}")
    public Result batchDelete(@PathVariable List<Integer> ids) {
        log.info("Batch Delete Files: {}", ids);
        if (iFileService.batchDelete(ids)) {
            return Result.success(ResultConstants.OK,"Files Removed Successfully");
        };
        log.error("Failed To Remove Files Id: {}", ids);
        return Result.error(ResultConstants.INTERNAL_SERVER_ERROR,"Server Error, Failed to Remove Files. Please Try Again.");
    };

    /**
     * Upload File
     * @param file
     * @return Result
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        log.info("Upload File: {}", file);
        String jwt = request.getHeader("token");
        log.info(jwt);
        User user = JwtUtil.getUserFromJwt(jwt);
        String url = iFileService.upload(file, user.getId());
        if (!url.isEmpty()) {
            return Result.success(url, "File Uploaded Successfully.");
        }
        log.error("Failed To Upload File (Server Error): {}", file);
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Unable To Upload File Please Try Again.");
    };



}

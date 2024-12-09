package com.fms.fmsback.service;

import com.fms.fmsback.entity.File;
import com.fms.fmsback.entity.PageBean;

import java.util.List;

public interface IFileService {

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
     * @return File
     */
    File getById(Integer id);

    /**
     * Create New File
     * @param file
     * @return Boolean
     */
    Boolean save(File file);

    /**
     * Update File By Id
     * @param file
     * @return Boolean
     */
    Boolean update(File file);

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

}

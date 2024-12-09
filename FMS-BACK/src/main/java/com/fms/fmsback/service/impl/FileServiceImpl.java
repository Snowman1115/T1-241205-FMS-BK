package com.fms.fmsback.service.impl;

import com.fms.fmsback.entity.File;
import com.fms.fmsback.entity.PageBean;
import com.fms.fmsback.service.IFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements IFileService {

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
        return null;
    }

    /**
     * Get File By Id
     * @param id
     * @return File
     */
    @Override
    public File getById(Integer id) {
        return null;
    }

    /**
     * Create New File
     * @param file
     * @return Boolean
     */
    @Override
    public Boolean save(File file) {
        return null;
    }

    /**
     * Update File By Id
     * @param file
     * @return Boolean
     */
    @Override
    public Boolean update(File file) {
        return null;
    }

    /**
     * Delete File By Id
     * @param id
     * @return Boolean
     */
    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    /**
     * Delete Files By Ids
     * @param ids
     * @return Boolean
     */
    @Override
    public Boolean batchDelete(List<Integer> ids) {
        return null;
    }
}

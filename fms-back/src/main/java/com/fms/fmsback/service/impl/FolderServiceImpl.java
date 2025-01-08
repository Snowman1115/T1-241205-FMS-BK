package com.fms.fmsback.service.impl;

import com.fms.fmsback.entity.Folder;
import com.fms.fmsback.mapper.FolderMapper;
import com.fms.fmsback.service.IFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderServiceImpl implements IFolderService {

    @Autowired
    private FolderMapper folderMapper;

    /**
     * Create New Folder
     * @param folder
     * @return Boolean
     */
    @Override
    public Boolean save(Folder folder) {
        return folderMapper.save(folder);
    }

    /**
     * Update Folder
     * @param folder
     * @return Boolean
     */
    @Override
    public Boolean update(Folder folder) {
        return folderMapper.update(folder);
    }

    /**
     * Delete Folder
     * @param id
     * @return Boolean
     */
    @Override
    public Boolean remove(Long id) {
        return null;
    }

}

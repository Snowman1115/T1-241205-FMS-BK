package com.fms.fmsback.service;

import com.fms.fmsback.entity.Folder;
import com.fms.fmsback.entity.PageBean;

public interface IFolderService {

    /**
     * Create New Folder
     * @param folder
     * @return Boolean
     */
    public Boolean save(Folder folder);

    /**
     * Update Folder
     * @param folder
     * @return Boolean
     */
    public Boolean update(Folder folder);

    /**
     * Delete Folder
     * @param id
     * @return Boolean
     */
    public Boolean remove(Long id);

}

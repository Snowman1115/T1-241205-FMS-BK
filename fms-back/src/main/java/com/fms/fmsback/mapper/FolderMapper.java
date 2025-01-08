package com.fms.fmsback.mapper;

import com.fms.fmsback.entity.Folder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FolderMapper {

    /**
     * Insert New Folder
     * @param folder
     * @return boolean
     */
    @Insert("insert into fms_folder (user_id, p_id, name)" +
            "VALUES (#{userId}, #{pId}, #{name})")
    Boolean save(Folder folder);

    /**
     * Update Folder By Id
     * @param folder
     * @return Boolean
     */
    Boolean update(Folder folder);

    /**
     * Delete File By Id
     * @param id
     * @return boolean
     */
    @Delete("DELETE FROM fms_folder WHERE id = #{id}")
    Boolean delete(Integer id);

}

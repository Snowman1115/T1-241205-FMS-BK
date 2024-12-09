package com.fms.fmsback.mapper;

import com.fms.fmsback.entity.File;
import com.fms.fmsback.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    /**
     * Retrieve All Folder BasedOn fileName/userId/folderId
     * @param fileName
     * @param userId
     * @param folderId
     * @return File List
     */
    public List<File> list(String fileName, String fileType, String userId, Long folderId);

    /**
     * Get File Details By Id
     * @param id
     * @return File
     */
    @Select("SELECT * FROM fms_file WHERE id = #{id}")
    File getFileById(Integer id);

    /**
     * Insert New File
     * @param file
     * @return boolean
     */
    @Insert("INSERT INTO fms_file (user_id, folder_id, name, type, size, url, md5)" +
            "VALUES (#{userId},#{folderId},#{name},#{type},#{size},#{url},#{md5})")
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
     * @return boolean
     */
    @Delete("DELETE FROM fms_file WHERE id = #{id}")
    Boolean delete(Integer id);

    /**
     * Delete Files By ids
     * @param ids
     * @return boolean
     */
    Boolean batchDelete(List<Integer> ids);

}

package com.fms.fmsback.mapper;

import com.fms.fmsback.entity.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FilesMapper {

    /**
     * Retrieve All Folder BasedOn fileName/userId/folderId
     * @param fileName
     * @param userId
     * @param folderId
     * @return File List
     */
    public List<Files> list(String fileName, String fileType, Long userId, Long folderId);

    /**
     * Get File Details By Id
     * @param id
     * @return File
     */
    @Select("SELECT * FROM fms_file WHERE id = #{id}")
    Files getFileById(Integer id);

    /**
     * Get File Details By Md5
     * @param md5
     * @return File
     */
    @Select("SELECT * FROM fms_file WHERE md5 = #{md5}")
    List<Files> getFileByMd5(String md5);

    /**
     * Insert New File
     * @param file
     * @return boolean
     */
    @Insert("INSERT INTO fms_file (user_id, folder_id, name, type, size, url, md5)" +
            "VALUES (#{userId},#{folderId},#{name},#{type},#{size},#{url},#{md5})")
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

package com.fms.fmsback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Files {

    /**
     * File Id
     */
    private Long id;

    /**
     * User Id
     */
    private Long userId;

    /**
     * Parent Folder Id
     */
    private Long folderId;

    /**
     * File Name
     */
    private String name;

    /**
     * File Type
     */
    private String type;

    /**
     * File Size
     */
    private Long size;

    /**
     * File URL
     */
    private String url;

    /**
     * is File Deleted 0:No, 1:Yes
     */
    private Boolean isDelete;

    /**
     * is File Enabled 0:No, 1:Yes
     */
    private Boolean enable;

    /**
     * File MD5
     */
    private String md5;

    /**
     * Last Access Date
     */
    private Date lastAccess;

    /**
     * Created Time
     */
    private Date createTime;

    /**
     * Updated Time
     */
    private Date updateTime;

}

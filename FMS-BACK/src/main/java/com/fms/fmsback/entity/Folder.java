package com.fms.fmsback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Folder {

    /**
     * Folder Id
     */
    private Long id;

    /**
     * User Id
     */
    private Long userId;

    /**
     * Parent Folder Id
     */
    private Long pId;

    /**
     * Folder Name
     */
    private String name;

    /**
     * All Files
     */
    private List<File> files;

    /**
     * Create Time
     */
    private Date createTime;

    /**
     * Update Time
     */
    private Date updateTime;

}

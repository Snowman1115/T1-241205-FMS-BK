package com.fms.fmsback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    /**
     Total Numbers
     */
    private Long total;

    /**
     List of Data
     */
    private List rows;

}

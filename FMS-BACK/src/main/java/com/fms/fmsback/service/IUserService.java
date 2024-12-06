package com.fms.fmsback.service;

import com.fms.fmsback.entity.PageBean;
import com.fms.fmsback.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface IUserService {

    /**
     * Retrieve All User
     * @param page
     * @param pageSize
     * @param nickname
     * @param gender
     * @param begin
     * @param end
     * @return PageBean
     */
    PageBean page(Integer page, Integer pageSize, String nickname, Short gender, LocalDate begin, LocalDate end);

    /**
     * Get User By Id
     * @param id
     * @return User
     */
    User getById(Integer id);

    /**
     * Create New User
     * @param user
     * @return boolean
     */
    Boolean save(User user);

    /**
     * Update User
     * @param user
     * @return boolean
     */
    Boolean update(User user);

    /**
     * Delete User By Ids
     * @param ids
     * @return boolean
     */
    Boolean delete(List<Integer> ids);

}

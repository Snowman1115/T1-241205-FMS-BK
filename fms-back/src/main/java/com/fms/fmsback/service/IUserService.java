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
     * Update User By Id
     * @param user
     * @return boolean
     */
    Boolean update(User user);

    /**
     * Delete User By Id
     * @param id
     * @return boolean
     */
    Boolean delete(Integer id);

    /**
     * Delete Users By Ids
     * @param ids
     * @return boolean
     */
    Boolean batchDelete(List<Integer> ids);

    /**
     * Get User By Username
     * @param username
     * @return user
     */
    User getByUsername(String username);

    /**
     * Get User By Username & Password
     * @param username
     * @param password
     * @return User
     */
    User getByUsernameNPassword(String username, String password);

}

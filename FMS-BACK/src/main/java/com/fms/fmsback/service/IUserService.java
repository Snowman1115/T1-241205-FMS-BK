package com.fms.fmsback.service;

import com.fms.fmsback.entity.User;

import java.util.List;

public interface IUserService {

    /**
     * Create New User
     * @param user
     * @return boolean
     */
    Boolean save(User user);

    /**
     * Get User By Id
     * @param id
     * @return User
     */
    User getById(Integer id);

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

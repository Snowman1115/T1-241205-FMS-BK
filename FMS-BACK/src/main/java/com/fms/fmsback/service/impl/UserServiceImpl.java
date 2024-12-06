package com.fms.fmsback.service.impl;

import com.fms.fmsback.entity.User;
import com.fms.fmsback.mapper.UserMapper;
import com.fms.fmsback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Create New User
     * @param user
     * @return boolean
     */
    @Override
    public Boolean save(User user) {
        return null;

    }

    /**
     * Get User By Id
     * @param id
     * @return User
     */
    @Override
    public User getById(Integer id) {
        return null;
    }

    /**
     * Update User
     * @param user
     * @return boolean
     */
    @Override
    public Boolean update(User user) {
        return null;
    }

    /**
     * Delete User By Ids
     * @param ids
     * @return boolean
     */
    @Override
    public Boolean delete(List<Integer> ids) {
        return null;
    }

}

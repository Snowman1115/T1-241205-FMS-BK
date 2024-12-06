package com.fms.fmsback.service.impl;

import com.fms.fmsback.entity.PageBean;
import com.fms.fmsback.entity.User;
import com.fms.fmsback.mapper.UserMapper;
import com.fms.fmsback.service.IUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

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
    @Override
    public PageBean page(Integer page, Integer pageSize, String nickname, Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);

        List<User> userList = userMapper.list(nickname,gender,begin,end);
        Page<User> users = (Page<User>) userList;

        PageBean pageBean = new PageBean(users.getTotal(), users.getResult());
        return pageBean;
    }

    /**
     * Get User By Id
     * @param id
     * @return User
     */
    @Override
    public User getById(Integer id) {
        return userMapper.getUserById(id);
    }

    /**
     * Create New User
     * @param user
     * @return boolean
     */
    @Override
    public Boolean save(User user) {
        return userMapper.save(user);
    }

    /**
     * Update User
     * @param user
     * @return boolean
     */
    @Override
    public Boolean update(User user) {
        return userMapper.update(user);
    }

    /**
     * Delete User By Id
     * @param id
     * @return Result
     */
    public Boolean delete(Integer id) {
        return userMapper.delete(id);
    };

    /**
     * Delete User By Ids
     * @param ids
     * @return boolean
     */
    @Override
    public Boolean batchDelete(List<Integer> ids) {
        return userMapper.batchDelete(ids);
    }

}

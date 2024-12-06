package com.fms.fmsback.mapper;

import com.fms.fmsback.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * Retrieve All User
     * @param nickname
     * @param gender
     * @param begin
     * @param end
     * @return User List
     */
    public List<User> list(String nickname, Short gender, LocalDate begin, LocalDate end);

    /**
     * Get User Details By Id
     * @param id
     * @return User
     */
    @Select("SELECT * FROM fms_user WHERE id = #{id}")
    User getUserById(Integer id);

    /**
     * Insert New User
     * @param user
     * @return boolean
     */
    @Insert("INSERT INTO fms_user (username, password, first_name, last_name, nickname, email, phone, gender)" +
            "VALUES (#{username}, #{password}, #{firstName}, #{lastName}, #{nickname}, #{email}, #{phone}, #{gender})")
    Boolean save(User user);

    /**
     * Update User Information
     * @param user
     * @return boolean
     */
    Boolean update(User user);

    /**
     * Delete User By Id
     * @param id
     * @return boolean
     */
    @Delete("DELETE FROM fms_user WHERE id = #{id}")
    Boolean delete(Integer id);

    /**
     * Delete Users By Ids
     * @param ids
     * @return boolean
     */
    Boolean batchDelete(List<Integer> ids);

}

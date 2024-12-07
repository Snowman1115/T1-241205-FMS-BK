package com.fms.fmsback.controller;

import com.fms.fmsback.common.constants.ResultConstants;
import com.fms.fmsback.common.result.Result;
import com.fms.fmsback.entity.PageBean;
import com.fms.fmsback.entity.User;
import com.fms.fmsback.exception.ServiceException;
import com.fms.fmsback.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * Retrieve Users
     * @param page
     * @param pageSize
     * @param nickname
     * @param gender
     * @param begin
     * @param end
     * @return Result
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(defaultValue = "") String nickname,
                       @RequestParam(defaultValue = "") Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("Page Query, Params: {}, {}, {}, {}, {}, {}, {}, {}",page,pageSize,nickname,gender,begin,end);
        PageBean pageBean = iUserService.page(page,pageSize,nickname,gender,begin,end);
        return Result.success(pageBean);
    };

    /**
     * Retrieve User By Id
     * @param id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("Query User By Id: {}",id);
        User user = iUserService.getById(id);
        return Result.success(user);
    };

    /**
     * Add New User
     * @param user
     * @return Result
     */
    @PostMapping
    public Result save(@RequestBody User user) {
        log.info("Create New User: {}", user);
        if (iUserService.save(user)) {
            return Result.success(null, "User Added Successfully.");
        };
        log.error("User Creation Failed: {}", user);
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to Save user. Please Try Again.");
    };

    /**
     * Update User By Id
     * @param user
     * @return Result
     */
    @PutMapping
    public Result update(@RequestBody User user) {
      log.info("Update User: {}", user);
      if (iUserService.update(user)) {
          return Result.success(null, "User Updated Successfully.");
      };
      log.error("User Update Failed (Not Found): {}", user);
      throw new ServiceException(ResultConstants.NOT_FOUND, "Update Fail, User Not Found. Please Try Again.");
    };

    /**
     * Delete User By Id
     * @param id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
      log.info("Delete User: {}", id);
      if (iUserService.delete(id)) {
          return Result.success(ResultConstants.OK,"User Removed Successfully");
      };
      log.error("Failed To Remove UserId (Not Found): {}", id);
      return Result.error(ResultConstants.INTERNAL_SERVER_ERROR,"Server Error, Failed to Delete User. Please Try Again.");
    };

    @DeleteMapping("/del/batch/{ids}")
    public Result batchDelete(@PathVariable List<Integer> ids) {
        log.info("Batch Delete Users: {}", ids);
        if (iUserService.batchDelete(ids)) {
            return Result.success(ResultConstants.OK,"Users Removed Successfully");
        };
        log.error("Failed To Remove Users Id: {}", ids);
        return Result.error(ResultConstants.INTERNAL_SERVER_ERROR,"Server Error, Failed to Remove  Users. Please Try Again.");
    };

}

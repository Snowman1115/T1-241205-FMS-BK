package com.fms.fmsback.controller;

import com.fms.fmsback.common.result.Result;
import com.fms.fmsback.entity.PageBean;
import com.fms.fmsback.entity.User;
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
        iUserService.save(user);
        return Result.success();
    };

    /**
     * Update User By Id
     * @param user
     * @return Result
     */
    @PutMapping
    public Result update(@RequestBody User user) {
      log.info("Update User: {}", user);
      iUserService.update(user);
      return Result.success();
    };

    /**
     * Delete User By Id
     * @param id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
      log.info("Delete User: {}", id);
      iUserService.delete(id);
      return Result.success();
    };

    @DeleteMapping("/del/batch/{ids}")
    public Result batchDelete(@PathVariable List<Integer> ids) {
        log.info("Batch Delete Users: {}", ids);
        iUserService.batchDelete(ids);
        return Result.success();
    };

}

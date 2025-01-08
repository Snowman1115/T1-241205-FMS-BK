package com.fms.fmsback.controller;

import com.fms.fmsback.common.constants.ResultConstants;
import com.fms.fmsback.common.result.Result;
import com.fms.fmsback.common.utils.JwtUtil;
import com.fms.fmsback.entity.Folder;
import com.fms.fmsback.entity.User;
import com.fms.fmsback.exception.ServiceException;
import com.fms.fmsback.service.IFolderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/folders")
public class FolderController {



    @Autowired
    private IFolderService iFolderService;

    /**
     * Create New Folder
     * @param name
     * @param pId
     * @param request
     * @return boolean
     */
    @PostMapping("/")
    public Result save(@RequestParam String name,
                       @RequestParam(defaultValue = "") Long pId,
                       HttpServletRequest request) {
        String jwt = request.getHeader("token");
        User user = JwtUtil.getUserFromJwt(jwt);

        log.info("Creating Folder: {}, {}", name, pId);

        Folder folder = new Folder();
        folder.setUserId(user.getId());
        folder.setName(name);
        folder.setPId(pId);

        if(iFolderService.save(folder)) {
            return Result.success(null, "Folder Create Successful");
        };
        log.error("Folder Creation Failed: {}, {}", name, pId);
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to Save Folder. Please Try Again.");
    }

    /**
     * Update Folder
     * @param name
     * @return Result
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id,
                         @RequestParam(defaultValue = "") Long pId,
                         @RequestParam String name) {
        log.info("Update Folder: {}. {}", id, pId, name);
        Folder folder = new Folder();
        folder.setId(id);
        if (pId != null) {
            folder.setPId(pId);
        }
        folder.setName(name);
        if(iFolderService.update(folder)) {
            return Result.success(null, "Folder Updated Successful.");
        };
        log.error("Folder Creation Failed: {}, {}", name, pId);
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to Update Folder. Please Try Again.");
    }

    /**
     * Delete Folder
     * @param id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        log.info("Delete Folder: {}", id);
        if (iFolderService.remove(id)) {
            return Result.success(null, "Folder Removed Successful.");
        };
        log.error("Folder Deletion Failed: {}, {}", id);
        throw new ServiceException(ResultConstants.INTERNAL_SERVER_ERROR, "Server Error, Failed to Update Folder. Please Try Again.");
    }

}

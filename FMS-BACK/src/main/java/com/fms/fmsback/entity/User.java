package com.fms.fmsback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    /**
     * User ID
     */
    private Long id;

    /**
     * Account Username
     */
    private String username;

    /**
     * Account Password
     */
    private String password;

    /**
     * Account isExpired (0:NO, 1:YES)
     */
    private boolean isAccountNonExpired = true;

    /**
     * Account isLock (0:NO, 1:YES)
     */
    private boolean isAccountNonLocked = true;

    /**
     * Password isExpired (0:NO, 1:YES)
     */
    private boolean isCredentialsNonExpired = true;

    /**
     * Account isEnable (0:YES, 1:NO)
     */
    private boolean isEnabled = true;

    /**
     * User First Name
     */
    private String firstName;

    /**
     * User Last Name
     */
    private String lastName;

    /**
     * User Nickname
     */
    private String nickname;

    /**
     *  Email
     */
    private String email;

    /**
     * Mobile Number
     */
    private String phone;

    /**
     * 0 = MALE : 1 = FEMALE
     */
    private Integer gender;

    /**
     * User Avatar Url
     */
    private String avatar;

    /**
     * 0 = NO : 1 = YES (SUPER_ADMIN)
     */
    private Integer isAdmin;

    /**
     * Last Login Date
     */
    private Date loginDate;

    /**
     * 0 = isActive : 1 isDeleted
     */
    private Integer isDelete;

    /**
     * Remarks
     */
    private String remark;

    /**
     * Create Time
     */
    private Date createTime;

    /**
     * Update Time
     */
    private Date updateTime;

    /**
     * Permission List
     */
    Collection<? extends GrantedAuthority> authorities;

}

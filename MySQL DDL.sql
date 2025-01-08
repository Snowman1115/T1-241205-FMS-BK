/* ========================================================================
Author: Sn0w_15
Database: File-Management-System
Project: T1-120524-FMS-BK
======================================================================== */

/* Create Database Schema*/
create database if not exists fms;

use fms;

create table fms_user
(
    id                         bigint auto_increment comment 'User ID' primary key,
    username                   varchar(100) charset utf8mb3        not null comment 'Account Username',
    password                   varchar(100) charset utf8mb3        not null comment 'Account Password',
    is_account_non_expired     tinyint   default 1                 not null comment 'Account isNotExpired (0:NO, 1:YES)',
    is_account_non_locked      tinyint   default 1                 not null comment 'Account isNotLock (0:NO, 1:YES)',
    is_credentials_non_expired tinyint   default 1                 not null comment 'Password isNotExpired (0:NO, 1:YES)',
    is_enabled                 tinyint   default 1                 not null comment 'Account isEnable (0:NO, 1:YES)',
    first_name                 varchar(100) charset utf8mb3        null comment 'User First Name',
    last_name                  varchar(100) charset utf8mb3        null comment 'User Last Name',
    nickname                   varchar(100) charset utf8mb3        null comment 'User Nickname',
    email                      varchar(100) charset utf8mb3        not null comment ' Email',
    phone                      varchar(11) charset utf8mb3         not null comment 'Mobile Number',
    gender                     tinyint                             not null comment '0 = MALE : 1 = FEMALE',
    avatar                     varchar(255) charset utf8mb3        null comment 'User Avatar Url',
    is_admin                   tinyint   default 0                 null comment '0 = NO : 1 = YES (SUPER_ADMIN)',
    login_date                 TIMESTAMP                           null comment 'Last Login Date',
    is_delete                  tinyint   default 0                 null comment '0 = isActive : 1 isDeleted',
    remark                     varchar(500) charset utf8mb3        null comment 'Remarks',
    create_time                TIMESTAMP default CURRENT_TIMESTAMP not null comment 'Create Time',
    update_time                TIMESTAMP default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Time'
)
    comment 'User Account and Details';

CREATE TABLE fms_folder
(
    id                         BIGINT AUTO_INCREMENT COMMENT 'Folder ID' PRIMARY KEY,
    user_id                    BIGINT                              NOT NULL COMMENT 'User ID',
    p_id                       BIGINT                              DEFAULT NULL COMMENT 'Parent Folder ID (NULL for root folder)',
    name                       VARCHAR(255) CHARSET utf8mb3        NOT NULL COMMENT 'Folder Name',
    create_time                TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'Create Time',
    update_time                TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update Time',
    FOREIGN KEY (user_id)      REFERENCES fms_user(id) ON DELETE CASCADE,
    FOREIGN KEY (p_id)         REFERENCES fms_folder(id) ON DELETE CASCADE
)
COMMENT 'Folder Table to Store Folder Data for Users';

CREATE TABLE fms_file (
    id                         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'File ID',
    user_id                    BIGINT                              NULL COMMENT 'User ID',
    folder_id                  BIGINT                              NULL COMMENT 'Parent Folder ID',
    name                       VARCHAR(255)                        NOT NULL COMMENT 'File Name',
    type                       VARCHAR(50)                         NOT NULL COMMENT 'File Type',
    size                       BIGINT                              NOT NULL COMMENT 'File Size',
    url                        VARCHAR(255)                        NOT NULL COMMENT 'File URL',
    is_delete                  TINYINT(1) DEFAULT 0                NOT NULL COMMENT '0: No, 1: Yes - File Deleted',
    enable                     TINYINT(1) DEFAULT 1                NOT NULL COMMENT '0: No, 1: Yes - File Enabled',
    md5                        VARCHAR(255)                        COMMENT 'File MD5',
    last_access                TIMESTAMP                           DEFAULT NULL COMMENT 'Last Access Date',
    create_time                TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'File Creation Time',
    update_time                TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT 'File Update Time',
    FOREIGN KEY (user_id) REFERENCES fms_user(id) ON DELETE CASCADE,
    FOREIGN KEY (folder_id) REFERENCES fms_folder(id) ON DELETE CASCADE
) COMMENT 'Files Table';

-- Inserting data into fms_user table
INSERT INTO fms_user (
    username,
    password,
    is_account_non_expired,
    is_account_non_locked,
    is_credentials_non_expired,
    is_enabled,
    first_name,
    last_name,
    nickname,
    email,
    phone,
    gender,
    avatar,
    is_admin,
    login_date,
    is_delete,
    remark,
    create_time,
    update_time
) VALUES
('user1', 'pass1234@', 1, 1, 1, 1, 'John', 'Doe', 'Johnny', 'randomuser1@example.com', '0123456789', 0, 'http://example.com/avatar1.jpg', 0, '2024-12-09 10:00:00', 0, 'Active user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user2', 'pass1234@', 1, 1, 1, 1, 'Jane', 'Smith', 'Janey', 'randomuser2@example.com', '0987654321', 1, 'http://example.com/avatar2.jpg', 0, '2024-12-09 11:00:00', 0, 'Active user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user3', 'pass1234@', 1, 1, 1, 1, 'Alice', 'Johnson', 'Ally', 'randomuser3@example.com', '0112233445', 0, 'http://example.com/avatar3.jpg', 0, '2024-12-09 12:00:00', 0, 'Active user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user4', 'pass1234@', 1, 1, 1, 1, 'Bob', 'Williams', 'Bobby', 'randomuser4@example.com', '0223344556', 1, 'http://example.com/avatar4.jpg', 1, '2024-12-09 13:00:00', 0, 'Super admin user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user5', 'pass1234@', 1, 1, 1, 0, 'Charlie', 'Brown', 'Chuck', 'randomuser5@example.com', '0334455667', 1, 'http://example.com/avatar5.jpg', 0, '2024-12-09 14:00:00', 1, 'Deactivated user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);




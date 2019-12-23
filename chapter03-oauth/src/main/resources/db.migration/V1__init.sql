drop table if exists `clientdetails`;
create table `clientdetails` (
  `appid` varchar(128) not null,
  `resourceids` varchar(256) default null,
  `appsecret` varchar(256) default null,
  `scope` varchar(256) default null,
  `granttypes` varchar(256) default null,
  `redirecturl` varchar(256) default null,
  `authorities` varchar(256) default null,
  `access_token_validity` int(11) default null,
  `refresh_token_validity` int(11) default null,
  `additionalinformation` varchar(4096) default null,
  `autoapprovescopes` varchar(256) default null,
  primary key (`appid`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- table structure for oauth_access_token
-- ----------------------------
drop table if exists `oauth_access_token`;
create table `oauth_access_token` (
  `token_id` varchar(256) default null,
  `token` blob,
  `authentication_id` varchar(128) not null,
  `user_name` varchar(256) default null,
  `client_id` varchar(256) default null,
  `authentication` blob,
  `refresh_token` varchar(256) default null,
  primary key (`authentication_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- table structure for oauth_approvals
-- ----------------------------
drop table if exists `oauth_approvals`;
create table `oauth_approvals` (
  `userid` varchar(256) default null,
  `clientid` varchar(256) default null,
  `scope` varchar(256) default null,
  `status` varchar(10) default null,
  `expiresat` datetime default null,
  `lastmodifiedat` datetime default null
) engine=innodb default charset=utf8;

-- ----------------------------
-- table structure for oauth_client_details
-- ----------------------------
drop table if exists `oauth_client_details`;
create table `oauth_client_details` (
  `client_id` varchar(128) not null,
  `resource_ids` varchar(256) default null,
  `client_secret` varchar(256) default null,
  `scope` varchar(256) default null,
  `authorized_grant_types` varchar(256) default null,
  `web_server_redirect_uri` varchar(256) default null,
  `authorities` varchar(256) default null,
  `access_token_validity` int(11) default null,
  `refresh_token_validity` int(11) default null,
  `additional_information` varchar(4096) default null,
  `autoapprove` varchar(256) default null,
  `name` varchar(64) DEFAULT NULL,
  primary key (`client_id`)
) engine=innodb default charset=utf8;

insert into `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `name`) values ('test', null, 'test', 'user', 'authorization_code,refresh_token,password,implicit,client_credentials', null, null, 43200, null, null, 'false', '基础应用');
insert into `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `name`) values ('test2', null, 'tes', 'user', 'authorization_code', null, null, 43205, null, null, 'true', '测试应用');

-- ----------------------------
-- table structure for oauth_client_token
-- ----------------------------
drop table if exists `oauth_client_token`;
create table `oauth_client_token` (
  `token_id` varchar(256) default null,
  `token` blob,
  `authentication_id` varchar(128) not null,
  `user_name` varchar(256) default null,
  `client_id` varchar(256) default null,
  primary key (`authentication_id`)
) engine=innodb default charset=utf8;

-- ----------------------------
-- table structure for oauth_code
-- ----------------------------
drop table if exists `oauth_code`;
create table `oauth_code` (
  `code` varchar(256) default null,
  `authentication` blob
) engine=innodb default charset=utf8;

-- ----------------------------
-- table structure for oauth_refresh_token
-- ----------------------------
drop table if exists `oauth_refresh_token`;
create table `oauth_refresh_token` (
  `token_id` varchar(256) default null,
  `token` blob,
  `authentication` blob
) engine=innodb default charset=utf8;

set foreign_key_checks = 1;

-- ----------------------------
-- 用户基本信息表
-- ----------------------------
drop table if exists `oauth_user`;
create table `oauth_user` (
  `user_id` varchar(36) not null,
  `user_name` varchar(100) default null,
  `password` varchar(255) default null,
  `phone` varchar(11) default null,
  `gender` varchar(6) default null comment 'male 男、female 女',
  `age` int(11) default null,
  `active` int(11) default null comment '0 禁用、1 启用',
  `create_date` datetime default null,
  `update_date` datetime default null,
  primary key (`user_id`),
  key `ak_key_2` (`user_name`)
) engine=innodb default charset=utf8;

insert into `oauth_user` values ('1001', 'jiangqiao', '123456', '18610380624', '0', 28, 0, '2019-11-13 18:50:20', '2019-11-13 18:50:25');
INSERT INTO `oauth_user` VALUES ('0cb71fdd99d743ae9c8db85039609687', 'test', '$2a$06$SBrOxaGm8nzNCEggHXh2yumyPr1ZC2vqcC.yawhwhQrfoFdXemXCi', '13100000000', 'male', '0', '1', '2019-11-15 09:00:55', null);

-- ----------------------------
-- 系统角色信息表
-- ----------------------------
drop table if exists `oauth_role`;
create table `oauth_role` (
  `role_id` varchar(36) not null,
  `role_code` varchar(64) default null,
  `role_name` varchar(64) default null,
  `create_date` datetime default null,
  `update_date` datetime default null,
  primary key (`role_id`)
) engine=innodb default charset=utf8;

insert into `oauth_role` values ('d352297eb11c418e94a5f8f2c1c229db', 'test', '测试角色', '2019-11-14 09:02:17', null);
insert into `oauth_role` values ('ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'sysadmin', '系统管理员', '2019-11-14 19:02:58', null);

-- ----------------------------
-- 用户角色关联信息表
-- ----------------------------
drop table if exists `oauth_user_role`;
create table `oauth_user_role` (
  `id` varchar(36) not null,
  `user_id` varchar(36) default null,
  `role_id` varchar(36) default null,
  primary key (`id`),
  key `fk_reference_2` (`role_id`),
  constraint `fk_reference_2` foreign key (`role_id`) references `oauth_role` (`role_id`) on delete cascade
) engine=innodb default charset=utf8;

insert into `oauth_user_role` values ('9c263bcb22d84f1aa0193e809c2ef4dd', '1001', 'd352297eb11c418e94a5f8f2c1c229db');

-- ----------------------------
-- 关键系统信息表
-- ----------------------------
drop table if exists `oauth_system`;
create table `oauth_system` (
  `system_id` varchar(36) not null,
  `system_name` varchar(64) default null,
  `project_name` varchar(64) default null,
  `active` int(11) default null comment '0 禁用、1 启用',
  `sort` int(11) default null,
  `create_date` datetime default null,
  `update_date` datetime default null,
  primary key (`system_id`)
) engine=innodb default charset=utf8;

insert into `oauth_system` values ('d69060a3-914b-11e7-8c99-00ff6227aaa1', '权限管理系统', 'resource-server', '1', '1', '2019-09-04 16:37:54', '2019-05-13 17:27:23');

-- ----------------------------
-- 系统资源信息表
-- ----------------------------
drop table if exists `oauth_module_resources`;
create table `oauth_module_resources` (
  `module_id` varchar(36) not null,
  `module_name` varchar(64) default null,
  `module_code` varchar(64) default null,
  `module_path` varchar(255) default null,
  `parent_id` varchar(36) default null,
  `module_icon` varchar(64) default null,
  `http_method` varchar(8) default null,
  `is_operating` int(11) default null comment '0 否，1 是',
  `sort` int(11) default null,
  `system_id` varchar(36) default null,
  `active` int(11) default null,
  `create_date` datetime default null,
  `update_date` datetime default null,
  primary key (`module_id`),
  key `fk_reference_5` (`parent_id`),
  key `system_id` (`system_id`),
  constraint `fk_reference_5` foreign key (`parent_id`) references `oauth_module_resources` (`module_id`) on delete cascade,
  constraint `fk_reference_6` foreign key (`system_id`) references `oauth_system` (`system_id`) on delete cascade on update cascade
) engine=innodb default charset=utf8;

insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('113e9c94-8405-11e7-b35a-00ff6227aaa1', '系统设置', 'ststem_settings', '/system', null, 'el-icon-setting', null, 0, 1, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-08-18 19:04:21', '2017-08-23 16:13:29');
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('142ce669-96a2-11e7-863e-00ff6227aaa1', '查询模块表格', 'query_module_table', '/module/table', 'e69131c2-870d-11e7-ad1e-00ff6227aaa1', null, 'post', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 11:33:31', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('26305844-96a5-11e7-863e-00ff6227aaa1', '获取已授权的模块', 'get_role_auth_module', '/role/auth/{roleid}', '32d0aeab-8405-11e7-b35a-00ff6227aaa1', null, 'get', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('2675469b-96a4-11e7-863e-00ff6227aaa1', '查询系统表格', 'query_system_table', '/system/table', 'b25a02e3-92c7-11e7-8c99-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('2c82fb06-8405-11e7-b35a-00ff6227aaa1', '用户管理', 'user_managed', '/user', '113e9c94-8405-11e7-b35a-00ff6227aaa1', 'el-icon-menu', null, 0, 1, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 10:53:15', '2017-08-23 17:09:34');
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('31e6c4b8-96a6-11e7-863e-00ff6227aaa1', '获取所有角色', 'get_roles', '/role', '32d0aeab-8405-11e7-b35a-00ff6227aaa1', null, 'get', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 12:02:45', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('32d0aeab-8405-11e7-b35a-00ff6227aaa1', '角色管理', 'role_managed', '/role', '113e9c94-8405-11e7-b35a-00ff6227aaa1', null, null, 0, 2, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 10:53:13', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('366b227a-2c30-11e8-bb2a-2cfda1b1e42a', '校验角色编码', 'validate_role_code', '/role/validate/{rolecode}', '32d0aeab-8405-11e7-b35a-00ff6227aaa1', null, 'get', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2018-03-20 19:17:02', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('3c6bf3e2-969c-11e7-863e-00ff6227aaa1', '查询系统', 'query_system', '/system', 'b25a02e3-92c7-11e7-8c99-00ff6227aaa1', null, 'get', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 10:53:18', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('42179355-96a4-11e7-863e-00ff6227aaa1', '新增系统', 'add_system', '/system', 'b25a02e3-92c7-11e7-8c99-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('4b7f31dc-96a5-11e7-863e-00ff6227aaa1', '保存授权模块', 'save_role_auth_module', '/module/role', '32d0aeab-8405-11e7-b35a-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('5039b225-96a4-11e7-863e-00ff6227aaa1', '编辑系统', 'edit_system', '/system', 'b25a02e3-92c7-11e7-8c99-00ff6227aaa1', null, 'put', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('55c48c41-96a2-11e7-863e-00ff6227aaa1', '新增模块', 'add_module', '/module', 'e69131c2-870d-11e7-ad1e-00ff6227aaa1', null, 'post', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 11:35:02', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('56b67433-96dc-11e7-863e-00ff6227aaa1', '校验模块', 'validate_module_code', '/module/validate/{modulecode}', 'e69131c2-870d-11e7-ad1e-00ff6227aaa1', null, 'get', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 18:31:58', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('5a46be4f3e3a443f9244c8e4ca8c836b', '校验手机号码', 'validate_user_phone', '/user/validate/phone/{phone}', '2c82fb06-8405-11e7-b35a-00ff6227aaa1', null, 'get', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2018-06-16 18:04:54', '2018-06-16 18:51:10');
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('5b5c0fe2-96a6-11e7-863e-00ff6227aaa1', '获取已授权的角色', 'get_user_role', '/user/role/{userid}', '2c82fb06-8405-11e7-b35a-00ff6227aaa1', null, 'get', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 12:03:59', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('5c82bed5-96a4-11e7-863e-00ff6227aaa1', '删除系统', 'delete_system', '/system', 'b25a02e3-92c7-11e7-8c99-00ff6227aaa1', null, 'delete', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('7362412e-96a2-11e7-863e-00ff6227aaa1', '编辑模块', 'edit_module', '/module', 'e69131c2-870d-11e7-ad1e-00ff6227aaa1', null, 'put', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 11:35:02', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('7bf5a9aa-96a6-11e7-863e-00ff6227aaa1', '保存用户角色授权', 'save_user_role', '/user/role', '2c82fb06-8405-11e7-b35a-00ff6227aaa1', null, 'post', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 12:04:51', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('7e669afe-96dc-11e7-863e-00ff6227aaa1', '校验系统项目名', 'validate_project_name', '/system/validate/{projectname}', 'b25a02e3-92c7-11e7-8c99-00ff6227aaa1', null, 'get', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2018-05-13 16:40:29', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('7e97b3b3-96a5-11e7-863e-00ff6227aaa1', '查询用户表格', 'query_user_table', '/user/table', '2c82fb06-8405-11e7-b35a-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('822f714c-96a2-11e7-863e-00ff6227aaa1', '删除模块', 'delete_module', '/module', 'e69131c2-870d-11e7-ad1e-00ff6227aaa1', null, 'delete', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 11:35:02', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('8338ce98-969c-11e7-863e-00ff6227aaa1', '获取模块树', 'get_module_tree', '/module/tree', 'e69131c2-870d-11e7-ad1e-00ff6227aaa1', null, 'post', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 10:53:20', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('8e669afe-96dc-11e7-863e-00ff6227aaa1', '校验用户名', 'validate_user_name', '/user/validate/{username}', '2c82fb06-8405-11e7-b35a-00ff6227aaa1', null, 'get', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 18:32:00', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('8f84a111-96a4-11e7-863e-00ff6227aaa1', '查询角色表格', 'query_role_table', '/role/table', '32d0aeab-8405-11e7-b35a-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('943d1ffa-96dd-11e7-863e-00ff6227aaa1', '应用管理', 'app_managed', '/app', '113e9c94-8405-11e7-b35a-00ff6227aaa1', null, 'get', 0, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 18:38:34', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('9d74d7eb-96a5-11e7-863e-00ff6227aaa1', '新增用户', 'add_user', '/user', '2c82fb06-8405-11e7-b35a-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('9fe933bc-96a4-11e7-863e-00ff6227aaa1', '新增角色', 'add_role', '/role', '32d0aeab-8405-11e7-b35a-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('ad04ec2c-96a4-11e7-863e-00ff6227aaa1', '编辑角色', 'edit_role', '/role', '32d0aeab-8405-11e7-b35a-00ff6227aaa1', null, 'put', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('adc1e2dc-96a5-11e7-863e-00ff6227aaa1', '编辑用户', 'edit_user', '/user', '2c82fb06-8405-11e7-b35a-00ff6227aaa1', null, 'put', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('b0c7cd02-96dd-11e7-863e-00ff6227aaa1', '查询应用表格', 'query_app_table', '/client/table', '943d1ffa-96dd-11e7-863e-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 18:39:22', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('b25a02e3-92c7-11e7-8c99-00ff6227aaa1', '系统管理', 'system_managed', '/system', '113e9c94-8405-11e7-b35a-00ff6227aaa1', null, null, 0, 3, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-08-22 15:45:08', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('ba185893-96a4-11e7-863e-00ff6227aaa1', '删除角色', 'delete_role', '/role', '32d0aeab-8405-11e7-b35a-00ff6227aaa1', null, 'delete', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('c3e5fc76-96a5-11e7-863e-00ff6227aaa1', '删除用户', 'delete_user', '/user', '2c82fb06-8405-11e7-b35a-00ff6227aaa1', null, 'delete', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('c54a02b8-96dd-11e7-863e-00ff6227aaa1', '新增应用', 'add_app', '/client', '943d1ffa-96dd-11e7-863e-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 18:39:56', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('cecfed9a0fc5452b8b4d889e5ea121ae', '校验应用id', 'validate_client_id', '/client/validate/{clientid}', '943d1ffa-96dd-11e7-863e-00ff6227aaa1', null, 'get', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2018-05-15 16:36:36', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('d3fccbfb-96dd-11e7-863e-00ff6227aaa1', '编辑应用', 'edit_app', '/client', '943d1ffa-96dd-11e7-863e-00ff6227aaa1', null, 'put', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 18:40:21', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('e1ff4d1d-96a5-11e7-863e-00ff6227aaa1', '重置密码', 'reset_password', '/user/password/{newpassword}', '2c82fb06-8405-11e7-b35a-00ff6227aaa1', null, 'post', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('e29bd47a-96dd-11e7-863e-00ff6227aaa1', '删除应用', 'delete_app', '/client', '943d1ffa-96dd-11e7-863e-00ff6227aaa1', null, 'delete', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 18:40:45', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('e69131c2-870d-11e7-ad1e-00ff6227aaa1', '模块管理', 'module_managed', '/module', '113e9c94-8405-11e7-b35a-00ff6227aaa1', null, null, 0, 3, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-08-22 15:45:08', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('eff8eda4-937d-11e7-8c99-00ff6227aaa1', '查询菜单', 'query_menu', '/menu', 'e69131c2-870d-11e7-ad1e-00ff6227aaa1', null, 'get', 1, 0, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-07 11:37:49', null);
insert into `oauth_module_resources`(`module_id`, `module_name`, `module_code`, `module_path`, `parent_id`, `module_icon`, `http_method`, `is_operating`, `sort`, `system_id`, `active`, `create_date`, `update_date`) values ('fe4a6d1f-96a4-11e7-863e-00ff6227aaa1', '获取系统与模块', 'get_system_module', '/system/module', 'b25a02e3-92c7-11e7-8c99-00ff6227aaa1', null, 'get', 1, null, 'd69060a3-914b-11e7-8c99-00ff6227aaa1', 1, '2017-09-11 10:53:18', null);

-- ----------------------------
-- 角色资源关联信息表
-- ----------------------------
drop table if exists `oauth_role_module`;
create table `oauth_role_module` (
  `id` varchar(36) not null,
  `role_id` varchar(36) default null,
  `module_id` varchar(36) default null,
  primary key (`id`),
  key `fk_reference_3` (`role_id`),
  key `fk_reference_4` (`module_id`),
  constraint `fk_reference_3` foreign key (`role_id`) references `oauth_role` (`role_id`) on delete cascade,
  constraint `fk_reference_4` foreign key (`module_id`) references `oauth_module_resources` (`module_id`) on delete cascade
) engine=innodb default charset=utf8;


insert into `oauth_role_module` values ('026d241f7dd4423b9bec3fce399df981', 'd352297eb11c418e94a5f8f2c1c229db', '3c6bf3e2-969c-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('1372abd931a24bddafd05dddb59dff3b', 'd352297eb11c418e94a5f8f2c1c229db', '31e6c4b8-96a6-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('1c10003ab8324a60bf73aa9cbf116975', 'd352297eb11c418e94a5f8f2c1c229db', '7e669afe-96dc-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('2239095a8b03410b9b0f6878571ead8e', 'd352297eb11c418e94a5f8f2c1c229db', '943d1ffa-96dd-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('271b057d7dc64a0f9f37d9bf04ee8a37', 'd352297eb11c418e94a5f8f2c1c229db', '26305844-96a5-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('30fe178c114d48e299b5a576e53b855b', 'd352297eb11c418e94a5f8f2c1c229db', '2675469b-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('337779fadfa64f6c8b888a24f995c206', 'd352297eb11c418e94a5f8f2c1c229db', '113e9c94-8405-11e7-b35a-00ff6227aaa1');
insert into `oauth_role_module` values ('4fa58e78b77e4977bdd28c4510cf428c', 'd352297eb11c418e94a5f8f2c1c229db', '7e97b3b3-96a5-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('53b2dbcf6b264f3ead75cb350b339eb1', 'd352297eb11c418e94a5f8f2c1c229db', 'fe4a6d1f-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('55fa30d390584321bc6c644c097bf7d5', 'd352297eb11c418e94a5f8f2c1c229db', '8f84a111-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('5f241edfa81e42a5b72fe3ef4fff5844', 'd352297eb11c418e94a5f8f2c1c229db', 'e69131c2-870d-11e7-ad1e-00ff6227aaa1');
insert into `oauth_role_module` values ('67ce416e629748a8b3170b2a4fab457c', 'd352297eb11c418e94a5f8f2c1c229db', '56b67433-96dc-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('6a021d873e9d4e3789a7529300937d00', 'd352297eb11c418e94a5f8f2c1c229db', 'b0c7cd02-96dd-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('73086e311647478cb084689fc972dd34', 'd352297eb11c418e94a5f8f2c1c229db', '32d0aeab-8405-11e7-b35a-00ff6227aaa1');
insert into `oauth_role_module` values ('8a23780e6fd445b688d2be6d8c26ceb0', 'd352297eb11c418e94a5f8f2c1c229db', '142ce669-96a2-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('8ba752162b7e450595c70e58bb60eb2d', 'd352297eb11c418e94a5f8f2c1c229db', 'cecfed9a0fc5452b8b4d889e5ea121ae');
insert into `oauth_role_module` values ('983510d092654891b947b8547fcafccf', 'd352297eb11c418e94a5f8f2c1c229db', '366b227a-2c30-11e8-bb2a-2cfda1b1e42a');
insert into `oauth_role_module` values ('ac9595cd3b364bc29287714c11fd2c8e', 'd352297eb11c418e94a5f8f2c1c229db', 'eff8eda4-937d-11e7-8c99-00ff6227aaa1');
insert into `oauth_role_module` values ('b68a7540e0154047a20bd03c02962736', 'd352297eb11c418e94a5f8f2c1c229db', '2c82fb06-8405-11e7-b35a-00ff6227aaa1');
insert into `oauth_role_module` values ('bb8f0b32dba743a289c93b755551f870', 'd352297eb11c418e94a5f8f2c1c229db', '8e669afe-96dc-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('bd046988996948d398df597eb1b66c6a', 'd352297eb11c418e94a5f8f2c1c229db', 'b25a02e3-92c7-11e7-8c99-00ff6227aaa1');
insert into `oauth_role_module` values ('cecfed9a0fc5452b8b4d889e5ea121ae', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'cecfed9a0fc5452b8b4d889e5ea121ae');
insert into `oauth_role_module` values ('d7303d21bdf44cdda70a9579599bd597', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '5a46be4f3e3a443f9244c8e4ca8c836b');
insert into `oauth_role_module` values ('d81dd115b997417b96944ab07a859bc8', 'd352297eb11c418e94a5f8f2c1c229db', '8338ce98-969c-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('e8a5b312211f4b89bbabeb7c0f93bd6f', 'd352297eb11c418e94a5f8f2c1c229db', '5b5c0fe2-96a6-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('efffb16b-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '113e9c94-8405-11e7-b35a-00ff6227aaa1');
insert into `oauth_role_module` values ('f0243a50-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '2c82fb06-8405-11e7-b35a-00ff6227aaa1');
insert into `oauth_role_module` values ('f024a821-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '5b5c0fe2-96a6-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02525d7-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '7bf5a9aa-96a6-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f025b799-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '7e97b3b3-96a5-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f0263f61-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '8e669afe-96dc-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f026bfbc-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '9d74d7eb-96a5-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02738c2-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'adc1e2dc-96a5-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f027b914-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'c3e5fc76-96a5-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02a2f29-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'e1ff4d1d-96a5-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02abdbb-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '32d0aeab-8405-11e7-b35a-00ff6227aaa1');
insert into `oauth_role_module` values ('f02b4cc5-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '26305844-96a5-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02bd49a-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '31e6c4b8-96a6-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02c7096-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '4b7f31dc-96a5-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02cef1b-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '8f84a111-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02d8521-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '9fe933bc-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02e1c58-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'ad04ec2c-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02ea3b4-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'ba185893-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02f1987-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '943d1ffa-96dd-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f02f9f0d-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'b0c7cd02-96dd-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f0303060-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'c54a02b8-96dd-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f030b2db-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'd3fccbfb-96dd-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f0313839-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'e29bd47a-96dd-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f031cf7f-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'b25a02e3-92c7-11e7-8c99-00ff6227aaa1');
insert into `oauth_role_module` values ('f032541b-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '2675469b-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f032e8cc-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '3c6bf3e2-969c-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f0336590-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '42179355-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f033dd3f-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '5039b225-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f03453f7-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '5c82bed5-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f034d097-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'fe4a6d1f-96a4-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f03546c3-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'e69131c2-870d-11e7-ad1e-00ff6227aaa1');
insert into `oauth_role_module` values ('f035c5a8-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '142ce669-96a2-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f0364945-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '55c48c41-96a2-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f04a7aa5-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '56b67433-96dc-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f04b08a8-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '7362412e-96a2-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f04ba0c7-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '822f714c-96a2-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f04c375e-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '8338ce98-969c-11e7-863e-00ff6227aaa1');
insert into `oauth_role_module` values ('f04cc577-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', 'eff8eda4-937d-11e7-8c99-00ff6227aaa1');
insert into `oauth_role_module` values ('f04cc578-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '366b227a-2c30-11e8-bb2a-2cfda1b1e42a');
insert into `oauth_role_module` values ('f14cc578-96dd-11e7-863e-00ff6227aaa1', 'ea75bdec-8404-11e7-b35a-00ff6227aaa1', '7e669afe-96dc-11e7-863e-00ff6227aaa1');
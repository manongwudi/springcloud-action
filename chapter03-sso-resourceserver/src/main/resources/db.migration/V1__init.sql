#单点登录用户信息表
create table `oauth_user_details` (
  `user_name` varchar(200) not null comment '用户id',
  `password` varchar(256) not null default '' comment '用户密码' ,
  `salt` varchar(256) not null default '' comment '用户密码MD5加盐值',
  `nick_name` varchar(128) not null default '' comment '用户昵称',
  `mobile` varchar(11) not null default '' comment '用户手机号' ,
  `gender` int not null default 3 comment '性别1-女；2-男；3-未知' ,
  `authorities` varchar(256) not null default 'all' comment '用户权限，使用英文逗号分隔',
  `non_expired` boolean default true comment '用户帐号是否没有过期，bool值，1表示true，0表示false',
  `non_locked` boolean default true comment '用户帐号是否没有锁定，bool值，1表示true，0表示false',
  `credentials_non_expired` boolean default true comment '用户密码是否没有过期，bool值，1表示true，0表示false',
  `enabled` boolean default true comment '帐号是否生效，1-表示生效，0-表示false',
  `create_time` timestamp not null default current_timestamp comment '用户帐号创建时间',
  `create_by` varchar(100) not null default 'system' comment '创建者',
  `update_time` timestamp not null default current_timestamp comment '最后更新时间',
  `update_by` varchar(100) not null default '' comment '最后更新人',
  primary key (`user_name`)
) engine=innodb default charset=utf8 comment '外部用户详细信息表';
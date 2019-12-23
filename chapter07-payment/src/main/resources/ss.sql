create table user_sms_code
(
   id                   bigint not null auto_increment comment 'id',
   mobile_no            varchar(11) comment '用户注册手机号',
   sms_code             varchar(10) comment '短信验证码',
   send_time            timestamp default current_timestamp comment '短信发送信息',
   create_time          timestamp default current_timestamp comment '创建时间',
   primary key (id)
);
alter table user_sms_code comment '短信验证码表';
create index idx_usc_mobile_no on user_sms_code(mobile_no);
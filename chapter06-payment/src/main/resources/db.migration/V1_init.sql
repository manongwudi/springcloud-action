#支付订单流水表
create table pay_order (
 id bigint not null primary key auto_increment,
 order_id varchar (50) comment '业务方订单号（业务方系统唯一）',
 trade_type varchar (30) comment '业务交易类型，例如topup-表示钱包充值',
 amount bigint comment '交易金额，以分为单位',
 currency varchar (10) comment '币种，cny-人民币',
 status varchar (2) comment '支付状态，0-待支付；1-支付中；2-支付成功；3-支付失败',
 channel varchar (10) comment '支付渠道编码，0-微信支付，1-支付宝支付',
 pay_type varchar (30) comment '渠道支付方式，ali_pay_pc-支付宝电脑网页支付；ali_pay_app-支付宝移动应用支付..',
 pay_id varchar (50) comment '支付平台自己生成的唯一订单流水号，用于与第三方渠道交互',
 trade_no varchar (32) comment '支付渠道流水号',
 user_id varchar (60) comment '业务方用户id',
 update_time timestamp null default current_timestamp on update current_timestamp comment '最后一次更新时间',
 create_time timestamp null default current_timestamp comment '交易创建时间',
 remark varchar(128)  comment '订单备注信息'
);
alter table pay_order comment '支付订单表';#添加索引信息
alter table pay_order add index unique_idx_pay_id ( pay_id );
alter table pay_order add index idx_order_id ( order_id );
alter table pay_order add index idx_create_time ( create_time );
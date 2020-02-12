--用于余额流水信息表，用于记录用户余额变动流水
create table user_balance_flow
(
  id            bigint not null primary key auto_increment,
  user_id       varchar(60) comment '业务方用户id',
  flow_no       varchar(32) comment '账户流水号,与业务方发起的流水号映射',
  acc_no        varchar(60) comment '账户唯一标示',
  busi_type     varchar(10) comment '余额流水业务类型,0-订单结费；1-购买月卡',
  amount        bigint comment '变动金额，以分为单位，区分正负；如：+10，-10',
  currency      varchar(10) comment '币种',
  begin_balance bigint comment '变动前余额',
  end_balance   bigint comment '变动后余额',
  fund_direct   varchar(2) comment '借贷方向，00-借方；01-贷方',
  update_time timestamp null default current_timestamp on update current_timestamp comment '最后一次更新时间',
  create_time timestamp null default current_timestamp comment '创建时间'
);

alter table user_balance_flow comment '余额变动流水信息表';

--创建相关索引信息
alter table user_balance_flow add index idx_ubf_user_id(user_id);
alter table user_balance_flow add index idx_ubf_acc_no(acc_no);
alter table user_balance_flow add index idx_ubf_flow_no(flow_no);
alter table user_balance_flow add index idx_ubf_create_time(create_time);
-- 建库脚本
drop table IF EXISTS CC_PMS_ACCOUNT_INFO;
create table CC_PMS_ACCOUNT_INFO
(
  acc_id   VARCHAR(32) not null,
  acc_name VARCHAR(20) not null,
  acc_pwd  VARCHAR(64) not null,
  name     VARCHAR(50),
  phone    VARCHAR(20),
  job      VARCHAR(50),
  remark   VARCHAR(50),
  acc_role VARCHAR(50),
  status   CHAR(1),
  lst_time DATE,
  unit_no  VARCHAR(32),
  unit_name varchar(100),
  ks       VARCHAR(50),
  addr     VARCHAR(50),
  email    VARCHAR(50),
  open_id  VARCHAR(100),
  union_id VARCHAR(100),
  PRIMARY KEY (acc_id)
) comment '账户表';

drop table IF EXISTS CC_PMS_ACC_ROLE;
create table CC_PMS_ACC_ROLE
(
  ur_id   VARCHAR(32) not null,
  acc_id  VARCHAR(32) not null,
  role_id VARCHAR(32) not null,
  PRIMARY KEY (ur_id)
) comment '账户角色配置表'
;

drop table IF EXISTS CC_PMS_ORG_INFO;
create table CC_PMS_ORG_INFO
(
  orgid           VARCHAR(32) not null,
  orgname         VARCHAR(120) not null,
  orgno           VARCHAR(120) not null,
  org_type        CHAR(2),
  org_kind        VARCHAR(50),
  lead_name       VARCHAR(50),
  lead_paper_type VARCHAR(2),
  lead_paper_no   VARCHAR(30),
  contact_name    VARCHAR(30),
  contact_phone   VARCHAR(60),
  fax             VARCHAR(60),
  postcode        CHAR(6),
  email           VARCHAR(50),
  address         VARCHAR(50),
  street          VARCHAR(50),
  community       VARCHAR(50),
  region          VARCHAR(100),
  super_org       VARCHAR(50),
  director_dep    VARCHAR(50),
  status          CHAR(1),
  remark          VARCHAR(100),
  upt_opr         VARCHAR(50),
  upt_time        DATE,
  seqno           INT,
  primary  key (orgid)
) comment '机构信息表'
;

drop table IF EXISTS CC_PMS_PHONE_CODE;
create table CC_PMS_PHONE_CODE
(
  cid         VARCHAR(32) not null,
  phone_no    VARCHAR(48),
  code        VARCHAR(10) not null,
  expire_time timestamp null,
  use_time    timestamp  null,
  crt_time    timestamp null,
  code_type   CHAR(1),
  primary  key (cid)
) comment '验证码表'
;
drop table IF EXISTS CC_PMS_RES_INFO;
create table CC_PMS_RES_INFO
(
  res_id   VARCHAR(32) not null,
  res_no   VARCHAR(32) not null,
  res_name VARCHAR(50) not null,
  res_path VARCHAR(200) not null,
  res_desc VARCHAR(100),
  p_id     VARCHAR(20),
  seq      INTEGER,
  icon     VARCHAR(50),
  app_id   VARCHAR(10) not null,
  component VARCHAR(50),
  zt    VARCHAR(1),
  redirect  VARCHAR(50),
  meta      VARCHAR(200),
  primary key (res_id)
) comment '资源信息表'
;

drop table IF EXISTS CC_PMS_ROLE_INFO;
create table CC_PMS_ROLE_INFO
(
  r_id      VARCHAR(32) not null,
  role_no   VARCHAR(10) not null,
  role_name VARCHAR(50) not null,
  role_desc VARCHAR(100),
  primary key (r_id)
)
;

drop table IF EXISTS CC_PMS_ROLE_RES;
create table CC_PMS_ROLE_RES
(
  rr_id   VARCHAR(32) not null,
  role_id VARCHAR(32) not null,
  res_id  VARCHAR(32) not null,
  primary key (rr_id)
) comment '角色权限表'
;

drop table IF EXISTS CC_PMS_USER_INFO;
create table CC_PMS_USER_INFO
(
  u_id            VARCHAR(32) not null,
  name            VARCHAR(30) not null,
  paper_no        VARCHAR(30),
  phone_no        VARCHAR(20) not null,
  corp_license_no VARCHAR(30),
  user_pwd        VARCHAR(50),
  user_status     CHAR(1),
  crt_time        DATE,
  user_type       CHAR(1) not null,
  corp_name       VARCHAR(50),
  unit_no         VARCHAR(20),
  primary key (u_id)
) comment '用户信息表'
;

drop table IF EXISTS CC_PMS_USER_LOGIN;
create table CC_PMS_USER_LOGIN
(
  login_id    VARCHAR(32) not null,
  u_id        VARCHAR(32) not null,
  login_key   VARCHAR(128),
  login_time  TIMESTAMP null,
  expire_time TIMESTAMP null,
  logout_time TIMESTAMP null,
  login_type  CHAR(1),
  unit_no     VARCHAR(20),
  primary key (login_id)
) comment '用户登录凭证表'
;

drop table IF EXISTS CC_SYSTEM_CONFIG;
create table CC_SYSTEM_CONFIG
(
  code  VARCHAR(50) not null,
  name  VARCHAR(50) not null,
  value VARCHAR(100) not null,
  primary key (code)
) comment '系统参数表'
;

insert into CC_PMS_ACCOUNT_INFO (acc_id, acc_name, acc_pwd, name, phone, job, remark, acc_role, status, lst_time, unit_no, ks, addr, email, open_id, union_id)
values ('1', 'admin', '670b14728ad9902aecba32e22fa4f6bd', '管理员', '18662470226', null, null, 'admin', '1', null, '1', null, null, null, null, null);
commit;

insert into CC_PMS_ORG_INFO (orgid,orgname,orgno,org_type,org_kind,lead_name,lead_paper_type,lead_paper_no,contact_name,contact_phone,fax,postcode,email,address,street,community,region,super_org,director_dep,status,remark,upt_opr,upt_time,seqno)
values ('1', '组织机构', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null, '1', 'hh', 'admin', '2021-09-23', null);
commit;


insert into CC_PMS_ROLE_INFO (r_id, role_no, role_name, role_desc)
values ('0', '0', '管理员', '拥有所有菜单权限');
commit;


-- 人员信息表
drop table IF EXISTS CC_CORE_PERSONAL_INFO;
create table CC_CORE_PERSONAL_INFO
(
   U_ID                 varchar(32)         not null,
   CUSTOMER_NAME        varchar(30),
   PAPER_TYPE           varchar(2),
   GENDER               varchar(1),
   PAPER_NO             varchar(30),
   PHONE_NO             varchar(20),
   PERSON_TYPE          char(1),
   DEPT_NO              varchar(50),
   dept_name            varchar(100),
   JOB_ID               varchar(50),
   NX_ID                varchar(50),
   STATUS               char(1),
   CRT_TIME             TIMESTAMP null,
   CRT_OPR              varchar(50),
   LST_TIME             TIMESTAMP null,
   LST_OPR              varchar(50),
   primary key (U_ID)
)comment '人员信息表'
;

INSERT INTO CC_CORE_PERSONAL_INFO (u_id, customer_name, paper_type, gender, paper_no, phone_no, person_type, dept_no, job_id, nx_id, status, crt_time, crt_opr, lst_time, lst_opr)
VALUES('1', '管理员', '0', '1', '', '', NULL, 'root', '', NULL, NULL, null, 'admin', NULL, NULL);


drop table if exists CC_FILEXX;
create table CC_FILEXX
(
   ID                   VARCHAR(32)         not null,
   FILENAME             VARCHAR(50)         not null,
   FILEPATH             VARCHAR(100)        not null,
   FLX                  VARCHAR(3)          not null,
   FID                  VARCHAR(32)         not null,
   CLX                  VARCHAR(10),
   CMC                  VARCHAR(50),
   CRT_TIME             TIMESTAMP null,
   CRT_OPR              VARCHAR(32),
   primary key (ID)
) comment '附件表'
;

-- 功能菜单
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('100100', 'sysmgr', '系统管理', '/sys', NULL, '0', '9', 'component', 'sys', '#', '1', '/sys/user', NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10010010', 'acc', '账户管理', 'acc', NULL, 'sysmgr', '1', 'user', 'sys', 'sys/acc', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10010011', 'role', '角色管理', 'role', NULL, 'sysmgr', '2', 'list', 'sys', 'sys/role', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10010012', 'org', '机构管理', 'org', NULL, 'sysmgr', '3', 'tree', 'sys', 'sys/org', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10010013', 'person', '人员管理', 'person', NULL, 'sysmgr', '4', 'peoples', 'sys', 'sys/person', '1', NULL, NULL);

INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('100200', 'jcsj', '基础数据', '/basic', NULL, '0', '8', 'component', 'sys', '#', '1', '/basic/sjxx', NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10020010', 'sjxx', '司机管理', 'sjxx', NULL, 'jcsj', '1', 'user', 'sys', 'basic/sjxx', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10020011', 'gsxx', '公司管理', 'gsxx', NULL, 'jcsj', '2', 'list', 'sys', 'basic/gsxx', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10020012', 'lbxx', '类别管理', 'lbxx', NULL, 'jcsj', '3', 'tree', 'sys', 'basic/lbxx', '1', NULL, NULL);

INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('100300', 'oltp', '业务处理', '/oltp', NULL, '0', '2', 'component', 'sys', '#', '1', '/oltp/jcgl', NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10030010', 'jcgl', '进仓管理', 'jcgl', NULL, 'oltp', '1', 'user', 'sys', 'oltp/jcgl', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10030011', 'tcgl', '退仓管理', 'tcgl', NULL, 'oltp', '2', 'list', 'sys', 'oltp/tcgl', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10030012', 'phzx', '拼货装箱', 'phzx', NULL, 'oltp', '3', 'tree', 'sys', 'oltp/phzx', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10030013', 'ckxxcx', '仓库信息查询', 'ckxxcx', NULL, 'oltp', '4', 'user', 'sys', 'oltp/ckxxcx', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10030014', 'sjlhtj', '司机拉货统计', 'sjlhtj', NULL, 'oltp', '5', 'list', 'sys', 'oltp/sjlhtj', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10030015', 'zxgzhtj', '装卸工装货统计', 'zxgzhtj', NULL, 'oltp', '6', 'tree', 'sys', 'oltp/zxgzhtj', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10030016', 'jcgstj', '进仓公司统计', 'jcgstj', NULL, 'oltp', '7', 'list', 'sys', 'oltp/jcgstj', '1', NULL, NULL);
INSERT INTO `cc_pms_res_info` (`res_id`, `res_no`, `res_name`, `res_path`, `res_desc`, `p_id`, `seq`, `icon`, `app_id`, `component`, `zt`, `redirect`, `meta`)
VALUES ('10030017', 'tcfytj', '退仓费用统计', 'tcfytj', NULL, 'oltp', '8', 'tree', 'sys', 'oltp/tcfytj', '1', NULL, NULL);

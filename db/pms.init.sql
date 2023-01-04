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
INSERT INTO `cc_pms_res_info` VALUES ('100100', 'sysmgr', '系统管理', '/sys', null, '0', '9', '\r\ncomponent', 'sys', '#', '1', '/sys/user', null);
INSERT INTO `cc_pms_res_info` VALUES ('10010010', 'acc', '账户管理', 'acc', null, 'sysmgr', '1', 'user', 'sys', 'sys/acc', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10010011', 'role', '角色管理', 'role', null, 'sysmgr', '2', 'list', 'sys', 'sys/role', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10010012', 'org', '机构管理', 'org', null, 'sysmgr', '3', 'tree', 'sys', 'sys/org', '1', null, null);
-- INSERT INTO `cc_pms_res_info` VALUES ('10010013', 'person', '人员管理', 'person', null, 'sysmgr', '4', 'peoples', 'sys', 'sys/person', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('100200', 'jcsj', '基础数据', '/basic', null, '0', '8', 'el-icon-setting', 'sys', '#', '1', '/basic/sjxx', null);
INSERT INTO `cc_pms_res_info` VALUES ('10020010', 'sjxx', '司机管理', 'sjxx', null, 'jcsj', '1', 'el-icon-truck', 'sys', 'basic/sjxx', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10020011', 'gsxx', '公司管理', 'gsxx', null, 'jcsj', '2', 'el-icon-house', 'sys', 'basic/gsxx', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10020012', 'lbxx', '类别管理', 'lbxx', null, 'jcsj', '3', 'el-icon-s-flag', 'sys', 'basic/lbxx', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('100300', 'oltp', '业务处理', '/oltp', null, '0', '2', 'edit', 'sys', '#', '1', '/oltp/jcgl', null);
INSERT INTO `cc_pms_res_info` VALUES ('10030010', 'jcgl', '进仓管理', 'jcgl', null, 'oltp', '1', 'el-icon-zoom-in', 'sys', 'oltp/jcgl', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10030011', 'tcgl', '退仓管理', 'tcgl', null, 'oltp', '2', 'el-icon-zoom-out', 'sys', 'oltp/tcgl', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10030012', 'phzx', '拼货装箱', 'phzx', null, 'oltp', '3', 'el-icon-box', 'sys', 'oltp/phzx', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10030013', 'ckxxcx', '仓库信息查询', 'ckxxcx', null, 'oltp', '4', 'excel', 'sys', 'oltp/ckxxcx', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10030014', 'sjlhtj', '司机拉货统计', 'sjlhtj', null, 'oltp', '5', 'excel', 'sys', 'oltp/sjlhtj', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10030015', 'zxgzhtj', '装卸工装货统计', 'zxgzhtj', null, 'oltp', '6', 'excel', 'sys', 'oltp/zxgzhtj', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10030016', 'jcgstj', '进仓公司统计', 'jcgstj', null, 'oltp', '7', 'excel', 'sys', 'oltp/jcgstj', '1', null, null);
INSERT INTO `cc_pms_res_info` VALUES ('10030017', 'tcfytj', '退仓费用统计', 'tcfytj', null, 'oltp', '8', 'excel', 'sys', 'oltp/tcfytj', '1', null, null);
commit;
-- 初始化角色
INSERT INTO `cc_pms_role_info` VALUES ('0', '0', '管理员', '系统管理');
INSERT INTO `cc_pms_role_info` VALUES ('f47108ae6fb14b16b5c6a34d413b6c85', '101', '业务员权限', '业务处理、基础数据');
commit;
-- 初始化角色权限
INSERT INTO `cc_pms_role_res` VALUES ('39cb5f0b1acd4689a30a28ca04a56bc9', '0', '100100');
INSERT INTO `cc_pms_role_res` VALUES ('c0ae55e4e5d34e6d952a13b810d082c5', '0', '10010010');
INSERT INTO `cc_pms_role_res` VALUES ('239142e50c91436081f285a33377af41', '0', '10010011');
INSERT INTO `cc_pms_role_res` VALUES ('459b9e33caf040118a3bdb057939b18d', '0', '10010012');
INSERT INTO `cc_pms_role_res` VALUES ('6438f0e9f9894d21b6f373f8cbc3906a', '0', '10010013');
INSERT INTO `cc_pms_role_res` VALUES ('3a5bb8283a3e41e79dbc2efe62bf273f', 'f47108ae6fb14b16b5c6a34d413b6c85', '100300');
INSERT INTO `cc_pms_role_res` VALUES ('4b6cfc6d7f6f4f4bb794b3e3fc73f3d8', 'f47108ae6fb14b16b5c6a34d413b6c85', '10020011');
INSERT INTO `cc_pms_role_res` VALUES ('5144627b8152407e8a5341077d823b1a', 'f47108ae6fb14b16b5c6a34d413b6c85', '10030015');
INSERT INTO `cc_pms_role_res` VALUES ('0b188630b0da4a84ba553f3093e85a5e', 'f47108ae6fb14b16b5c6a34d413b6c85', '10030014');
INSERT INTO `cc_pms_role_res` VALUES ('6e32e8ad10d44d19aa6d91238d8b5abd', 'f47108ae6fb14b16b5c6a34d413b6c85', '100200');
INSERT INTO `cc_pms_role_res` VALUES ('94b2995a7170427db2fb5f05500d955b', 'f47108ae6fb14b16b5c6a34d413b6c85', '10030016');
INSERT INTO `cc_pms_role_res` VALUES ('9b8a66c97b1d4997a5a1789b577ac20c', 'f47108ae6fb14b16b5c6a34d413b6c85', '10030010');
INSERT INTO `cc_pms_role_res` VALUES ('a49700f7084a4a26a87eb959560e1b72', 'f47108ae6fb14b16b5c6a34d413b6c85', '10030017');
INSERT INTO `cc_pms_role_res` VALUES ('afec4a9b1719424d88936a8dbc283e73', 'f47108ae6fb14b16b5c6a34d413b6c85', '10030011');
INSERT INTO `cc_pms_role_res` VALUES ('cbbfb95efe0f4166809703e2ad71b7ee', 'f47108ae6fb14b16b5c6a34d413b6c85', '10020010');
INSERT INTO `cc_pms_role_res` VALUES ('d1d0214b9da64480a3fa5d8d455183f6', 'f47108ae6fb14b16b5c6a34d413b6c85', '10030013');
INSERT INTO `cc_pms_role_res` VALUES ('da8a337bed56436a840500eae8be11d1', 'f47108ae6fb14b16b5c6a34d413b6c85', '10030012');
INSERT INTO `cc_pms_role_res` VALUES ('e5a901298c7841a382b57bafb3901268', 'f47108ae6fb14b16b5c6a34d413b6c85', '10020012');
commit;
-- 初始化用户
insert into CC_PMS_ACCOUNT_INFO (acc_id, acc_name, acc_pwd, name, phone, job, remark, acc_role, status, lst_time, unit_no, ks, addr, email, open_id, union_id)
values ('1', 'admin', '670b14728ad9902aecba32e22fa4f6bd', '超级管理员', '1234567890123', null, null, 'admin', '1', null, '1', null, null, null, null, null);
commit;
-- 初始化机构信息
INSERT INTO `cc_pms_org_info` VALUES ('1001', '中川货代', '1001', null, null, null, null, null, '', '', null, null, null, null, null, null, null, '0', null, '1', null, 'admin', '2022-11-24', '1');
INSERT INTO `cc_pms_org_info` VALUES ('1002', '中井货代', '1002', null, null, null, null, null, '', '', null, null, null, null, null, null, null, '0', null, '1', null, 'admin', '2022-11-24', '1');
INSERT INTO `cc_pms_org_info` VALUES ('1003', '泛成货代', '1003', null, null, null, null, null, '', '', null, null, null, null, null, null, null, '0', null, '1', null, 'admin', '2022-11-24', '1');
commit;
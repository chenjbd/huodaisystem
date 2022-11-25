SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cec_basictbl
-- ----------------------------
DROP TABLE IF EXISTS `cec_basictbl`;
CREATE TABLE `cec_basictbl`  (
                                 `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                 `bigTypeID` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '大类别：\n00：箱子类型  01： 装卸工队 02：报关地区  03：仓库 \n91：箱子状态 92：在库状态\n91-99系统类型专用 用户不作修改',
                                 `litTypeID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小类别',
                                 `typeName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
                                 `corpNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号:1001-中川货代 1002-中井货代 1003-泛成货代',
                                 PRIMARY KEY (id) USING BTREE
    -- PRIMARY KEY (`bigTypeID`, `litTypeID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cec_containertbl
-- ----------------------------
DROP TABLE IF EXISTS `cec_containertbl`;
CREATE TABLE `cec_containertbl`  (
                                     `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                     `sealNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '封号',
                                     `inBoundIndex` bigint(20) NOT NULL COMMENT '序号   每箱的货物从1递增',
                                     `containerNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '箱号',
                                     `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
                                     `orderIndex` int(11) NOT NULL COMMENT '排序index',
                                     `corpNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号:1001-中川货代 1002-中井货代 1003-泛成货代',
    -- PRIMARY KEY (`sealNum`, `inBoundIndex`) USING BTREE
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cec_creatboxtbl
-- ----------------------------
DROP TABLE IF EXISTS `cec_creatboxtbl`;
CREATE TABLE `cec_creatboxtbl`  (
                                    `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                    `sealNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '封号',
                                    `containerNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '箱号',
                                    `boxTypeID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '箱子类型',
                                    `billofladingNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提单号',
                                    `areaID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报关地区',
                                    `containerDate` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '装箱日期',
                                    `stevedoreID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '装卸工队',
                                    `cost` int(11) NOT NULL COMMENT '装箱费用',
                                    `cubicNum` decimal(12, 2) NOT NULL COMMENT '装箱立方',
                                    `statue` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '箱子状态 001：空箱  002：已装货 003：已出',
                                    `notes` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '箱子说明',
                                    `stevedoreNotes` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工队说明',
                                    `supervisedCbm` decimal(12, 2) NOT NULL COMMENT '监管仓立方',
                                    `corpNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号:1001-中川货代 1002-中井货代 1003-泛成货代',
    -- PRIMARY KEY (`sealNum`) USING BTREE
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cec_customertbl
-- ----------------------------
DROP TABLE IF EXISTS `cec_customertbl`;
CREATE TABLE `cec_customertbl`  (
                                    `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                    `customerID` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户ID',
                                    `customerIndex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户拼音索引\n',
                                    `customerName` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户名称\n',
                                    `tel` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
                                    `fax` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
                                    `note` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
                                    `corpNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号:1001-中川货代 1002-中井货代 1003-泛成货代',
    -- PRIMARY KEY (`customerID`) USING BTREE
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cec_drivercargotbl
-- ----------------------------
DROP TABLE IF EXISTS `cec_drivercargotbl`;
CREATE TABLE `cec_drivercargotbl`  (
                                       `tid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                       `ID` bigint(20) NOT NULL COMMENT '自增',
                                       `driverID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci  COMMENT '司机ID',
                                       `customerID` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci  COMMENT '客户公司ID',
                                       `inBoundIndex` bigint(20) NOT NULL COMMENT '进仓index',
                                       `cbm` decimal(12, 2) NOT NULL COMMENT '立方数',
                                       `pks` int(11) NOT NULL COMMENT '件数',
                                       `shippingMark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唛头',
                                       `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备考',
                                       `corpNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号:1001-中川货代 1002-中井货代 1003-泛成货代',
    -- PRIMARY KEY (`ID`) USING BTREE
                                       PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22528 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cec_drivertbl
-- ----------------------------
DROP TABLE IF EXISTS `cec_drivertbl`;
CREATE TABLE `cec_drivertbl`  (
                                  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                  `driverID` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '司机ID',
                                  `driverName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '司机名字',
                                  `tel` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '司机联系电话',
                                  `note` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
                                  `flg` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '有效区分 1：有效  0：无效',
                                  `corpNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号:1001-中川货代 1002-中井货代 1003-泛成货代',
    -- PRIMARY KEY (`driverID`) USING BTREE
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cec_inboundtbl
-- ----------------------------
DROP TABLE IF EXISTS `cec_inboundtbl`;
CREATE TABLE `cec_inboundtbl`  (
                                   `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                   `inBoundIndex` bigint(20) NOT NULL COMMENT '从000000000001自增',
                                   `customerID` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
                                   `inboundNo` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '进仓编号',
                                   `cbm` decimal(12, 2) NOT NULL COMMENT '立方数',
                                   `pks` int(11) NOT NULL COMMENT '件数',
                                   `shippingMark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唛头',
                                   `driverID` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci  COMMENT '司机',
                                   `date` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '进仓日期',
                                   `statue` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 001：在库 002：部分退仓 003：出库',
                                   `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                                   `stevedoreID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工队ID',
                                   `boundID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '仓库ID',
                                   `boxStatue` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '装箱状态:1：未装箱 2：已装箱',
                                   `corpNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号:1001-中川货代 1002-中井货代 1003-泛成货代',
    -- PRIMARY KEY (`inBoundIndex`) USING BTREE
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22528 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cec_outboundtbl
-- ----------------------------
DROP TABLE IF EXISTS `cec_outboundtbl`;
CREATE TABLE `cec_outboundtbl`  (
                                    `tid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                    `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增',
                                    `inBoundIndex` bigint(20) NOT NULL COMMENT '进仓表的index',
                                    `outDate` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退仓日期',
                                    `cbm` decimal(12, 2) NOT NULL COMMENT '退仓立方数',
                                    `pks` int(11) NOT NULL COMMENT '退仓件数',
                                    `stevedoreID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工队ID',
                                    `stevedoreMoney` decimal(12, 2) NOT NULL COMMENT '工队费用',
                                    `driverID` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退仓拉货司机',
                                    `driverMoney` decimal(12, 2) NULL DEFAULT NULL COMMENT '退仓司机费用',
                                    `note` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
                                    `corpNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号:1001-中川货代 1002-中井货代 1003-泛成货代',
    -- PRIMARY KEY (`ID`) USING BTREE
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for cec_usertbl
-- ----------------------------
DROP TABLE IF EXISTS `cec_usertbl`;
CREATE TABLE `cec_usertbl`  (
                                `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
                                `userID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
                                `userName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
                                `password` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
                                `userType` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户类别  1：管理员  2：操作员\n',
                                `corpNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司编号:1001-中川货代 1002-中井货代 1003-泛成货代',
    -- PRIMARY KEY (`userID`) USING BTREE
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- 客户序列号
DROP TABLE IF EXISTS `seq_cec_customerbl`;
CREATE TABLE `seq_cec_customerbl` (
                                      `SEQ` int(6) NOT NULL AUTO_INCREMENT,
                                      PRIMARY KEY (`SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='公司序列号';

-- 司机序列号
DROP TABLE IF EXISTS `seq_cec_driverbl`;
CREATE TABLE `seq_cec_driverbl` (
                                      `SEQ` int(4) NOT NULL AUTO_INCREMENT,
                                      PRIMARY KEY (`SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='司机序列号';

-- 入仓序列号
DROP TABLE IF EXISTS `seq_cec_inboundtbl`;
CREATE TABLE `seq_cec_inboundtbl` (
                                       `SEQ` int(8) NOT NULL AUTO_INCREMENT,
                                       PRIMARY KEY (`SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8 COMMENT='入仓序列号';

-- 出仓序列号
DROP TABLE IF EXISTS `seq_cec_outboundtbl`;
CREATE TABLE `seq_cec_outboundtbl` (
                                    `SEQ` int(8) NOT NULL AUTO_INCREMENT,
                                    PRIMARY KEY (`SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8 COMMENT='出仓序列号';

-- 箱号
DROP TABLE IF EXISTS `seq_cec_createboxbl`;
CREATE TABLE `seq_cec_createboxbl` (
                                       `SEQ` int(6) NOT NULL AUTO_INCREMENT,
                                       PRIMARY KEY (`SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='箱号';
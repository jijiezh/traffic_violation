

create database ai_test;

GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON ai_test.* TO ai_test@root IDENTIFIED BY 'root';

-- ----------------------------
-- Table structure for traffic_violation
-- ----------------------------
DROP TABLE IF EXISTS `traffic_violation`;
CREATE TABLE `traffic_violation` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `plate_number` varchar(64) DEFAULT NULL COMMENT '违章车牌号',
  `owner` varchar(64) DEFAULT NULL COMMENT '违章人员',
  `address` varchar(64) DEFAULT NULL COMMENT '地址',
  `vehicle_type` varchar(200) DEFAULT NULL COMMENT '违章车类型',
  `model` varchar(64) DEFAULT NULL COMMENT '品牌型号',
  `register_date` varchar(64) DEFAULT NULL COMMENT '注册日期',
  `issue_date` varchar(64) DEFAULT NULL COMMENT '发证日期',
  `vin` varchar(64) DEFAULT NULL COMMENT '车辆识别代号',
  `use_character` varchar(64) DEFAULT NULL COMMENT '使用性质',
  `start_time` varchar(64) DEFAULT NULL COMMENT '违章时间',
  `count` varchar(64) DEFAULT NULL COMMENT '违章次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='违章表';

-- ----------------------------
-- Records of traffic_violation
-- ----------------------------

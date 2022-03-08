package com.alibaba.innodb.java.reader;

import com.alibaba.innodb.java.reader.schema.Column;
import com.alibaba.innodb.java.reader.schema.TableDef;

/**
 * Created by lb on 2021/2/20.
 */

public class TestDataHolder {

  public static final String SQL_WX = "CREATE TABLE `wx` (\n" +
      "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
      "  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
      "  PRIMARY KEY (`id`)\n" +
      ") ENGINE=InnoDB AUTO_INCREMENT=16921 DEFAULT CHARSET=utf8mb4;";

  public static final String SQL_USER = "CREATE TABLE `user` (\n" +
      "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
      "  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
      "  PRIMARY KEY (`id`)\n" +
      ") ENGINE=InnoDB AUTO_INCREMENT=16921 DEFAULT CHARSET=utf8mb4;";

  public static final String SQL_B_TERMINAL_LOGS = "CREATE TABLE `b_terminal_logs` (\n" +
      "  `id` int(20) NOT NULL AUTO_INCREMENT,\n" +
      "  `ter_no` varchar(20) DEFAULT NULL COMMENT '终端编号',\n" +
      "  `type` varchar(1) DEFAULT NULL COMMENT '类型[1:输入   2：输出]',\n" +
      "  `parameter` varchar(256) DEFAULT NULL COMMENT '即指令',\n" +
      "  `status` int(1) DEFAULT NULL COMMENT '状态【1：成功  0：失败】',\n" +
      "  `message` text COMMENT '信息',\n" +
      "  `create_date` datetime DEFAULT NULL COMMENT '创建时间',\n" +
      "  `file` varchar(128) DEFAULT NULL COMMENT '图片路径',\n" +
      "  `face_sample_update_id` int(11) DEFAULT NULL,\n" +
      "  `l_cardnoid` int(11) DEFAULT NULL,\n" +
      "  `temperature` varchar(8) DEFAULT NULL,\n" +
      "  PRIMARY KEY (`id`),\n" +
      "  KEY `ter_no` (`ter_no`)\n" +
      ") ENGINE=InnoDB AUTO_INCREMENT=35476403 DEFAULT CHARSET=utf8;";

  public static final String SQL_B_OWNER_APARTMENT = "CREATE TABLE `b_owner_apartment` (\n" +
      "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
      "  `owner_id` int(11) NOT NULL,\n" +
      "  `apartment_id` int(11) NOT NULL,\n" +
      "  `type` int(11) NOT NULL COMMENT '用户类型【1：业主   2：亲属  3：租户 4:朋友】',\n" +
      "  `status` int(11) NOT NULL COMMENT '状态【-1：用户申请后，业主暂未同意  0：审核中（业主邀请后，用户暂时未确认）',\n" +
      "  `mold` int(11) DEFAULT NULL,\n" +
      "  `apply_time` datetime DEFAULT NULL,\n" +
      "  `remark` varchar(256) DEFAULT NULL,\n" +
      "  `is_delete` int(11) DEFAULT NULL,\n" +
      "  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',\n" +
      "  `effective_start_time` datetime DEFAULT NULL,\n" +
      "  `effective_end_time` datetime DEFAULT NULL,\n" +
      "  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',\n" +
      "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
      "  PRIMARY KEY (`id`),\n" +
      "  UNIQUE KEY `owner_id` (`owner_id`,`apartment_id`)\n" +
      ") ENGINE=InnoDB AUTO_INCREMENT=48902 DEFAULT CHARSET=utf8;";

  public static final String SQL_USERINFO_UNINDEX = "CREATE TABLE `userinfo` (\n" +
      "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
      "  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
      "  `age` int(11) NOT NULL,\n" +
      "  PRIMARY KEY (`id`)\n" +
      ") ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4";

  public static final String SQL_USERINFO_INDEX = "CREATE TABLE `userinfo` (\n" +
      "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
      "  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
      "  `age` int(11) NOT NULL,\n" +
      "  PRIMARY KEY (`id`),\n" +
      "  KEY `age` (`age`)\n" +
      ") ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;";

  public static final String CREATETABLESQL = SQL_USERINFO_INDEX;
  //public static final String CREATETABLESQL = SQL_B_TERMINAL_LOGS;

  public static final TableDef TABLE_DEF = new TableDef()
      .addColumn(new Column().setName("id").setType("int(11)").setNullable(false).setPrimaryKey(true))
      .addColumn(new Column().setName("name").setType("varchar(255)").setNullable(true));

  //public static final String IBD_PATH = "D:\\ibdData\\ibdata1";

  //public static final String IBD_PATH = "D:\\ibdData\\userinfo\\1-userinfo-unindex.ibd";
  public static final String IBD_PATH = "D:\\ibdData\\userinfo\\2-userinfo-index.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\userinfo\\3-userinfo-index_much.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\b_owner_apartment.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\b_terminal_logs.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\b_terminal_logs_x.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\b_entrance_guard_record.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\1-wx.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\2-wx.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\2-b_terminal_monitor_log.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\1-b_terminal_monitor_log.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\w_clock_bg.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\b_user_tag.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\c_subject.ibd";
  //public static final String IBD_PATH = "D:\\ibdData\\1-user.ibd";
}

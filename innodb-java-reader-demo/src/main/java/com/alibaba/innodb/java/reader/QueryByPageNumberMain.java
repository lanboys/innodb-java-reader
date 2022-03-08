/*
 * Copyright (C) 1999-2019 Alibaba Group Holding Limited
 */
package com.alibaba.innodb.java.reader;

import com.alibaba.innodb.java.reader.page.index.GenericRecord;

import java.util.Arrays;
import java.util.List;

/**
 * @author xu.zx
 */
public class QueryByPageNumberMain {

  public static void main(String[] args) {
    String createTableSql = "CREATE TABLE `tb11`\n"
        + "(`id` int(11) NOT NULL ,\n"
        + "`a` bigint(20) NOT NULL,\n"
        + "`b` varchar(64) NOT NULL,\n"
        + "PRIMARY KEY (`id`),\n"
        + "KEY `key_a` (`a`))\n"
        + "ENGINE=InnoDB;";
    String ibdFilePath = "/usr/local/mysql/data/test/t.ibd";
    try (TableReader reader = new TableReaderImpl(TestDataHolder.IBD_PATH, TestDataHolder.CREATETABLESQL)) {
      reader.open();
      // 只能查询聚簇索引
      List<GenericRecord> recordList = reader.queryByPageNumber(4);
      for (GenericRecord record : recordList) {
        Object[] values = record.getValues();
        System.out.println(Arrays.asList(values));
        assert record.getPrimaryKey() == record.get("id");
        //System.out.println("id=" + record.get("id"));
        //System.out.println("a=" + record.get("a"));
        if (!record.isLeafRecord()) {
          System.out.println(record.getChildPageNumber());
        }
      }
    }
  }

}

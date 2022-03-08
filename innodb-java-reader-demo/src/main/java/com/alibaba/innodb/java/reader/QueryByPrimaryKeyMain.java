/*
 * Copyright (C) 1999-2019 Alibaba Group Holding Limited
 */
package com.alibaba.innodb.java.reader;

import com.alibaba.innodb.java.reader.page.index.GenericRecord;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;

import static com.alibaba.innodb.java.reader.TestDataHolder.CREATETABLESQL;

/**
 * @author xu.zx
 */
public class QueryByPrimaryKeyMain {

  public static void main(String[] args) {
    String createTableSql = "CREATE TABLE `tb11`\n"
        + "(`id` int(11) NOT NULL ,\n"
        + "`a` bigint(20) NOT NULL,\n"
        + "`b` varchar(64) NOT NULL,\n"
        + "PRIMARY KEY (`id`),\n"
        + "KEY `key_a` (`a`))\n"
        + "ENGINE=InnoDB;";
    String ibdFilePath = "/usr/local/mysql/data/test/t.ibd";

    try (TableReader reader = new TableReaderImpl(TestDataHolder.IBD_PATH, CREATETABLESQL)) {
      reader.open();

      //Integer key = 3804;
      //Integer key = 3806;
      Integer key = 3797;
      //Integer key = 1;
      //Integer key = -1;
      //Integer key = 2;
      //Integer key = 28036;
      //Integer key = 28820;
      //Integer key = 29190140 * 2;

      // ~~~ query by primary key
      GenericRecord record = reader.queryByPrimaryKey(ImmutableList.of(key));
      Object[] values = record.getValues();
      System.out.println(Arrays.asList(values));
      assert record.getPrimaryKey() == record.get("id");
      System.out.println("id=" + record.get("id"));
      System.out.println("name=" + record.get("name"));

      // ~~~ query by primary key with projection
      //record = reader.queryByPrimaryKey(ImmutableList.of(key), ImmutableList.of("name"));
      //System.out.println("main(): " + record);
    }
  }
}

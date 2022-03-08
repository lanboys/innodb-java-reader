/*
 * Copyright (C) 1999-2019 Alibaba Group Holding Limited
 */
package com.alibaba.innodb.java.reader;

import com.alibaba.innodb.java.reader.page.index.GenericRecord;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author xu.zx
 */
public class GetQueryAllIteratorMain {

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

      // ~~~ query all records
      Iterator<GenericRecord> iterator = reader.getQueryAllIterator();
      int count = 0;
      while (iterator.hasNext()) {
        GenericRecord record = iterator.next();
        Object[] values = record.getValues();
        System.out.println(Arrays.asList(values));
        assert record.getPrimaryKey() == record.get("id");
        System.out.println("id=" + record.get("id"));
        System.out.println("a=" + record.get("a"));
        count++;
      }
      System.out.println(count);

      // ~~~ query all records with projection
      //[1, null, aaaaaaaa]
      //[2, null, bbbbbbbb]
      //[3, null, cccccccc]
      //[4, null, dddddddd]
      //[5, null, eeeeeeee]
      List<String> projection = Arrays.asList("b");
      iterator = reader.getQueryAllIterator(projection);
      while (iterator.hasNext()) {
        GenericRecord record = iterator.next();
        Object[] values = record.getValues();
        System.out.println(Arrays.asList(values));
      }

      // ~~~ query all records with order
      //[5, 10, eeeeeeee]
      //[4, 8, dddddddd]
      //[3, 6, cccccccc]
      //[2, 4, bbbbbbbb]
      //[1, 2, aaaaaaaa]
      boolean ascOrder = false;
      iterator = reader.getQueryAllIterator(ascOrder);
      while (iterator.hasNext()) {
        GenericRecord record = iterator.next();
        Object[] values = record.getValues();
        System.out.println(Arrays.asList(values));
      }

      // ~~~ query all records with projection and order
      //[5, null, eeeeeeee]
      //[4, null, dddddddd]
      //[3, null, cccccccc]
      //[2, null, bbbbbbbb]
      //[1, null, aaaaaaaa]
      projection = Arrays.asList("b");
      ascOrder = false;
      iterator = reader.getQueryAllIterator(projection, ascOrder);
      while (iterator.hasNext()) {
        GenericRecord record = iterator.next();
        Object[] values = record.getValues();
        System.out.println(Arrays.asList(values));
      }
    }
  }

}

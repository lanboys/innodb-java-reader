/*
 * Copyright (C) 1999-2019 Alibaba Group Holding Limited
 */
package com.alibaba.innodb.java.reader;

import com.alibaba.innodb.java.reader.comparator.ComparisonOperator;
import com.alibaba.innodb.java.reader.page.index.GenericRecord;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.Iterator;

import static com.alibaba.innodb.java.reader.TestDataHolder.CREATETABLESQL;

/**
 * @author xu.zx
 */
public class QueryBySecondaryKeyMain {

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

      int count = 0;
      // ~~~ query by sk
      Iterator<GenericRecord> iterator = reader.getRecordIteratorBySk("age",
          ImmutableList.of(12), ComparisonOperator.GTE,
          ImmutableList.of(13), ComparisonOperator.LTE);
      while (iterator.hasNext()) {
        GenericRecord record = iterator.next();
        Object[] values = record.getValues();
        System.out.println(Arrays.asList(values));
        count++;
      }
        System.out.println(count);

      // ~~~ query by sk, point query
      iterator = reader.getRecordIteratorBySk("age",
          ImmutableList.of(16L), ComparisonOperator.GTE,
          ImmutableList.of(26L), ComparisonOperator.LTE);
      while (iterator.hasNext()) {
        GenericRecord record = iterator.next();
        Object[] values = record.getValues();
        System.out.println(Arrays.asList(values));
      }

      // ~~~ query by sk, projection
      iterator = reader.getRecordIteratorBySk("key_a",
          ImmutableList.of(6L), ComparisonOperator.GTE,
          null, ComparisonOperator.NOP, ImmutableList.of("b"));
      while (iterator.hasNext()) {
        GenericRecord record = iterator.next();
        Object[] values = record.getValues();
        System.out.println(Arrays.asList(values));
      }

      // ~~~ query by sk, projection and order
      boolean isAsc = false;
      iterator = reader.getRecordIteratorBySk("key_a",
          ImmutableList.of(6L), ComparisonOperator.GTE,
          null, ComparisonOperator.NOP,
          ImmutableList.of("id", "a", "b"), isAsc);
      while (iterator.hasNext()) {
        GenericRecord record = iterator.next();
        Object[] values = record.getValues();
        System.out.println(Arrays.asList(values));
      }
    }
  }

}

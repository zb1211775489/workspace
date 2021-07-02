package com.bayee.petition.service;

import java.util.Map;

public interface HBaseClientService {

    /**
     * 分组获取数据并做分页
     * @param tableName 表名
     * @param word 分组字段，多个字段以英文逗号隔开
     * @param pageIndex 页码
     * @param pageSize 每页显示的条数
     * @return
     */
    Map<String, Object> getGroupData(String tableName, String word, int pageIndex, int pageSize);


    /**
     * 表的总条数
     * @param tableName 表名
     * @return
     */
    Map<String, Object> getCount(String tableName);


    /**
     * 分页条件查询
     * @param tableName 表名
     * @param fields 展示的字段
     * @param symbol 运算符
     * @param condition 条件
     * @param pageIndex 页码
     * @param pageSize 每页展示的条数
     * @return
     */
    Map<String, Object> getConditionPageData(String tableName, String fields, String symbol, String condition, int pageIndex, int pageSize);

    /**
     * 全量条件查询
     * @param tableName 表名
     * @param fields 展示的字段
     * @param symbol 运算符
     * @param condition 条件
     * @return
     */
    Map<String, Object> getConditionData(String tableName, String fields, String symbol, String condition);

    /**
     * 聚合查询
     * @param tableName 表名
     * @param fields 展示的字段
     * @param pageIndex 页码
     * @param pageSize 每页展示的条数
     * @return
     */
    Map<String, Object> getAggregationData(String tableName, String fields, int pageIndex, int pageSize);
}

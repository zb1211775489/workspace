package com.bayee.petition.service.impl;

import com.bayee.petition.HBaseClient;
import com.bayee.petition.service.HBaseClientService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HBaseClientServiceImpl implements HBaseClientService {

    @Override
    public Map<String, Object> getGroupData(String tableName, String word, int pageIndex, int pageSize) {
        return HBaseClient.queryByGroup(tableName,word,pageIndex,pageSize);
    }


    @Override
    public Map<String, Object> getCount(String tableName) {
        return HBaseClient.queryCount(tableName);
    }

    @Override
    public Map<String, Object> getConditionPageData(String tableName, String fields, String symbol, String condition, int pageIndex, int pageSize) {
        return HBaseClient.queryPageByCondition(tableName,fields,symbol,condition,pageIndex,pageSize);
    }

    @Override
    public Map<String, Object> getConditionData(String tableName, String fields, String symbol, String condition) {
        return HBaseClient.queryByCondition(tableName,fields,symbol,condition);
    }

    @Override
    public Map<String, Object> getAggregationData(String tableName, String fields, int pageIndex, int pageSize) {
        return HBaseClient.queryByAggregation(tableName,fields,pageIndex,pageSize);
    }


}

package com.bayee.petition.controller;

import com.bayee.petition.service.HBaseClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class HBaseClientController {


    @Autowired
    private HBaseClientService hBaseClientService;

    @RequestMapping("/HBaseClient/groupData")
    @ResponseBody
    public Map<String, Object> getGroupData(HttpServletResponse response,String tableName,String word,
                                            @RequestParam(required = false,defaultValue = "1") int pageIndex,
                                            @RequestParam(required = false,defaultValue = "10") int pageSize) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        return hBaseClientService.getGroupData(tableName,word,pageIndex,pageSize);
    }

    @RequestMapping("/HBaseClient/countData")
    @ResponseBody
    public Map<String, Object> getCountData(HttpServletResponse response,String tableName) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        return hBaseClientService.getCount(tableName);
    }

    @RequestMapping("/HBaseClient/conditionPageData")
    @ResponseBody
    public Map<String, Object> getConditionPageData(HttpServletResponse response,String tableName,@RequestParam(required = false,defaultValue = "*") String fields,
                                                @RequestParam(required = false,defaultValue = "and") String symbol,String condition,
                                                @RequestParam(required = false,defaultValue = "1") int pageIndex,
                                                @RequestParam(required = false,defaultValue = "50") int pageSize) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        return hBaseClientService.getConditionPageData(tableName,fields,symbol,condition,pageIndex,pageSize);
    }

    @RequestMapping("/HBaseClient/conditionData")
    @ResponseBody
    public Map<String, Object> getConditionData(HttpServletResponse response,String tableName,@RequestParam(required = false,defaultValue = "*") String fields,
                                                @RequestParam(required = false,defaultValue = "and") String symbol,String condition) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        return hBaseClientService.getConditionData(tableName,fields,symbol,condition);
    }

    @RequestMapping("/HBaseClient/aggregationData")
    @ResponseBody
    public Map<String, Object> getAggregationData(HttpServletResponse response,String tableName,String fields,
                                                @RequestParam(required = false,defaultValue = "1") int pageIndex,
                                                @RequestParam(required = false,defaultValue = "50") int pageSize) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        return hBaseClientService.getAggregationData(tableName,fields,pageIndex,pageSize);
    }

}

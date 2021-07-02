package com.bayee.dataimport.service.impl;

import com.bayee.dataimport.service.YHHService;
import com.bayee.dataimport.util.HttpClientUtil;
import com.bayee.dataimport.util.PropertyUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * YHHServiceImpl
 *
 * @author antuo
 * @since 2021/6/15 15:24
 */
public class YHHServiceImpl implements YHHService {

    private static final String BASE_URL = PropertyUtils.getValue("yhh.interface.base.url");
    private static final String APPKEY = PropertyUtils.getValue("yhh.app.key");
    private static final String APPSRCRET = PropertyUtils.getValue("yhh.app.secret");

    private Map<String,String> map = null;

    @Override
    public String getMyPorts() {
        map = new HashMap<>();
        map.put("appkey", APPKEY);
        map.put("appSecret",APPSRCRET);
        String timestamp = System.currentTimeMillis() + "";
        map.put("timestamp", timestamp);

        String url = BASE_URL + "/weather/getMyPorts";

        return HttpClientUtil.doPost(url,map);
    }

    @Override
    public String findComplexNow() {
        return null;
    }

    @Override
    public String harbourType() {
        return null;
    }

    @Override
    public String orderLine() {
        return null;
    }

    @Override
    public String findComplexYear() {
        return null;
    }

    @Override
    public String getWindAreaDataList() {
        return null;
    }
}

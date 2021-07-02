package com.bayee.dataimport.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bayee.dataimport.service.CallInterfaceService;
import com.bayee.dataimport.util.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName CallInterfaceServiceImpl
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/11
 **/

@Service
public class CallInterfaceServiceImpl implements CallInterfaceService {

    private static final Logger log = Logger.getLogger(com.bayee.dataimport.service.impl.CallInterfaceServiceImpl.class);

    private static final String WATER_URL = PropertyUtils.getValue("yhh.interface.water.url");

    private Map<String,String> map = null;


    @Override
    public String getAllWaterInfo(String stationCode, String startDate, String endDate) {
        map = new HashMap<>();
        map.put("stationCode",stationCode);
        map.put("startDate",startDate);
        map.put("endDate",endDate);

        String url = "http://zssf.ygwjg.com/sysWaterLevel/search";
        return HttpClientUtil.doPost(url,map);
    }

    @Override
    public String getListStations(String pageSize,String pageNumber) {
        map = new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("pageNumber",pageNumber);
        String json = JSONObject.toJSONString(map);
        String url = WATER_URL + "/102/station/listStations";
        return HttpClientUtil.doPostJson(url,json);
    }

    @Override
    public String getListStationRain(String stcd, String startTime, String endTime) {
        map = new HashMap<>();
        map.put("stcd",stcd);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        String json = JSONObject.toJSONString(map);
        String url = WATER_URL + "/102/rain/listStationRain";
        return HttpClientUtil.doPostJson(url,json);
    }

    @Override
    public String getListStationWater(String stcd, String sttp, String startTime, String endTime) {
        map = new HashMap<>();
        map.put("stcd",stcd);
        map.put("sttp",sttp);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        String json = JSONObject.toJSONString(map);
        String url = WATER_URL + "/102/water/listStationWater";
        return HttpClientUtil.doPostJson(url,json);
    }


    @Override
    public String query(String code,String pageSize,String pageNumber,String startTime,String endTime) {
        map = new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("pageNumber",pageNumber);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        String json = JSONObject.toJSONString(map);
        String url = "https://zssgpt.zhoushan.gov.cn:48001/apiBus/api/provider/uncheck/query/"+code;
        return HttpClientUtil.doPostJson(url,json);
    }

    @Override
    public String getSecretKey(String appKey, String tenantCode) {
        String password = "4M2y8HpTv9jn4Vhh";
        String sign = new SM3Utils().sm3(tenantCode+"#"+appKey+"#"+password);
        map = new HashMap<>();
        map.put("appKey",appKey);
        map.put("tenantCode",tenantCode);
        map.put("sign",sign);
        String url = "http://10.145.102.24:30010/aicshare/rest/service/safety/refreshsecret";
        return HttpClientUtil.doPost(url,map);
    }

    @Override
    public String getProvincialData(String resourceCode, String version, String App_key, String TenantCode, String pageSize, String pageNum) {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("App_key",App_key));
        params.add(new BasicNameValuePair("TenantCode",TenantCode));
        String Nonce = UUID.randomUUID().toString();
        params.add(new BasicNameValuePair("Nonce",Nonce));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String Created = sdf.format(new Date());
        params.add(new BasicNameValuePair("Created",Created));
        String secretKey = getSecretKey(App_key, TenantCode);
        secretKey = SymmetricEncoder.AESDncode(App_key, JSONObject.parseObject(secretKey).getJSONObject("data").getString("secretKey"));
        String PasswdDigest = new SM3Utils().sm3(Nonce+"_"+Created+"_"+secretKey);
        params.add(new BasicNameValuePair("PasswdDigest",PasswdDigest));
        String parameter = "{}";
        params.add(new BasicNameValuePair("parameter",parameter));
        params.add(new BasicNameValuePair("pageNum",pageNum));
        params.add(new BasicNameValuePair("pageSize",pageSize));
        String url = "http://10.145.102.24:30010/aicshare/rest/query/"+resourceCode+"/"+version;
        return HttpClientUtil.sendPost(url,params);
    }

    @Override
    public String getProvincialData(String resourceCode, String version, String App_key, String TenantCode, String date, String pageSize, String pageNum) {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("App_key",App_key));
        params.add(new BasicNameValuePair("TenantCode",TenantCode));
        String Nonce = UUID.randomUUID().toString();
        params.add(new BasicNameValuePair("Nonce",Nonce));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String Created = sdf.format(new Date());
        params.add(new BasicNameValuePair("Created",Created));
        String secretKey = getSecretKey(App_key, TenantCode);
        secretKey = SymmetricEncoder.AESDncode(App_key, JSONObject.parseObject(secretKey).getJSONObject("data").getString("secretKey"));
        String PasswdDigest = new SM3Utils().sm3(Nonce+"_"+Created+"_"+secretKey);
        params.add(new BasicNameValuePair("PasswdDigest",PasswdDigest));
        Map<String,Object> param = new HashMap<>();
        param.put("condition","update_time.gte.'"+date+"'");
        String parameter = JSONObject.toJSONString(param);
        params.add(new BasicNameValuePair("parameter",parameter));
        params.add(new BasicNameValuePair("pageNum",pageNum));
        params.add(new BasicNameValuePair("pageSize",pageSize));
        String url = "http://10.145.102.24:30010/aicshare/rest/query/"+resourceCode+"/"+version;
        return HttpClientUtil.sendPost(url,params);
    }

}

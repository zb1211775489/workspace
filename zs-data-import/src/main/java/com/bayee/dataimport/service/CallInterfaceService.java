package com.bayee.dataimport.service;


/**
 * 调用接口服务
 */
public interface CallInterfaceService {

    /*-------------------------------------------- 水利相关接口开始 ------------------------------------------------*/
    String getAllWaterInfo(String stationCode, String startDate, String endDate);

    String getListStations(String pageSize,String pageNumber);

    String getListStationRain(String stcd, String startTime, String endTime);

    String getListStationWater(String stcd, String sttp, String startTime, String endTime);

    String query(String code,String pageSize, String pageNumber,String startTime,String endTime);
    /*-------------------------------------------- 水利相关接口结束 ------------------------------------------------*/

    /*--------------------------------------------省厅相关接口开始-------------------------------*/
    String getSecretKey(String appKey, String tenantCode);
    String getProvincialData(String resourceCode, String version, String App_key, String TenantCode,
                             String pageSize, String pageNum);

    String getProvincialData(String resourceCode, String version, String App_key, String TenantCode,
                             String date, String pageSize, String pageNum);


}

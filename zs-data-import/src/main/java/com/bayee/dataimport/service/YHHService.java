package com.bayee.dataimport.service;

/**
 * YHHService
 *
 * @author antuo
 * @since 2021/6/15 15:23
 */

public interface YHHService {

    /**
     * getMyPorts 获取已绑定港口数据
     * @return
     */
    String getMyPorts();

    /**
     * findComplexNow 获取当前实时的台风信息
     * @return
     */
    String findComplexNow();

    /**
     * harbourType 获取已绑定港口类型数据
     * @return
     */
    String harbourType();

    /**
     * orderLine 获取定线制绕行基础数据数据
     * @return
     */
    String orderLine();

    /**
     * findComplexYear 查询台风年份
     * @return
     */
    String findComplexYear();

    /**
     * getWindAreaDataList 获取大风分布
     * @return
     */
    String getWindAreaDataList();
}

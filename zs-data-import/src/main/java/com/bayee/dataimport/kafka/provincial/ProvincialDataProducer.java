package com.bayee.dataimport.kafka.provincial;

import com.bayee.dataimport.service.CallInterfaceService;
import com.bayee.dataimport.service.impl.CallInterfaceServiceImpl;
import com.bayee.dataimport.util.JsonUtils;
import com.bayee.dataimport.util.KafkaTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

/**
 * @ClassName ProvincialDataProducer
 * @Description: TODO
 * @Author zb
 * @Date 2021/5/20
 **/
public class ProvincialDataProducer {

    private CallInterfaceService callInterfaceService = new CallInterfaceServiceImpl();

    @Autowired
    private KafkaTemplateUtil kafkaTemplateUtil;

    private String topic;

    private String resourceCode;
    private String version;
    private String App_key;
    private String TenantCode;
    private long time;

    public ProvincialDataProducer(String topic, String resourceCode, String version, String App_key, String TenantCode) {
        this.topic = topic;
        this.resourceCode = resourceCode;
        this.version = version;
        this.App_key = App_key;
        this.TenantCode = TenantCode;
    }

    public ProvincialDataProducer(String topic, String resourceCode, String version, String App_key, String TenantCode, long time) {
        this.topic = topic;
        this.resourceCode = resourceCode;
        this.version = version;
        this.App_key = App_key;
        this.TenantCode = TenantCode;
        this.time = time;
    }

    public void send() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(time-1800000);

        String total = JsonUtils.getTotal2(callInterfaceService.getProvincialData(resourceCode,version,App_key,TenantCode,"1","1"));
        //String total = JsonUtils.getTotal2(callInterfaceService.getProvincialData(resourceCode,version,App_key,TenantCode,date,"1","1"));

        long pageSize = 5000;
        long pageNum = Long.parseLong(total)/pageSize;
        pageNum = Long.parseLong(total)%pageSize==0?pageNum:pageNum+1;

        for (long pageIndex = 1; pageIndex <= pageNum; pageIndex++) {
            //String messages = JsonUtils.processMessage3(callInterfaceService.getProvincialData(resourceCode, version, App_key, TenantCode, date, String.valueOf(pageSize), String.valueOf(pageIndex)));
            String messages = JsonUtils.processMessage3(callInterfaceService.getProvincialData(resourceCode, version, App_key, TenantCode,String.valueOf(pageSize), String.valueOf(pageIndex)));

            if (messages != null) {
                String[] messageArray = messages.split(",\\{");
                for (int i = 1; i < messageArray.length; i++) {
                    messageArray[i] = "{" + messageArray[i];
                }
                for (String message : messageArray) {
                    kafkaTemplateUtil.send(topic,message);
                }
            }
        }
    }
}

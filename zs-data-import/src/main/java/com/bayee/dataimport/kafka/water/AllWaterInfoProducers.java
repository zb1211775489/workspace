package com.bayee.dataimport.kafka.water;

import com.bayee.dataimport.service.CallInterfaceService;
import com.bayee.dataimport.service.impl.CallInterfaceServiceImpl;
import com.bayee.dataimport.util.JsonUtils;
import com.bayee.dataimport.util.KafkaTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName KafkaProducers
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/11
 **/
public class AllWaterInfoProducers {


    private CallInterfaceService callInterfaceService = new CallInterfaceServiceImpl();
    @Autowired
    private KafkaTemplateUtil kafkaTemplateUtil;

    private static String topic;
    private long time;

    public AllWaterInfoProducers(String topic , long time) {
        this.topic = topic;
        this.time = time;
    }


    public void send() {
        List<String> stationCodes = JsonUtils.STATION_CODE;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endDate = sdf.format(time);
        String startDate = sdf.format(time - 1800000);

        for (String stationCode : stationCodes) {
            String messages = JsonUtils.processMessage3(callInterfaceService.getAllWaterInfo(stationCode,startDate,endDate));
            if(messages != null) {
                String[] messageArray = messages.split(",\\{");
                for (int i=1; i<messageArray.length; i++) {
                    messageArray[i] = "{"+messageArray[i];
                }
                for (String message : messageArray) {
                    kafkaTemplateUtil.send(topic,message);
                }
            }
        }
    }
}

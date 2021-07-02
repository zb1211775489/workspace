package com.bayee.dataimport.kafka.water;

import com.bayee.dataimport.util.KafkaTemplateUtil;
import com.bayee.dataimport.service.CallInterfaceService;
import com.bayee.dataimport.service.impl.CallInterfaceServiceImpl;
import com.bayee.dataimport.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @ClassName KafkaProducers
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/11
 **/
public class ListStationsProducers {

    private CallInterfaceService callInterfaceService = new CallInterfaceServiceImpl();

    @Autowired
    private KafkaTemplateUtil kafkaTemplateUtil;

    private static String topic;

    public ListStationsProducers(String topic) {
        this.topic = topic;
    }


    public void send() {

        String total = JsonUtils.getTotal(callInterfaceService.getListStations(null, null));

        long pageSize = 1000;
        long pageNum = Long.parseLong(total)/pageSize;
        pageNum = Long.parseLong(total)%pageSize==0?pageNum:pageNum+1;

        for (long pageIndex = 1; pageIndex <= pageNum; pageIndex++) {
            String messages = JsonUtils.processMessage(callInterfaceService.getListStations(String.valueOf(pageSize), String.valueOf(pageIndex)));
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

package com.bayee.dataimport.kafka.water;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bayee.dataimport.util.KafkaTemplateUtil;
import com.bayee.dataimport.service.CallInterfaceService;
import com.bayee.dataimport.service.impl.CallInterfaceServiceImpl;
import com.bayee.dataimport.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName StationRainInfoProducers
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/11
 **/
public class StationRainInfoProducers {

    private CallInterfaceService callInterfaceService = new CallInterfaceServiceImpl();
    @Autowired
    private KafkaTemplateUtil kafkaTemplateUtil;

    private static String topic;
    private long time;

    public StationRainInfoProducers(String topic,long time) {
        this.topic = topic;
        this.time = time;
    }


    public void send() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = sdf.format(time -1800000);
        String endTime = sdf.format(time);

        String total = JsonUtils.getTotal(callInterfaceService.getListStations(null,null));

        long pageSize = 1000;
        long pageNum = Long.parseLong(total)/pageSize;
        pageNum = Long.parseLong(total)%pageSize==0?pageNum:pageNum+1;

        for (long pageIndex = 1; pageIndex <= pageNum; pageIndex++) {
            String messages = JsonUtils.processMessage(callInterfaceService.getListStations(String.valueOf(pageSize),String.valueOf(pageIndex)));
            String[] split = messages.split(",\\{");
            for (int i=1; i<split.length; i++) {
                split[i] = "{" + split[i];
            }

            for (String s : split) {
                JSONObject stcdjson = JSON.parseObject(s);
                String stcd = stcdjson.getString("stcd");
                String message = JsonUtils.processMessage2(callInterfaceService.getListStationRain(stcd,startTime, endTime));
                if (message != null) {
                    String[] split1 = message.split(",\\{");
                    for (int i=1; i<split1.length; i++) {
                        split1[i] = "{"+split1[i];
                    }
                    for (String s1 : split1) {
                        System.out.println(s1);
                        //kafkaTemplateUtil.send(topic,s1);
                    }
                }
            }

        }
    }
}

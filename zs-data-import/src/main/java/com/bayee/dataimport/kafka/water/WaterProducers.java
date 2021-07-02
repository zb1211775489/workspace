package com.bayee.dataimport.kafka.water;

import com.bayee.dataimport.util.KafkaTemplateUtil;
import com.bayee.dataimport.service.CallInterfaceService;
import com.bayee.dataimport.service.impl.CallInterfaceServiceImpl;
import com.bayee.dataimport.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName KafkaProducers
 * @Description: TODO
 * @Author Lyb
 * @Date 2021/3/11
 **/
public class WaterProducers {
    private CallInterfaceService callInterfaceService = new CallInterfaceServiceImpl();

    @Autowired
    private KafkaTemplateUtil kafkaTemplateUtil;

    private  String topic;

    private String code;

    private long time;



    public WaterProducers(String topic, String code, long time) {
        this.topic = topic;
        this.code = code;
        this.time = time;
    }


    public void send() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDate = sdf.format(time - 1800000);
        String endDate = sdf.format(time);

        String total = JsonUtils.getTotal(callInterfaceService.query(code, null, null, startDate, endDate));

        long pageSize = 1000;
        long pageNum = Long.parseLong(total)/pageSize;
        pageNum = Long.parseLong(total)%pageSize==0?pageNum:pageNum+1;

        for (long pageIndex = 1; pageIndex <= pageNum; pageIndex++) {
            String message = JsonUtils.processMessage(callInterfaceService.query(code, String.valueOf(pageSize), String.valueOf(pageIndex), startDate, endDate));
            if (message != null) {
                String[] split1 = message.split(",\\{");
                for (int i=1; i<split1.length; i++) {
                    split1[i] = "{"+split1[i];
                }
                for (String s1 : split1) {
                    kafkaTemplateUtil.send(topic,s1);
                }
            }
        }

    }
}

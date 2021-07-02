import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bayee.dataimport.service.YHHService;
import com.bayee.dataimport.service.impl.CallInterfaceServiceImpl;
import com.bayee.dataimport.service.impl.YHHServiceImpl;
import com.bayee.dataimport.util.JsonUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dd
 *
 * @author antuo
 * @since 2021/6/1 19:49
 */
public class Test1 {
    @Test
    public void test1() {
        CallInterfaceServiceImpl callInterfaceService = new CallInterfaceServiceImpl();
        List<String> stationCode = JsonUtils.STATION_CODE;
        for (String s : stationCode) {
            String query = callInterfaceService.getAllWaterInfo(s,"2021-06-08 14:00:00","2021-06-08 14:30:00");
            System.out.println(query);
        }

    }

    @Test
    public void test10() {
        CallInterfaceServiceImpl callInterfaceService = new CallInterfaceServiceImpl();
        String query = callInterfaceService.getAllWaterInfo("3160211048","2021-06-08 16:40:00","2021-06-08 17:00:00");
        System.out.println(query);

    }

    @Test
    public void test2() {
        CallInterfaceServiceImpl callInterfaceService = new CallInterfaceServiceImpl();
        String query = callInterfaceService.getListStations(null,null);
        System.out.println(query);
    }

    @Test
    public void test7() {
        CallInterfaceServiceImpl callInterfaceService = new CallInterfaceServiceImpl();
        String query = callInterfaceService.query("215c7368",null,null,null,null);
        System.out.println(query);
    }

    @Test
    public void test3() {
        CallInterfaceServiceImpl callInterfaceService = new CallInterfaceServiceImpl();
        String query = callInterfaceService.getListStationRain("13320","2021-06-03 10:00:00","2021-06-03 10:10:00");
        System.out.println(query);
    }

    @Test
    public void test4() {
        int count = 0;
        List<String> sttps = JsonUtils.STTPS;
        CallInterfaceServiceImpl callInterfaceService = new CallInterfaceServiceImpl();
        String total = JsonUtils.getTotal(callInterfaceService.getListStations(null,null));

        long pageSize = 1000;
        long pageNum = Long.parseLong(total)/pageSize;
        pageNum = Long.parseLong(total)%pageSize==0?pageNum:pageNum+1;

        List<String> stcds = new ArrayList<>();
        for (long pageIndex = 1; pageIndex <= pageNum; pageIndex++) {
            String messages = JsonUtils.processMessage(callInterfaceService.getListStations(String.valueOf(pageSize),String.valueOf(pageIndex)));
            String[] split = messages.split(",\\{");
            for (int i=1; i<split.length; i++) {
                split[i] = "{" + split[i];
            }

            for (String s : split) {
                JSONObject stcdjson = JSON.parseObject(s);
                String stcd = stcdjson.getString("stcd");
                stcds.add(stcd);
            }

        }
        Map<String,String> map = new HashMap<>();
            for (String sttp : sttps) {
                for (String stcd : stcds) {
                    String message = null;
                    try {
                        message = JsonUtils.processMessage2(callInterfaceService.getListStationWater(stcd, sttp, "2021-06-03 17:10:00", "2021-06-03 17:20:00"));
                    } catch (Exception e) {
                        map.put(stcd,sttp);
                    }
                    if (message != null) {
                        String[] split1 = message.split(",\\{");
                        split1[0] = "{\"sttp\":\"" + sttp + "\"," + split1[0].replace("{", "");
                        for (int i = 1; i < split1.length; i++) {
                            split1[i] = "{\"sttp\":\"" + sttp + "\"," + split1[i];
                        }
                        for (String s1 : split1) {
                            System.out.println(s1);
                            count++;
                        }
                    }
                }
            }

        System.out.println(stcds.size());
        System.out.println(sttps.size());
        System.out.println(count);
        map.forEach((k,v)->System.out.println(k+"===="+v));
    }

    @Test
    public void test5() {
        CallInterfaceServiceImpl callInterfaceService = new CallInterfaceServiceImpl();
        String provincialData = callInterfaceService.getProvincialData("U0098S00174",
                "V1", "acd19ac7-ef30-46a3-bd99-975bfd3b19f9","U0177Z0001","1", "1");
        System.out.println(provincialData);
    }

    @Test
    public void test6() {
        CallInterfaceServiceImpl callInterfaceService = new CallInterfaceServiceImpl();
        List<String> list = new ArrayList<>();
        String total = JsonUtils.getTotal(callInterfaceService.getListStations(null,null));
        List<String> stcds = new ArrayList<>();
        String messages = JsonUtils.processMessage(callInterfaceService.getListStations(total,"1"));
        String[] split = messages.split(",\\{");
        for (int i=1; i<split.length; i++) {
            split[i] = "{" + split[i];
        }
        for (String s : split) {
            JSONObject stcdjson = JSON.parseObject(s);
            String stcd = stcdjson.getString("stcd");
            stcds.add(stcd);
        }

        for (String stcd : stcds) {
            String message = null;
            try{
                message = JsonUtils.processMessage2(callInterfaceService.getListStationRain(stcd,"2021-06-11 14:00:00", "2021-06-11 14:30:00"));
            }catch (Exception e) {
                list.add(stcd);
            }
            if (message != null) {
                String[] split1 = message.split(",\\{");
                for (int i=1; i<split1.length; i++) {
                    split1[i] = "{"+split1[i];
                }
                for (String s1 : split1) {
                    System.out.println(s1);
                }
            }
        }
    }


    @Test
    public void test9() {
        List<String> sttps = JsonUtils.STTPS;
        CallInterfaceServiceImpl callInterfaceService = new CallInterfaceServiceImpl();
        for (String sttp : sttps) {
            String message = JsonUtils.processMessage2(callInterfaceService.getListStationWater("14734",sttp,"2021-06-08 18:10:00", "2021-06-08 18:20:00"));
            System.out.println(message);
        }
    }

    @Test
    public void test11() throws IOException {
        YHHService yhhService = new YHHServiceImpl();
        System.out.println(yhhService.getMyPorts());
    }

}

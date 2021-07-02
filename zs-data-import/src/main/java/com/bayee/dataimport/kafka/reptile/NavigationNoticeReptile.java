package com.bayee.dataimport.kafka.reptile;

import com.alibaba.fastjson.JSONObject;
import com.bayee.dataimport.hbase.htable.impl.NavigationWarningHTable;
import com.bayee.dataimport.util.HbaseUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutatorParams;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

/**
 * 航行警告
 *
 * @author antuo
 * @since 2021/6/23 13:46
 */
public class NavigationNoticeReptile {

    public static void main(String[] args) throws IOException {
        System.getProperties().setProperty("webdriver.chrome.driver","C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        //开启webDriver进程
        WebDriver webDriver = new ChromeDriver();

//        webDriver.get("https://www.msa.gov.cn/page/openInfo/articleList.do?channelId=8E10EA74-EB9E-4C96-90F8-F891968ADD80&pageSize=1&pageNo=1");

        WebDriverWait wait = new WebDriverWait(webDriver,30);

//        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("main_list_ul")));
//
//        int count = Integer.parseInt(webDriver.findElement(By.className("number")).getText().replace("共", "").replace("条", ""));
//
//        int pageSize = 100;
//
//        int pageNum = count%pageSize==0?count/pageSize:count/pageSize+1;
//
//        List<String> urls = new ArrayList<>();
//
//        for (int i = 1; i <= pageNum; i++) {
//            webDriver.get("https://www.msa.gov.cn/page/openInfo/articleList.do?channelId=8E10EA74-EB9E-4C96-90F8-F891968ADD80&pageSize="+pageSize+"&pageNo="+i);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("main_list_ul")));
//
//            for (int j = 0; j < webDriver.findElements(By.className("common_list")).size(); j++) {
//                urls.add(webDriver.findElements(By.className("common_list")).get(j).findElement(By.tagName("a")).getAttribute("href"));
//            }
//        }
//
//        Configuration conf = HBaseConfiguration.create();
//        conf.set(HConstants.ZOOKEEPER_QUORUM, "manager1.ambari.com:2181");
//        conf.set(HConstants.ZOOKEEPER_CLIENT_PORT, "2181");
//        conf.setInt(HConstants.HBASE_CLIENT_OPERATION_TIMEOUT, 30000);
//        conf.setInt(HConstants.HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD, 30000);
//
//        Connection conn = ConnectionFactory.createConnection(conf);
//        BufferedMutatorParams params = new BufferedMutatorParams(TableName.valueOf("hb_maritime"));
//        params.writeBufferSize(1024 * 1024);
//        BufferedMutator mutator = conn.getBufferedMutator(params);

        //for (String url : urls) {
            String url = "https://www.msa.gov.cn/html/hxaq/aqxx/hxtg/ZJHSJ/20210623/A0B8C203-2532-49DC-A1B7-F88A89692731.html";
            webDriver.get(url);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("articletongji")));

            String id = url.substring(20,50);
            System.out.println(id);

            String title = webDriver.findElement(By.id("title")).getText();
            System.out.println(title);

            String source = webDriver.findElement(By.className("pull-left")).findElements(By.tagName("span")).get(0).getText().substring(3);
            System.out.println(source);

            String pubTime = webDriver.findElement(By.className("pull-left")).findElements(By.tagName("span")).get(2).getText().substring(5);
            System.out.println(pubTime);

            String content = webDriver.findElement(By.id("ch_p")).getAttribute("outerHTML").trim();
            System.out.println(content);

//            NavigationWarningHTable navigationWarningHTable = new NavigationWarningHTable();
//            navigationWarningHTable.setId("5214EEB9-4A8B-403C-835D-D28AA7926410");
//            navigationWarningHTable.setTitle(title);
//            navigationWarningHTable.setData_source(source);
//            navigationWarningHTable.setPub_time(pubTime);
//            navigationWarningHTable.setContent(content);
//
//            mutator.mutate(HbaseUtils.getPut(JSONObject.parseObject(JSONObject.toJSONString(navigationWarningHTable),NavigationWarningHTable.class)));
//            mutator.flush();
//        }

//        mutator.close();
//        conn.close();

        webDriver.close();
        webDriver.quit();
    }

}

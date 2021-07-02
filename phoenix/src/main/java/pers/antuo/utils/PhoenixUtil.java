package pers.antuo.utils;

import com.alibaba.fastjson.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

public class PhoenixUtil {
    private static Properties props;
    private static Connection conn;
    static {
        props = new Properties();
        props.setProperty("url","jdbc:phoenix:manager1:2181");
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        conn = DriverManager.getConnection(props.getProperty("url"));
        return conn;
    }

    public static <T> String createSql(T t , String tableName ,String rowkey) {
        String s1 = JSONObject.toJSONString(t,true);
        JSONObject jsonObject = JSONObject.parseObject(s1);
        Set<String> set = jsonObject.keySet();
        set.remove(rowkey);
        String[] array = set.toArray(new String[0]);
        for (int i = 0; i < array.length; i++) {
            array[i] = "\"info\".\""+array[i]+"\" varchar,";
        }
        String sql = "";
        for (String s : array) {
            sql = sql + s;
        }
        sql = "create table if not exists \"hb_"+tableName+"\"("+sql + "\""+rowkey+"\" varchar primary key)column_encoded_bytes=0";
        return sql;
    }

    public static <T> void createTable(T t,String tableName,String rowKey) throws SQLException, ClassNotFoundException {
        String sql = createSql(t, tableName, rowKey);
        System.out.println(sql);
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.executeUpdate();
    }

    public static void createTable2(String json,String tableName,String rowKey) throws SQLException, ClassNotFoundException {
        String sql = createSql2(json, tableName, rowKey);
        System.out.println(sql);
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.executeUpdate();
    }

    public static String createSql2(String json , String tableName ,String rowkey) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Set<String> set = jsonObject.keySet();
        set.remove(rowkey);
        String[] array = set.toArray(new String[0]);
        for (int i = 0; i < array.length; i++) {
            array[i] = "\"info\".\""+array[i]+"\" varchar,";
        }
        String sql = "";
        for (String s : array) {
            sql = sql + s;
        }
        sql = "create table if not exists \"hb_"+tableName+"\"("+sql + "\""+rowkey+"\" varchar primary key)column_encoded_bytes=0";
        return sql;
    }

    public static void close() throws SQLException {
        if(conn != null) {
            conn.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            createTable2("{\n" +
                    "            \"id\": \"115963\",\n" +
                    "            \"elasticsearch_id\": \"1598361368000\",\n" +
                    "            \"title\": \"292\",\n" +
                    "            \"pub_time\": \"赵飞\",\n" +
                    "            \"data_source\": \"13515805140\",\n" +
                    "            \"files\": \"330921198309240023\",\n" +
                    "            \"content\": \"2e244d3c-c1ef-42e3-aebd-67781cc3ffc9\",\n" +
                    "            \"create_date\": 1594785600000,\n" +
                    "            \"creator\": \"舟山市\",\n" +
                    "            \"update_date\": \"定海区\",\n" +
                    "            \"updator\": \"昌国街道\",\n" +
                    "            \"valid\": \"北园社区\"\n" +
                    "        }","maritime","id");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}



import java.io.IOException;
import java.sql.*;
import java.util.*;


public class PhoenixClient {

    public static String[] getMetaData(String tableName) {
        Connection conn = null;
        Statement sm = null;
        ResultSet rs = null;
        List<String> word = null;
        String str[] = {};
        try {
            conn = JDBCUtils.getMysql01Conn();
            sm = conn.createStatement();
            String sql = "select * from " + tableName + " limit 1";
            rs = sm.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getPrimaryKeys(null, "%", tableName);
            String key = "";
            while (resultSet.next()) {
                key = key + resultSet.getString("COLUMN_NAME");
            }
            System.out.println(key);
            if(key == "") {
                throw new NullPointerException();
            } else {
                word = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    word.add(rsmd.getColumnName(i));
                }
                if (word.contains(key)) {
                    //如果第一个字段不是主键，就把主键字段插入到下标为0的位置
                    if(word.get(0) != key) {
                        word.add(0,key);
                        //如果只有单独主键作为rowkey,就把最后一个主键字段删除
                        if(word.lastIndexOf(key) != 0) {
                            word.remove(word.lastIndexOf(key));
                        }
                    }
                } else {
                    System.out.println(tableName +"不止一个主键");
                }
            }
        } catch (Exception e) {

            System.out.println("-----------------------------"+tableName);
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,sm,rs);
        }
        return word.toArray(str);
    }

    public static String[] getMetaData(String tableName,String colum) {
        Connection conn = null;
        Statement sm = null;
        ResultSet rs = null;
        List<String> word = null;
        String str[] = {};
        try {
            conn = JDBCUtils.getMysql01Conn();
            sm = conn.createStatement();
            String sql = "select * from " + tableName + " limit 1";
            rs = sm.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DatabaseMetaData metaData = conn.getMetaData();
            word = new ArrayList<>();

            word.add(colum);
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                word.add(rsmd.getColumnName(i));
            }

        } catch (Exception e) {
            System.out.println("-----------------------------"+tableName);
        } finally {
            JDBCUtils.close(conn,sm,rs);
        }
        return word.toArray(str);
    }

    //sqoop
    public static String stringSplicingUtil(String[] str) throws Exception {

        String word = "";
        for (String s : str) {
            word += s+",";
        }
        return  word;
    }

    public static String stringSplicing(String[] str) throws Exception {
        str[0] = "\""+str[0]+"\" varchar primary key,";
        for (int i=1; i<str.length-1; i++) {
            str[i] = "\""+"info"+"\""+"."+"\""+str[i]+"\" varchar,";
        }
        str[str.length-1] = "\""+"info"+"\""+"."+"\""+str[str.length-1]+"\" varchar";
        String word = "";
        for (String s : str) {
            word += s;
        }
        return  word;
    }



    public static void createTable(String tableName,String column) {
        Connection conn = null;
        Statement sm = null;
        try {
            String word = stringSplicing(getMetaData(tableName,column));
            System.out.println(word);
            tableName = "\"hb_"+tableName+"\"";

            conn = JDBCUtils.getPhoenixConn();
            sm = conn.createStatement();
            String sql = "create table if not exists "+tableName+"("+word+")column_encoded_bytes=0";
            sm.executeUpdate(sql);
        } catch (Exception e) {

        } finally {
            JDBCUtils.close(conn,sm,null);
        }

    }

    public static List<String> getAllTableNames() {
        Connection conn = null;
        Statement sm = null;
        ResultSet rs = null;
        List<String> tableNames = null;
        try {
            conn = JDBCUtils.getMysql01Conn();
            sm = conn.createStatement();
            String sql = "show tables";
            rs = sm.executeQuery(sql);
            tableNames = new ArrayList<>();
            while (rs.next()) {
                tableNames.add(rs.getString(1));
            }
        } catch (Exception e) {

        } finally {
            JDBCUtils.close(conn,sm,rs);
        }
        return tableNames;
    }

    public static List<String> queryAllTableNames() {
        Connection conn = null;
        Statement sm = null;
        ResultSet rs = null;
        List<String> tableNames = null;
        try {
            conn = JDBCUtils.getMysqlConn();
            sm = conn.createStatement();
            String sql = "select h_tb_name from hb_catlog";
            rs = sm.executeQuery(sql);
            tableNames = new ArrayList<>();
            while (rs.next()) {
                tableNames.add(rs.getString(1));
            }
        } catch (Exception e) {

        } finally {
            JDBCUtils.close(conn,sm,rs);
        }
        return tableNames;
    }


    public static void queryTable(String tableName) {
        Connection conn = null;
        Statement sm = null;
        try {
            tableName = "\""+tableName+"\"";
            conn = JDBCUtils.getPhoenixConn();
            sm = conn.createStatement();
            String sql = "select count(1) from "+tableName;
            ResultSet rs = sm.executeQuery(sql);
            long count = 0;
            while (rs.next()) {
                count = rs.getLong(1);
            }
            System.out.println(tableName+"-----------------"+count);
        } catch (Exception e) {

        } finally {
            JDBCUtils.close(conn,sm,null);
        }

    }


    public static void createTable1(String tableName) {
        Connection conn = null;
        Statement sm = null;
        try {
            tableName = "\"hb_"+tableName+"\"";
            conn = JDBCUtils.getPhoenixConn();
            sm = conn.createStatement();
            String sql = "create table if not exists "+tableName+"("
                    + "\"id\" varchar primary key,"
                    + "\"info\".\"mj\" varchar,"
                    + "\"info\".\"dlmc\" varchar,"
                    + "\"info\".\"xzqmc\" varchar,"
                    + "\"info\".\"nf\" varchar"
                    +")column_encoded_bytes=0";
            sm.executeUpdate(sql);
        } catch (Exception e) {

        } finally {
            JDBCUtils.close(conn,sm,null);
        }

    }

    public static void createTable(String tableName) {
        Connection conn = null;
        Statement sm = null;
        try {
            String word = stringSplicing(getMetaData(tableName));
            System.out.println(word);
            tableName = "\"hb_"+tableName+"\"";

            conn = JDBCUtils.getPhoenixConn();
            sm = conn.createStatement();
            String sql = "create table if not exists "+tableName+"("+word+")column_encoded_bytes=0";
            System.out.println(sql);
            sm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,sm,null);
        }

    }

    public static void main(String[] args) throws IOException {

//        List<String> queryAllTableNames = queryAllTableNames();
//        System.out.println(queryAllTableNames.size());
//
//        List<String> allTableNames = getAllTableNames();
//        System.out.println(allTableNames.size());

//        for (int i = 0; i < allTableNames.size(); i++) {
//            if(queryAllTableNames.contains("hb_"+allTableNames.get(i))) {
//                createTable(allTableNames.get(i));
//                System.out.println("==========================================================");
//            }
//        }

        createTable("ais_t_ship_dt");


//        for (int i = 0; i < allTableNames.size(); i++) {
//            if(queryAllTableNames.contains("hb_"+allTableNames.get(i))) {
//                queryTable("hb_"+allTableNames.get(i));
//                System.out.println("==========================================================");
//                System.out.println();
//                System.out.println();
//
//
//            }
//        }
    }
}

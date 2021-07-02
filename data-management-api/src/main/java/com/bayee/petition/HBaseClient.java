package com.bayee.petition;

import com.bayee.petition.utils.JDBCUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.IOException;
import java.sql.*;
import java.util.*;


/**
 * @Description: TODO
 * @Author YueCang
 * @Date 2021/1/269:03
 */
public class HBaseClient {

    public static Map<String,Object> queryCount(String tableName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String,Object> data = new HashMap<>();
        long count = 0;
        try {
            String sql = "select count(1) from "+JDBCUtils.quotes(tableName);
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
               count = rs.getLong(1);
            }
            data.put("count",count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }
        return data;
    }

    public static Map<String,Object> queryByGroup(String tableName, String word, int pageIndex, int pageSize) {
        List<Object> data;
        Map<String,Object> jsonData = new TreeMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String words = "";
        String[] split = word.split(",");
        for (String s : split) {
            words = words+"\""+s+"\",";
        }
        int lastIndexOf = words.lastIndexOf(",");
        words = words.substring(0,lastIndexOf);
        try {
            String sql = "select count(*) as count,"+words+" from "+JDBCUtils.quotes(tableName)+
                    " group by "+words+" limit "+pageSize+" offset "+((pageIndex-1)*pageSize);
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            data = addData(rs);
            //jsonData.put("count",queryCount(tableName).get("count"));
            jsonData.put("data",data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }
        return jsonData;
    }

    public static Map<String,Object> queryPageByCondition(String tableName,String fields, String symbol, String condition ,int pageIndex, int pageSize) {
        List<Object> data;
        Map<String,Object> jsonData = new TreeMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            //展示的字段
            String field = getField(fields);
            //查询条件
            String factor = getFactor(symbol, condition);
            //拼接sql
            String sql = "";
            String countSql = "";
            if (factor != "") {
                if ("*".equals(fields)) {
                    sql = "select * from " + JDBCUtils.quotes(tableName) + " where " + factor + " limit " + pageSize + " offset " + ((pageIndex - 1) * pageSize);
                } else {
                    sql = "select " + field + " from " + JDBCUtils.quotes(tableName) + " where " + factor + " limit " + pageSize + " offset " + ((pageIndex - 1) * pageSize);
                }
                countSql = "select count(1) from " + JDBCUtils.quotes(tableName) + " where " + factor;
            } else {
                if ("*".equals(fields)) {
                    sql = "select * from " + JDBCUtils.quotes(tableName) + " limit " + pageSize + " offset " + ((pageIndex - 1) * pageSize);
                } else {
                    sql = "select " + field + " from " + JDBCUtils.quotes(tableName)  + " limit " + pageSize + " offset " + ((pageIndex - 1) * pageSize);
                }
                countSql = "select count(1) from " + JDBCUtils.quotes(tableName);
            }
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            data = addData(rs);
            System.out.println(ps);
            ps = conn.prepareStatement(countSql);
            System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            jsonData.put("count",count);
            jsonData.put("data",data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }
        return jsonData;
    }

    public static Map<String,Object> queryByCondition(String tableName,String fields, String symbol, String condition) {
        List<Object> data;
        Map<String,Object> jsonData = new TreeMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //展示的字段
            String field = getField(fields);
            //查询条件
            String factor = getFactor(symbol, condition);
            //拼接sql
            String sql = "";
            if (factor != "") {
                if ("*".equals(fields)) {
                    sql = "select * from " + JDBCUtils.quotes(tableName) + " where " + factor;
                } else {
                    sql = "select " + field + " from " + JDBCUtils.quotes(tableName) + " where " + factor;
                }
            } else {
                if ("*".equals(fields)) {
                    sql = "select * from " + JDBCUtils.quotes(tableName);
                } else {
                    sql = "select " + field + " from " + JDBCUtils.quotes(tableName);
                }
            }
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            //插入数据
            data = addData(rs);
            jsonData.put("count",data.size());
            jsonData.put("data",data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }
        return jsonData;
    }

    private static List<Object> addData(ResultSet rs) throws SQLException {
        List<Object> data = new ArrayList<>();
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        while (rs.next()) {
            Map<String,String> map = new HashMap<>();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                String columName = resultSetMetaData.getColumnName(i);
                String value = rs.getString(i);
                map.put(columName,value);
            }
            data.add(map);
        }
        return data;
    }

    private static String getField(String fields) {
        String[] fieldArray = fields.split(",");
        String field = "";
        for (int i = 0; i < fieldArray.length; i++) {
            fieldArray[i] = JDBCUtils.quotes(fieldArray[i]);
            field = field + fieldArray[i] + ",";
        }
        field = field.substring(0, field.lastIndexOf(","));
        return field;
    }

    private static String getFactor(String symbol, String condition) {
        String term = "";
        if(condition != null) {
            //以逗号区分多个条件
            String[] conditions = condition.split(",");
            for (String s : conditions) {
                //以小数点将条件拆分为字段 条件 值
                String[] terms = s.split("\\.", 3);
                String temp = "";
                if ("like".equals(terms[1])) {
                    terms[0] = JDBCUtils.quotes(terms[0]);
                    terms[2] = "'%"+terms[2]+"%'";
                } else {
                    //判断值是否是数字类型
                    if (NumberUtils.isNumber(terms[2])) {
                        terms[0] = "to_number(" + JDBCUtils.quotes(terms[0]) + ")";
                    } else {
                        terms[0] = JDBCUtils.quotes(terms[0]);
                        if (!"null".equals(terms[2])) {
                            terms[2] = "'" + terms[2] + "'";
                        }
                    }
                }
                for (int i = 0; i < terms.length; i++) {
                    temp = temp + terms[i] + " ";
                }
                term = term + temp.trim() + " " + symbol + " ";
            }
            term = term.substring(0, term.lastIndexOf(symbol)).trim();
        }
        return term;
    }

    public static Map<String,Object> queryByAggregation(String tableName,String fields, int pageIndex, int pageSize) {
        List<Object> data;
        Map<String,Object> jsonData = new TreeMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            /**
             * 展示的字段
             */
            String[] fieldArray = fields.split(",");
            String field = "";
            for (String s : fieldArray) {
                String[] terms = s.split("\\.");
                String temp = terms[0]+"(to_number("+JDBCUtils.quotes(terms[1])+")) as "+JDBCUtils.quotes(terms[2]);
                field = field+temp+",";
            }
            field =  field.substring(0,field.lastIndexOf(","));


            String sql = "";
            sql = "select " +field+" from " + JDBCUtils.quotes(tableName) +" limit "+pageSize+" offset "+((pageIndex-1)*pageSize);
            System.out.println(sql);
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            data = addData(rs);
            jsonData.put("count",data.size());
            jsonData.put("data",data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn,ps,rs);
        }
        return jsonData;
    }

    public static void main(String[] args) throws IOException {
        


//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        int count = 1;
//        String[] title = {"船名"};
//        File file = new File("D:\\test01.xls");
//        try {
//            file.createNewFile();
//            // 2.创建工作簿
//            WritableWorkbook workbook = Workbook.createWorkbook(file);
//
//            // 3.创建工作表
//            WritableSheet sheet = workbook.createSheet("sheet01", 0);
//            Label label = null;
//
//            // 4.添加表头数据
//            for (int i = 0; i < title.length; i++) {
//                label = new Label(i, 0, title[i]);
//                sheet.addCell(label);
//            }
//
//            String sql = "select DISTINCT \"boat_name\" from \"hb_rea_nat_fishing_boat\"";
//            conn = JDBCUtils.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString(1);
//                label = new Label(0,count,name);
//                sheet.addCell(label);
//                count++;
//            }
//            workbook.write();
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtils.close(conn,ps,rs);
//        }
    }

}

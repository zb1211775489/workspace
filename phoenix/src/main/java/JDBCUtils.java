import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    //创建Properties集合
    private static Properties p = new Properties();
    //创建线程绑定
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        //通过流写入配置文件中的数据
        InputStream is = JDBCUtils.class.getResourceAsStream("/jdbc.properties");
        try {
            p.load(is);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {is.close();}catch(Exception e1){}
        }
    }
    public static Connection getPhoenixConn() throws Exception{
        Connection conn = tl.get();
        if(conn == null) {
            Class.forName(p.getProperty("phoenix.driver"));
            conn = DriverManager.getConnection(p.getProperty("phoenix.url"),"","");
            tl.set(conn);
        }
        return conn;
    }

    public static Connection getMysqlConn() throws Exception{
        Connection conn = tl.get();
        if(conn == null) {
            Class.forName(p.getProperty("bayee.jdbc.driverClassName"));
            conn = DriverManager.getConnection(p.getProperty("bayee.jdbc.url"),p.getProperty("bayee.jdbc.username"),p.getProperty("bayee.jdbc.password"));
            tl.set(conn);
        }
        return conn;
    }

    public static Connection getMysql01Conn() throws Exception{
        Connection conn = tl.get();
        if(conn == null) {
            Class.forName(p.getProperty("jdbc.driverClassName"));
            conn = DriverManager.getConnection(p.getProperty("jdbc.url"),p.getProperty("jdbc.username"),p.getProperty("jdbc.password"));
            tl.set(conn);
        }
        return conn;
    }

    public static void close(Connection conn, Statement sm, ResultSet rs) {
        if(rs != null) {try{rs.close();}catch(Exception e){}}
        if(sm != null) {try{sm.close();}catch(Exception e){}}
        if(conn != null) {try{conn.close();tl.remove();}catch(Exception e){}}
    }
}


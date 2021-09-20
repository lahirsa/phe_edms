package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
    public static Connection getConnection() throws Exception, NullPointerException {
//            String driver = "oracle.jdbc.driver.OracleDriver";
////            String url = "jdbc:oracle:thin:@localhost:1521:XE";
////            String username = "BIG";
////            String password = "BIG";
//            String url = "jdbc:oracle:thin:@192.168.210.209:1521:orcl";
//            String username = "DEV_BIG_PORTAL";
//            String password = "Pertaminahe19";
//            Class.forName(driver);
//            Connection conn = DriverManager.getConnection(url, username, password);
           
           InitialContext ctx=new InitialContext();
           DataSource ds = (DataSource)ctx.lookup("jdbc/CIDconn");
           Connection conn = ds.getConnection();
           System.out.println("==============Connection===============");          
           return conn;
       }
}

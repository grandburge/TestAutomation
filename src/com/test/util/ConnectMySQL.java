package com.test.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.*;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
 
public class ConnectMySQL {
 
    public static String driver = "com.mysql.jdbc.Driver";
 
    private static String host;
 
    private static String user;
 
    private static String pwd;
 
    private static Connection conn = null;
 
    private static Statement stmt = null;
 
    public static void connect(String host, String user, String pwd) {
        ConnectMySQL.close();
        ConnectMySQL.host = host;
        ConnectMySQL.user = user;
        ConnectMySQL.pwd = pwd;
    }
 
    public static synchronized List<HashMap<String, String>> query(String sql) {
        return ConnectMySQL.result(sql);
    }
 
    public static synchronized void close() {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    private static void connectMySQL() {
        try {
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://"
                    + host + "?useUnicode=true&characterEncoding=UTF8", user,
                    pwd);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }
 
    private static void statement() {
        if (conn == null) {
            ConnectMySQL.connectMySQL();
        }
        try {
            stmt = (Statement) conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    private static ResultSet resultSet(String sql) {
        ResultSet rs = null;
        if (stmt == null) {
            ConnectMySQL.statement();
        }
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
 
    private static List<HashMap<String, String>> result(String sql) {
        ResultSet rs = ConnectMySQL.resultSet(sql);
        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        try {
            ResultSetMetaData md = rs.getMetaData();
            int cc = md.getColumnCount();
            while (rs.next()) {
                HashMap<String, String> columnMap = new HashMap<String, String>();
                for (int i = 1; i <= cc; i++) {
                    columnMap.put(md.getColumnName(i), rs.getString(i));
                }
                result.add(columnMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    public static void main(String[] args) throws SQLException {
        ConnectMySQL
                .connect("192.168.1.1/test", "test", "test");
        List<HashMap<String, String>> rs = ConnectMySQL
                .query("SELECT * from test");
        System.out.println(rs.get(0).get("test"));
        ConnectMySQL.close();
    }
}

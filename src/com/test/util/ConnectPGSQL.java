package com.test.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.postgresql.*;

public class ConnectPGSQL {
	public static String driver = "org.postgresql.Driver";
	private static String host;
	private static String user;
	private static String pwd;
	private static Connection conn;
	private static Statement statement;
	
	public static void connect(String host,String user,String pwd)
	{
		ConnectPGSQL.close();
		ConnectPGSQL.host=host;
		ConnectPGSQL.user=user;
		ConnectPGSQL.pwd=pwd;
	}
	
	private static void connctPGSQL()
	{
		try
		{
			Class.forName("org.postgresql.Driver").newInstance();
			ConnectPGSQL.conn = DriverManager.getConnection("jdbc:postgresql://"+host+"?useUnicode=true&characterEncoding=UTF8", user,pwd);
		}catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	private  static void setStatement()
	{
		if(conn == null)
		{
			ConnectPGSQL.connctPGSQL();			
		}
		try
		{
			ConnectPGSQL.statement = ConnectPGSQL.conn.createStatement();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close()
	{
		if(statement != null)
		{
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement = null;
		}
		if(conn != null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	private static ResultSet result(String sql)
	{
		if(ConnectPGSQL.statement == null)
		{
			ConnectPGSQL.setStatement();
		}
		
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static List<HashMap<String,String>> query(String sql)
	{
		ResultSet rs = result(sql);
		
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		try
		{
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();			
			while (rs.next())
			{
				HashMap<String,String> rowdata = new HashMap<String,String>();
				for(int i=1;i<=columnCount;i++)
				{
					rowdata.put(md.getColumnName(i), rs.getString(i));
				}
				result.add(rowdata);
			}		
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectPGSQL.connect("127.0.0.1/ricky", "clearsight", "clearsight1");
		List<HashMap<String,String>> result = ConnectPGSQL.query("select * from blogword");
		for(HashMap<String,String> row : result)
		{
			for (String key : row.keySet())
			{
				System.out.println(key+":"+row.get(key));				
			}
			System.out.println("+++++++++++++++++++++++");
				
		}
		
	}

}

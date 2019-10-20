package com.zcc.database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbPool {
	private static ComboPooledDataSource dataSource = null;
	
  static{
	  try {
           dataSource=new ComboPooledDataSource();
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/db201907d?characterEncoding=UTF-8");
			dataSource.setUser("root");
			dataSource.setPassword("");
			dataSource.setInitialPoolSize(20); //初始化连接池的个数
			dataSource.setMinPoolSize(10);  //最小数
			dataSource.setMaxPoolSize(30); //最大数
			dataSource.setAutoCommitOnClose(false);//事务提交方式
			dataSource.setMaxIdleTime(60);//  链接最大等待时间
			dataSource.setIdleConnectionTestPeriod(60);//  线程检查间隔时间
			
			
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
	
	
	public static Connection getConnection(){
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}
}

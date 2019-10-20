package com.zcc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBconnection 
{
	private String url=Config.maps.get("url");
	private String user=Config.maps.get("user");
	private String pwd=Config.maps.get("pwd");

	private Connection con=null;
	
	//加载驱动
	static 
	{
		try{
			Class.forName(Config.maps.get("driver"));
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	//连接服务器
	public DBconnection()
	{
		try{
			this.con=DriverManager.getConnection(url,user,pwd);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//不带参增删改语句
	public int excuteUpdate(String sql)
	{
		Statement stmt=null;
		try{
			Connection conn=DbPool.getConnection();
			stmt=conn.createStatement();
			return stmt.executeUpdate(sql);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try{
				if(stmt!=null) stmt.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	//带参增删改语句
    public int excuteUpdate(String sql,Object[] params)
    {
    	PreparedStatement stmt=null;
    	try{
    		stmt=this.con.prepareStatement(sql);
    		if(null!=params)
    			for(int i=0;i<params.length;i++)
    				stmt.setObject(i+1,params[i]);
    		return stmt.executeUpdate();              //????
    	}catch(SQLException e)
    	{
    		e.printStackTrace();
    	}finally
    	{
    		try{
    			if(null!=stmt) stmt.close();
    		}catch(SQLException e)
    		{
    			e.printStackTrace();
    		}
    	}
    	return 0;
    }
    
    //无参查询 返回结果集
    public ResultSet excuteQuery(String sql)
    {
    	Statement stmt=null;
    	ResultSet rs=null;
    	try{
    		stmt=this.con.createStatement();
    		rs=stmt.executeQuery(sql);
    	}catch(SQLException e)
    	{
    		e.printStackTrace();
    	}finally
    	{
    		try{
    			if(null!=stmt) stmt.close();
    		}catch(SQLException e)
    		{
    			e.printStackTrace();
    		}
    	}
    	return rs;
    }
    
    //带参查询 返回集合
    public List<Object[]> excuteQuery(String sql,Object[] params)
    {
    	PreparedStatement stmt=null;
    	ResultSet rs=null;
    	List<Object[]> result=new ArrayList<Object[]>();
    	try{
    		stmt=this.con.prepareStatement(sql);
    		if(null!=stmt)
    			for(int i=0;i<params.length;i++)
    				stmt.setObject(i+1,params[i] );
    		rs=stmt.executeQuery();
    		int cols=rs.getMetaData().getColumnCount();
    		while(rs.next())
    		{
    			Object[] t=new Object[cols];
    			for(int i=0;i<cols;i++)
    				t[i]=rs.getObject(i+1);
    			result.add(t);
    		}
    	}catch(SQLException e)
    	{
    		e.printStackTrace();
    	}finally
    	{
    		try{
    			if(null!=rs)  rs.close();
    			if(null!=stmt) stmt.close();
    		}catch(SQLException e)
    		{
    			e.printStackTrace();
    		}
    	}
    	//System.out.println("DBconnection "+result);
    	return result;
    }
    
    
    
/*    public List<Object> excuteQuery(String sql,Object[] params)   //换个方式
    {
    	PreparedStatement stmt=null;
    	ResultSet rs=null;
    	List<Object> result=new ArrayList<Object>();
    	try{
    		stmt=this.con.prepareStatement(sql);
    		if(null!=stmt)
    			for(int i=0;i<params.length;i++)
    				stmt.setObject(i+1,params[i] );
    		rs=stmt.executeQuery();
    		int cols=rs.getMetaData().getColumnCount();
    		while(rs.next())
    		{
    			Object[] t=new Object[cols];
    			for(int i=0;i<cols;i++)
    			{
    				t[i]=rs.getObject(i+1);
    			}
    			result.add(t);
    		}
    	}catch(SQLException e)
    	{
    		e.printStackTrace();
    	}finally
    	{
    		try{
    			if(null!=rs)  rs.close();
    			if(null!=stmt) stmt.close();
    		}catch(SQLException e)
    		{
    			e.printStackTrace();
    		}
    	}
    	//System.out.println("DBconnection "+result);
    	return result;
    }*/
    
    
    //反射机制 插入一个对象
    public boolean save(Object object)
    {
    	StringBuffer stf=new StringBuffer(500);
    	Object[] params=ORMUtil.toInsert(object, stf);             //进入ORMUtil，让他来编排SQL语句
    	return excuteUpdate(stf.toString(), params)>0;
    }
    
    //反射机制 查询结果 返回集合
    public List<Object> excuteQuery(String sql,Object[] params,Object object)
    {
    	PreparedStatement stmt=null;
    	ResultSet rs=null;
    	List<Object> result=new ArrayList<Object>();
    	try{
    		stmt=this.con.prepareStatement(sql);
    		if(null!=params)
    			for(int i=0;i<params.length;i++)
    				stmt.setObject(i+1,params[i]);
    		rs=stmt.executeQuery();
    		Class clz=object.getClass();
    		while(rs.next())
    		{
    			result.add(ORMUtil.toObject2(rs, clz));
    		}
    	}catch(SQLException e)
        {
        	e.printStackTrace();
        }finally
        {
        	try{
        		if(rs!=null) rs.close();
        		if(stmt!=null) stmt.close();
        		
        	}catch(SQLException e)
        	{
        		e.printStackTrace();
        	}
        }
        return result;
    }

}


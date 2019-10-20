package com.zcc.database;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public final class ORMUtil 
{
	private ORMUtil(){}
	
	public static Object[] toInsert(Object object , StringBuffer stf)
	{
		if(object==null)  return null;
		Class clz=object.getClass();           //创建object对象类的字节码对象
		String tablename=clz.getSimpleName();     //获取这个类的名字
		stf.append("insert into ").append(tablename);
		stf.append(" ( ");
		Field[] fields=clz.getDeclaredFields();    //获取这个对象里的属性方法
		Object[] params=new Object[fields.length];
		int fieldCount=0;
		try{
			for(int i=0;i<fields.length;i++)
			{
				fields[i].setAccessible(true);         //越过权限，访问private属性
				Object value=fields[i].get(object);     //获取属性对应的值
				if(value==null) continue;
				params[fieldCount++]=value;
				stf.append(fields[i].getName()).append(",");    //获取属性的名字
			}
			int index=stf.lastIndexOf(",");
			stf.replace(index, index+1, ")values(");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		for(int i=0;i<fieldCount;i++)
			stf.append("?,");
		int index=stf.lastIndexOf(",");
		stf.replace(index, index+1, ")");	
		if(fieldCount==params.length)  return params;
		Object[] temp=new Object[fieldCount];
		for(int i=0;i<temp.length;i++)                 //???
			temp[i]=params[i];                   
		return temp;
	}
	
	public static Object toObject(ResultSet rs,Class clz)
	{
		if(null==rs || null==clz ) return null;
		Object object=null;
		try{
			int clos=rs.getMetaData().getColumnCount();     //查询结果的字段数
			object = clz.newInstance();
			for(int i=0;i<clos;i++)
			{
				String fieldName=rs.getMetaData().getColumnName(i+1);  //查询结果字段从1开始，获取对应的值
				Field field=clz.getDeclaredField(fieldName); // 获取类特定的方法，name参数指定了属性的名称,即对应字段名
				String value=rs.getString(fieldName);  //获取查询结果中字段对应的值
				if(value==null) continue;
				field.setAccessible(true);
				if(field.getType()==Integer.class||field.getType()==Integer.TYPE)
					field.set(object, Integer.parseInt(value));
				else if(field.getType()==Double.class||field.getType()==Double.TYPE)
					field.set(object, Double.parseDouble(value));
				else if(field.getType()==Boolean.class||field.getType()==Boolean.TYPE)
					field.set(object, Boolean.parseBoolean(value));
				else if(field.getType()==Float.class||field.getType()==Float.TYPE)
					field.set(object, Float.parseFloat(value));
				else
					field.set(object, value);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return object;
	}
	
	
	public static Object toObject2(ResultSet rs,Class clz)
	{
		if(rs==null||clz==null) return null;
		Object object=null;
		try{
		  Field[] fields=clz.getDeclaredFields();   //获取类中所有的属性(public、protected、default、private)，但不包括继承的属性，返回 Field 对象的一个数组
		 object=clz.newInstance();   //new一个新对象，即往这个对象里插入查询结果
		 for(int i=0;i<fields.length;i++)
		 {
			String fieldName=fields[i].getName();  //获取属性的名字
			String value=rs.getString(fieldName);   //依次获取对应字段的值
			Field field=fields[i];               //获取类中的属性，下面就可以把值付给对应的属性
			if(value==null) continue;
			field.setAccessible(true);
			if(field.getType()==Integer.class||field.getType()==Integer.TYPE)      //??
				field.set(object, Integer.parseInt(value));
			else if(field.getType()==Double.class||field.getType()==Double.TYPE)
				field.set(object, Double.parseDouble(value));
			else if(field.getType()==Boolean.class||field.getType()==Boolean.TYPE)
				field.set(object, Boolean.parseBoolean(value));
			else if(field.getType()==Float.class||field.getType()==Float.TYPE)
				field.set(object, Float.parseFloat(value));
			else
				field.set(object, value);
		 }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return object;
	}

}

package com.zcc.database;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/*public final class Config 
{
	private Config(){}
	public static String driver;
	public static String url;
	public static String user;
	public static String pwd;
	public static int    pwdCount;
	public static HashMap<String,String> tipsByLangues=new HashMap<String,String>();
	
	static{
		Properties prop=new Properties();
		try{
			String path=Config.class.getClassLoader().getResource("").toString().substring(6);
			//System.out.print(path);
			prop.load(new FileInputStream(path+"jdbc.properties"));
			driver=prop.getProperty("driver");
			url=prop.getProperty("url");
			user=prop.getProperty("user");
			pwd=prop.getProperty("pwd");
			String res=prop.getProperty("pwdCount");
			if(res==null || "".equals(res))
				pwdCount=3;
			else pwdCount=Integer.parseInt(res);
			
			Set<Object> keys=prop.keySet();
			for(Object object : keys)
			{
				String key=object.toString();
				if(key.charAt(0)=='e')
					Config.tipsByLangues.put(key, prop.getProperty(key));
			}
		} catch(FileNotFoundException  e1)
		{
			e1.printStackTrace();
		}catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
	}

	
	public static void main(String[] arsgs)
	{
	    Set<String> keys= Config.tipsByLangues.keySet();
	    for (String string : keys) 
	    {
	    	System.out.println(Config.tipsByLangues.get(string));
			
		}

	}
}*/


public final class Config 
{
	private Config(){}
	public static HashMap<String, String> maps=new HashMap<String,String>();
	public static String driver;
	public static String url;
	public static String user;
	public static String pwd;
	public static int    pwdCount;
	public static HashMap<String,String> tipsByLangues=new HashMap<String,String>();
	
	static{
		Properties prop=new Properties();
		try{
			String path=Config.class.getClassLoader().getResource("").toString().substring(6);
			//System.out.print(path);
			prop.load(new FileInputStream(path+"jdbc.properties"));
			driver=prop.getProperty("driver");
			url=prop.getProperty("url");
			user=prop.getProperty("user");
			pwd=prop.getProperty("pwd");
			String res=prop.getProperty("pwdCount");
			if(res==null || "".equals(res))
				pwdCount=3;
			else pwdCount=Integer.parseInt(res);
			
			Set<Object> keys=prop.keySet();
			for(Object object : keys)
			{
				String key=object.toString();
				if(key.charAt(0)=='e')
					Config.tipsByLangues.put(key, prop.getProperty(key));
			}
		} catch(FileNotFoundException  e1)
		{
			e1.printStackTrace();
		}catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
	}

	 
    static{
   	 try { 
   	    String path=Config.class.getClassLoader().getResource("")
   	    		 .toString().substring(6);
   	   // System.out.println(path+"jdbc.xml");
   	  	SAXBuilder builder = new SAXBuilder();  
   		 Document doc = builder.build(new File(path+"jdbc.xml"));  
   		 Element foo = doc.getRootElement();  
   		 List allChildren = foo.getChildren(); 
   		// System.out.println(allChildren.size());
   		 for(int i=0;i<allChildren.size();i++)
   		 {  
   			 Element element=(Element)allChildren.get(i);
   			 String key=element.getName();
   			 String value=element.getText();
   	         maps.put(key, value);
   	    
   		   }
   			} catch (Exception e) { 
   				     e.printStackTrace(); 
   				}
   	 
    }
	
	public static void main(String[] arsgs)
	{
/*	    Set<String> keys= Config.tipsByLangues.keySet();
	    for (String string : keys) 
	    {
	    	System.out.println(Config.tipsByLangues.get(string));
			
		}*/
		//System.out.println(Config.maps.get("url"));
	}
}



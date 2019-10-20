package com.zcc.service;

import java.util.List;

import com.zcc.po.StudentVO;

public interface StudentService 
{
	//注册
	public abstract boolean register(String sno,String sname,String pwd);
	
	//登录
	public abstract StudentVO login(String sno,String pwd);
	
	//更新登陆次数与时间
	public abstract boolean updateCountAndTime(String sno,int count,long time);
	
	//查询全部用户
	public abstract List<Object> queryAccount();
	
	//查询个别用户
	public abstract List<Object> queryAccount(String str);
	
	//删除
	public abstract boolean deleteAccount(String sno);
	
	//修改
	public abstract boolean modifyAccount(String sno,String pwd,String sname);
	
	//设置管理员
	public abstract boolean setManager(String sno);

}

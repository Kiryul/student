package com.zcc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zcc.database.Config;
import com.zcc.database.DBconnection;
import com.zcc.po.Student;
import com.zcc.po.StudentVO;
import com.zcc.service.StudentService;

public class StudentServiceImpl implements StudentService
{

	private DBconnection dbcon=new DBconnection();
	
	
	@Override
	public boolean register(String sno,String sname,String pwd) 
	{
		// TODO Auto-generated method stub
		String sql="select * from Student where sno=?";
		Object[] params={sno};
		List<Object> lists=dbcon.excuteQuery(sql, params,new Student());
		
		if(lists.size()==0)
		{
			Student student = new Student();
			student.setSno(sno);
			student.setSname(sname);
			if("".equals(pwd)) pwd="000";
			student.setPwd(pwd);
			return dbcon.save(student);
		}
		return false;

	}

	@Override
	public StudentVO login(String sno, String pwd) {
		// TODO Auto-generated method stub
		StudentVO vo=new StudentVO();
		String sql="select * from Student where sno=?";
		Object[] params={sno};
		List<Object> lists=dbcon.excuteQuery(sql, params,new Student());
		
		List<Object> lockeduser= new ArrayList<Object>();
		
		if(lists.size()==0)
		{
			vo.setMessage("e100");
			vo.setStudent(null);
			return vo;
		}
		Student student=(Student) lists.get(0);
		long now = System.currentTimeMillis(); 
		long locktime=0;
		long errortime=0;
		if(student.getErrortime()==null || (Long.parseLong(student.getErrortime()))==0) locktime=0;
		else 
		{
			errortime=Long.parseLong(student.getErrortime());
			long daySecond = 60 * 60 * 24 * 1000; 
			locktime=errortime - (errortime + 8 * 3600 * 1000) % daySecond + 1*daySecond;
		}
		if(now<locktime)
		{
			vo.setMessage("e300");
			return vo;
		}
		else
		{
/*			for(int i=0;i<lockeduser.size();i++)
			{
				if()
			}*/
			if(pwd.equals(student.getPwd()))
			{
				 vo.setMessage("e600");
				 vo.setStudent(student);
				 updateCountAndTime(sno, 0, 0);
				 return vo; 
			}
			if(locktime>0) student.setCount(0);
			student.setCount(student.getCount()+1);
			if(student.getCount()==Config.pwdCount) 
			{
				   vo.setMessage("e200");
				   updateCountAndTime(sno,Config.pwdCount,now);
				   lockeduser.add(student);
				   return vo;
			}
			vo.setMessage("e20"+(Config.pwdCount-student.getCount()));
			updateCountAndTime(sno,student.getCount(),0);
			return vo;
		}
		
	}

	@Override
	public boolean updateCountAndTime(String sno, int count, long time) {
		// TODO Auto-generated method stub
		String sql="update student set count=?,errortime=? where sno=?";
		Object[] params={count,time,sno};
		return dbcon.excuteUpdate(sql,params)>0;
	}

	@Override
	public boolean deleteAccount(String sno) {
		// TODO Auto-generated method stub
		String sql="delete from student where sno=?";
		Object[] params={sno};
		return dbcon.excuteUpdate(sql, params)>0;
	}

	@Override
	public boolean modifyAccount(String sno, String sname, String pwd) {
		// TODO Auto-generated method stub
		String sql="update student set pwd=?,sname=? where sno=?";
		if("".equals(sname) || "".endsWith(pwd)) return false; 
		Object[] params={pwd,sname,sno};
		return dbcon.excuteUpdate(sql, params)>0;
	}

	@Override
	public List<Object> queryAccount() 
	{
		// TODO Auto-generated method stub
		String sql="select sno,sname from student";
		List<Object> lists=new ArrayList<Object>();
		lists= dbcon.excuteQuery(sql, null, new Student());
		return lists;
	}

	@Override
	public List<Object> queryAccount(String str) {
		// TODO Auto-generated method stub
		String sql="select * from student where sno=?";
		Object[] params={str};
		List<Object> listsone=new ArrayList<Object>();
		listsone=dbcon.excuteQuery(sql, params,new Student());
		return listsone;
	}

	@Override
	public boolean setManager(String sno) {
		// TODO Auto-generated method stub
		String sql="update student set admin='manager' where sno='"+sno+"'";
		
		return dbcon.excuteUpdate(sql)>1;
	}

}

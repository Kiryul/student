package com.zcc.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.zcc.util.OnlineCounter;

/**
 * Application Lifecycle Listener implementation class OnlineListener
 *
 */
@WebListener
public class OnlineListener implements HttpSessionListener
{

	/**
	 * Default constructor.
	 */
	public OnlineListener()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0)
	{
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		System.out.println(session.getId());
		if (session.getAttribute("stu") == null)
			OnlineCounter.onLineVisitor.incrementAndGet();
		else
			OnlineCounter.onLineVIP.incrementAndGet();
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0)
	{
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		System.out.println(session.getId());
		OnlineCounter.onLineVisitor.decrementAndGet();

	}

}

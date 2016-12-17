package com.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.dao.ActivityTypeDao;
import com.test.model.ActivityType;

/**
 * Hello world!
 *
 */
public class App 
{
	public static String[] ARGS;
    public static void main( String[] args )
    {
    	ARGS = args;
    			
        //System.out.println( "Hello World!" );
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		ActivityTypeDao activityTypeDao = context.getBean(ActivityTypeDao.class);
		
		ActivityType at = new ActivityType();
		at.setName("Running");
		
		activityTypeDao.save(at);
		
		System.out.println(at);
		
		List<ActivityType> list = activityTypeDao.list();
		
		for(ActivityType a : list){
			System.out.println("ActivityType List::"+a);
		}
		//close resources
		context.close();	
	
		int i = 0;
		for (String s:ARGS){
			System.out.println(""+i+"th value = "+ s);
		}
		System.out.println("env = " + System.getProperty("env"));
    }
}

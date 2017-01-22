package com.test;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.dao.ActivityTypeDao;
import com.test.model.ActivityType;
import com.test.process.MessageQueue;
import com.test.process.WorkerThread;
import com.test.process.WriterThread;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	public static Logger logger = LoggerFactory.getLogger(App.class);
	public static String[] ARGS;
    public static void main( String[] args )
    {
    	ARGS = args;
    			
        //System.out.println( "Hello World!" );
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		ActivityTypeDao activityTypeDao = context.getBean(ActivityTypeDao.class);
		List<ActivityType> list = activityTypeDao.list();
		list.forEach(i -> System.out.println("Activity Type Item = " + i));

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateBefore = new Date();
		System.out.println("Before Insert" + dateFormat.format(dateBefore));
		
		int numOfRecords = 100000;
		for (int i = 0 ; i<numOfRecords ; i++){
			ActivityType a = new ActivityType();
			a.setName("Running_" + i);
			activityTypeDao.save(a);
		}
		Date dateAfter = new Date();
		System.out.println("After Insert " + dateFormat.format(dateAfter));
/*		try {
			MessageQueue mq = new MessageQueue();
			WriterThread master = new WriterThread(mq,list);
			WorkerThread wt1 = new WorkerThread(mq,1);
			WorkerThread wt2 = new WorkerThread(mq,2);
			WorkerThread wt3 = new WorkerThread(mq,3);
			
			master.start();
			
			wt1.start();
			wt2.start();
			wt3.start();
			
			master.join();
			if (mq.size()==0){
				//wt1.interrupt();
			}
			wt1.join();
			wt2.join();
			wt3.join();
		}
		catch (Exception e) {
			
		}
*/		
/*		ActivityType at = new ActivityType();
		at.setName("Running");
		
		activityTypeDao.save(at);
		
		System.out.println(at);
		
		
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
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			File file = new File("test_file.json");
			if (file.exists()){
			    final CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, ActivityType.class);
			    List<ActivityType> aList = (List<ActivityType>)mapper.readValue(file, javaType);
				if (aList!=null){
					System.out.println("List size = " + aList.size());
					System.out.println("List all items = " + aList.toString());
					//System.out.println("List 0 item = " + aList.get(0).toString());
					aList.forEach(k -> System.out.println(k.toString()));
				}
			}
			else {
				mapper.writeValue(file, list);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}*/
		
    }
}

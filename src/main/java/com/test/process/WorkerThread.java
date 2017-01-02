package com.test.process;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.test.model.ActivityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerThread extends Thread{
	public static Logger logger = LoggerFactory.getLogger(WorkerThread.class);
	private MessageQueue msgQue;
	private int index;
	
	public WorkerThread(MessageQueue msgQue, int index){
		this.msgQue = msgQue;
		this.index = index;
		this.setName("WorkerThread_"+this.index);
	}

	public void run(){
		while (true){
			while (msgQue.size()>0){
				try {
					synchronized(msgQue){
						Map<String, List<ActivityType>> message = msgQue.remove();
						//String msg_name = message.entrySet()
						for (Entry e : message.entrySet())	{
							List<ActivityType> data = (List<ActivityType>)e.getValue();
							String msg_name = (String)e.getKey();
							logger.debug("" + this.getName() + " : " + msg_name + " : data length = " + data.size());
						}
					}
					logger.debug(this.getName() + " : sleeping");		
					Thread.sleep(500); //1 sec
				}
				catch (Exception e){
					logger.debug(this.getName() + " : Exception 1 : " + e);
				}
			}
			try {
				logger.debug(this.getName() + " : waiting ");
				synchronized(msgQue) {
					msgQue.wait(5000); //5 sec
				}
			}
			catch (Exception e){
				logger.debug(this.getName() + " : Exception 2 : " + e);
			}
		}
	}
}

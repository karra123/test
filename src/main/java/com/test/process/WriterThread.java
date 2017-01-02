package com.test.process;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.test.App;
import com.test.model.ActivityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriterThread extends Thread{
	public static Logger logger = LoggerFactory.getLogger(WriterThread.class);
	private Random random;
	private MessageQueue msgQue;
	private List<ActivityType> data;
	
	private int writeIteration;
	
	public WriterThread(MessageQueue msgQue, List<ActivityType> data){
		this.msgQue = msgQue;
		this.data = data;
		random = new Random();
		writeIteration = 0;
		this.setName("WriteThread");
	}

	public void writeToQueue(){
		int numMessages = random.nextInt(20)+1;
		for (int i=0; i<numMessages; i++){
			String msgName = "Message_"+writeIteration+"_"+i;
			Map<String, List<ActivityType>> message = new HashMap<>();
			List<ActivityType> tempList =data.subList(0, numMessages); 
			message.put(msgName, tempList);	
			msgQue.add(message);
			logger.debug("WriterThread : Message written : " + msgName + " : data length " + tempList.size() );
		}
		writeIteration = writeIteration + 1;
	}
	
	public void run(){
		while (writeIteration <= 10){
			try {
				logger.debug("WriterThread : before synchronized block");
				synchronized(msgQue){
					writeToQueue();
					msgQue.notifyAll();
				}
				logger.debug("WriterThread : sleeping");
				Thread.sleep(1*5*1000); // 1 minute
			}
			catch (Exception e){
				logger.debug("WriterThread : Exception : " + e);
			}
		}
	}
}

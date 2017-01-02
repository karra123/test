package com.test.process;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.test.model.ActivityType;

public class MessageQueue {
	
	private LinkedList<Map<String, List<ActivityType>>> messageQueue;
	
	public MessageQueue(){
		messageQueue = new LinkedList<>();
	}
	
	public void add(Map<String, List<ActivityType>> mObj){
		messageQueue.add(mObj);
	}
	
	public Map<String, List<ActivityType>> remove(){
		return messageQueue.remove();
	}
	
	public int size(){
		return messageQueue.size();
	}

}

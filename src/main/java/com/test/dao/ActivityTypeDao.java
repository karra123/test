package com.test.dao;

import java.util.List;

import com.test.model.ActivityType;

public interface ActivityTypeDao {
	public void save(ActivityType a);	
	public List<ActivityType> list();
}

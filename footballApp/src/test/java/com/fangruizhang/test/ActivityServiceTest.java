package com.fangruizhang.test;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.service.impl.ActivityServiceImpl;

public class ActivityServiceTest extends TestCase {

	@Test
	public void test() throws Throwable{
		ActivityServiceImpl service=new ActivityServiceImpl();
		Activity activity=new Activity();
		activity.setActivityExpense(11);
		activity.setActivityArea("fsdfdsfds");
		activity.setActivityIsneedRight(1);
		activity.setActivityPlayersCnt(2);
		activity.setActivityTime(new Date());
//		 List<Activity> list=service.selectAll();
//		 Activity activity2=service.selectById(2);
//		 System.out.println(activity2.getActivityArea());
//		 for(Activity a:list){
//			 System.out.println(a.getActivityArea());
//		 }
	}
	
}

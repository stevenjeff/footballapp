package com.fangruizhang.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.service.ActivityService;
import com.fangruizhang.util.MybatisUtil;

public class ActivityServiceImpl implements ActivityService {

	SqlSession session=MybatisUtil.getSqlSession();
	ActivityService mapperActivityService = session.getMapper(ActivityService.class);
	public boolean insertValue(Activity activity) {
		boolean bresult=false;
		try {
			mapperActivityService.insertValue(activity);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bresult;
	}

	public boolean deleteById(int id) {
		boolean bresult=false;
		try {
			mapperActivityService.deleteById(id);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bresult;
	}

	public boolean updateValue(Activity activity) {
		boolean bresult=false;
		try {
			mapperActivityService.updateValue(activity);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bresult;
	}

	public Activity selectById(int id) {
		Activity activity=null;
		try {
			activity=(Activity)mapperActivityService.selectById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return activity;
	}

	public List<Activity> selectAll() {
		List<Activity> list =null;
		try {
			list=mapperActivityService.selectAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


}

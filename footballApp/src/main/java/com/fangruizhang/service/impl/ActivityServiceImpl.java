package com.fangruizhang.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.entity.Player;
import com.fangruizhang.service.ActivityService;
import com.fangruizhang.util.MybatisUtil;

public class ActivityServiceImpl implements ActivityService {

	SqlSession session=MybatisUtil.getSqlSession();
	ActivityService mapperActivityService = session.getMapper(ActivityService.class);
	public boolean insertValue(Activity activity) throws Exception{
		boolean bresult=false;
		try {
			mapperActivityService.insertValue(activity);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bresult;
	}

	public boolean deleteById(int id) throws Exception{
		boolean bresult=false;
		try {
			mapperActivityService.deleteById(id);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bresult;
	}

	public boolean updateValue(Activity activity) throws Exception{
		boolean bresult=false;
		try {
			mapperActivityService.updateValue(activity);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bresult;
	}

	public Activity selectById(int id) throws Exception{
		Activity activity=null;
		try {
			activity=(Activity)mapperActivityService.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return activity;
	}

	public List<Activity> selectAll(int beginNum,int endNum) throws Exception{
		List<Activity> list =null;
		try {
			list=mapperActivityService.selectAll(beginNum,endNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	@Override
	public List<Activity> selectPageByPlayerId(int activityPlayerId,int beginNum,int endNum) throws Exception{
		List<Activity> list =null;
		try {
			list=mapperActivityService.selectPageByPlayerId(activityPlayerId,beginNum,endNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	@Override
	public int selectPageCountByPlayerId(int activityPlayerId) throws Exception{
		int size =0;
		try {
			size=mapperActivityService.selectPageCountByPlayerId(activityPlayerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return size;
	}

	@Override
	public int selectAllPageCount() throws Exception {
		int size =0;
		try {
			size=mapperActivityService.selectAllPageCount();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return size;
	}

	@Override
	public Player getPlayer(int activityPlayerId) throws Exception {
		Player player = null;
			try {
				player = mapperActivityService.getPlayer(activityPlayerId);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		return player;
	}


}

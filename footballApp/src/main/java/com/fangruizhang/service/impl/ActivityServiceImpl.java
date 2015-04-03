package com.fangruizhang.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.entity.Player;
import com.fangruizhang.entity.Request;
import com.fangruizhang.entity.Team;
import com.fangruizhang.service.ActivityService;
import com.fangruizhang.util.MybatisUtil;

public class ActivityServiceImpl implements ActivityService {

	public boolean insertValue(Activity activity) throws Exception{
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			mapperActivityService.insertValue(activity);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return bresult;
	}

	public boolean deleteById(int id) throws Exception{
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			mapperActivityService.deleteById(id);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return bresult;
	}

	public boolean updateValue(Activity activity) throws Exception{
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			mapperActivityService.updateValue(activity);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return bresult;
	}

	public Activity selectById(int id) throws Exception{
		Activity activity=null;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			activity=(Activity)mapperActivityService.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return activity;
	}

	public List<Activity> selectAll(int beginNum,int endNum) throws Exception{
		List<Activity> list =null;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			list=mapperActivityService.selectAll(beginNum,endNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<Activity> selectPageByPlayerId(int activityPlayerId,int beginNum,int endNum) throws Exception{
		List<Activity> list =null;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			list=mapperActivityService.selectPageByPlayerId(activityPlayerId,beginNum,endNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public int selectPageCountByPlayerId(int activityPlayerId) throws Exception{
		int size =0;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			size=mapperActivityService.selectPageCountByPlayerId(activityPlayerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return size;
	}

	@Override
	public int selectAllPageCount() throws Exception {
		int size =0;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			size=mapperActivityService.selectAllPageCount();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return size;
	}

	@Override
	public Player getPlayer(int activityPlayerId) throws Exception {
		Player player = null;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
			try {
				player = mapperActivityService.getPlayer(activityPlayerId);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}finally{
				session.close();
			}
		return player;
	}

	@Override
	public Team getTeam(int id) throws Exception {
		Team team = null;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			team = mapperActivityService.getTeam(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
	return team;
	}

	@Override
	public Activity searchActivityWithRequest(int id) throws Exception {
		Activity activity=null;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			activity=(Activity)mapperActivityService.searchActivityWithRequest(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return activity;
	}

	@Override
	public List<Request> getRequests(int activityPlayerId) throws Exception {
		List<Request> list =null;
		SqlSession session=MybatisUtil.getSqlSession();
		ActivityService mapperActivityService = session.getMapper(ActivityService.class);
		try {
			list=mapperActivityService.getRequests(activityPlayerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return list;
	}


}

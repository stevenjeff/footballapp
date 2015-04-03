package com.fangruizhang.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.entity.Player;
import com.fangruizhang.entity.Request;
import com.fangruizhang.entity.Team;
import com.fangruizhang.service.RequestService;
import com.fangruizhang.util.MybatisUtil;

public class RequestServiceImpl implements RequestService {

	public boolean insertValue(Request team)  throws Exception{
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			mapperRequestService.insertValue(team);
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

	public boolean deleteById(int id)  throws Exception{
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			mapperRequestService.deleteById(id);
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

	public boolean updateValue(Request team) throws Exception {
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			mapperRequestService.updateValue(team);
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

	public Request selectById(int id) throws Exception {
		Request request=null;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			request=(Request)mapperRequestService.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return request;
	}

	public List<Request> selectAll() throws Exception {
		List<Request> list =null;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			list=mapperRequestService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public Player getPlayer(int activityPlayerId) throws Exception {
		Player player=null;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			player=(Player)mapperRequestService.getPlayer(activityPlayerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return player;
	}

	@Override
	public Team getTeam(int teamId) throws Exception {
		Team team=null;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			team=(Team)mapperRequestService.getTeam(teamId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return team;
	}

	@Override
	public Activity getActivity(int id) throws Exception {
		Activity activity=null;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			activity=(Activity)mapperRequestService.getActivity(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return activity;
	}

	@Override
	public boolean updateRequestStatus(int requestId, int requestStatus)
			throws Exception {
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			mapperRequestService.updateRequestStatus(requestId, requestStatus);
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

	@Override
	public boolean updateRequestStatusByActivityIdAndType(int activityId,
		int requestType, int requestStatus) throws Exception {
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		RequestService mapperRequestService = session.getMapper(RequestService.class);
		try {
			mapperRequestService.updateRequestStatusByActivityIdAndType(activityId, requestType, requestStatus);
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


}

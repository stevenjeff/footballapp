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

	SqlSession session=MybatisUtil.getSqlSession();
	RequestService mapperRequestService = session.getMapper(RequestService.class);
	public boolean insertValue(Request team)  throws Exception{
		boolean bresult=false;
		try {
			mapperRequestService.insertValue(team);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bresult;
	}

	public boolean deleteById(int id)  throws Exception{
		boolean bresult=false;
		try {
			mapperRequestService.deleteById(id);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bresult;
	}

	public boolean updateValue(Request team) throws Exception {
		boolean bresult=false;
		try {
			mapperRequestService.updateValue(team);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bresult;
	}

	public Request selectById(int id) throws Exception {
		Request request=null;
		try {
			request=(Request)mapperRequestService.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return request;
	}

	public List<Request> selectAll() throws Exception {
		List<Request> list =null;
		try {
			list=mapperRequestService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	@Override
	public Player getPlayer(int activityPlayerId) throws Exception {
		Player player=null;
		try {
			player=(Player)mapperRequestService.getPlayer(activityPlayerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return player;
	}

	@Override
	public Team getTeam(int teamId) throws Exception {
		Team team=null;
		try {
			team=(Team)mapperRequestService.getTeam(teamId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return team;
	}

	@Override
	public Activity getActivity(int id) throws Exception {
		Activity activity=null;
		try {
			activity=(Activity)mapperRequestService.getActivity(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return activity;
	}


}

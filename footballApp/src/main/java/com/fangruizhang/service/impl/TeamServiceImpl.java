package com.fangruizhang.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.entity.Team;
import com.fangruizhang.service.TeamService;
import com.fangruizhang.util.MybatisUtil;

public class TeamServiceImpl implements TeamService {

	SqlSession session=MybatisUtil.getSqlSession();
	TeamService mapperTeamService = session.getMapper(TeamService.class);
	public boolean insertValue(Team team) throws Exception {
		boolean bresult=false;
		try {
			mapperTeamService.insertValue(team);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bresult;
	}

	public boolean deleteById(int id) throws Exception {
		boolean bresult=false;
		try {
			mapperTeamService.deleteById(id);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bresult;
	}

	public boolean updateValue(Team team) throws Exception {
		boolean bresult=false;
		try {
			mapperTeamService.updateValue(team);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bresult;
	}

	public Team selectById(int id) throws Exception {
		Team team=null;
		try {
			team=(Team)mapperTeamService.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return team;
	}

	public List<Team> selectAll() throws Exception {
		List<Team> list =null;
		try {
			list=mapperTeamService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	@Override
	public List<Team> selectPageByPlayerId(int playerId, int beginNum,
			int endNum) throws Exception {
		List<Team> list =null;
		try {
			list=mapperTeamService.selectPageByPlayerId(playerId, beginNum, endNum);
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
			size=mapperTeamService.selectPageCountByPlayerId(activityPlayerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return size;
	}

}

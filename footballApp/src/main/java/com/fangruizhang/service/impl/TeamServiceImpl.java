package com.fangruizhang.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.entity.Player;
import com.fangruizhang.entity.Request;
import com.fangruizhang.entity.Team;
import com.fangruizhang.service.TeamService;
import com.fangruizhang.util.MybatisUtil;

public class TeamServiceImpl implements TeamService {

	public boolean insertValue(Team team) throws Exception {
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			mapperTeamService.insertValue(team);
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

	public boolean deleteById(int id) throws Exception {
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			mapperTeamService.deleteById(id);
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

	public boolean updateValue(Team team) throws Exception {
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			mapperTeamService.updateValue(team);
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

	public Team selectById(int id) throws Exception {
		Team team=null;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			team=(Team)mapperTeamService.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return team;
	}

	public List<Team> selectAll(int playerId) throws Exception {
		List<Team> list =null;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			list=mapperTeamService.selectAll(playerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<Team> selectPageByPlayerId(int playerId, int beginNum,
			int endNum) throws Exception {
		List<Team> list =null;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			list=mapperTeamService.selectPageByPlayerId(playerId, beginNum, endNum);
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
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			size=mapperTeamService.selectPageCountByPlayerId(activityPlayerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return size;
	}

	@Override
	public Player getPlayer(int playerId) throws Exception {
		Player player=null;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			player=(Player)mapperTeamService.getPlayer(playerId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return player;
	}

	@Override
	public List<Team> selectPageAll(int beginNum, int endNum) throws Exception {
		List<Team> list =null;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			list=mapperTeamService.selectPageAll(beginNum, endNum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public int selectPageCountAll() throws Exception {
		int size =0;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			size=mapperTeamService.selectPageCountAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return size;
	}

	@Override
	public Team selectWithRequestById(int id) throws Exception {
		Team team=null;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			team=(Team)mapperTeamService.selectWithRequestById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return team;
	}

	@Override
	public List<Request> getRequests(int teamId) throws Exception {
		List<Request> requests=null;
		SqlSession session=MybatisUtil.getSqlSession();
		TeamService mapperTeamService = session.getMapper(TeamService.class);
		try {
			requests=(List<Request>)mapperTeamService.getRequests(teamId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return requests;
	}

}

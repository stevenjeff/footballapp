package com.fangruizhang.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fangruizhang.entity.Team;
import com.fangruizhang.service.TeamService;
import com.fangruizhang.util.MybatisUtil;

public class TeamServiceImpl implements TeamService {

	SqlSession session=MybatisUtil.getSqlSession();
	TeamService mapperTeamService = session.getMapper(TeamService.class);
	public boolean insertValue(Team team) {
		boolean bresult=false;
		try {
			mapperTeamService.insertValue(team);
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
			mapperTeamService.deleteById(id);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bresult;
	}

	public boolean updateValue(Team team) {
		boolean bresult=false;
		try {
			mapperTeamService.updateValue(team);
		    session.commit();
		    bresult=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bresult;
	}

	public Team selectById(int id) {
		Team team=null;
		try {
			team=(Team)mapperTeamService.selectById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return team;
	}

	public List<Team> selectAll() {
		List<Team> list =null;
		try {
			list=mapperTeamService.selectAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


}

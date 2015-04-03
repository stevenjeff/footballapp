package com.fangruizhang.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fangruizhang.entity.Player;
import com.fangruizhang.service.PlayerService;
import com.fangruizhang.util.MybatisUtil;

public class PlayerServiceImpl implements PlayerService {
	public boolean insertValue(Player player) throws Exception {
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		PlayerService mapperPlayerService = session.getMapper(PlayerService.class);
		try {
			mapperPlayerService.insertValue(player);
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

	public boolean deleteById(int id) throws Exception  {
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		PlayerService mapperPlayerService = session.getMapper(PlayerService.class);
		try {
			mapperPlayerService.deleteById(id);
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

	public boolean updateValue(Player player) throws Exception  {
		boolean bresult=false;
		SqlSession session=MybatisUtil.getSqlSession();
		PlayerService mapperPlayerService = session.getMapper(PlayerService.class);
		try {
			mapperPlayerService.updateValue(player);
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

	public Player selectById(int id) throws Exception  {
		Player player=null;
		SqlSession session=MybatisUtil.getSqlSession();
		PlayerService mapperPlayerService = session.getMapper(PlayerService.class);
		try {
			player=(Player)mapperPlayerService.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return player;
	}

	public List<Player> selectAll() throws Exception  {
		List<Player> list =null;
		SqlSession session=MybatisUtil.getSqlSession();
		PlayerService mapperPlayerService = session.getMapper(PlayerService.class);
		try {
			list=mapperPlayerService.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.fangruizhang.service.PlayerService#selectByName(java.lang.String)
	 */
	@Override
	public Player selectByName(String playerName) throws Exception {
		Player player=null;
		SqlSession session=MybatisUtil.getSqlSession();
		PlayerService mapperPlayerService = session.getMapper(PlayerService.class);
		try {
			player=(Player)mapperPlayerService.selectByName(playerName);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return player;
	}


}

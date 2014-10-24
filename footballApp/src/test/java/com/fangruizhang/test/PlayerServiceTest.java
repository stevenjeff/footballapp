package com.fangruizhang.test;

import java.util.Date;

import org.junit.Test;

import com.fangruizhang.entity.Player;
import com.fangruizhang.service.impl.PlayerServiceImpl;

import junit.framework.TestCase;

public class PlayerServiceTest extends TestCase {

	@Test
	public void testInsert() {
		PlayerServiceImpl serviceImpl= new PlayerServiceImpl();
		Player player=new Player();
		player.setBirthday(new Date());
		player.setQq("234324324");
		player.setCreatetime(new Date());
		player.setMail("a@b.com");
		player.setWeixin("fsdfdsf");
		player.setAttendtimes(2);
		player.setPhone("2312312312");
		player.setPlayerName("stevenzhang");
		player.setAttendtimes(3);
		try {
			serviceImpl.insertValue(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdate(){
		PlayerServiceImpl serviceImpl= new PlayerServiceImpl();
		Player player=new Player();
		player.setBirthday(new Date());
		player.setQq("234324324");
		player.setCreatetime(new Date());
		player.setMail("a@b.com");
		player.setWeixin("fsdfdsf");
		player.setAttendtimes(2);
		player.setPhone("2312312312");
		player.setPlayerName("stevenzhang");
		player.setAttendtimes(3);
		player.setPlayerId(1);
		player.setPlayerName("aadgsdfs");
		try {
			serviceImpl.updateValue(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

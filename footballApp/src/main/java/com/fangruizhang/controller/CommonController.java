package com.fangruizhang.controller;

import javax.servlet.http.HttpSession;

import com.fangruizhang.entity.Player;

public class CommonController {
	public int pagesize=10;
	
	public Player getLoginPlayer(HttpSession session) throws Exception{
		if(session.getAttribute("player")==null)
			throw new Exception("user did not login");
		return (Player)session.getAttribute("player");
	}
	
	public Player getLoginPlayerNoException(HttpSession session) throws Exception{
		return (Player)session.getAttribute("player");
	}

	public int getIdNum(Integer i){
		return i==null?-1:i;
	}
}

package com.fangruizhang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fangruizhang.entity.Player;
import com.fangruizhang.service.impl.PlayerServiceImpl;
import com.fangruizhang.util.ExceptionUtil;

@Controller
public class LoginController {

	@RequestMapping(value="/login.action",method=RequestMethod.POST)
    public ModelAndView login(@RequestParam(value="username", required=false) String username,
    		@RequestParam(value="password", required=false) String password,Model model,HttpSession session){
		PlayerServiceImpl service= new PlayerServiceImpl();
		try {
			Player player = service.selectByName(username);
			if(player==null){
				throw new Exception("username not found");
			}
			if(!player.getPassword().equals(password)){
				throw new Exception("password not correct");
			}
			session.setAttribute("player", player);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror", "错误信息："+ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/index.action");
	}
}

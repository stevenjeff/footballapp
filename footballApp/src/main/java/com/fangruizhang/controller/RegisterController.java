package com.fangruizhang.controller;

import java.util.Date;

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
public class RegisterController {

	@RequestMapping(value="/register.action",method=RequestMethod.POST)
    public ModelAndView register(@RequestParam(value="username", required=false) String username,
    		@RequestParam(value="password", required=false) String password,
    		@RequestParam(value="sex", required=false) int sex,
    		@RequestParam(value="birthday", required=false) Date birthday,
    		@RequestParam(value="phone", required=false) String phone,
    		@RequestParam(value="qq", required=false) String qq,
    		@RequestParam(value="weixin", required=false) String weixin,
    		@RequestParam(value="mail", required=false) String mail,
    		Model model) {
		boolean isSuccess=false;
		PlayerServiceImpl serviceImpl= new PlayerServiceImpl();
		Player player=new Player();
		player.setPlayerName(username);
		player.setSex(sex);
		player.setPassword(password);
		player.setBirthday(birthday);
		player.setQq(qq);
		player.setCreatetime(new Date());
		player.setMail(mail);
		player.setWeixin(weixin);
		player.setPhone(phone);
		try {
			isSuccess=serviceImpl.insertValue(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("globalerror", "错误信息："+ExceptionUtil.handlerException(e));
		}
		if(!isSuccess){
			return new ModelAndView("forward:/register.jsp");
		}
        return new ModelAndView("forward:/index.jsp");
    }

}

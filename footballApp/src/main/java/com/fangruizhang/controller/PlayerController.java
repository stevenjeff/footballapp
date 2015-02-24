package com.fangruizhang.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fangruizhang.entity.Player;
import com.fangruizhang.service.impl.PlayerServiceImpl;
import com.fangruizhang.util.ExceptionUtil;

@Controller
public class PlayerController extends CommonController {

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
			return new ModelAndView("forward:/WEB-INF/views/register.jsp");
		}
        return new ModelAndView("forward:/index.jsp");
    }
	
	@ResponseBody
	@RequestMapping(value="/playerDetail.action",method=RequestMethod.GET)
    public Player playerDetail(@RequestParam(value="playerId", required=false) Integer playerId,
    		Model model) {
		PlayerServiceImpl serviceImpl= new PlayerServiceImpl();
		Player player = null;
		try {
			player=serviceImpl.selectById(playerId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("globalerror", "错误信息："+ExceptionUtil.handlerException(e));
		}
        return player;
    }
	
	@RequestMapping(value="/viewPlayer.action",method=RequestMethod.GET)
    public ModelAndView viewPlayer(@RequestParam(value="playerId", required=false) Integer playerId,
    		Model model,HttpSession session) {
		try {
			if(this.getLoginPlayerNoException(session)==null||this.getLoginPlayerNoException(session).getPlayerId()!=playerId){
				return new ModelAndView("forward:/WEB-INF/views/playerDetail.jsp?playerId="+playerId+"&viewModel=view");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("globalerror", "错误信息："+ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/WEB-INF/views/playerDetail.jsp?playerId="+playerId+"&viewModel=edit");
    }
	
	@RequestMapping(value="/forwardRegister.action",method=RequestMethod.GET)
    public ModelAndView forwardRegister() {
		return new ModelAndView("forward:/WEB-INF/views/register.jsp");
    }
	
	@RequestMapping(value="/help.action",method=RequestMethod.GET)
    public ModelAndView forwardHelp() {
		return new ModelAndView("forward:/WEB-INF/views/help.jsp");
    }
	
	@RequestMapping(value="/logout.action",method=RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("forward:/index.action");
    }
}

package com.fangruizhang.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
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
import com.fangruizhang.util.PageUtil;

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
    		@RequestParam(value="birthday", required=false) String birthday,
    		@RequestParam(value="phone", required=false) String phone,
    		@RequestParam(value="qq", required=false) String qq,
    		@RequestParam(value="weixin", required=false) String weixin,
    		@RequestParam(value="mail", required=false) String mail,
    		Model model) {
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd");
		boolean isSuccess=false;
		try {
			PlayerServiceImpl serviceImpl= new PlayerServiceImpl();
			Player player=new Player();
			player.setPlayerName(username);
			player.setSex(sex);
			player.setPassword(password);
			player.setBirthday(dateformat.parse(birthday));
			player.setQq(qq);
			player.setCreatetime(new Date());
			player.setMail(mail);
			player.setWeixin(weixin);
			player.setPhone(phone);
			player.setAttendtimes(0);
			player.setAttendsuccescnt(0);
			player.setInsertDate(new Date());
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
    public ModelAndView viewPlayer(@RequestParam(value="id", required=false) Integer playerId,
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
	
	@ResponseBody
	@RequestMapping(value = "/searchPlayerAllJson.action", method = RequestMethod.GET)
	public List<Player> searchPlayerAllJson(
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			Model model, HttpSession session) {
		PlayerServiceImpl serviceImpl= new PlayerServiceImpl();
		List<Player> list = null;
		int beginNum = 0;
		try {
			beginNum = (pageNum - 1) * pageSize >= 0 ? (pageNum - 1) * pageSize
					: 0;
			list = serviceImpl.selectAll(beginNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return list;
	}
	
	@RequestMapping(value = "/playerSearchAll.action")
	public ModelAndView teamSearchAll(Model model, HttpSession session) {
		PlayerServiceImpl serviceImpl= new PlayerServiceImpl();
		try {
			int pageSize =pagesize;
			Integer recordCount = serviceImpl.selectAllCountAll();
			Integer pageCount = (recordCount + pageSize - 1) / pageSize;
			StringBuffer dislayCols=new StringBuffer();
			dislayCols.append("{'球员名称': 'playerName',");
			dislayCols.append("'球员性别': 'sex',");
			dislayCols.append("'生日': 'birthday',");
			dislayCols.append("'出场次数': 'attendtimes'}");
			PageUtil.initPageMode(model, recordCount, pageCount, dislayCols, "searchPlayerAllJson.action", "所有球员", "playerId", "", "viewPlayer.action","","");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/WEB-INF/views/playerList.jsp");
	}
	
	@RequestMapping(value="/forwardRegister.action",method=RequestMethod.GET)
    public ModelAndView forwardRegister() {
		return new ModelAndView("forward:/WEB-INF/views/register.jsp");
    }
	
	@RequestMapping(value="/playerViewAndEdit.action",method=RequestMethod.GET)
    public ModelAndView playerViewAndEdit() {
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

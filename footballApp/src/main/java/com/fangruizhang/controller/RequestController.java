package com.fangruizhang.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.entity.Request;
import com.fangruizhang.entity.Team;
import com.fangruizhang.service.ActivityService;
import com.fangruizhang.service.RequestService;
import com.fangruizhang.service.TeamService;
import com.fangruizhang.service.impl.ActivityServiceImpl;
import com.fangruizhang.service.impl.RequestServiceImpl;
import com.fangruizhang.service.impl.TeamServiceImpl;
import com.fangruizhang.util.EnumNames;
import com.fangruizhang.util.ExceptionUtil;

@Controller
public class RequestController extends CommonController {

	@RequestMapping(value = "/activityRequestCreate.action", method = RequestMethod.POST)
	public ModelAndView activityRequestCreate(
			@RequestParam(value = "activityId", required = false) Integer activityId,
			@RequestParam(value = "requestTeamId", required = false) Integer requestTeamId,
			@RequestParam(value = "requestMsg", required = false) String requestMsg,
			Model model, HttpSession session) {
		RequestService service = new RequestServiceImpl();
		Request request = new Request();
		try {
			request.setRequestPlayer(this.getLoginPlayer(session));
			request.setRequestMsg(requestMsg);
			request.setRequestTime(new Date());
			Activity requestActivity = service.getActivity(activityId);
			request.setRequestType(requestActivity.getActivityType());
			request.setRequestActivity(requestActivity);
			request.setRequestStatus(1+"");
			Team againstTeam = new Team();
			againstTeam.setTeamId(-1);
			if(requestActivity.getActivityType().equals(EnumNames.RequestTypeEnum.TeamActivityRequest.getCode()+"")){
				againstTeam.setTeamId(requestTeamId);
			}
			request.setAgainstTeam(againstTeam);
			service.insertValue(request);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/index.action");
	}
	
	@RequestMapping(value = "/teamPlayerRequestCreate.action", method = RequestMethod.POST)
	public ModelAndView teamPlayerRequestCreate(
			@RequestParam(value = "activityId", required = false) Integer activityId,
			@RequestParam(value = "requestTeamId", required = false) Integer requestTeamId,
			@RequestParam(value = "requestMsg", required = false) String requestMsg,
			Model model, HttpSession session) {
		RequestService service = new RequestServiceImpl();
		Request request = new Request();
		try {
			request.setRequestPlayer(this.getLoginPlayer(session));
			request.setRequestMsg(requestMsg);
			request.setRequestTime(new Date());
			Activity requestActivity = service.getActivity(activityId);
			request.setRequestType(EnumNames.RequestTypeEnum.PlayerTeamRequest.getCode()+"");
			request.setRequestActivity(requestActivity);
			request.setRequestStatus(1+"");
			Team againstTeam = new Team();
			againstTeam.setTeamId(-1);
			Team requestTeam = new Team();
			requestTeam.setTeamId(requestTeamId);
			request.setAgainstTeam(againstTeam);
			request.setRequestTeam(requestTeam);
			service.insertValue(request);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/teamManageSearchBySinglePlayer.action");
	}
	
	@RequestMapping(value = "/applyActivity.action")
	public ModelAndView applyActivity(
			@RequestParam(value = "activityId", required = false) Integer activityId,
			Model model, HttpSession session) {
		ActivityService activityService=new ActivityServiceImpl();
		try {
			Activity activity = activityService.selectById(activityId);
			if(activity==null){
				throw new Exception("match record not found");
			}
			model.addAttribute("activityId", activityId);
			model.addAttribute("activityType", activity.getActivityType());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/applyActivity.jsp");
	}
	
	@ResponseBody
	@RequestMapping(value = "/applyActivityValidate.action", method = RequestMethod.GET)
	public int applyActivityValidate(
			@RequestParam(value = "activityId", required = false) Integer activityId,
			Model model, HttpSession session) {
		ActivityService activityService=new ActivityServiceImpl();
		TeamService teamService = new TeamServiceImpl();
		List<Team> list =null;
		try {
			Activity activity = activityService.selectById(activityId);
			if(activity==null){
				throw new Exception("match record not found");
			}
			if(activity.getActivityPlayer().getPlayerId()==getLoginPlayer(session).getPlayerId()&&activity.getActivityType().equals(EnumNames.ActivityTypeEnum.TeamActivity.getCode()+"")){
				return 2;
			}
			if(activity.getActivityType().equals(EnumNames.ActivityTypeEnum.TeamActivity.getCode()+"")){
				list = teamService.selectAll(this.getLoginPlayer(session).getPlayerId());
				if(list==null||list.size()==0){
					return 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return 0;
	}
}

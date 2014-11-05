package com.fangruizhang.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.entity.Request;
import com.fangruizhang.entity.Team;
import com.fangruizhang.service.RequestService;
import com.fangruizhang.service.impl.RequestServiceImpl;
import com.fangruizhang.util.EnumNames;
import com.fangruizhang.util.ExceptionUtil;

@Controller
public class RequestController extends CommonController {

	@RequestMapping(value = "/activityRequestCreate.action", method = RequestMethod.POST)
	public ModelAndView activityRequestCreate(
			@RequestParam(value = "activityId", required = false) Integer activityId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
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
			if(requestActivity.getActivityType().equals("1")){
				againstTeam.setTeamId(teamId);
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
}

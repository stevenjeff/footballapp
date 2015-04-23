package com.fangruizhang.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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
			if(Integer.valueOf(requestActivity.getActivityType())==EnumNames.ActivityTypeEnum.PlayerActivity.getCode()){
				request.setRequestType(EnumNames.RequestTypeEnum.PlayerActivityRequest.getCode()+"");
			}else{
				request.setRequestType(EnumNames.RequestTypeEnum.TeamActivityRequest.getCode()+"");
			}
			request.setRequestActivity(requestActivity);
			request.setRequestStatus(EnumNames.RequestStatusEnum.ApplyStatus.getCode()+"");
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
	
	@RequestMapping(value = "/playerTeamRequestCreate.action", method = RequestMethod.POST)
	public ModelAndView playerTeamRequestCreate(
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "requestMsg", required = false) String requestMsg,
			Model model, HttpSession session) {
		RequestService service = new RequestServiceImpl();
		Request request = new Request();
		try {
			request.setRequestPlayer(this.getLoginPlayer(session));
			request.setRequestMsg(requestMsg);
			request.setRequestTime(new Date());
			request.setRequestType(EnumNames.RequestTypeEnum.PlayerTeamRequest.getCode()+"");
			request.setRequestStatus(1+"");
			Team againstTeam = new Team();
			againstTeam.setTeamId(-1);
			Team requestTeam = new Team();
			requestTeam.setTeamId(teamId);
			request.setAgainstTeam(againstTeam);
			request.setRequestTeam(requestTeam);
			service.insertValue(request);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/teamSearchAll.action");
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
				"forward:/WEB-INF/views/applyActivity.jsp");
	}
	
	@RequestMapping(value = "/applyTeam.action")
	public ModelAndView applyTeam(
			@RequestParam(value = "teamId", required = false) Integer teamId,
			Model model, HttpSession session) {
		TeamService teamService=new TeamServiceImpl();
		try {
			Team team = teamService.selectById(teamId);
			if(team==null){
				throw new Exception("team record not found");
			}
			model.addAttribute("teamId", teamId);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/WEB-INF/views/applyTeam.jsp");
	}
	@RequestMapping(value = "/updateRequestStatus.action")
	public ModelAndView updateRequestStatus(
			@RequestParam(value = "requestId", required = true) Integer requestId,
			@RequestParam(value = "activityId", required = true) Integer activityId,
			@RequestParam(value = "requestStatus", required = true) Integer requestStatus,
			Model model, HttpSession session) {
		RequestService requestService=new RequestServiceImpl();
		ActivityService service = new ActivityServiceImpl();
		Activity activity = null;
		Boolean issucess = false;
		try {
			issucess = requestService.updateRequestStatus(requestId, requestStatus);
			if(issucess==null){
				throw new Exception("request status update failed");
			}
			activity = service.selectById(activityId);
			int actitvityPlayerId = activity.getActivityPlayer().getPlayerId();
			if(this.getLoginPlayerNoException(session)==null||this.getLoginPlayerNoException(session).getPlayerId().intValue()!=actitvityPlayerId){
				return new ModelAndView("forward:/WEB-INF/views/activityDetail.jsp?id="+activityId+"&viewModel=view");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/WEB-INF/views/activityDetail.jsp?id="+activityId+"&viewModel=edit");
	}
	public static final int RECORD_NOT_FOUND=-1;
	public static final int REQUESTPERSON_CANNOT_BE_SAME=2;
	public static final int ALREADYAPPLIED=3;
	@ResponseBody
	@RequestMapping(value = "/applyActivityValidate.action", method = RequestMethod.GET)
	public int applyActivityValidate(
			@RequestParam(value = "activityId", required = true) Integer activityId,
			@RequestParam(value = "playerId", required = true) Integer playerId,
			Model model, HttpSession session) {
		ActivityService activityService=new ActivityServiceImpl();
		TeamService teamService = new TeamServiceImpl();
		RequestService requestService = new RequestServiceImpl();
		List<Team> list =null;
		List<Request> requestList = null;
		try {
			Activity activity = activityService.selectById(activityId);
			requestList = requestService.selectByActivityAndPlayer(activityId, playerId);
			if(activity==null){
				return RECORD_NOT_FOUND;
			}
			if(activity.getActivityPlayer().getPlayerId()==getLoginPlayer(session).getPlayerId()&&activity.getActivityType().equals(EnumNames.ActivityTypeEnum.TeamActivity.getCode()+"")){
				return REQUESTPERSON_CANNOT_BE_SAME;
			}
			if(!CollectionUtils.isEmpty(requestList)){
				
			}
			if(activity.getActivityType().equals(EnumNames.ActivityTypeEnum.TeamActivity.getCode()+"")){
				list = teamService.selectAll(this.getLoginPlayer(session).getPlayerId());
				if(list==null||list.size()==0){
					return 1;
				}
			}
			if(!activity.getActivityStatus().equals("3")){
				return 3;
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return 0;
	}
	
	@ResponseBody
	@RequestMapping(value = "/applyTeamValidate.action", method = RequestMethod.GET)
	public int applyTeamValidate(
			@RequestParam(value = "teamId", required = false) Integer teamId,
			Model model, HttpSession session) {
		TeamService teamService = new TeamServiceImpl();
		try {
			Team team = teamService.selectById(teamId);
			if(team==null){
				throw new Exception("team record not found");
			}
			if(!team.getTeamStatus().equals("3")){
				return 3;
			}
			if(team.getCreator().getPlayerId()==getLoginPlayer(session).getPlayerId()){
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return 0;
	}
}

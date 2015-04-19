/*
 *  Copyright (c) 2014 Transaction Processing Specialist Inc., A Xerox Company
 *  All rights reserved.
 *
 *  This item contains confidential and trade secret information and may not be
 *  transferred, published, disclosed, reproduced, or used by any party without
 *  the express written permission of TPS, Inc.
 */
package com.fangruizhang.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import com.fangruizhang.entity.Team;
import com.fangruizhang.service.ActivityService;
import com.fangruizhang.service.RequestService;
import com.fangruizhang.service.TeamService;
import com.fangruizhang.service.impl.ActivityServiceImpl;
import com.fangruizhang.service.impl.RequestServiceImpl;
import com.fangruizhang.service.impl.TeamServiceImpl;
import com.fangruizhang.util.EnumNames;
import com.fangruizhang.util.EnumNames.RequestStatusEnum;
import com.fangruizhang.util.ExceptionUtil;
import com.fangruizhang.util.PageUtil;

/**
 * @ClassName: ActivityController
 * @Description: TODO
 * @author Steven Zhang
 * @date Oct 16, 2014 4:34:46 PM
 *
 */

@Controller
public class ActivityController extends CommonController {

	@RequestMapping(value = "/activityCreate.action", method = RequestMethod.POST)
	public ModelAndView createActivity(
			@RequestParam(value = "activityArea", required = false) String activityArea,
			@RequestParam(value = "activityTime", required = false) String activityTime,
			@RequestParam(value = "activityType", required = false) String activityType,
			@RequestParam(value = "activityExpense", required = false) Integer activityExpense,
			@RequestParam(value = "activityPlayersCnt", required = false) Integer activityPlayersCnt,
			@RequestParam(value = "isneedright", required = false) Integer isneedright,
			@RequestParam(value = "activityTeam", required = false) Integer activityTeam,
			Model model, HttpSession session) {
		ActivityService service = new ActivityServiceImpl();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			Activity activity = new Activity();
			activity.setActivityArea(activityArea);
			activity.setActivityExpense(activityExpense == null ? 0
					: activityExpense);
			activity.setActivityIsneedRight(isneedright == null ? 0
					: isneedright);
			activity.setActivityPlayersCnt(activityPlayersCnt);
			activity.setActivityType(activityType);
			activity.setActivityTime(dateformat.parse(activityTime));
			activity.setActivityPlayer(this.getLoginPlayer(session));
			Team team = new Team();
			if(activityTeam!=null){
				team.setTeamId(activityTeam);
			}else{
				team.setTeamId(-1);
			}
			activity.setActivityTeam(team);
			activity.setActivityOpponentTeamId(-1);
			service.insertValue(activity);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/activityManageSearchBySinglePlayer.action");
	}
	
	@RequestMapping(value = "/activityUpdate.action", method = RequestMethod.POST)
	public ModelAndView updateActivity(
			@RequestParam(value = "activityId", required = true) Integer activityId,
			@RequestParam(value = "activityArea", required = false) String activityArea,
			@RequestParam(value = "activityTime", required = false) String activityTime,
			@RequestParam(value = "activityType", required = false) String activityType,
			@RequestParam(value = "activityExpense", required = false) Integer activityExpense,
			@RequestParam(value = "activityPlayersCnt", required = false) Integer activityPlayersCnt,
			@RequestParam(value = "isneedright", required = false) Integer isneedright,
			@RequestParam(value = "activityTeam", required = false) Integer activityTeam,
			@RequestParam(value = "playerSel", required = false) String[] playerSel,
			@RequestParam(value = "teamSel", required = false) String[] teamSel,
			Model model, HttpSession session) {
		ActivityService service = new ActivityServiceImpl();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			Activity activity = new Activity();
			activity.setActivityId(activityId);
			activity.setActivityArea(activityArea);
			activity.setActivityExpense(activityExpense == null ? 0
					: activityExpense);
			activity.setActivityIsneedRight(isneedright == null ? 0
					: isneedright);
			activity.setActivityPlayersCnt(activityPlayersCnt);
			activity.setActivityType(activityType);
			activity.setActivityTime(dateformat.parse(activityTime));
			activity.setActivityPlayer(this.getLoginPlayer(session));
			Team team = new Team();
			if(activityTeam!=null){
				team.setTeamId(activityTeam);
			}else{
				team.setTeamId(-1);
			}
			activity.setActivityTeam(team);
			activity.setActivityOpponentTeamId(-1);
			
			RequestService requestService = new RequestServiceImpl();
			requestService.updateRequestStatusByActivityIdAndType(activityId, EnumNames.RequestTypeEnum.PlayerActivityRequest.getCode(), EnumNames.RequestStatusEnum.ApplyStatus.getCode());
			if(playerSel!=null&&playerSel.length!=0){
				String[] strs = null;
				for(String selVal:playerSel){
					strs=selVal.split(":");
					requestService.updateRequestStatus(Integer.parseInt(strs[2]), RequestStatusEnum.ApproveStatus.getCode());
				}
			}
			requestService.updateRequestStatusByActivityIdAndType(activityId, EnumNames.RequestTypeEnum.TeamActivityRequest.getCode(), EnumNames.RequestStatusEnum.ApplyStatus.getCode());
			if(teamSel!=null&&teamSel.length!=0){
				String[] strs = null;
				for(String selVal:teamSel){
					strs=selVal.split(":");
					requestService.updateRequestStatus(Integer.parseInt(strs[2]), RequestStatusEnum.ApproveStatus.getCode());
				}
			}
			service.updateValue(activity);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/activityManageSearchBySinglePlayer.action");
	}

	@RequestMapping(value = "/activityManageSearchBySinglePlayer.action")
	public ModelAndView playerActivitySearchManage(Model model,
			HttpSession session) {
		ActivityService service = new ActivityServiceImpl();
		try {
			int pageSize = pagesize;
			Integer recordCount = service
					.selectPageCountByPlayerId(getLoginPlayer(session)
							.getPlayerId());
			Integer pageCount = (recordCount + pageSize - 1) / pageSize;
			StringBuffer dislayCols = new StringBuffer();
			dislayCols.append("{'比赛地点': 'activityArea',");
			dislayCols.append("'比赛时间': 'activityTime',");
			dislayCols.append("'比赛规模（几人制）': 'activityPlayersCnt',");
			dislayCols.append("'创建人': 'activityPlayer.playerName',");
			dislayCols.append("'比赛类型': 'activityType',");
			dislayCols.append("'状态': 'activityStatus'}");
			PageUtil.initPageMode(model, recordCount, pageCount, dislayCols,
					"searchActivityByLoginPlayerJson.action", "我的比赛计划",
					"activityId", "deleteActivityById.action",
					"forwardActivityDetail.action","","");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/WEB-INF/views/activityManage.jsp");
	}

	@RequestMapping(value = "/index.action")
	public ModelAndView playerActivitySearchAllManage(Model model,
			HttpSession session) {
		ActivityService service = new ActivityServiceImpl();
		try {
			int pageSize = pagesize;
			Integer recordCount = service.selectAllPageCount();
			Integer pageCount = (recordCount + pageSize - 1) / pageSize;
			StringBuffer dislayCols = new StringBuffer();
			dislayCols.append("{'比赛地点': 'activityArea',");
			dislayCols.append("'比赛时间': 'activityTime',");
			dislayCols.append("'比赛规模（几人制）': 'activityPlayersCnt',");
			dislayCols.append("'创建人': 'activityPlayer.playerName',");
			dislayCols.append("'比赛类型': 'activityType',");
			dislayCols.append("'状态': 'activityStatus'}");
			String applyUrl = "";
			if(this.getLoginPlayerNoException(session)!=null){
				applyUrl = "applyActivity.action";
			}
			
			PageUtil.initPageMode(model, recordCount, pageCount, dislayCols,
					"searchAllActivityJson.action", "比赛计划", "activityId",
					"", "activityDetail.action",applyUrl,"");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/index.jsp");
	}

	@ResponseBody
	@RequestMapping(value = "/searchAllActivityJson.action", method = RequestMethod.GET)
	public List<Activity> searchAllActivityJson(
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			Model model, HttpSession session) {
		ActivityService service = new ActivityServiceImpl();
		List<Activity> list = null;
		int beginNum = 0;
		try {
			beginNum = (pageNum - 1) * pageSize >= 0 ? (pageNum - 1) * pageSize
					: 0;
			list = service.selectAll(beginNum, pageSize);
			PageUtil.setColValue(list, "ActivityType", EnumNames.activityTypes);
			PageUtil.setColValue(list, "ActivityStatus", EnumNames.requestStatuss);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/searchActivityByLoginPlayerJson.action", method = RequestMethod.GET)
	public List<Activity> searchByLoginPlayerJson(
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			Model model, HttpSession session) {
		ActivityService service = new ActivityServiceImpl();
		List<Activity> list = null;
		int beginNum = 0;
		try {
			beginNum = (pageNum - 1) * pageSize >= 0 ? (pageNum - 1) * pageSize
					: 0;
			list = service.selectPageByPlayerId(getLoginPlayer(session)
					.getPlayerId(), beginNum, pageSize);
			PageUtil.setColValue(list, "ActivityType", EnumNames.activityTypes);
			PageUtil.setColValue(list, "ActivityStatus", EnumNames.requestStatuss);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return list;
	}

	@RequestMapping(value = "/deleteActivityById.action")
	public ModelAndView deleteActivityById(Model model,
			@RequestParam(value = "id", required = true) int id) {
		ActivityService service = new ActivityServiceImpl();
		try {
			service.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/activityManageSearchBySinglePlayer.action");
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRelativeTeamJson.action", method = RequestMethod.GET)
	public List<Team> getRelativeTeamJson(
			Model model, HttpSession session) {
		TeamService service = new TeamServiceImpl();
		List<Team> list = null;
		try {
			list = service.selectAll(getLoginPlayer(session).getPlayerId());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRelativeTeamDetailJson.action", method = RequestMethod.GET)
	public List<Team> getRelativeTeamDetailJson(
			Model model, HttpSession session,@RequestParam(value = "activityId", required = true) int activityId) {
		TeamService teamService = new TeamServiceImpl();
		ActivityService activityService = new ActivityServiceImpl();
		List<Team> list = null;
		Activity activity = null;
		try {
			activity = activityService.selectById(activityId);
			list=teamService.selectAll(activity.getActivityPlayer().getPlayerId());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getActivityDetailById.action", method = RequestMethod.GET)
	public Activity getActivityDetailById(Model model,
			@RequestParam(value = "activityId", required = true) int activityId) {
		ActivityService service = new ActivityServiceImpl();
		Activity activity = null;
		try {
			activity = service.searchActivityWithRequest(activityId);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return activity;
	}
	
	@RequestMapping(value = "/activityDetail.action", method = RequestMethod.GET)
	public ModelAndView activityDetail(Model model, HttpSession session,
			@RequestParam(value = "id", required = true) int id) {
		ActivityService service = new ActivityServiceImpl();
		Activity activity = null;
		try {
			activity = service.selectById(id);
			int actitvityPlayerId = activity.getActivityPlayer().getPlayerId();
			if(this.getLoginPlayerNoException(session)==null||this.getLoginPlayerNoException(session).getPlayerId().intValue()!=actitvityPlayerId){
				return new ModelAndView("forward:/WEB-INF/views/activityDetail.jsp?id="+id+"&viewModel=view");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/WEB-INF/views/activityDetail.jsp?id="+id+"&viewModel=edit");
	}
	
	@RequestMapping(value = "/forwardActivityDetail.action", method = RequestMethod.GET)
	public ModelAndView forwardActivityDetail() {

		return new ModelAndView("forward:/WEB-INF/views/activityDetail.jsp");
	}
	
	@RequestMapping(value = "/forwardActivityCreate.action", method = RequestMethod.GET)
	public ModelAndView forwardActivityCreate() {

		return new ModelAndView("forward:/WEB-INF/views/activityCreate.jsp");
	}
}

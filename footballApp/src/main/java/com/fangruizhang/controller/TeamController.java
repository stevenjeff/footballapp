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
import com.fangruizhang.service.TeamService;
import com.fangruizhang.service.impl.ActivityServiceImpl;
import com.fangruizhang.service.impl.TeamServiceImpl;
import com.fangruizhang.util.ExceptionUtil;
import com.fangruizhang.util.PageUtil;

/**
 * @ClassName: TeamController
 * @Description: TODO
 * @author Steven Zhang
 * @date Oct 16, 2014 4:34:46 PM
 *
 */

@Controller
public class TeamController extends CommonController {

	@RequestMapping(value = "/teamCreate.action", method = RequestMethod.POST)
	public ModelAndView createTeam(
			@RequestParam(value = "teamName", required = false) String teamName,
			@RequestParam(value = "createTime", required = false) String createTime,
			@RequestParam(value = "memebercnt", required = false) int memebercnt,
			Model model, HttpSession session) {
		TeamService service = new TeamServiceImpl();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat(
					"yyyy-MM-dd");
			Team team = new Team();
			team.setTeamName(teamName);
			team.setCreatetime(dateformat.parse(createTime));
			team.setCreator(this.getLoginPlayer(session));
			team.setMemebercnt(memebercnt);
			service.insertValue(team);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/teamManageSearchBySinglePlayer.action");
	}

	@RequestMapping(value = "/teamManageSearchBySinglePlayer.action")
	public ModelAndView playerTeamSearchManage(Model model, HttpSession session) {
		TeamService service = new TeamServiceImpl();
		try {
			int pageSize =pagesize;
			Integer recordCount = service.selectPageCountByPlayerId(getLoginPlayer(session)
						.getPlayerId());
			Integer pageCount = (recordCount + pageSize - 1) / pageSize;
			StringBuffer dislayCols=new StringBuffer();
			dislayCols.append("{'球队名称': 'teamName',");
			dislayCols.append("'球队创建时间': 'createtime',");
			dislayCols.append("'球队人数': 'memebercnt',");
			dislayCols.append("'创建人': 'creator.playerName'}");
			PageUtil.initPageMode(model, recordCount, pageCount, dislayCols, "searchTeamByLoginPlayerJson.action", "我的球队", "teamId", "deleteTeamById.action", "teamDetail.action","","");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/WEB-INF/views/teamManage.jsp");
	}
	
	@RequestMapping(value = "/teamSearchAll.action")
	public ModelAndView teamSearchAll(Model model, HttpSession session) {
		TeamService service = new TeamServiceImpl();
		try {
			int pageSize =pagesize;
			Integer recordCount = service.selectPageCountAll();
			Integer pageCount = (recordCount + pageSize - 1) / pageSize;
			StringBuffer dislayCols=new StringBuffer();
			dislayCols.append("{'球队名称': 'teamName',");
			dislayCols.append("'球队创建时间': 'createtime',");
			dislayCols.append("'球队人数': 'memebercnt',");
			dislayCols.append("'创建人': 'creator.playerName'}");
			PageUtil.initPageMode(model, recordCount, pageCount, dislayCols, "searchTeamAllJson.action", "所有球队", "teamId", "", "teamDetail.action","","");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/WEB-INF/views/teamList.jsp");
	}
	
	@RequestMapping(value = "/teamDetail.action", method = RequestMethod.GET)
	public ModelAndView teamDetail(Model model, HttpSession session,
			@RequestParam(value = "id", required = true) int id) {
		TeamService service = new TeamServiceImpl();
		Team team = null;
		try {
			team = service.selectById(id);
			int teamCreatorId = team.getCreator().getPlayerId();
			if(this.getLoginPlayerNoException(session)==null||this.getLoginPlayerNoException(session).getPlayerId().intValue()!=teamCreatorId){
				return new ModelAndView("forward:/WEB-INF/views/teamedit.jsp?id="+id+"&viewModel=view");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/WEB-INF/views/teamedit.jsp?id="+id+"&viewModel=edit");
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTeamDetailById.action", method = RequestMethod.POST)
	public Team getActivityDetailById(Model model,
			@RequestParam(value = "teamId", required = true) int teamId) {
		TeamService service = new TeamServiceImpl();
		Team team = null;
		try {
			team = service.selectById(teamId);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return team;
	}

	@ResponseBody
	@RequestMapping(value = "/searchTeamByLoginPlayerJson.action", method = RequestMethod.GET)
	public List<Team> searchByLoginPlayerJson(
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			Model model, HttpSession session) {
		TeamService service = new TeamServiceImpl();
		List<Team> list = null;
		int beginNum = 0;
		try {
			beginNum = (pageNum - 1) * pageSize >= 0 ? (pageNum - 1) * pageSize
					: 0;
			list = service.selectPageByPlayerId(getLoginPlayer(session)
					.getPlayerId(), beginNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchTeamAllJson.action", method = RequestMethod.GET)
	public List<Team> searchTeamAllJson(
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			Model model, HttpSession session) {
		TeamService service = new TeamServiceImpl();
		List<Team> list = null;
		int beginNum = 0;
		try {
			beginNum = (pageNum - 1) * pageSize >= 0 ? (pageNum - 1) * pageSize
					: 0;
			list = service.selectPageAll(beginNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return list;
	}

	@RequestMapping(value = "/deleteTeamById.action")
	public ModelAndView deleteTeamById(Model model, @RequestParam(value = "id", required = true) int id) {
		TeamService service = new TeamServiceImpl();
		try {
			service.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/teamManageSearchBySinglePlayer.action");
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchAllTeamByPlayerId.action", method = RequestMethod.GET)
	public List<Team> searchAllTeamByPlayerId(
			Model model, HttpSession session) {
		TeamService service = new TeamServiceImpl();
		List<Team> list = null;
		try {
			list = service.selectAll(getLoginPlayer(session)
					.getPlayerId());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return list;
	}
	
	@RequestMapping(value = "/teamUpdate.action", method = RequestMethod.POST)
	public ModelAndView updateTeam(
		@RequestParam(value = "teamId", required = true) Integer teamId,
		@RequestParam(value = "teamName", required = true) String teamName,
		@RequestParam(value = "teamTime", required = true) String teamTime,
		@RequestParam(value = "memebercnt", required = true) int memebercnt,
		Model model, HttpSession session) {
		TeamService service = new TeamServiceImpl();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			Team team = new Team();
			team.setTeamName(teamName);
			team.setCreatetime(dateformat.parse(teamTime));
			team.setCreator(this.getLoginPlayer(session));
			team.setMemebercnt(memebercnt);
			team.setTeamId(teamId);
			service.updateValue(team);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView(
				"forward:/teamManageSearchBySinglePlayer.action");
	}
	
	@RequestMapping(value = "/forwardTeamCreate.action", method = RequestMethod.GET)
	public ModelAndView forwardTeamCreate() {

		return new ModelAndView("forward:/WEB-INF/views/teamCreate.jsp");
	}
}

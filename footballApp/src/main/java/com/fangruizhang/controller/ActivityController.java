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
import com.fangruizhang.service.ActivityService;
import com.fangruizhang.service.impl.ActivityServiceImpl;
import com.fangruizhang.util.ExceptionUtil;

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
			@RequestParam(value = "activityType", required = false) int activityType,
			@RequestParam(value = "activityExpense", required = false) Integer activityExpense,
			@RequestParam(value = "activityPlayersCnt", required = false) int activityPlayersCnt,
			@RequestParam(value = "isneedright", required = false) Integer isneedright,
			Model model, HttpSession session) {
		ActivityService service = new ActivityServiceImpl();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Activity activity = new Activity();
			activity.setActivityArea(activityArea);
			activity.setActivityExpense(activityExpense == null ? 0
					: activityExpense);
			activity.setActivityIsneedRight(isneedright == null ? 0
					: isneedright);
			activity.setActivityPlayersCnt(activityPlayersCnt);
			activity.setActivityType(activityType);
			activity.setActivityTime(dateformat.parse(activityTime));
			activity.setActivityPlayerId(this.getLoginPlayer(session)
					.getPlayerId());
			activity.setActivityTeamId(-1);
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

	@RequestMapping(value = "/activityManageSearchBySinglePlayer.action")
	public ModelAndView playerActivitySerarchManage(Model model, HttpSession session) {
		ActivityService service = new ActivityServiceImpl();
		try {
			int pageSize =5;
			Integer recordCount = service.selectPageCountByPlayerId(getLoginPlayer(session)
						.getPlayerId());
			Integer pageCount = (recordCount + pageSize - 1) / pageSize;
			model.addAttribute("recordCount", recordCount);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("pageTitle", "我的比赛计划");
			StringBuffer dislayCols=new StringBuffer();
			dislayCols.append("{'比赛地点': 'activityArea',");
			dislayCols.append("'比赛队伍': 'activityTeamId',");
			dislayCols.append("'比赛时间': 'activityTime',");
			dislayCols.append("'比赛规模（几人制）': 'activityPlayersCnt',");
			dislayCols.append("'创建人': 'activityPlayerId',");
			dislayCols.append("'比赛类型': 'activityType'}");
			model.addAttribute("displayCols", dislayCols.toString());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/activityManage.jsp");
	}

	@ResponseBody
	@RequestMapping(value = "/searchByLoginPlayerJson.action", method = RequestMethod.GET)
	public List<Activity> searchByLoginPlayerJson(
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
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
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror",
					"错误信息：" + ExceptionUtil.handlerException(e));
		}
		return list;
	}
}

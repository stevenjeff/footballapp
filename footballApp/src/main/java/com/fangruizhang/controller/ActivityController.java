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

	@RequestMapping(value="/activityCreate.action",method=RequestMethod.POST)
    public ModelAndView createActivity(@RequestParam(value="activityArea", required=false) String activityArea,
    		@RequestParam(value="activityTime", required=false) String activityTime,
    		@RequestParam(value="activityType", required=false) int activityType,
    		@RequestParam(value="activityExpense", required=false) Integer activityExpense,
    		@RequestParam(value="activityPlayersCnt", required=false) int activityPlayersCnt,
    		@RequestParam(value="isneedright", required=false) Integer isneedright,
    		Model model,HttpSession session){
		ActivityService service=new ActivityServiceImpl();
		try {
			SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Activity activity=new Activity();
			activity.setActivityArea(activityArea);
			activity.setActivityExpense(activityExpense==null?0:activityExpense);
			activity.setActivityIsneedRight(isneedright==null?0:isneedright);
			activity.setActivityPlayersCnt(activityPlayersCnt);
			activity.setActivityType(activityType);
			activity.setActivityTime(dateformat.parse(activityTime));
			activity.setActivityPlayerId(this.getLoginPlayer(session).getPlayerId());
			activity.setActivityTeamId(-1);
			activity.setActivityOpponentTeamId(-1);
			service.insertValue(activity);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror", "错误信息："+ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/searchByLoginPlayer.action");
	}

	@RequestMapping(value="/searchByLoginPlayer.action",method=RequestMethod.POST)
	public ModelAndView searchByLoginPlayer(Model model,HttpSession session){
		ActivityService service=new ActivityServiceImpl();
		try {
			List<Activity> list = service.selectByPlayerId(getLoginPlayer(session).getPlayerId());
			model.addAttribute(list);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("globalerror", "错误信息："+ExceptionUtil.handlerException(e));
		}
		return new ModelAndView("forward:/activityManage.jsp");
	}
}

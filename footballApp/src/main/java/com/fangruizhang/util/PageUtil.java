package com.fangruizhang.util;

import org.springframework.ui.Model;

public class PageUtil {
	public static void initPageMode(Model model,Integer recordCount,Integer pageCount,
			StringBuffer dislayCols,String jsonAction,String pageTitle,String idKey,
			String delAction,String editAction){
		model.addAttribute("recordCount", recordCount);
		model.addAttribute("jsonAction", jsonAction);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("displayCols", dislayCols.toString());
		model.addAttribute("idkey", idKey);
		model.addAttribute("delAction", delAction);
		model.addAttribute("editAction", editAction);
	}
}

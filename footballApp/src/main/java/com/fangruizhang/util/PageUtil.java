package com.fangruizhang.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.ui.Model;

public class PageUtil {
	public static void initPageMode(Model model,Integer recordCount,Integer pageCount,
			StringBuffer dislayCols,String jsonAction,String pageTitle,String idKey,
			String delAction,String editAction,String applyAction,String approveAction){
		model.addAttribute("recordCount", recordCount);
		model.addAttribute("jsonAction", jsonAction);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("displayCols", dislayCols.toString());
		model.addAttribute("idkey", idKey);
		model.addAttribute("delAction", delAction);
		model.addAttribute("editAction", editAction);
		model.addAttribute("applyAction", applyAction);
		model.addAttribute("approveAction", approveAction);
	}
	
	public static <T> List<T> setColValue(List<T> list,String fieldName,HashMap<String,String> values){
		try {
			for(T o:list){
				Class c = Class.forName(o.getClass().getName());
				Method setMethod=c.getMethod("set"+fieldName, String.class);
				Method getMethod=c.getMethod("get"+fieldName);
				String originString=(String)getMethod.invoke(o, null);
				Set set = values.keySet();
				Iterator<String> iterator=set.iterator();
				String keyString=null;
				while(iterator.hasNext()){
					keyString=iterator.next();
					if(keyString.equals(originString)){
						setMethod.invoke(o, values.get(keyString));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

/*
 *  Copyright (c) 2014 Transaction Processing Specialist Inc., A Xerox Company
 *  All rights reserved.
 *
 *  This item contains confidential and trade secret information and may not be
 *  transferred, published, disclosed, reproduced, or used by any party without
 *  the express written permission of TPS, Inc.
 */
package com.fangruizhang.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: ExceptionUtil
 * @Description: TODO
 * @author Steven Zhang
 * @date Oct 16, 2014 10:57:34 AM
 *
 */
public class ExceptionUtil {
	static HashMap<String,String> map = new HashMap<String,String>(){
	    {
	        put("player_name_index", "用户名重复");
	        put("username not found", "用户名未找到");
	        put("password not correct", "密码不正确");
	        put("user did not login", "用户未登录");
	    }
	};

	public static String handlerException(Exception e){
		Set<String> keySet=map.keySet();
		Iterator<String> iterator = keySet.iterator();
		String key = null;
		while(iterator.hasNext()){
			key = iterator.next();
			if(e.getMessage()!=null&&e.getMessage().contains(key)){
				return map.get(key);
			}
		}
		return e.getMessage();
	}
}

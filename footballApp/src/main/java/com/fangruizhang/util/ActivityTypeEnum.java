/*
 *  Copyright (c) 2014 Transaction Processing Specialist Inc., A Xerox Company
 *  All rights reserved.
 *
 *  This item contains confidential and trade secret information and may not be
 *  transferred, published, disclosed, reproduced, or used by any party without
 *  the express written permission of TPS, Inc.
 */
package com.fangruizhang.util;

/**
 * @ClassName: ActivityTypeEnum
 * @Description: TODO
 * @author Steven Zhang
 * @date Oct 16, 2014 4:18:16 PM
 *
 */
public enum ActivityTypeEnum {
	TeamActivity(1), PlayerActivity(2);

	private int code;
	ActivityTypeEnum(int i){
		this.code=i;
	}
	public int getCode() {
		return code;
	}
}

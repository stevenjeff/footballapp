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
 * @ClassName: RequestType
 * @Description: TODO
 * @author Steven Zhang
 * @date Oct 16, 2014 4:25:38 PM
 *
 */
public enum RequestTypeEnum {
	TeamActivityRequest(1),TeamPlayerRequest(2),PlayerActivityRequest(3),PlayerTeamRequest(4);

	private int code;

	RequestTypeEnum(int i){
		this.code=i;
	}
	public int getCode() {
		return code;
	}
}

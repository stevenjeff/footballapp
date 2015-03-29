package com.fangruizhang.util;

import java.util.HashMap;

public class EnumNames {
	public static HashMap<String, String> activityTypes = new HashMap<String, String>() {
		private static final long serialVersionUID = 2065898320935785062L;

		{
			put(ActivityTypeEnum.TeamActivity.getCode()+"", "球队约战");
			put(ActivityTypeEnum.PlayerActivity.getCode()+"", "散客约战");
		}
	};
	
	public static HashMap<String, String> requestTypes = new HashMap<String, String>() {
		private static final long serialVersionUID = 3479134669500018255L;

		{
			put(RequestTypeEnum.TeamActivityRequest.getCode()+"", "球队约战申请");
			put(RequestTypeEnum.PlayerActivityRequest.getCode()+"", "散客活动申请");
			put(RequestTypeEnum.PlayerTeamRequest.getCode()+"", "加队申请");
		}
	};
	
	public static HashMap<String, String> requestStatus = new HashMap<String, String>() {
		private static final long serialVersionUID = 8547547414022326863L;

		{
			put(RequestStatusEnum.ApplyStatus.getCode()+"", "申请中");
			put(RequestStatusEnum.ApproveStatus.getCode()+"", "申请通过");
		}
	};
	
	public static enum ActivityTypeEnum {
		TeamActivity(1), PlayerActivity(2);

		private int code;
		ActivityTypeEnum(int i){
			this.code=i;
		}
		public int getCode() {
			return code;
		}
	}

	public static enum RequestTypeEnum {
		TeamActivityRequest(1),PlayerActivityRequest(2),PlayerTeamRequest(3);

		private int code;

		RequestTypeEnum(int i){
			this.code=i;
		}
		public int getCode() {
			return code;
		}
	}
	
	public static enum RequestStatusEnum {
		ApplyStatus(1),ApproveStatus(2);

		private int code;

		RequestStatusEnum(int i){
			this.code=i;
		}
		public int getCode() {
			return code;
		}
	}

	public static enum ActivityStatusEnum {
		ActivityEnabled(1),ActivityDeleted(2),ActivityOpend(3),ActivityClosed(4);

		private int code;

		ActivityStatusEnum(int i){
			this.code=i;
		}
		public int getCode() {
			return code;
		}
	}
}

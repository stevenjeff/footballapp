package com.fangruizhang.util;

import java.util.HashMap;

public class EnumNames {
	public static HashMap<String, String> activityTypes = new HashMap<String, String>() {
		private static final long serialVersionUID = 2065898320935785062L;

		{
			put(ActivityTypeEnum.TeamActivity.getCode()+"", "球队约战");
			put(ActivityTypeEnum.PlayerActivity.getCode()+"", "散客约战");
			put(ActivityTypeEnum.TeamPlayerActivity.getCode()+"", "球队散客混合");
		}
	};
	
	public static HashMap<String, String> requestTypes = new HashMap<String, String>() {
		private static final long serialVersionUID = 3479134669500018255L;

		{
			put(RequestTypeEnum.TeamActivityRequest.getCode()+"", "球队约战申请");
			put(RequestTypeEnum.PlayerActivityRequest.getCode()+"", "散客活动申请");
			put(RequestTypeEnum.PlayerTeamRequest.getCode()+"", "加队申请");
			put(RequestTypeEnum.PlayerAndTeamActivityRequest.getCode()+"", "球队散客混合申请");
		}
	};
	
	public static HashMap<String, String> requestStatuss = new HashMap<String, String>() {
		private static final long serialVersionUID = 8547547414022326863L;

		{
			put(ActivityStatusEnum.ActivityEnabled.getCode()+"", "正常进行");
			put(ActivityStatusEnum.ActivityDeleted.getCode()+"", "删除");
			put(ActivityStatusEnum.ActivityOpened.getCode()+"", "开放申请");
			put(ActivityStatusEnum.ActivityClosed.getCode()+"", "取消关闭");
		}
	};
	
	public static HashMap<String, String> teamStatuss = new HashMap<String, String>() {
		
		private static final long serialVersionUID = 1653894188148720950L;

		{
			put(TeamStatusEnum.TeamEnabled.getCode()+"", "正常进行");
			put(TeamStatusEnum.TeamDeleted.getCode()+"", "删除");
			put(TeamStatusEnum.TeamOpened.getCode()+"", "开放申请");
			put(TeamStatusEnum.TeamClosed.getCode()+"", "取消关闭");
		}
	};
	
public static HashMap<String, String> requestValidateErrors = new HashMap<String, String>() {
		
		private static final long serialVersionUID = 1653894188148720950L;

		{
			put(RequestValidateErrorEnum.RECORD_NOT_FOUND.getCode()+"", "记录未找到");
			put(RequestValidateErrorEnum.NOT_OPEN_STATUS.getCode()+"", "当前非开放申请状态");
			put(RequestValidateErrorEnum.TEAM_ACTIVITY_REQUESTPERSON_CANNOT_BE_SAME.getCode()+"", "球队约战类型申请人不能为创建人");
			put(RequestValidateErrorEnum.TEAM_ACTIVITY_NOT_HAVE_TEAM.getCode()+"", "当前为球队约战,请创建队伍");
			put(RequestValidateErrorEnum.ALREADYAPPLIED.getCode()+"", "您已经申请过");
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
		TeamActivity(1), PlayerActivity(2), TeamPlayerActivity(3);

		private int code;
		ActivityTypeEnum(int i){
			this.code=i;
		}
		public int getCode() {
			return code;
		}
	}

	public static enum RequestTypeEnum {
		TeamActivityRequest(1),PlayerActivityRequest(2),PlayerTeamRequest(3),PlayerAndTeamActivityRequest(4);

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
		ActivityEnabled(1),ActivityDeleted(2),ActivityOpened(3),ActivityClosed(4);

		private int code;

		ActivityStatusEnum(int i){
			this.code=i;
		}
		public int getCode() {
			return code;
		}
	}
	
	public static enum TeamStatusEnum {
		TeamEnabled(1),TeamDeleted(2),TeamOpened(3),TeamClosed(4);

		private int code;

		TeamStatusEnum(int i){
			this.code=i;
		}
		public int getCode() {
			return code;
		}
	}
	
	public static enum RequestValidateErrorEnum {
		RECORD_NOT_FOUND(-1),NOT_OPEN_STATUS(1),TEAM_ACTIVITY_REQUESTPERSON_CANNOT_BE_SAME(2),TEAM_ACTIVITY_NOT_HAVE_TEAM(3),ALREADYAPPLIED(4);

		private int code;

		RequestValidateErrorEnum(int i){
			this.code=i;
		}
		public int getCode() {
			return code;
		}
	}
}

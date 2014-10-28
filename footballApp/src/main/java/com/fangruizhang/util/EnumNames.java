package com.fangruizhang.util;

public class EnumNames {
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
		TeamActivityRequest(1),TeamPlayerRequest(2),PlayerActivityRequest(3),PlayerTeamRequest(4);

		private int code;

		RequestTypeEnum(int i){
			this.code=i;
		}
		public int getCode() {
			return code;
		}
	}
}

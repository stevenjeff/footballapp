package com.fangruizhang.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.fangruizhang.entity.Activity;
import com.fangruizhang.entity.Player;
import com.fangruizhang.entity.Request;
import com.fangruizhang.entity.Team;

public interface RequestService {
	@Insert("insert into request (request_player_id,request_team_id,request_status,request_time,request_msg,request_activity_id,against_team_id,request_type) values (#{requestPlayer.playerId},#{requestTeam.teamId},#{requestStatus},#{requestTime},#{requestMsg},#{requestActivity.activityId},#{againstTeam.teamId},#{requestType})")
	public boolean insertValue(Request request) throws Exception;
	@Delete("delete from request where request_id = #{id}")
	public boolean deleteById(int id) throws Exception;

	@Update("update request set request_player_id=#{requestPlayer.playerId}, request_team_id=#{requestTeam.teamId},request_status=#{requestStatus}, request_time=#{requestTime}, request_msg=#{requestMsg}, request_activity_id=#{requestActivity.activityId} where request_id = #{requestId}")
	public boolean updateValue(Request request) throws Exception;

	@Update("update request set request_status=#{requestStatus} where request_id = #{requestId}")
	public boolean updateRequestStatus(@Param("requestId") int requestId,@Param("requestStatus") int requestStatus) throws Exception;
	
	@Update("update request set request_status=#{requestStatus} where request_activity_id = #{activityId} and request_type=#{requestType}")
	public boolean updateRequestStatusByActivityIdAndType(@Param("activityId") int activityId,@Param("requestType") int requestType,@Param("requestStatus") int requestStatus) throws Exception;
	
	@Update("update request set request_status=#{requestStatus} where request_team_id = #{teamId} and request_type=#{requestType}")
	public boolean updateRequestStatusByTeamIdAndType(@Param("teamId") int teamId,@Param("requestType") int requestType,@Param("requestStatus") int requestStatus) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "requestId", column = "request_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestPlayer", column = "request_player_id", one=@One(select = "getPlayer")),
			@Result(property = "requestTeam", column = "request_team_id", one=@One(select = "getTeam")),
			@Result(property = "requestStatus", column = "request_status", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestTime", column = "request_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "requestMsg", column = "request_msg", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestType", column = "request_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestActivity", column = "request_activity_id", one=@One(select = "getActivity"))})
	@Select("SELECT * FROM REQUEST WHERE request_id = #{id}")
	public Request selectById(int id) throws Exception;

	@Results(value = {
			@Result(id = true, property = "requestId", column = "request_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestPlayer", column = "request_player_id",  one=@One(select = "getPlayer")),
			@Result(property = "requestTeam", column = "request_team_id",  one=@One(select = "getTeam")),
			@Result(property = "requestStatus", column = "request_status", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestTime", column = "request_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "requestMsg", column = "request_msg", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestType", column = "request_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestActivity", column = "request_activity_id",  one=@One(select = "getActivity"))})
	@Select("SELECT * FROM REQUEST")
	public List<Request> selectAll() throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "playerId", column = "player_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "playerName", column = "player_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createtime", column = "createtime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "phone", column = "phone", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attendtimes", column = "attendtimes", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "attendsuccescnt", column = "attendsuccescnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "qq", column = "qq", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "weixin", column = "weixin", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "mail", column = "mail", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "sex", column = "sex", javaType = Integer.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "birthday", column = "birthday", javaType = Date.class, jdbcType = JdbcType.DATE)})
	@Select("SELECT * FROM PLAYER WHERE player_id = #{activityPlayerId}")
	public Player getPlayer(@Param("activityPlayerId") int activityPlayerId) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
	@Select("SELECT * FROM TEAM WHERE team_id = #{teamId} and team_status=1")
	public Team getTeam(@Param("teamId") int teamId) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "activityId", column = "activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityArea", column = "activity_area", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityTime", column = "activity_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "activityPlayersCnt", column = "activity_players_cnt", javaType = Integer.class, jdbcType = JdbcType.SMALLINT),
			@Result(property = "activityExpense", column = "activity_expense", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityType", column = "activity_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityPlayer", column = "activity_player_id", one=@One(select = "getPlayer")),
			@Result(property = "activityTeam", column = "activity_team_id", one=@One(select = "getTeam")),
			@Result(property = "activityOpponentTeamId", column = "activity_opponent_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityIsneedRight", column = "activity_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM Activity WHERE activity_id = #{id}")
	public Activity getActivity(int id) throws Exception;
	
	@Results(value = {
		@Result(id = true, property = "requestId", column = "request_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM REQUEST where request_activity_id=#{activityId} and request_player_id=#{requestPlayerId}")
	public List<Request> selectByActivityAndPlayer(int activityId,int requestPlayerId) throws Exception;
	
	@Results(value = {
		@Result(id = true, property = "requestId", column = "request_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM REQUEST where request_team_id=#{teamId} and request_player_id=#{requestPlayerId}")
	public List<Request> selectByTeamAndPlayer(int teamId,int requestPlayerId) throws Exception;
}

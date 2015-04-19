package com.fangruizhang.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
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

public interface ActivityService {

	@Insert("insert into activity (activity_area,activity_time,activity_players_cnt,activity_expense,activity_type,activity_player_id,activity_team_id,activity_opponent_team_id,activity_isneed_right,activity_status) values (#{activityArea},#{activityTime},#{activityPlayersCnt},#{activityExpense},#{activityType},#{activityPlayer.playerId},#{activityTeam.teamId},#{activityOpponentTeamId},#{activityIsneedRight},#{activityStatus})")
	public boolean insertValue(Activity activity) throws Exception;
	@Update("update activity set activity_status=2 where activity_id = #{id}")
	public boolean deleteById(int id) throws Exception;
	
	@Update("update activity set activity_area=#{activityArea},activity_time=#{activityTime},activity_players_cnt=#{activityPlayersCnt},activity_expense=#{activityExpense},activity_type=#{activityType},activity_team_id=#{activityTeam.teamId},activity_isneed_right=#{activityIsneedRight},activity_status=#{activityStatus} where activity_id = #{activityId}")
	public boolean updateValue(Activity activity) throws Exception;

	@Results(value = {
			@Result(id = true, property = "activityId", column = "activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityArea", column = "activity_area", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityStatus", column = "activity_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityTime", column = "activity_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "activityPlayersCnt", column = "activity_players_cnt", javaType = Integer.class, jdbcType = JdbcType.SMALLINT),
			@Result(property = "activityExpense", column = "activity_expense", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityType", column = "activity_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityPlayer", column = "activity_player_id", one=@One(select = "getPlayer")),
			@Result(property = "activityTeam", column = "activity_team_id", one=@One(select = "getTeam")),
			@Result(property = "activityOpponentTeamId", column = "activity_opponent_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityIsneedRight", column = "activity_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM Activity WHERE activity_id = #{id}")
	public Activity selectById(int id) throws Exception;
	
	@Select("SELECT count(activity_id) FROM Activity WHERE activity_status!=2")
	public int selectAllPageCount() throws Exception;

	@Select("SELECT * FROM Activity where activity_status!=2 order by activity_time desc limit #{beginNum},#{endNum}")
	@Results(value = {
			@Result(id = true, property = "activityId", column = "activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityStatus", column = "activity_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityArea", column = "activity_area", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityTime", column = "activity_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "activityPlayersCnt", column = "activity_players_cnt", javaType = Integer.class, jdbcType = JdbcType.SMALLINT),
			@Result(property = "activityExpense", column = "activity_expense", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityType", column = "activity_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityPlayer", column = "activity_player_id", one=@One(select = "getPlayer")),
			@Result(property = "activityTeam", column = "activity_team_id", one=@One(select = "getTeam")),
			@Result(property = "activityOpponentTeamId", column = "activity_opponent_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityIsneedRight", column = "activity_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	public List<Activity> selectAll(@Param("beginNum") int beginNum,@Param("endNum") int endNum) throws Exception;

	@Results(value = {
			@Result(id = true, property = "activityId", column = "activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityStatus", column = "activity_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityArea", column = "activity_area", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityTime", column = "activity_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "activityPlayersCnt", column = "activity_players_cnt", javaType = Integer.class, jdbcType = JdbcType.SMALLINT),
			@Result(property = "activityExpense", column = "activity_expense", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityType", column = "activity_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityPlayer", column = "activity_player_id", one=@One(select = "getPlayer")),
			@Result(property = "activityTeam", column = "activity_team_id", one=@One(select = "getTeam")),
			@Result(property = "activityOpponentTeamId", column = "activity_opponent_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityIsneedRight", column = "activity_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM Activity WHERE activity_player_id = #{activityPlayerId} and activity_status!=2 order by activity_time desc limit #{beginNum},#{endNum}")
	public List<Activity> selectPageByPlayerId(@Param("activityPlayerId") int activityPlayerId,@Param("beginNum") int beginNum,@Param("endNum") int endNum) throws Exception;

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
	@Select("SELECT * FROM TEAM WHERE team_id = #{activityTeamId} and team_status=1")
	public Team getTeam(@Param("activityTeamId") int activityTeamId) throws Exception;
	
	@Select("SELECT count(activity_id) FROM Activity WHERE activity_player_id = #{activityPlayerId} and activity_status!=2")
	public int selectPageCountByPlayerId(int activityPlayerId) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "activityId", column = "activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityArea", column = "activity_area", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityTime", column = "activity_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
			@Result(property = "activityPlayersCnt", column = "activity_players_cnt", javaType = Integer.class, jdbcType = JdbcType.SMALLINT),
			@Result(property = "activityExpense", column = "activity_expense", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityType", column = "activity_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityStatus", column = "activity_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityPlayer", column = "activity_player_id", one=@One(select = "getPlayer")),
			@Result(property = "activityTeam", column = "activity_team_id", one=@One(select = "getTeam")),
			@Result(property = "activityOpponentTeamId", column = "activity_opponent_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestList", javaType=List.class, column="activity_id", many=@Many(select="getRequests")),
			@Result(property = "activityIsneedRight", column = "activity_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM Activity WHERE activity_id = #{id}")
	public Activity searchActivityWithRequest(int id) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "requestId", column = "request_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestPlayer", column = "request_player_id",  one=@One(select = "getPlayer")),
			@Result(property = "requestTeam", column = "request_team_id",  one=@One(select = "getTeam")),
			@Result(property = "againstTeam", column = "against_team_id",  one=@One(select = "getTeam")),
			@Result(property = "requestStatus", column = "request_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestTime", column = "request_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "requestMsg", column = "request_msg", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestType", column = "request_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestActivity", column = "request_activity_id",  one=@One(select = "selectById"))})
	@Select("SELECT * FROM REQUEST WHERE request_activity_id=#{activityPlayerId}")
	public List<Request> getRequests(@Param("activityPlayerId")int activityPlayerId) throws Exception;
}

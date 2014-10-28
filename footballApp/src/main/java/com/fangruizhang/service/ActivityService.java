package com.fangruizhang.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.fangruizhang.entity.Activity;

public interface ActivityService {

	@Insert("insert into activity (activity_area,activity_time,activity_players_cnt,activity_expense,activity_type,activity_player_id,activity_team_id,activity_opponent_team_id,activity_isneed_right) values (#{activityArea},#{activityTime},#{activityPlayersCnt},#{activityExpense},#{activityType},#{activityPlayerId},#{activityTeamId},#{activityOpponentTeamId},#{activityIsneedRight})")
	public boolean insertValue(Activity activity) throws Exception;
	@Delete("delete from activity where activity_id = #{id}")
	public boolean deleteById(int id) throws Exception;
	public boolean updateValue(Activity activity) throws Exception;

	@Results(value = {
			@Result(id = true, property = "activityId", column = "activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityArea", column = "activity_area", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityTime", column = "activity_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "activityPlayersCnt", column = "activity_players_cnt", javaType = Integer.class, jdbcType = JdbcType.SMALLINT),
			@Result(property = "activityExpense", column = "activity_expense", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityType", column = "activity_type", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
			@Result(property = "activityPlayerId", column = "activity_player_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityTeamId", column = "activity_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityOpponentTeamId", column = "activity_opponent_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityIsneedRight", column = "activity_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM Activity WHERE activity_id = #{id}")
	public Activity selectById(int id) throws Exception;

	@Select("SELECT * FROM Activity")
	@Results(value = {
			@Result(id = true, property = "activityId", column = "activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityArea", column = "activity_area", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityTime", column = "activity_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "activityPlayersCnt", column = "activity_players_cnt", javaType = Integer.class, jdbcType = JdbcType.SMALLINT),
			@Result(property = "activityExpense", column = "activity_expense", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityType", column = "activity_type", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
			@Result(property = "activityPlayerId", column = "activity_player_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityTeamId", column = "activity_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityOpponentTeamId", column = "activity_opponent_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityIsneedRight", column = "activity_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	public List<Activity> selectAll() throws Exception;

	@Results(value = {
			@Result(id = true, property = "activityId", column = "activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityArea", column = "activity_area", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "activityTime", column = "activity_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "activityPlayersCnt", column = "activity_players_cnt", javaType = Integer.class, jdbcType = JdbcType.SMALLINT),
			@Result(property = "activityExpense", column = "activity_expense", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityType", column = "activity_type", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
			@Result(property = "activityPlayerId", column = "activity_player_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityTeamId", column = "activity_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityOpponentTeamId", column = "activity_opponent_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "activityIsneedRight", column = "activity_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM Activity WHERE activity_player_id = #{activityPlayerId} and activity_status=1 limit #{beginNum},#{endNum}")
	public List<Activity> selectPageByPlayerId(@Param("activityPlayerId") int activityPlayerId,@Param("beginNum") int beginNum,@Param("endNum") int endNum) throws Exception;

	@Select("SELECT count(activity_id) FROM Activity WHERE activity_player_id = #{activityPlayerId}")
	public int selectPageCountByPlayerId(int activityPlayerId) throws Exception;
}

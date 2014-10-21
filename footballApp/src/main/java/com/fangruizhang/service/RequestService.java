package com.fangruizhang.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.fangruizhang.entity.Request;

public interface RequestService {
	@Insert("insert into request (request_player_id,request_player_id,request_team_id,request_status,request_time,request_msg,request_activity_id) values (#{requestPlayerId},#{requestTeamId},#{requestStatus},#{requestTime},#{requestMsg},#{requestActivityId})")
	public boolean insertValue(Request request);
	@Delete("delete from request where request_id = #{id}")
	public boolean deleteById(int id);
	
	@Update("update request set request_player_id=#{requestPlayerId}, request_team_id=#{requestTeamId},request_status=#{requestStatus}, request_time=#{requestTime}, request_msg=#{requestMsg}, request_activity_id=#{requestActivityId} where request_id = #{requestId}")
	public boolean updateValue(Request request);
	
	@Results(value = { 
			@Result(id = true, property = "requestId", column = "request_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT), 
			@Result(property = "requestPlayerId", column = "request_player_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestTeamId", column = "request_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestStatus", column = "request_status", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestTime", column = "request_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "requestMsg", column = "request_msg", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestActivityId", column = "request_activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT)}) 
	@Select("SELECT * FROM REQUEST WHERE request_id = #{id}")
	public Request selectById(int id);
	
	@Results(value = { 
			@Result(id = true, property = "requestId", column = "request_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT), 
			@Result(property = "requestPlayerId", column = "request_player_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestTeamId", column = "request_team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestStatus", column = "request_status", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestTime", column = "request_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "requestMsg", column = "request_msg", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestActivityId", column = "request_activity_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT)}) 
	@Select("SELECT * FROM REQUEST")
	public List<Request> selectAll();
}

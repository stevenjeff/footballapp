package com.fangruizhang.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.fangruizhang.entity.Team;

public interface TeamService {
	@Insert("insert into Team (team_name,creattime,creatorid,memebercnt) values (#{teamName},#{creattime},#{creatorid},#{memebercnt})")
	public boolean insertValue(Team team) throws Exception;
	@Delete("delete from Team where team_id = #{id}")
	public boolean deleteById(int id) throws Exception;

	@Update("update team set team_name")
	public boolean updateValue(Team team) throws Exception;

	@Results(value = {
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createtime", column = "creattime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "creatorid", column = "creatorid", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "memebercnt", column = "memebercnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM TEAM WHERE team_id = #{id}")
	public Team selectById(int id) throws Exception;

	@Results(value = {
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "creattime", column = "creattime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "creatorid", column = "creatorid", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "memebercnt", column = "memebercnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM TEAM")
	public List<Team> selectAll() throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "creattime", column = "creattime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "creatorid", column = "creatorid", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "memebercnt", column = "memebercnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM TEAM WHERE creatorid=#{playerId} and team_status=1 limit #{beginNum},#{endNum}")
	public List<Team> selectPageByPlayerId(@Param("playerId") int playerId,@Param("beginNum") int beginNum,@Param("endNum") int endNum) throws Exception;
	
	@Select("SELECT count(team_id) FROM TEAM WHERE creatorid=#{playerId}")
	public int selectPageCountByPlayerId(@Param("playerId") int playerId) throws Exception;
}

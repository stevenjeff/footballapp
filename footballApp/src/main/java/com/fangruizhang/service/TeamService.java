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
import com.fangruizhang.entity.Team;

public interface TeamService {
	@Insert("insert into Team (team_name,creattime,creatorid,memebercnt) values (#{teamName},#{creattime},#{creatorid},#{memebercnt})")
	public boolean insertValue(Team team);
	@Delete("delete from Team where team_id = #{id}")
	public boolean deleteById(int id);
	
	@Update("update team set team_name")
	public boolean updateValue(Team team);
	
	@Results(value = { 
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT), 
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createtime", column = "creattime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "creatorid", column = "creatorid", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "memebercnt", column = "memebercnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT)}) 
	@Select("SELECT * FROM TEAM WHERE team_id = #{id}")
	public Team selectById(int id);
	
	@Results(value = { 
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT), 
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "creattime", column = "creattime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "creatorid", column = "creatorid", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "memebercnt", column = "memebercnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT)}) 
	@Select("SELECT * FROM TEAM")
	public List<Team> selectAll();
}

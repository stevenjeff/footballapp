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

import com.fangruizhang.entity.Player;

public interface PlayerService {
	@Insert("insert into player (player_name,createtime,phone,attendtimes,attendsuccescnt,qq,weixin,mail,sex,birthday,password,insert_date) values (#{playerName},#{createtime},#{phone},#{attendtimes},#{attendsuccescnt},#{qq},#{weixin},#{mail},#{sex},#{birthday},#{password},#{insertDate})")
	public boolean insertValue(Player player) throws Exception;
	@Delete("delete from player where player_id = #{id}")
	public boolean deleteById(int id) throws Exception;

	@Update("update player set player_name=#{playerName}, phone=#{phone},qq=#{qq}, weixin=#{weixin}, mail=#{mail}, sex=#{sex}, birthday=#{birthday}, password=#{password} where player_id = #{playerId}")
	public boolean updateValue(Player player) throws Exception;

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
	@Select("SELECT * FROM PLAYER WHERE player_id = #{id}")
	public Player selectById(int id) throws Exception;

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
	@Select("SELECT * FROM PLAYER limit #{beginNum},#{endNum}")
	public List<Player> selectAll(@Param("beginNum") int beginNum,@Param("endNum") int endNum) throws Exception;

	@Select("SELECT count(player_id) FROM PLAYER")
	public int selectAllCountAll() throws Exception;
	
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
	@Select("SELECT * FROM PLAYER WHERE player_name = #{playerName}")
	public Player selectByName(String playerName) throws Exception;
}

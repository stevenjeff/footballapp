package com.fangruizhang.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.fangruizhang.entity.Player;
import com.fangruizhang.entity.Request;
import com.fangruizhang.entity.Team;

public interface TeamService {
	@Insert("insert into Team (team_name,creattime,creatorid,memebercnt,team_isneed_right,team_status) values (#{teamName},#{createtime},#{creator.playerId},#{memebercnt},#{teamIsneedRight},#{teamStatus})")
	public boolean insertValue(Team team) throws Exception;
	@Update("update team set team_status=2 where team_id = #{id}")
	public boolean deleteById(int id) throws Exception;

	@Update("update team set team_name=#{teamName},creattime=#{createtime},memebercnt=#{memebercnt},team_isneed_right=#{teamIsneedRight},team_status=#{teamStatus} where team_id=#{teamId}")
	public boolean updateValue(Team team) throws Exception;

	@Results(value = {
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "teamStatus", column = "team_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createtime", column = "creattime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "teamIsneedRight", column = "team_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "creator", column = "creatorid",  one=@One(select = "getPlayer")),
			@Result(property = "memebercnt", column = "memebercnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM TEAM WHERE team_id = #{id} and team_status!=2")
	public Team selectById(int id) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "teamStatus", column = "team_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createtime", column = "creattime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "creator", column = "creatorid",  one=@One(select = "getPlayer")),
			@Result(property = "requestList", javaType=List.class, column="team_id", many=@Many(select="getRequests")),
			@Result(property = "teamIsneedRight", column = "team_isneed_right", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "memebercnt", column = "memebercnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM TEAM WHERE team_id = #{id} and team_status!=2")
	public Team selectWithRequestById(int id) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "requestId", column = "request_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "requestPlayer", column = "request_player_id",  one=@One(select = "getPlayer")),
			@Result(property = "requestStatus", column = "request_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestTime", column = "request_time", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "requestMsg", column = "request_msg", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "requestType", column = "request_type", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
	@Select("SELECT * FROM REQUEST WHERE request_team_id=#{teamId} ")
	public List<Request> getRequests(@Param("teamId")int teamId) throws Exception;

	@Results(value = {
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "teamStatus", column = "team_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
	@Select("SELECT team_id,team_name FROM TEAM where creatorid=#{playerId} and team_status!=2")
	public List<Team> selectAll(@Param("playerId") int playerId) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "teamStatus", column = "team_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createtime", column = "creattime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "creator", column = "creatorid", one=@One(select = "getPlayer")),
			@Result(property = "memebercnt", column = "memebercnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM TEAM WHERE creatorid=#{playerId} and team_status!=2 limit #{beginNum},#{endNum}")
	public List<Team> selectPageByPlayerId(@Param("playerId") int playerId,@Param("beginNum") int beginNum,@Param("endNum") int endNum) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "teamId", column = "team_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "teamStatus", column = "team_status", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teamName", column = "team_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "createtime", column = "creattime", javaType = Date.class, jdbcType = JdbcType.DATE),
			@Result(property = "creator", column = "creatorid", one=@One(select = "getPlayer")),
			@Result(property = "memebercnt", column = "memebercnt", javaType = Integer.class, jdbcType = JdbcType.BIGINT)})
	@Select("SELECT * FROM TEAM WHERE team_status=1 limit #{beginNum},#{endNum}")
	public List<Team> selectPageAll(@Param("beginNum") int beginNum,@Param("endNum") int endNum) throws Exception;
	
	@Select("SELECT count(team_id) FROM TEAM WHERE creatorid=#{playerId} and team_status=1")
	public int selectPageCountByPlayerId(@Param("playerId") int playerId) throws Exception;
	
	@Select("SELECT count(team_id) FROM TEAM WHERE team_status=1")
	public int selectPageCountAll() throws Exception;
	
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
	@Select("SELECT * FROM PLAYER WHERE player_id = #{playerId}")
	public Player getPlayer(@Param("playerId") int playerId) throws Exception;
	
	@Results(value = {
			@Result(id = true, property = "playerId", column = "player_id", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
			@Result(property = "playerName", column = "player_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "birthday", column = "birthday", javaType = Date.class, jdbcType = JdbcType.DATE)})
	@Select("SELECT P.* FROM PLAYER P,TEAM_PLAYER_RELATION R WHERE P.PLAYER_ID=R.PLAYER_ID AND R.TEAM_ID = #{teamId}")
	public List<Player> getTeamRelationPlayer(@Param("teamId") int teamId) throws Exception;
}

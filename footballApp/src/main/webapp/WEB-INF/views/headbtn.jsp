<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include  file="/WEB-INF/layout/libs.jsp"%>
<c:if test="${not empty player }">
<li><a href="activityManageSearchBySinglePlayer.action">球赛管理</a></li>
<li><a href="teamManageSearchBySinglePlayer.action">球队管理</a></li>
</c:if>
<li><a href="teamSearchAll.action">所有球队</a></li>
<li><a href="playerSearchAll.action">所有球员</a></li>
<li><a href="index.action">所有比赛场次</a></li>
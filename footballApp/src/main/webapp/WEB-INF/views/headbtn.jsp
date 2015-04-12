<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include  file="/WEB-INF/layout/libs.jsp"%>
<p>
	<c:if test="${not empty player }">
	<button type="button" class="btn btn-danger"
		onclick="javascript: window.location.href='activityManageSearchBySinglePlayer.action';">球赛管理</button>
	<button type="button" class="btn btn-danger" onclick="javascript: window.location.href='teamManageSearchBySinglePlayer.action';">球队管理</button>
	</c:if>
	<button type="button" class="btn btn-success" onclick="javascript: window.location.href='teamSearchAll.action';">所有球队</button>
	<button type="button" class="btn btn-success" onclick="javascript: window.location.href='playerSearchAll.action';">所有球员</button>
	<button type="button" class="btn btn-success" onclick="javascript: window.location.href='index.action';">所有比赛场次</button>
</p>
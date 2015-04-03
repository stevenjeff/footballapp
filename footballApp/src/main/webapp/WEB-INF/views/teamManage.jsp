<%@ include file="/WEB-INF/layout/libs.jsp"%>
<tiles:insertTemplate template="/WEB-INF/layout/template.jsp">
	<tiles:putAttribute name="body" type="string">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<p>
			<button type="button" class="btn btn-sm btn-primary" onclick="javascript: window.location.href='forwardTeamCreate.action';">创建</button>
			</p>
			<div id="divResult" class="table-responsive"></div>
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>
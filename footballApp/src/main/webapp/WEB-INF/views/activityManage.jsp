<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertTemplate template="/WEB-INF/layout/template.jsp">
	<tiles:putAttribute name="body" type="string">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<p></p>
			<div id="divResult" class="table-responsive"></div>
		</div>
	</tiles:putAttribute>
</tiles:insertTemplate>
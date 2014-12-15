<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8">
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="assets/css/bootstrap-theme.min.css" rel="stylesheet" />
<link href="assets/css/signin.css" rel="stylesheet">
<link href="assets/css/jquery-ui.min.css" rel="stylesheet">
<link href="assets/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="assets/css/jquery-ui-timepicker-addon.css" type="text/css" />
<title>帮助</title>
</head>
<body>
	<form action="activityRequestCreate.action" class="form-signin" role="form"
		method="post">
		<h2 class="form-signin-heading">申请提交</h2>
		<font color="red">${globalerror}</font> <input type="hidden" name="activityId"
			value="${activityId}">
		<div class="form-group">
			<label for="requestMsg">申请消息</label> <input type="text"
				class="form-control" id="requestMsg" name="requestMsg"
				placeholder="请输入消息" maxlength="50">
		</div>
		<div class="form-group" id="teamDiv">
			<label for="requestTeamId">比赛球队</label> <select id="requestTeamId"
				name="requestTeamId"
				class="selectpicker show-tick show-menu-arrow span2"
				data-style="btn-info">
			</select>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">确定</button>
		<input type="button" value="返回" onclick="javascript:history.back();" class="btn btn-lg btn-primary btn-block">
	</form>
</body>
<script src="assets/js/jquery-1.11.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/docs.min.js"></script>
<script src="assets/js/jquery-ui.min.js"></script>
<script src="assets/js/bootstrapValidator.min.js"></script>
<script src="assets/js/jquery-ui-timepicker-addon.js"
	type="text/javascript"></script>
<script src="assets/js/jquery.ui.datepicker-zh-CN.js.js"
	type="text/javascript" charset="gb2312"></script>
<script src="assets/js/jquery-ui-timepicker-zh-CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	init();
	function init() {
		if ("${globalerror}" != "") {
			alert("${globalerror}");
		}
		if ("${globalerror}" == "" && "${activityType}"=="1") {
			 getRelativeTeam();
		}else{
			$("#teamDiv").hide();
		}
	}

	function getRelativeTeam() {
		$.ajax({
					url : "${pageContext.request.contextPath}/getRelativeTeamJson.action",
					type : "GET",
					data : "",
					dataType : "json",
					success : function(json) {
						var activityTeamSelect = document
								.getElementById("requestTeamId");
						for (position in json) {
							var varItem = new Option(json[position].teamName,
									json[position].teamId);
							activityTeamSelect.options.add(varItem);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(XMLHttpRequest);
						alert(textStatus);
						alert(errorThrown);
					}
				});
	}
</script>
</html>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="assets/img/favicon.ico"/>
    <title>Tianjin Football Association</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="assets/css/bootstrap-theme.min.css" rel="stylesheet"/>
 	<link href="assets/css/signin.css" rel="stylesheet">
 	<link href="assets/css/jquery-ui.min.css" rel="stylesheet">
 	<link href="assets/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="assets/css/jquery-ui-timepicker-addon.css" type="text/css" />
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="assets/js/html5shiv.min.js"></script>
      <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<form action="activityUpdate.action" class="form-signin" role="form" method="post">
<input type="hidden" id="activityId" name="activityId"/> 
<h2 class="form-signin-heading">球赛详情</h2>
<font color="red">${globalerror}</font>
<div class="form-group">
  <label for="activityArea">球场地址</label>
  <input type="text" class="form-control" id="activityArea" name="activityArea" placeholder="请输入球场地址" maxlength="50">
</div>
<div class="form-group">
  <label for="activityPlayersCnt">赛制（几人制）</label>
  <select id="activityPlayersCnt" name="activityPlayersCnt" class="selectpicker show-tick show-menu-arrow span2" data-style="btn-info">
  	<option value="3">3</option>
  	<option value="5">5</option>
  	<option value="7">7</option>
  	<option value="9">9</option>
  	<option value="11">11</option>
  </select>
</div>
<div class="form-group">
  <label for="activityTime">比赛时间</label>
  <input type="text" class="form-control" id="activityTime" name="activityTime" placeholder="比赛时间" maxlength="20" readOnly="readonly">
</div>
<div class="form-group">
  <label for="activityExpense">比赛费用</label>
  <input type="text" class="form-control" id="activityExpense" name="activityExpense" placeholder="请输入比赛费用" maxlength="10">
</div>
<div class="form-group">
  <label for="activityType">比赛类型</label>
  <select id="activityType" name="activityType" class="selectpicker show-tick show-menu-arrow span2" data-style="btn-info">
  	<option value="1">球队约战</option>
  	<option value="2">散客约战</option>
  </select>
</div>
<div class="form-group" id="teamDiv">
  <label for="activityTeam">比赛球队</label>
  <select id="activityTeam" name="activityTeam" class="selectpicker show-tick show-menu-arrow span2" data-style="btn-info">
  </select>
</div>
<div class="form-group">
  <label for="isneedright">是否需要授权      </label>
  <input type="checkbox" id="isneedright" name="isneedright" value="0" onclick="changeRightVal()"> （比赛约战申请是否需要您的审批）
</div>
<div class="form-group">
  <label for="suretoComeTab">确定出席人员 </label>
  <table id="suretoComeTab" class="table table-striped">
  </table>
</div>
<div class="form-group">
  <label for="waitoComeTab">待审批出席人员</label>
  <table id="waitoComeTab" class="table table-striped">
  </table>
</div>
<div class="form-group">
<div class="col-md-2 col-sm-5 col-xs-6 col-sm-offset-4">
<button id="submitBtn" class="btn btn-lg btn-primary btn-block" type="submit">确定</button>
</div>
<div class="col-md-2 col-sm-5 col-xs-6">
<button onclick="javascript:window.location.href='index.action';" class="btn btn-lg btn-primary btn-block">返回</button>
</div>
</div>
</form>
    <script src="assets/js/jquery-1.11.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/docs.min.js"></script>
    <script src="assets/js/jquery-ui.min.js"></script>
    <script src="assets/js/bootstrapValidator.min.js"></script>
    <script src="assets/js/jquery.blockUI.min.js"></script>
    <script src="assets/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
    <script src="assets/js/jquery.ui.datepicker-zh-CN.js.js" type="text/javascript" charset="gb2312"></script>
    <script src="assets/js/jquery-ui-timepicker-zh-CN.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(function () {
    // 时间设置
    jQuery('#activityTime').datetimepicker({
        timeFormat: "HH:mm",
        dateFormat: "yy-mm-dd"
    });
    jQuery('#activityTime').datetimepicker('setDate', (new Date()) );
});
$(document).ready(function() {
    $('.form-signin').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	activityArea: {
                message: '球场地址无效',
                validators: {
                    notEmpty: {
                        message: '球场地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '球场地址最多50字符长'
                    }
                }
            },
            activityExpense: {
                validators: {
                	regexp: {
                        regexp: /^[0-9]+$/,
                        message: '费用只能包括数字'
                    }
                }
            }
        }
    });
});
var hasTeam;
getRelativeTeam();
function getRelativeTeam() {
	$.ajax({
        url: "${pageContext.request.contextPath}/getRelativeTeamDetailJson.action",
        type: "GET",
        data: "activityId=${param.id}",
        dataType: "json",
        beforeSend: function () {
            ShowDiv();

        },
        success: function (json) {
        	var activityTeamSelect = document.getElementById("activityTeam");
        	var varItem = new Option('无', 01);      
        	activityTeamSelect.options.add(varItem);  
        	for(position in json){
        		var varItem = new Option(json[position].teamName,json[position].teamId);      
        		activityTeamSelect.options.add(varItem);  
        	}
        	setDetail();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest);
            alert(textStatus);
            alert(errorThrown);
        }
    });
}

function setDetail(){
	$.ajax({
        url: "${pageContext.request.contextPath}/getActivityDetailById.action",
        type: "GET",
        data: "activityId=${param.id}",
        dataType: "json",
        success: function (json) {
        	initPage(json);
        },
        complete: function () {
            HiddenDiv();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest);
            alert(textStatus);
            alert(errorThrown);
        }
    });
}
function initPage(json){
	if(json.activityType=="2"){
		$("#teamDiv").hide();
	}
	var activityTeamSelect = document.getElementById("activityTeam");
	$("#activityId").val(json.activityId);
	$("#activityArea").val(json.activityArea);
	$("#activityPlayersCnt").val(json.activityPlayersCnt);
	$("#activityTime").val(json.activityTime);
	$("#activityExpense").val(json.activityExpense);
	$("#activityType").val(json.activityType);
	$("#activityTeam").val(json.activityTeam);
	var viewModel="${param.viewModel}";
	if(viewModel=="view"){
		$("#activityArea").attr("disabled","disabled");
		$("#activityPlayersCnt").attr("disabled","disabled");
		$("#activityTime").attr("disabled","disabled");
		$("#activityExpense").attr("disabled","disabled");
		$("#activityType").attr("disabled","disabled");
		$("#activityTeam").attr("disabled","disabled");
		$("#isneedright").attr("disabled","disabled");
		$("#submitBtn").hide();
	}
	$("#isneedright").val(json.activityIsneedRight);
	if(json.activityIsneedRight==1){
		$("#isneedright").val(1);
		$("#isneedright").attr("checked",true);
	}else{
		$("#isneedright").val(0);
		$("#isneedright").attr("checked",false);
	}
	createTable(json.requestList);
}


function createTable(jsonObj){
	var viewModel="${param.viewModel}";
	var htmlApprove="<thead><tr><td>用户名</td><td>操作</td></tr></thead>";
	htmlApprove+="<tbody>";
	var htmlWaitForApprove="<thead><tr><td>用户名</td><td>操作</td></tr></thead>";
	htmlWaitForApprove+="<tbody>";
	var isChecked=$("#isneedright").attr("checked");
	if(jsonObj!=""&&jsonObj!=null){
		for(var obj in jsonObj){
			if(!isChecked){
				htmlApprove+=htmlAppendApprove(jsonObj[obj],viewModel);
			}
			if(isChecked&&jsonObj[obj].requestStatus==1){
				htmlWaitForApprove+=htmlAppendWaitForApprove(jsonObj[obj],viewModel);
			}else if(isChecked&&jsonObj[obj].requestStatus==2){
				htmlApprove+=htmlAppendApprove(jsonObj[obj],viewModel);
			}
		}
	}
	htmlApprove+="</tbody>";
	htmlWaitForApprove+="</tbody>";
	$("#suretoComeTab").html(htmlApprove);
	$("#waitoComeTab").html(htmlWaitForApprove);
}
function htmlAppendApprove(jsonRequestObj,viewModel){
	var html="";
	html+="<tr>";
	html+="<td>"+jsonRequestObj.requestPlayer.playerName+"</td>";
	html+="<td>";
	html+="<a href='viewPlayer.action?playerId="+jsonRequestObj.requestPlayer.playerId+"'>查看";
	if(viewModel!="view"){
		html+="&nbsp;&nbsp;<a href='updateRequestStatus.action?requestStatus=1&requestId="+jsonRequestObj.requestId+"&activityId=${param.id}'>移除";
	}
	html+="</td>";
	html+="</tr>";
	return html;
}

function htmlAppendWaitForApprove(jsonRequestObj,viewModel){
	var html="";
	html+="<tr>";
	html+="<td>"+jsonRequestObj.requestPlayer.playerName+"</td>";
	html+="<td>";
	html+="<a href='viewPlayer.action?playerId="+jsonRequestObj.requestPlayer.playerId+"'>查看";
	if(viewModel!="view"){
		html+="&nbsp;&nbsp;<a href='updateRequestStatus.action?requestStatus=2&requestId="+jsonRequestObj.requestId+"&activityId=${param.id}'>同意";
	}
	html+="</td>";
	html+="</tr>";
	return html;
}

function changeRightVal(){
	var viewModel="${param.viewModel}";
	if(viewModel=="view")
		return;
	var isneedRight = $("#isneedright").attr("checked");
	if(isneedRight){
		$("#isneedright").val(1);
	}else{
		$("#isneedright").val(0);
	}
}

function ShowDiv() {
	$.blockUI({ message: '<h3><img src="assets/img/busy.gif" /> Loading...</h3>' });
}
function HiddenDiv() {
	$.unblockUI();
}

</script>
</body>
</html>
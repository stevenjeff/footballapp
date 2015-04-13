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
    <link href="assets/css/bootstrap-multiselect.css"type="text/css"/>
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
<form action="teamUpdate.action" class="form-signin" role="form" method="post">
<h2 class="form-signin-heading">球队详情</h2>
<font color="red">${globalerror}</font>
<input type="hidden" name="teamId" id="teamId">
<div class="form-group">
  <label for="teamName">球队名称</label>
  <input type="text" class="form-control" id="teamName" name="teamName" placeholder="请输入球队名称" maxlength="30">
</div>
<div class="form-group">
  <label for="memebercnt">球队人数</label>
  <input type="text" class="form-control" id="memebercnt" name="memebercnt" placeholder="请输入球队人数" maxlength="7">
</div>
<div class="form-group">
  <label for="teamTime">球队创建时间</label>
  <input type="text" class="form-control" id="teamTime" name="teamTime" placeholder="球队创建时间" maxlength="20" readOnly="readonly">
</div>
<div class="btn-group">
		<select id="playerSel" name="playerSel" multiple="multiple">
		</select>
		<button id="playerSel-select" class="btn btn-primary">全部选择</button>
</div>
<div id="playerSel-text" style="margin-top:6px;"></div>
</div>
<div class="form-group">
<div class="col-md-1 col-sm-1 col-xs-1 col-sm-offset-4">
<button id="submitBtn" class="btn btn-primary" type="submit">确定</button>
</div>
<div class="col-md-1 col-sm-1 col-xs-1">
<button onclick="javascript:window.location.href='teamManageSearchBySinglePlayer.action';" class="btn btn-primary">返回</button>
</div>
</div>
</form>
    <script src="assets/js/jquery-1.11.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/docs.min.js"></script>
    <script src="assets/js/jquery-ui.min.js"></script>
    <script src="assets/js/bootstrapValidator.min.js"></script>
    <script src="assets/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
    <script src="assets/js/jquery.ui.datepicker-zh-CN.js.js" type="text/javascript" charset="gb2312"></script>
    <script src="assets/js/jquery-ui-timepicker-zh-CN.js" type="text/javascript"></script>
    <script src="assets/js/jquery.blockUI.min.js"></script>
    <script src="assets/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(function () {
    // 时间设置
    jQuery('#teamTime').datepicker({
    	showOtherMonths: true,
        selectOtherMonths: true,
        changeYear: true,
        yearRange: '1960:2020' 
    });
    jQuery('#teamTime').datepicker('setDate', (new Date()) );
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
        	teamName: {
                message: '名称无效',
                validators: {
                    notEmpty: {
                        message: '球队名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '球队名称最多30字符长'
                    }
                }
            },
            memebercnt: {
                validators: {
                	regexp: {
                        regexp: /^[0-9]+$/,
                        message: '费用只能包括数字'
                    }
                }
            }
        }
    });
    $('#playerSel').multiselect({
    	enableFiltering: true,
    	onChange: function(element, checked) {
    		 if($('#playerSel').val()==null){
    			 $('#playerSel-text').text('出场人员: ').addClass('alert alert-info');
    		 }else{
    			 var value=$('#playerSel').val()+"";
    			 var playerId=value.split(":")[1];
        		 var playerName=value.split(":")[0];
        		 var html="<a href='javascript:void(0)' onclick='getPlayerDetail("+playerId+")'>"+playerName+"</a>";
    			 $('#playerSel-text').html('出场人员: ' + html).addClass('alert alert-info');
    		 }
    	}
    	});
    $('#playerSel-select').click(function(e) {
    	e.preventDefault();
    	multiselect_toggle($("#playerSel"), $(this));
    	});
    setDetail();
    function setDetail(){
    	$.ajax({
            url: "${pageContext.request.contextPath}/getTeamDetailById.action",
            type: "POST",
            data: "teamId=${param.id}",
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
    function getPlayerDetail(playerId){
    	window.open ('viewPlayer.action?id='+playerId,'newwindow','height=500,width=400,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no') 
    }
    function initPage(json){
    	$("#teamId").val(json.teamId);
    	$("#teamName").val(json.teamName);
    	$("#createtime").val(json.createtime);
    	$("#memebercnt").val(json.memebercnt);
    	var viewModel="${param.viewModel}";
    	if(viewModel=="view"){
    		$("#teamName").attr("disabled","disabled");
    		$("#createtime").attr("disabled","disabled");
    		$("#memebercnt").attr("disabled","disabled");
    		$("#submitBtn").hide();
    	}
    	createPlayerMultiSel(json.requestList);
    }

    function createPlayerMultiSel(jsonObj){
    	var viewModel="${param.viewModel}";
    	var isChecked=$("#isneedright").attr("checked");
    	var multiSelJsonStrBegin = '[';
    	var multiSelJsonStrmiddle = '';
    	var multiSelJsonStrEnd = ']';
    	var newJsonObjStr;
    	if(jsonObj!=""&&jsonObj!=null){
    		for(var obj in jsonObj){
    			if(jsonObj[obj].requestType==1||jsonObj[obj].requestType==3){
    				continue;
    			}
    			multiSelJsonStrmiddle += '{ "label": "'+jsonObj[obj].requestPlayer.playerName+'", "value": "'+jsonObj[obj].requestPlayer.playerName+":"+jsonObj[obj].requestPlayer.playerId+':'+jsonObj[obj].requestId+'","requestStatus":"'+jsonObj[obj].requestStatus+'" },';
    		}
    		if(multiSelJsonStrmiddle.length>0){
    			multiSelJsonStrmiddle=multiSelJsonStrmiddle.substring(0,multiSelJsonStrmiddle.length-1);
    		}
    		newJsonObjStr=multiSelJsonStrBegin+multiSelJsonStrmiddle+multiSelJsonStrEnd;
    		var newJsonObj = JSON.parse(newJsonObjStr); 
    		$("#playerSel").multiselect('dataprovider', newJsonObj);
    		for(var o in newJsonObj){
    			if(newJsonObj[o].requestStatus==2&&isChecked){
    				$("#playerSel").multiselect('select', newJsonObj[o].value);
    				setPlayerSelectText();
    			}
    		}
    	}
    }

    function setPlayerSelectText(){
    	if($('#playerSel').val()==null){
    		 $('#playerSel-text').text('出场人员: ').addClass('alert alert-info');
    	 }else{
    		 var value=$('#playerSel').val()+"";
    		 var playerId=value.split(":")[1];
    		 var playerName=value.split(":")[0];
    		 var html="<a href='javascript:void(0)' onclick='getPlayerDetail("+playerId+")'>"+playerName+"</a>";
    		 $('#playerSel-text').html('出场人员: ' + html).addClass('alert alert-info');
    	 }
    }
    
    function multiselect_selectAll($el) {
    	$('option', $el).each(function(element) {
    	$el.multiselect('select', $(this).val());
    	});
    	setPlayerSelectText();
    }

    function multiselect_deselectAll($el) {
    	$('option', $el).each(function(element) {
    	$el.multiselect('deselect', $(this).val());
    	});
    	setPlayerSelectText();
    }

    function multiselect_selected($el) {
    	var ret = true;
    	$('option', $el).each(function(element) {
    	if (!!!$(this).prop('selected')) {
    	ret = false;
    	}
    	});
    	return ret;
    }
    	
    function multiselect_toggle($el, $btn) {
    	if (multiselect_selected($el)) {
    	multiselect_deselectAll($el);
    	$btn.text("选择全部");
    	}
    	else {
    	multiselect_selectAll($el);
    	$btn.text("全部取消");
    	}
    }
    
    function ShowDiv() {
    	$.blockUI({ message: '<h3><img src="assets/img/busy.gif" /> Loading...</h3>' });
    }
    function HiddenDiv() {
    	$.unblockUI();
    }
});
</script>
</body>
</html>
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
<form action="teamCreate.action" class="form-signin" role="form" method="post">
<h2 class="form-signin-heading">球队创建</h2>
<font color="red">${globalerror}</font>
<div class="form-group">
  <label for="teamName">球队名称</label>
  <input type="text" class="form-control" id="teamName" name="teamName" placeholder="请输入球队名称" maxlength="30">
</div>
<div class="form-group">
  <label for="memebercnt">球队人数</label>
  <input type="text" class="form-control" id="memebercnt" name="memebercnt" placeholder="请输入球队人数" maxlength="7">
</div>
<div class="form-group">
  <label for="createTime">球队创建时间</label>
  <input type="text" class="form-control" id="createTime" name="createTime" placeholder="球队创建时间" maxlength="20" readOnly="readonly">
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
<script type="text/javascript">
jQuery(function () {
    // 时间设置
    jQuery('#createTime').datepicker({
    	showOtherMonths: true,
        selectOtherMonths: true
    });
    jQuery('#createTime').datepicker('setDate', (new Date()) );
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
});
</script>
</body>
</html>
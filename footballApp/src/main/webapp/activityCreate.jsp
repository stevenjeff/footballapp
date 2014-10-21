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
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<form action="register.action" class="form-signin" role="form" method="post">
<h2 class="form-signin-heading">球赛创建</h2>
<font color="red">${globalerror}</font>
<div class="form-group">
  <label for="username">球场地址</label>
  <input type="text" class="form-control" id="activityArea" name="activityArea" placeholder="请输入球场地址" maxlength="50">
</div>
<div class="form-group">
  <label for="activityPlayersCnt">赛制（几人制）</label>
  <select id="activityPlayersCnt" name="activityPlayersCnt" class="selectpicker show-tick show-menu-arrow span2" data-style="btn-info">
  	<option value="1">3</option>
  	<option value="5">5</option>
  	<option value="7">7</option>
  	<option value="9">9</option>
  	<option value="11">11</option>
  </select>
</div>
<div class="form-group">
  <label for="activityTime">比赛时间</label>
  <input type="text" class="form-control" id="activityTime" name="activityTime" placeholder="比赛时间" maxlength="20">
</div>
<div class="form-group">
  <label for="activityExpense">比赛费用</label>
  <input type="activityExpense" class="form-control" id="activityExpense" name="activityExpense" placeholder="请输入比赛费用" maxlength="10">
</div>
<div class="form-group">
  <label for="activityType">比赛类型</label>
  <select id="activityType" name="activityType" class="selectpicker show-tick show-menu-arrow span2" data-style="btn-info">
  	<option value="1">球队约战</option>
  	<option value="2">散客约战</option>
  </select>
</div>
<div class="form-group">
  <label for="isneedright">是否需要授权      </label>
  <input type="checkbox" name="isneedright" value="checkbox"> （比赛约战申请是否需要您的审批）
</div>

<button class="btn btn-lg btn-primary btn-block" type="submit">确定</button>
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
    jQuery('#activityTime').datetimepicker({
        timeFormat: "HH:mm:ss",
        dateFormat: "yy-mm-dd"
    });

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
            username: {
                message: '用户名无效',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 30,
                        message: '用户名必须为5到30个字符长'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名职能包括数字和字母'
                    }
                }
            },
            birthday: {
                validators: {
                	notEmpty: {
                        message: '生日不能为空'
                    },
                }
            },
            mail: {
                validators: {
                    emailAddress: {
                        message: '输入邮箱地址无效'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: '密码与密码确认必须相同'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '密码确认不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '密码与确认密码不同'
                    }
                }
            }
        }
    });
});
</script>
</body>
</html>
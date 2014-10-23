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
	<link href="assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
 	<link href="assets/css/signin.css" rel="stylesheet">
 	<link href="assets/css/jquery-ui.min.css" rel="stylesheet">
 	<link href="assets/css/bootstrapValidator.min.css" rel="stylesheet">

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
<h2 class="form-signin-heading">用户注册</h2>
<font color="red">${globalerror}</font>
<div class="form-group">
  <label for="username">用户名</label>
  <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名" maxlength="30">
</div>
<div class="form-group">
  <label for="password">密码</label>
  <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" maxlength="20">
</div>
<div class="form-group">
  <label for="password">密码确认</label>
  <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="请输入确认密码" maxlength="20">
</div>
<div class="form-group">
  <label for="sex">性别</label>
  <select id="sex" name="sex" class="selectpicker show-tick show-menu-arrow span2" data-style="btn-info">
  	<option value="1">男</option>
  	<option value="2">女</option>
  	<option value="3">未知</option>
  </select>
</div>
<div class="form-group">
  <label for="birthday">生日</label>
  <input type="text" id="birthday" name="birthday" readonly="readonly" onchange="birthDayHandler()">
</div>
<div class="form-group">
  <label for="phone">电话</label>
  <input type="text" class="form-control" id="phone" name="phone" placeholder="电话" maxlength="15">
</div>
<div class="form-group">
  <label for="qq">QQ</label>
  <input type="text" class="form-control" id="qq" name="qq" placeholder="QQ" maxlength="15">
</div>
<div class="form-group">
  <label for="weixin">微信</label>
  <input type="text" class="form-control" id="weixin" name="weixin" placeholder="微信" maxlength="15">
</div>
<div class="form-group">
  <label for="mail">邮件</label>
  <input type="text" class="form-control" id="mail" name="mail" placeholder="邮件" maxlength="40">
</div>

<button class="btn btn-lg btn-primary btn-block" type="submit">确定</button>
</form>
    <script src="assets/js/jquery-1.11.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/docs.min.js"></script>
    <script src="assets/js/jquery-ui.min.js"></script>
    <script src="assets/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">
var flag=false;
function birthDayHandler(){
	if(!flag){
		$( "#birthday").keyup();
		flag=true;
	}
}
$(function() {
    $( "#birthday" ).datepicker();
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
                        message: '用户名只能包括数字和字母'
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
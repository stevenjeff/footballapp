<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="assets/img/favicon.ico"/>

    <title>Football Association</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="assets/css/bootstrap-theme.min.css" rel="stylesheet"/>
 	<link href="assets/css/signin.css" rel="stylesheet">
 	<link href="assets/css/jquery-ui.min.css" rel="stylesheet">
 	<link href="assets/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="assets/css/dashboard.css" rel="stylesheet"/>
    <script src="assets/js/jquery-1.11.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/docs.min.js"></script>
    <script src="assets/js/bootstrapValidator.min.js"></script>
    <%@ include  file="/WEB-INF/layout/paginator.js"%>
    <script type="text/javascript">
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
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                }
            }
        });
        if('${globalerror==null?'':globalerror}'!=''){
        	$("#modalLink").click();
        }
    });
    </script>
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">天津足球约战网</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="register.jsp">注册</a></li>
            <li><a id="modalLink" data-toggle="modal" data-target="#myModal">登陆</a></li>
            <li><a href="help.jsp">使用帮助</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search..."/>
          </form>
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row">
      <%@ include  file="/WEB-INF/views/headbtn.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <p>
      	</p>
          <div id="divResult" class="table-responsive">
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
<div class="modal fade logindiv" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<form action="login.action" class="form-signin" role="form" method="post">
<h2 class="form-signin-heading">请登录</h2>
<font color="red">${globalerror}</font>
<div class="form-group">
  <label for="username">用户名</label>
  <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
</div>
<div class="form-group">
  <label for="password">密码</label>
  <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
</div>
<button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
</form>
</div>
  </body>
</html>
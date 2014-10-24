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
    <link href="assets/css/dashboard.css" rel="stylesheet"/>
    <script src="assets/js/jquery-1.11.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/docs.min.js"></script>
    <script src="assets/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript">
    var pageIndex = 0;
    var pageSize = 5;
    $(function () {

            pageIndex = 1;
            AjaxGetData(pageIndex, pageSize);

    });

    function AjaxGetData( index, size) {
        $.ajax({
            url: "${pageContext.request.contextPath}/searchByLoginPlayerJson.action",
            type: "GET",
            data: "pageNum=" + index + "&pageSize=" + size,
            dataType: "json",
            success: function (json) {

var html = "";
                 html += "<table class=\"table table-striped\">";
                 html += "<thead>";
                 html += "<tr><th colspan=7 >${pageTitle}</th></tr>";
                 html += "<tr><th>ID</th><th>Name</th><th>Location</th><th>Nature</th><th>Number</th><th>End Date</th><th>Operation</th></tr>";

                 html += "</thead>";
                 html += "<tbody>";
               for(position in json){
               html += "<tr>";
               html += "<td>"+json[position].activityArea+"</td>";
                   html += "<td>"+json[position].activityPlayerId+"</td>";
                   html += "<td>"+json[position].activityType+"</td>";
                   html += "<td>"+json[position].activityTime+"</td>";
                   html += "<td>"+json[position].activityPlayersCnt+"</td>";
                   html += "<td>"+json[position].activityTeamId+"</td>";
                   html += "<td><a href='editposition?id="+json[position].activityPlayerId+"'>Edit&nbsp;<a href='position?id="+json[position].activityPlayerId+"'>View</td>";
                   html += "</tr>";

               }
               html += "</tbody>";

              html += "<tfoot>";
              html += "<tr>";
              html += "<td colspan='7'>";
              html += "<span>Total Records:" + ${recordCount} + "; Total Page:<span id='count'>" +${pageCount} + "" + "";
              html += "<a href='javascript:void' onclick='GoToFirstPage()' id='aFirstPage' >First&nbsp;&nbsp; ";
              html += "<a href='javascript:void' onclick='GoToPrePage()' id='aPrePage' >Pre&nbsp;&nbsp; ";
              html += "<a href='javascript:void' onclick='GoToNextPage()' id='aNextPage'>Next&nbsp;&nbsp; ";
              html += "<a href='javascript:void' onclick='GoToEndPage()' id='aEndPage' >Last&nbsp;&nbsp; ";
              html += "<input type='text' size='4' /><input type='button' value='Jump' onclick='GoToAppointPage(this)' /> ";
              html += "</td>";
              html += "</tr>";
              html += "</tfoot>";
              html += "</table>";
              //alert(html);
               $('#divResult').html("");
               $('#divResult').html(html);


            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest);
                alert(textStatus);
                alert(errorThrown);
            }
        });
    }

    function GoToFirstPage() {
        pageIndex = 1;
        AjaxGetData( pageIndex, pageSize);
    }

    function GoToPrePage() {
        pageIndex -= 1;
        pageIndex = pageIndex >= 1 ? pageIndex : 1;
        AjaxGetData( pageIndex, pageSize);
    }

    function GoToNextPage() {
        if (pageIndex < parseInt($("#count").text())) {
            pageIndex += 1;
        }
            AjaxGetData( pageIndex, pageSize);
    }

    function GoToEndPage() {
        pageIndex = parseInt($("#count").text()) ;
        AjaxGetData( pageIndex, pageSize);
    }

    function GoToAppointPage(e) {
        var page = $(e).prev().val();
        if (isNaN(page)) {
            alert("Page should be a valid number");
        }
        else {
            var tempPageIndex = pageIndex;
            pageIndex = parseInt($(e).prev().val());
            if (pageIndex <= 0 || pageIndex > parseInt($("#count").text())) {
                pageIndex = tempPageIndex;
                alert("Please input valid page scope!");
            }
            else {
                AjaxGetData(pageIndex, pageSize);
            }
        }
    }
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
      <%@ include  file="headbtn.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <p>
        <button type="button" class="btn btn-sm btn-primary">创建</button>
        <button type="button" class="btn btn-sm btn-primary">修改</button>
        <button type="button" class="btn btn-sm btn-danger">删除</button>
      	</p>
          <h2 class="sub-header">My Football Match</h2>
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
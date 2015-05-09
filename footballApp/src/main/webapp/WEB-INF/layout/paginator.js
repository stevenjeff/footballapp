<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<script src="assets/js/jquery.blockUI.min.js"></script>
<script type="text/javascript">
var hasData=true;
if("${displayCols}"==""){
	window.location.href='index.action';
	hasData=false;
}
var jsonobj="";
try{
	jsonobj=eval('('+"${displayCols}"+')')
}catch(e){
	
}
var pageIndex = 0;
    var pageSize = 10;
    $(function () {
    		if(!hasData){
    			return;
    		}
            pageIndex = 1;
            AjaxGetData(pageIndex, pageSize);

    });

    function AjaxGetData( index, size) {
        $.ajax({
            url: "${pageContext.request.contextPath}/${jsonAction}",
            type: "GET",
            data: "pageNum=" + index + "&pageSize=" + size,
            dataType: "json",
            beforeSend: function () {
	            ShowDiv();

	        },
	        complete: function () {
	            HiddenDiv();
	        },
            success: function (json) {
            	 var html = "";
                 html += "<table class=\"table table-striped\">";
                 html += "<thead>";
                 html += "<tr><th colspan=7 >${pageTitle}</th></tr>";
                 html += "<tr>";
                 for(feildName in jsonobj){
                	 html +="<th>";
                	 html +=feildName;
                	 html +="</th>";
                 }
                 html += "<th></th>";
                 html += "</tr>";
                 html += "</thead>";
                 html += "<tbody>";
               for(position in json){
            	   html += "<tr>";
	               for(feildName in jsonobj){
	            	   try{
	            		   html += "<td>"+eval("json[position]."+jsonobj[feildName]+"")+"</td>";
	            	   }catch(e){
	            		   html += "<td> </td>";
	            	   }
	               }
	               var idKey=eval("json[position]."+"${idkey}"+"");
	               var str="<td>";
	               if("${editAction}"!=""){
	            	   str+="<a href='${editAction}?id="+idKey+"' class='btn btn-primary btn-sm'>详情";
	               }
	               if("${delAction}"!=""){
	            	   str+="&nbsp;<a href='${delAction}?id="+idKey+"' class='btn btn-primary btn-sm btn-danger'>删除";
	               }
	               if("${applyAction}"!=""){
	            	   if("${applyAction}"=="teamApply.action"){
	            		   str+="&nbsp;<a href='javascript:applyTeam("+idKey+")' class='btn btn-primary btn-sm'>申请";
	            	   }else{
	            		   str+="&nbsp;<a href='javascript:applyActivity("+idKey+")' class='btn btn-primary btn-sm'>申请";
	            	   }
	            		
	            	   
	               }
	               if("${approveAction}"!=""){
	            	   str+="&nbsp;<a href='${approveAction}?id="+idKey+"' class='btn btn-primary btn-block btn-sm'>同意";
	               }
	               str+="</td>";
                   html += str;
                   html += "</tr>";

               }
              html += "</tbody>";
              html += "<tfoot>";
              html += "<tr>";
              html += "<td colspan='7'>";
              html += "<span>总记录数:" + ${recordCount} + "</span>; 总页数:<span id='count'>" +${pageCount} + " ; 当前第：" +index+ "页</span>";
              html += "<a href='javascript:void(0)' onclick='GoToFirstPage()' id='aFirstPage' >&nbsp;&nbsp;首页&nbsp;&nbsp;</a> ";
              html += "<a href='javascript:void(0)' onclick='GoToPrePage()' id='aPrePage' >前一页&nbsp;&nbsp; </a> ";
              html += "<a href='javascript:void(0)' onclick='GoToNextPage()' id='aNextPage'>下一页&nbsp;&nbsp; </a> ";
              html += "<a href='javascript:void(0)' onclick='GoToEndPage()' id='aEndPage' >最后一页&nbsp;&nbsp; </a> ";
              html += "<input type='text' size='4' /><input type='button' value='跳转到' onclick='GoToAppointPage(this)' /> ";
              html += "</td>";
              html += "</tr>";
              html += "</tfoot>";
              html += "</table>";
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

    function applyActivity(idKey){
    	applyActivityValidate(idKey);

    }
    
    function applyTeam(idKey){
    	applyTeamValidate(idKey);
    }
    
	function applyActivityValidate(idkey) {
		$.ajax({
					url : "${pageContext.request.contextPath}/applyActivityValidate.action",
					type : "GET",
					data : "activityId="+idkey,
					dataType : "text",
					beforeSend: function () {
			            ShowDiv();

			        },
			        complete: function () {
			            HiddenDiv();
			        },
					success : function(obj) {
						if(obj!='""'){
							alert(obj);
						}else{
							window.location.href="applyActivity.action?activityId="+idkey;
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(XMLHttpRequest);
						alert(textStatus);
						alert(errorThrown);
					}
				});
	}
	
	function applyTeamValidate(idkey) {
		$.ajax({
					url : "${pageContext.request.contextPath}/applyTeamValidate.action",
					type : "GET",
					data : "teamId="+idkey,
					dataType : "text",
					beforeSend: function () {
			            ShowDiv();

			        },
			        complete: function () {
			            HiddenDiv();
			        },
					success : function(obj) {
			        	if(obj!='""'){
							alert(obj);
						}else{
							window.location.href="applyTeam.action?teamId="+idkey;
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
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
    function ShowDiv() {
    	$.blockUI({ message: '<h3><img src="assets/img/busy.gif" /> Loading...</h3>' });
    }
    function HiddenDiv() {
    	$.unblockUI();
    }

    </script>
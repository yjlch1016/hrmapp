<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人事管理系统 ——后台登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
<!-- 导入jquery插件 -->
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${ctx }/js/fkjava_timer.js"></script>
<script type="text/javascript">
    /** 文档加载完成后立即执行的方法 */
    // var weeks = new Array();
	
    $(function(){
    	$("#nowTime").runTimer();
    	
    	$("#exit").click(function(){
    		/** parent从主界面进行退出,避免局部刷新*/
    		parent.location = "${ctx}/logout.action";
    	})
	})
	
    
    
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="320" height="80" class="topbg"><img src="${ctx}/images/top_logo.gif" width="320" height="80"></td>
	<td class="topbg">
	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
	    <tr>
		  <td height="50" class="toplink" align="right"><img src="${ctx}/images/top_home.gif">&nbsp;&nbsp;<a href="javascript:void(0);">网站首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${ctx}/images/top_exit.gif">&nbsp;&nbsp;<a href="javascript:void(0);" id="exit">注销退出</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
		  <td height="30" class="topnavbg">
		    <table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td width="60"><img src="${ctx}/images/StatBarL.gif" width="60" height="30"></td>
				<td class="topnavlh" align="left"><img src="${ctx}/images/StatBar_admin.gif">&nbsp;&nbsp;当前用户：【${sessionScope.user_session.username}】</td>
				<td class="topnavlh" align="right"><img src="${ctx}/images/StatBar_time.gif">&nbsp;&nbsp;<span id="nowTime"></span>
				
  			 </td>
                <td width="3%"></td>
			  </tr>
			</table>
		  </td>
		</tr>
	  </table>
	</td>
  </tr>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="common/init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Xgame</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <%@ include file="common/head.jsp" %>
</head>
<body style="overflow-y: hidden">
<%@ include file="menu.jsp" %>
<%@ include file="sidebar.jsp" %>
<div style="margin-left:230px;" id="page-iframes">
    <iframe id="centerFrame" name="centerFrame" class="page-iframe" width="100%" height="100%" src="http://www.baidu.com"
            frameborder="0"></iframe>
</div>
</body>
<script type="text/javascript">
    $(function () {
        setHeight();
    });

    function setHeight() {
        var wH = window.innerHeight - 50;
        $("#sidebar").height(wH);
        $("#centerFrame").attr("height", wH);
    }


    window.onresize = function () {
        setHeight();
    }
</script>
</html>

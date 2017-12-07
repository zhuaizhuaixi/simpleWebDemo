<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<div class="container">

    <div class="form-signin" style="width: 50%;margin: 0 auto;padding: 50px 0 0 0">
        <h2 class="form-signin-heading" style="text-align: center">Xgame</h2>
        <input type="text" id="username" class="form-control" placeholder="请输入用户名" required autofocus>
        <input type="password" id="password" class="form-control" placeholder="请输入密码" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin: 40px 0px" onclick="login()">登录</button>
    </div>
</div> <!-- /container -->
</body>
<script>
    $(function () {

    });

    function login() {
        $.ajax({
            url: ctx + "/login",
            type: "post",
            data: {
                username: $("#username").val(),
                password: $("#password").val()
            },
            success: function (data) {
                if (data != null && data.code == 1) {
                    window.location.href = "/index";
                } else {
                    layer.alert( data.note);
                }
            }
        });
    }
</script>
</html>
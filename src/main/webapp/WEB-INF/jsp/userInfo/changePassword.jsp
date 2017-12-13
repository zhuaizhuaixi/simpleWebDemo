<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common/init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Xgame</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="../common/head.jsp" %>
</head>
<body style="background-color: #eee;margin: 0 auto;">
<div style="width: 80%;margin:  auto;">
    <div class="input-group" style="padding: 10px 0">
        <span class="input-group-addon" style="background-color: #00b4ef">请输入旧密码</span>
        <input type="password" class="form-control" id="oldPassword">
    </div>
    <div class="input-group" style="padding: 10px 0">
        <span class="input-group-addon" style="background-color: #00b4ef">请输入新密码</span>
        <input type="password" class="form-control" id="newPassword">
    </div>
    <div class="input-group" style="padding: 10px 0">
        <span class="input-group-addon" style="background-color: #00b4ef">请再次输入新密码</span>
        <input type="password" class="form-control" id="newPasswordAgain">
    </div>
    <div style="text-align: center">
        <button type="button" class="smal-btn" onclick="changePassword()" style="width:40%;">修改</button>
    </div>
</div>
</body>
<script type="text/javascript">
    function changePassword() {
        var oldPassword = $("#oldPassword").val();
        var newPassword = $("#newPassword").val();
        var newPasswordAgain = $("#newPasswordAgain").val();
        if (newPassword != newPasswordAgain) {
            layer.alert("两次密码输入不同，请重新输入。")
            return
        }

        $.ajax({
            url: ctx + "/user/changePassword",
            type: "post",
            data: {
                oldPassword: oldPassword,
                newPassword: newPassword
            },
            success: function (data) {
                if (data != null && data.code == 1) {
                    layer.alert(data.note,function () {
                        window.parent.location.reload();
                    });
                } else {
                    layer.alert(data.note);
                }
            }
        });

    }
</script>
</html>
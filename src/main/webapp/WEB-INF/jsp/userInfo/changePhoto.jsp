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
<body >
<div style="width: 80%;margin:  auto;">
    <form  id="photoForm">
        <input type="file" name="image"/>
    </form>
    <button type="button" class="smal-btn" onclick="changePhoto()" style="width:40%;">修改</button>
</div>
</body>
<script type="text/javascript">
    function changePhoto() {
        var form = $('#photoForm')[0];
        var data = new FormData(form);
        $.ajax({
            url: "/user/changePhoto",
            contentType: false,
            type: "post",
            enctype: 'multipart/form-data',
            processData: false,
            data: data,
            success: function (data) {
                if (data != null && data.code == 1) {
                    window.parent.layer.alert("修改头像成功",function () {
                        window.parent.parent.location.reload();
                    });
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }
</script>
</html>
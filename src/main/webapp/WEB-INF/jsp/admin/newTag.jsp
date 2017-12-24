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
        <span class="input-group-addon" style="background-color: #00b4ef">标签名</span>
        <input class="form-control" id="tagName">
    </div>
    <div style="text-align: center">
        <button type="button" class="smal-btn" onclick="addTag()" style="width:40%;">添加</button>
    </div>
</div>
</body>
<script type="text/javascript">
    function addTag() {
        var tagName = $("#tagName").val();
        if (tagName == "") {
            parent.layer.alert("标签名不能为空。")
            return
        }

        $.ajax({
            url: ctx + "/admin/addTag",
            type: "post",
            data: {
                tagName: tagName
            },
            success: function (data) {
                if (data != null && data.code == 1) {


                    parent.layer.alert(data.note);
                    window.parent.getAllTags();
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);//再执行关闭

                } else {
                    layer.alert(data.note);
                }
            }
        });

    }
</script>
</html>
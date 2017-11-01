<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ddddd</title>
    <script type="text/javascript" src="/js/common/jquery-3.2.1.js"></script>
</head>
<body>
<span > 上传文件 </span>
<form method="post" action="/sign/upload" enctype="multipart/form-data">
    <input type="input" name="text"/>
    <input type="file" name="image"/>
    <input type="submit"/>
</form>
<div style="padding: 40px;"><span id="ajax-content">ajax请求内容</span>
<button onclick="ajaxRequest()">Ajax请求</button></div>
<div style="padding: 40px;"><button onclick="gotoNewPage()">跳转到新的页面</button></div>

<iframe src="/sign/test" style="" frameborder="0"></iframe>
</body>
<script type="text/javascript">
    function gotoNewPage() {
        window.location.href = "/sign/text"
    }

    function ajaxRequest() {
        $.ajax({
            url: "/getUsers",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                $("#ajax-content").html(data.users[0].userName + " " + data.users[0].id);
            }
        })
    }
</script>
</html>

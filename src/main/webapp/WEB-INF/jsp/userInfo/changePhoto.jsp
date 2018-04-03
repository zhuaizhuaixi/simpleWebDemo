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
    <link type="text/css" rel="stylesheet" href="${ctx}/js/common/bootstrap-fileinput/css/fileinput.css"/>
    <script type="text/javascript" src="${ctx}/js/common/bootstrap-fileinput/js/fileinput.js"></script>
    <script type="text/javascript" src="${ctx}/js/common/bootstrap-fileinput/js/locales/zh.js"></script>
</head>

<body>
<div id="app" style="width: 80%;margin:  auto;text-align: center" >
    <form id="photoForm">
        <input type="file" name="image" class="projectfile"/>
    </form>
    <button type="button" class="smal-btn" onclick="changePhoto()" style="width:40%;margin:10px">修改</button>
</div>
</body>
<script type="text/javascript">

    var projectfileoptions = {
        showUpload: false,
        showRemove: false,
        language: 'zh',
        allowedPreviewTypes: ['image'],
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        maxFileSize: 2000
    };

    $(function () {
// 文件上传框
        $('input[class=projectfile]').each(function () {
            var imageurl = $(this).attr("value");

            if (imageurl) {
                var op = $.extend({
                    initialPreview: [ // 预览图片的设置
                        "<img src='" + imageurl + "' class='file-preview-image'>"]
                }, projectfileoptions);

                $(this).fileinput(op);
            } else {
                $(this).fileinput(projectfileoptions);
            }
        });
    });


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
                    window.parent.layer.alert("修改头像成功", function () {
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
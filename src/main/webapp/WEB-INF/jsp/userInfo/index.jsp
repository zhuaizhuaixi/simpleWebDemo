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
    <script type="text/javascript"
            src="${ctx}/js/common/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript"
            src="${ctx}/js/common/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
            charset="UTF-8"></script>
    <link href="${ctx}/js/common/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
          media="screen">
</head>
<body style="background-color: #eee;margin: 0 auto;">
<div style="width: 50%;margin: 100px auto;">
    <div style="text-align: center">
        <img src="/icon" alt="user-photo" id="photoConfig"/>
        <button type="button" class="smal-btn info-option" onclick="initChangePhoto()">设置头像</button>
        <button type="button" class="smal-btn info-option" onclick="initChangePassword()">修改密码</button>
        <button type="button" class="smal-btn info-option" onclick="initTagSetting()">个人标签设置</button>
    </div>
    <div class="input-group" style="padding: 10px 0">
        <span class="input-group-addon" >账户</span>
        <label class="form-control" id="userAccount"></label>
    </div>
    <div class="input-group" style="padding: 10px 0">
        <span class="input-group-addon" style="background-color: #00b4ef">昵称</span>
        <input type="text" class="form-control" id="nickname">
    </div>
    <div class="input-group" style="padding: 10px 0">
        <span class="input-group-addon" style="background-color: #00b4ef">性别</span>
        <select class="form-control" id="sex-selection">
            <option value="man">男</option>
            <option value="woman">女</option>
        </select>
    </div>
    <div class="input-group" style="padding: 10px 0">
        <span class="input-group-addon" style="background-color: #00b4ef">生日</span>
        <div class="input-group date form_date col-md-5" style="width:100%" data-date="" data-date-format="yyyy-mm-dd"
             data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
            <input id="birthday" class="form-control" size="16" type="text" value="2014-11-15" readonly>
            <span class="input-group-addon calendar"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
    </div>

    <div style="text-align: center">
        <button type="button" class="smal-btn" onclick="updateInformation()" style="width:20%;">修改</button>
    </div>

</div>

</body>
<script type="text/javascript">

    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $(function () {
        getUserInformation();
    });

    function getUserInformation() {
        $.ajax({
            url: ctx + "/user/information",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    $("#userAccount").html(data.user.username);
                    $("#nickname").val(data.user.nickname);
                    $("#sex-selection").val(data.user.sex);
                    $("#birthday").val(fmtDate(data.user.birthday));
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function fmtDate(obj) {
        var date = new Date(obj);
        var y = 1900 + date.getYear();
        var m = "0" + (date.getMonth() + 1);
        var d = "0" + date.getDate();
        return y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length);
    }

    function initChangePassword() {
        layer.open({
            type: 2,
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 1, //不显示关闭按钮
            anim: 2,
            fixed: false, //不固定
            title: "修改密码",
            area: ['380px', '260px'],
            shadeClose: true, //开启遮罩关闭
            content: ctx + '/user/initChangePassword'
        });
    }

    function initChangePhoto() {
        layer.open({
            type: 2,
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 1, //不显示关闭按钮
            anim: 2,
            fixed: false, //不固定
            title: "修改头像",
            area: ['380px', '130px'],
            shadeClose: true, //开启遮罩关闭
            content: ctx + '/user/initChangePhoto'
        });
    }

    function initTagSetting() {
        layer.open({
            type: 2,
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 1, //不显示关闭按钮
            anim: 2,
            fixed: false, //不固定
            title: "标签设置",
            area: ['380px', '260px'],
            shadeClose: true, //开启遮罩关闭
            content: ctx + '/user/initTagSetting'
        });
    }

    function updateInformation() {
        $.ajax({
            url: ctx + "/user/updateInformation",
            type: "post",
            data: {
                nickname: $("#nickname").val(),
                sex: $("#sex-selection").val(),
                birthday: $("#birthday").val()
            },
            success: function (data) {
                if (data != null && data.code == 1) {
                    layer.alert("信息修改成功", function () {
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

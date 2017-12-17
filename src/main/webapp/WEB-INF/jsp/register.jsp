<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="./common/init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Xgame</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="./common/head.jsp" %>
    <script type="text/javascript" src="${ctx}/js/common/vue.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common/element/index.js"></script>
    <link rel="stylesheet" href="${ctx}/js/common/element/index.css">
</head>
<body style="background-color: #eee;margin: 0 auto;">
<div style="width: 80%;margin:  auto;">
    <div class="input-group register-input-group">
        <span class="input-group-addon">用户名</span>
        <input type="text" class="form-control" id="username">
    </div>
    <div class="input-group register-input-group">
        <span class="input-group-addon">昵称</span>
        <input type="text" class="form-control" id="nickname">
    </div>
    <div class="input-group register-input-group">
        <span class="input-group-addon">性别</span>
        <select class="form-control" id="sex-selection">
            <option value="man">男</option>
            <option value="woman">女</option>
        </select>
    </div>
    <div class="input-group register-input-group">
        <span class="input-group-addon">密码</span>
        <input type="password" class="form-control" id="password">
    </div>
    <div class="input-group register-input-group">
        <span class="input-group-addon">确认密码</span>
        <input type="password" class="form-control" id="passwordAgain">
    </div>
    <div id="app" style="width: 80%;margin:  auto;text-align: center">
        <template style="padding: 10px 0 ">
            <el-checkbox-group v-model="checkedTags" :max="100">
                <el-checkbox-button v-for="tag in tags" :label="tag" :key="tag">{{tag}}</el-checkbox-button>
            </el-checkbox-group>
        </template>
    </div>
    <div style="text-align: center">
        <button type="button" class="smal-btn" onclick="register()" style="width:40%;">注册</button>
    </div>

</div>
</body>
<script type="text/javascript">
    var config = {
        el: '#app',
        data: {
            checkedTags: [],
            tags: []
        }
    };

    $(function () {
        getAllTags();
        new Vue(config);

    });

    function getAllTags() {
        $.ajax({
            url: ctx + "/user/allTags",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.data.tags = data.tags;
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function register() {
        var username = $("#username").val();
        var nickname = $("#nickname").val();
        var sex = $("#sex-selection").val();
        var password = $("#password").val();
        var passwordAgain = $("#passwordAgain").val();
        if (username == "" || nickname == "" || password == "" || passwordAgain == "") {
            layer.alert("请将信息填写完整。");
            return;
        }
        if (password != passwordAgain) {
            layer.alert("两次密码不同，请重新输入。");
            return;
        }
        if (config.data.checkedTags.length >= 3) {


            $.ajax({
                url: ctx + "/register",
                type: "post",
                data: {
                    username: username,
                    nickname: nickname,
                    password: password,
                    sex: sex,
                    tags: config.data.checkedTags
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
        } else {
            layer.alert("请至少选择3个标签。");
        }

    }

</script>
</html>
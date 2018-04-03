<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="${ctx}/js/common/vue.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common/element/index.js"></script>
    <link rel="stylesheet" href="${ctx}/js/common/element/index.css">
</head>
<body>

<div class="container">

    <div class="form-signin" style="width: 50%;margin: 0 auto;padding: 50px 0 0 0;text-align: center">
        <h2 class="form-signin-heading" style="text-align: center">XX游戏平台</h2>
        <input type="text" id="username" class="form-control" placeholder="请输入用户名" required autofocus>
        <input type="password" id="password" class="form-control" placeholder="请输入密码" required>
        <button class="smal-btn" type="submit" style="margin: 40px 5px;text-align: center;width:40%" onclick="login()">登录</button>
        <button class="smal-btn" type="submit" style="margin: 40px 5px;text-align: center;width:40%" onclick="initRegister()">注册</button>
    </div>
    <div id="app" style="padding:10px">
        <template>
            <el-carousel :interval="4000" type="card" height="260px">
                <el-carousel-item v-for="item in items" :key="item">
                    <image :src="item.image" :gameID="item.id" height="300px" style="width:100%"/>
                </el-carousel-item>
            </el-carousel>
        </template>
    </div>
</div> <!-- /container -->

</body>
<script type="text/javascript">

    var config = {
        el: "#app",
        data: {
            items: []
        }
    };

    $(function () {
        new Vue(config);
        getRandomGames();
    });

    function getRandomGames() {
        $.ajax({
            url: "/homepage/randomGames",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.data.items = data.games;
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

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
                    if(data.note == "admin"){
                        window.location.href = "/admin";
                    }else {
                        window.location.href = "/index";
                    }
                } else {
                    layer.alert( data.note);
                }
            }
        });
    }

    function initRegister() {
        layer.open({
            type: 2,
            skin: 'layui-layer-demo', //样式类名
            closeBtn: 1, //不显示关闭按钮
            anim: 1,
            fixed: false, //不固定
            title: "注册新用户",
            area: ['500px', '600px'],
            shadeClose: true, //开启遮罩关闭
            content: ctx + '/initRegister'
        });
    }
</script>
</html>
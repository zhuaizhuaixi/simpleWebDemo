<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xgame</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="../common/head.jsp" %>
    <script type="text/javascript" src="${ctx}/js/common/vue.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common/element/index.js"></script>
    <link rel="stylesheet" href="${ctx}/js/common/element/index.css">
</head>
<body style="margin: 0 auto;">
<div class="container" style="margin:30px">
    <div class="page-header ">
        <h1>欢迎使用XX游戏平台
            <small>（游戏推荐平台）</small>
        </h1>
        <div class="jumbotron" style="padding:0;margin:0">
            <p style="text-indent:2em;margin-top:10px;margin-bottom: 0">
                互联网的出现和普及给用户带来了大量的信息，满足了用户在信息时代对信息的需求，但随着网络的迅速发展而带来的网上信息量的大幅增长，使得用户在面对大量信息时无法从中获得对自己有用的那部分信息，对信息的使用效率反而降低了，这就是所谓的信息超载。推荐系统作为一种信息过滤的重要手段，是当前解决信息超载问题非常有潜力的方法。随着游戏产业的蓬勃发展，游戏平台的种类也会越来越多，而这时平台的用户体验将会成为该平台最大的竞争力。</p>
            <p style="text-indent:2em;">
                该系统使用基于内容推荐、协同过滤推荐进行推荐列表的生成，在计算物品相似度是使用余弦相似性、杰卡德系数进行计算。将推荐算法与相似度算法相结合，计算并比较，最后得出最合适的推荐算法，并设计算法调度策略，应用于系统。</p>
        </div>
    </div>
    <div id="app" style="padding:10px">
        <template>
            <el-carousel :interval="4000" type="card" height="260px">
                <el-carousel-item v-for="item in items" :key="item">
                    <image :src="item.image" :gameID="item.id" height="300px" style="width:100%"
                           onclick="showDetail(this)"/>
                </el-carousel-item>
            </el-carousel>
        </template>
    </div>
</div>
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

    function showDetail(obj) {
        var gameID = $(obj).attr("gameID");
        parent.layer.open({
            type: 2,
            title: "游戏详情",
            closeBtn: 1, //不显示关闭按钮
            fixed: false, //不固定
            maxmin: true, //开启最大化最小化按钮
            area: ['90%', '90%'],
            shadeClose: true,
            content: ctx + '/homepage/game?id=' + gameID
        });
    }


</script>
</html>

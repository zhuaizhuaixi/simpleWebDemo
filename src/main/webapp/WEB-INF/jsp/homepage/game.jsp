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
<body>

<div id="app">
    <div class="page-header">
        <el-button class="game-buy-button" type="primary" round>购买游戏</el-button>
        <el-button class="game-star-button" type="warning" round>关注游戏</el-button>
        <h1 id="gameName"></h1>
    </div>
    <div style="width:100%">
        <div style="width: 50%; text-align: right;display: inline-block;">
            <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513515402826&di=b44fefd2458e335ab1881312cccfcfa3&imgtype=0&src=http%3A%2F%2Fxboxone.tgbus.com%2FUploadFiles_5686%2F201707%2F20170707170600487.jpg"
                 style="width:400px;height:200px">
        </div>
        <div style="width:50%;float:right;text-align: center">
            <div class="page-header">
                 <small>游戏标签：</small>
            </div>
            <template style="padding: 10px 0 ">
                <el-checkbox-group :max="100">
                    <el-checkbox-button v-for="tag in tags" :label="tag" :key="tag">{{tag.name}}</el-checkbox-button>
                </el-checkbox-group>
            </template>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var gameID = ${gameID};

    var config = {
        el: '#app',
        data: {
            tags: []
        }
    };


    $(function () {
        new Vue(config);

        $.ajax({
            url: ctx + "/homepage/gameInformation?id=" + gameID,
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    console.dir(data.game);
                    console.log(data.tags);
                    config.data.tags = data.tags;
                    $("#gameName").html(data.game.name);
                } else {
                    layer.alert(data.note);
                }
            }
        });
    })

</script>
</html>

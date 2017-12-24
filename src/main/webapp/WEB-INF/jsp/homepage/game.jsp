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
        <el-button id="gamePurchase" class="game-buy-button" type="primary" round onclick="buyGame()">购买游戏</el-button>
        <el-button id="gameStar" class="game-star-button" type="warning" round>关注游戏</el-button>
        <label id="gameName" style="padding:0 20px"></label>
    </div>
    <div style="width:100%">
        <div style="width: 50%; text-align: right;display: inline-block;">
            <img id="gameImg" style="width:70%;height:40%">
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
    <div>
        <div class="page-header" style="padding: 0 10px">
            <small>游戏描述：</small>
        </div>
        <div style="text-align: center">
        <textarea id="gameDescription" class="game-description" disabled>
        </textarea>
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
        getGameInformation();
    });

    function getGameInformation() {
        $.ajax({
            url: ctx + "/homepage/gameInformation?id=" + gameID,
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.data.tags = data.tags;
                    $("#gameName").html(data.game.name);
                    $("#gameImg").attr("src", data.game.image)
                    $("#gameDescription").html(data.game.description)
                    if (data.hasPurchased) {
                        $("#gamePurchase").attr("disabled", "true");
                        $("#gamePurchase").html("已购买");

                    }
                    if (data.hasStared) {
                        $("#gameStar").html("取消关注");
                        $("#gameStar").click(function () {
                            unStarGame();
                        });
                    } else {
                        $("#gameStar").click(function () {
                            starGame();
                        });
                    }
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function buyGame() {
        $.ajax({
            url: ctx + "/homepage/gamePurchase?gameID=" + gameID,
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    layer.alert(data.note, function () {
                        window.location.reload();
                        window.parent.parent.getGameNumber();
                    });

                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function starGame() {
        $.ajax({
            url: ctx + "/homepage/gameStar?gameID=" + gameID,
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    layer.alert(data.note, function () {
                        window.location.reload();
                        window.parent.parent.getGameNumber();
                    });

                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function unStarGame() {
        $.ajax({
            url: ctx + "/homepage/gameUnStar?gameID=" + gameID,
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    layer.alert(data.note, function () {
                        window.location.reload();
                        window.parent.parent.getGameNumber();
                    });

                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

</script>
</html>

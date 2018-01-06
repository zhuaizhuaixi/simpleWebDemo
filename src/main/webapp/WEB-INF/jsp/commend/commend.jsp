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
<div class="page-header" style="margin: 4px 0 0;padding:0">
    <ul id="myTabs" class="nav nav-tabs" style="padding: 0 40px;">
        <li class="active"><a href="#table" id="home-tab"><span class="glyphicon glyphicon-align-justify"></span></a>
        </li>
        <li><a href="#icon" id="profile-tab"><span class="glyphicon glyphicon-th-large"></span></a></li>
        <button type="button" class="smal-btn info-option" onclick="recommend1()">余弦相似性+基于内容推荐</button>
        <button type="button" class="smal-btn info-option" onclick="recommend2()">杰卡德系数+基于内容推荐</button>
        <button type="button" class="smal-btn info-option" onclick="recommend3()">余弦相似性+协同过滤推荐</button>
        <button type="button" class="smal-btn info-option" onclick="recommend4()">杰卡德系数+协同过滤推荐</button>
    </ul>
</div>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="table">
        <div id="app" style="width: 90%;margin:  auto;text-align: center;padding: 10px 0;">

            <template>
                <el-table :data="games" border style="width: 100%">
                    <el-table-column prop="name" label="游戏名" width="300"></el-table-column>
                    <el-table-column prop="recommendIndex" label="推荐指数"></el-table-column>
                    <el-table-column prop="tags" label="类型"></el-table-column>
                    <el-table-column label="操作">
                        <template scope="scope">
                            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </template>
        </div>
    </div>
    <div class="tab-pane fade" id="icon">

    </div>
</div>
</body>
<script type="text/javascript">

    $('#myTabs a').click(function (e) {
        $(this).tab('show');
    });

    var config = {
        el: '#app',
        data: {
            games: []
        },
        methods: {
            handleEdit: function (index, row) {
                viewGameDetail(row.name, row.id);
            },
            getData: function () {
                $.ajax({
                    url: ctx + "/commend/commendList",
                    contentType: "application/json; charset=utf-8",
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data != null && data.code == 1) {
                            config.data.games = data.games;
                            produceIcon(data.games);
                        } else {
                            layer.alert(data.note);
                        }
                    }
                });
            }
        },
        mounted: function () {
            config.methods.getData();
        }
    };

    $(function () {
        new Vue(config);
    });

    function recommend1() {
        $.ajax({
            url: ctx + "/commend/cosSimilarBaseOnContent",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.methods.getData();
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function recommend2() {
        $.ajax({
            url: ctx + "/commend/jaccardSimilarBaseOnContent",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.methods.getData();
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function recommend3() {
        $.ajax({
            url: ctx + "/commend/cosSimilarBaseOnFiltering",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.methods.getData();
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function recommend4() {
        $.ajax({
            url: ctx + "/commend/jaccardSimilarBaseOnFiltering",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.methods.getData();
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function produceIcon(games) {
        $("#icon").empty();
        for (var i = 0; i < games.length; i++) {
            var iconStr = '<div class="box box-default color-palette-box mt-20 box-body col-xs-3" style="width:33%;margin: 1px;">' +
                ' <img src="' + games[i].image + '" gameID="' + games[i].id + '" class="game-img" style="width:100%;height:250px;cursor: pointer"/> ' +
                '<h3>' + games[i].name + '<h4 style="float:right">推荐指数：' + games[i].recommendIndex + '</h4></h3> </div>'
            $("#icon").append(iconStr);
        }

        $(".game-img").click(function (event) {
            viewGameDetail("", $(this).attr('gameID'))
        });
    }

    function viewGameDetail(name, id) {
        parent.layer.open({
            type: 2,
            title: name + "游戏详情",
            closeBtn: 1, //不显示关闭按钮
            fixed: false, //不固定
            maxmin: true, //开启最大化最小化按钮
            area: ['90%', '90%'],
            shadeClose: true,
            content: ctx + '/homepage/game?id=' + id
        });
    }

</script>
</html>

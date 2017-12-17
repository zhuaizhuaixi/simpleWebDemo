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

<div id="app" style="width: 90%;margin:  auto;text-align: center">
    <template>
        <el-table :data="games" border style="width: 100%">
            <el-table-column prop="name" label="游戏名" width="300"></el-table-column>
            <el-table-column prop="price" label="价格"></el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看详情</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-col :span="24" class="toolbar">
            <el-pagination layout="total,prev, pager, next,jumper "
                           @current-change="function(val){handleCurrentChange(val,'asdf');}"
                           :page-size="10" :total="total"
                           style="float:right;">
            </el-pagination>
        </el-col>
    </template>
</div>
</body>
<script type="text/javascript">

    var pageSize = 15;

    var config = {
        el: '#app',
        data: {
            games: [],
            total: 0,
            page: 1
        },
        methods: {
            handleEdit: function (index, row) {
                layer.open({
                    type: 2,
                    title: row.name + "游戏详情",
                    shadeClose: true,
                    shade: false,
                    maxmin: true, //开启最大化最小化按钮
                    area: ['90%', '90%'],
                    content: ctx + '/homepage/game?id=' + row.id
                });
            },
            handleCurrentChange: function (val, str) {
                config.methods.getData(val, pageSize);
            },
            getData: function (page, pageSize) {
                config.data.page = page;
                $.ajax({
                    url: ctx + "/homepage/gameList?page=" + page + "&pageSize=" + pageSize,
                    contentType: "application/json; charset=utf-8",
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data != null && data.code == 1) {
                            config.data.games = data.games;
                        } else {
                            layer.alert(data.note);
                        }
                    }
                });
            }
        },
        mounted:function () {
            config.methods.getData(1, pageSize);
        }
    };

    $(function () {
        new Vue(config);
        getAllGames();
    });

    function getAllGames() {
        $.ajax({
            url: ctx + "/homepage/allGames",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.data.total = data.games.length;
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }
</script>
</html>

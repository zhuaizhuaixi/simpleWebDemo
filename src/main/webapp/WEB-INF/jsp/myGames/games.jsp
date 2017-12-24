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
<div class="page-header" style="margin: 10px 0 0;">
    <label style="padding:0 20px">已购买游戏</label>
</div>
<div id="app" style="width: 90%;margin:  auto;text-align: center">

    <template>
        <el-table :data="games" border style="width: 100%">
            <el-table-column prop="name" label="游戏名" width="300"></el-table-column>
            <el-table-column prop="price" label="价格"></el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看详情</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">退购游戏</el-button>
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

    var pageSize = 10;

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
                    closeBtn: 1, //不显示关闭按钮
                    fixed: false, //不固定
                    maxmin: true, //开启最大化最小化按钮
                    area: ['90%', '90%'],
                    shadeClose: true,
                    content: ctx + '/homepage/game?id=' + row.id
                });
            },
            handleDelete: function (index,row) {
                layer.confirm('是否要退购该游戏？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    $.ajax({
                        url: ctx + "/homepage/gameUnPurchase?gameID=" + row.id,
                        contentType: "application/json; charset=utf-8",
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            if (data != null && data.code == 1) {
                                layer.alert(data.note, function () {
                                    window.location.reload();
                                    window.parent.getGameNumber();
                                });

                            } else {
                                layer.alert(data.note);
                            }
                        }
                    });
                }, function(){

                });
            },
            handleCurrentChange: function (val, str) {
                config.methods.getData(val, pageSize);
            },
            getData: function (page, pageSize) {
                config.data.page = page;
                $.ajax({
                    url: ctx + "/game/myGames?page=" + page + "&pageSize=" + pageSize,
                    contentType: "application/json; charset=utf-8",
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data != null && data.code == 1) {
                            config.data.games = data.myGames;
                        } else {
                            layer.alert(data.note);
                        }
                    }
                });
            }
        },
        mounted: function () {
            config.methods.getData(1, pageSize);
        }
    };

    $(function () {
        new Vue(config);
        getAllGames();
    });

    function getAllGames() {
        $.ajax({
            url: ctx + "/gameNumber",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.data.total = data.purchaseNumber;
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }
</script>
</html>

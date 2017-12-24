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
    <label style="padding:0 20px">登录记录</label>
</div>
<div id="app" style="width: 90%;margin:  auto;text-align: center">

    <template>
        <el-table :data="history" border style="width: 100%">
            <el-table-column prop="operation" label="操作" width="400"></el-table-column>
            <el-table-column prop="date" label="时间"></el-table-column>
            <el-table-column label="删除">
            <template scope="scope">
                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除记录</el-button>
            </template>
            </el-table-column>
        </el-table>
        <el-col :span="24" class="toolbar">
            <el-pagination layout="total,prev, pager, next,jumper "
                           @current-change="function(val){handleCurrentChange(val);}"
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
            history: [],
            total: 0,
            page: 1
        },
        methods: {
            handleDelete: function (index, row) {
                layer.confirm('是否要删除该记录？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.ajax({
                        url: ctx + "/user/deleteHistory?historyID=" + row.id,
                        contentType: "application/json; charset=utf-8",
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            if (data != null && data.code == 1) {
                                layer.alert(data.note, function () {
                                    window.location.reload();
                                });

                            } else {
                                layer.alert(data.note);
                            }
                        }
                    });
                }, function () {

                });
            },
            handleCurrentChange: function (val) {
                config.methods.getData(val, pageSize);
            },
            getData: function (page, pageSize) {
                config.data.page = page;
                $.ajax({
                    url: ctx + "/user/historyList?type=" + 1 + "&page=" + page + "&pageSize=" + pageSize,
                    contentType: "application/json; charset=utf-8",
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data != null && data.code == 1) {
                            for (var i = 0; i < data.history.length; i++) {
                                data.history[i].date = formatDateTime(data.history[i].date);
                            }
                            config.data.history = data.history;
                            config.data.total = data.total;
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
    });

    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    };
</script>
</html>

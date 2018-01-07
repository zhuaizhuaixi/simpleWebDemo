<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="common/head.jsp" %>
    <script type="text/javascript" src="${ctx}/js/common/vue.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common/element/index.js"></script>
    <link rel="stylesheet" href="${ctx}/js/common/element/index.css">
</head>
<body>

<div class="container">

    <div>
        <ul id="myTabs" class="nav nav-tabs">
            <li class="active"><a href="#analyse" id="analyse-tab">数据分析</a></li>
            <li><a href="#home" id="home-tab">游戏管理</a></li>
            <li><a href="#profile" id="profile-tab">标签管理</a></li>
            <button type="button" class="smal-btn pull-right" style="padding:5px 20px;margin:5px 15px"
                    onclick="logout()">返回登录界面
            </button>
            <button type="button" class="smal-btn pull-right" style="padding:5px 20px;margin:5px 15px"
                    onclick="addGame()">新增游戏
            </button>
            <button type="button" class="smal-btn pull-right" style="padding:5px 20px;margin:5px 15px"
                    onclick="addTag()">新增标签
            </button>

        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="analyse">
                <div class="col-md-12" style="padding:0">
                    <!-- Line chart -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <i class="fa fa-bar-chart-o"></i>
                            <h3 class="box-title">游戏销量排行榜</h3>
                        </div>
                        <div class="box-body">
                            <div class="chart">
                                <div id="gameSale" style="height:400px; width: 100%;"></div>
                            </div>
                        </div>
                        <!-- /.box-body-->
                    </div>
                </div>
                <div class="col-md-12" style="padding:0">
                    <!-- Line chart -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <i class="fa fa-bar-chart-o"></i>
                            <h3 class="box-title">最受欢迎的游戏类型</h3>
                        </div>
                        <div class="box-body">
                            <div class="chart">
                                <div id="popularTags" style="height:400px; width: 100%;"></div>
                            </div>
                        </div>
                        <!-- /.box-body-->
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="home">
                <div id="app" style="margin:  auto;">
                    <template>
                        <el-table :data="games" border style="width: 100%">
                            <el-table-column prop="name" label="游戏名" width="300"></el-table-column>
                            <el-table-column prop="price" label="价格"></el-table-column>
                            <el-table-column label="操作">
                                <template scope="scope">
                                    <el-button size="mini" @click="handleManage(scope.$index, scope.row)">管理游戏
                                    </el-button>
                                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看详情</el-button>
                                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">
                                        删除游戏
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-col :span="24" class="toolbar">
                            <el-pagination layout="total,prev, pager, next,jumper "
                                           @current-change="function(val){handleCurrentChange(val,'asdf');}"
                                           :page-size="15" :total="total" style="float:right;">
                            </el-pagination>
                        </el-col>
                    </template>
                </div>
            </div>
            <div class="tab-pane fade" id="profile">
                <div id="tagApp" style="margin:  auto;">
                    <template>
                        <el-table :data="tags" border style="width: 100%">
                            <el-table-column prop="name" label="标签名"></el-table-column>
                            <el-table-column label="操作">
                                <template scope="scope">
                                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">
                                        删除标签
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </template>
                </div>
            </div>
        </div>
    </div>
</div> <!-- /container -->

</body>
<script>
    $('#myTabs a').click(function (e) {
        $(this).tab('show');
    });

    var pageSize = 15;

    var config = {
        el: '#app',
        data: {
            games: [],
            total: 0,
            page: 1
        },
        methods: {
            handleManage: function (index, row) {
                layer.open({
                    type: 2,
                    title: row.name + "游戏详情",
                    shadeClose: true,
                    shade: false,
                    maxmin: true, //开启最大化最小化按钮
                    area: ['90%', '90%'],
                    content: ctx + '/admin/gameManage?gameID=' + row.id
                });
            },
            handleEdit: function (index, row) {
                layer.open({
                    type: 2,
                    title: row.name + "游戏详情",
                    shadeClose: true,
                    shade: false,
                    maxmin: true, //开启最大化最小化按钮
                    area: ['90%', '90%'],
                    content: ctx + '/admin/gameView?gameID=' + row.id
                });
            },
            handleDelete: function (index, row) {
                layer.confirm('是否要删除该游戏？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.ajax({
                        url: ctx + "/admin/deleteGame?gameID=" + row.id,
                        contentType: "application/json; charset=utf-8",
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            if (data != null && data.code == 1) {
                                config.methods.getData(config.data.page, pageSize);
                                layer.alert(data.note);
                            } else {
                                layer.alert(data.note);
                            }
                        }
                    });
                }, function () {

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
        mounted: function () {
            config.methods.getData(1, pageSize);
        }
    };

    var tagConfig = {
        el: '#tagApp',
        data: {
            tags: [],
            total: 0
        },
        methods: {
            handleDelete: function (index, row) {
                layer.confirm('是否要删除该标签？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.ajax({
                        url: ctx + "/admin/deleteTag?tagID=" + row.id,
                        contentType: "application/json; charset=utf-8",
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            if (data != null && data.code == 1) {
                                getAllTags();
                                layer.alert(data.note);
                            } else {
                                layer.alert(data.note);
                            }
                        }
                    });
                }, function () {

                });
            }
        },
        mounted: function () {
        }
    };

    $(function () {
        initGameSale();
        initPopularTags();
        new Vue(config);
        new Vue(tagConfig);
        getAllGames();
        getAllTags();
    });

    function logout() {
        $.ajax({
            url: ctx + "/logout",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    window.location.href = "/";
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

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

    function addGame() {
        layer.open({
            type: 2,
            title: "新增游戏",
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['90%', '90%'],
            content: ctx + '/admin/newGame'
        });
    }

    function getAllTags() {
        $.ajax({
            url: ctx + "/admin/allTags",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    tagConfig.data.tags = data.tags;
                    tagConfig.data.total = data.tags.length;
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function addTag() {
        layer.open({
            type: 2,
            title: "新增标签",
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['300px', '150px'],
            content: ctx + '/admin/newTag'
        });
    }

    function initGameSale() {
        var gameSaleChart = echarts.init($("#gameSale")[0]);
        var gameSaleOption = {
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: [],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '购买量',
                    type: 'bar',
                    barWidth: '60%',
                    data: []
                }
            ]
        };

        $.ajax({
            url: "/admin/gameSale",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    var list = data.list;
                    for (var index in list) {
                        var obj = list[index];
                        gameSaleOption.xAxis[0].data.push(obj["name"]);
                        gameSaleOption.series[0].data.push(obj["sale"]);
                    }
                    gameSaleChart.setOption(gameSaleOption);
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function initPopularTags() {
        var popularTagsChart = echarts.init($("#popularTags")[0]);
        var popularTagsOption = {
            tooltip: {},
            radar: {
                name: {
                    textStyle: {
                        color: '#fff',
                        backgroundColor: '#999',
                        borderRadius: 3,
                        padding: [3, 5]
                    }
                },
                indicator: []
            },
            series: [{
                type: 'radar',
                areaStyle: {normal: {}},
                data: [
                    {
                        value: [],
                        name: '各类型游戏销量'
                    }
                ]
            }]
        };

        $.ajax({
            url: "/admin/popularTags",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    var max = data.max;
                    for (var index in data.list) {
                        var obj = new Object();
                        obj["name"] = data.list[index]["name"];
                        obj["max"] = max;
                        popularTagsOption.series[0].data[0].value.push(data.list[index]["sale"]);
                        popularTagsOption.radar.indicator.push(obj);
                    }
                    popularTagsChart.setOption(popularTagsOption);
                } else {
                    layer.alert(data.note);
                }
            }
        });

    }
</script>
</html>
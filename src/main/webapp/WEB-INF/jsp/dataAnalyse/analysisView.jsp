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
<div class="container" style="width: 100%;margin:  auto;text-align: center">


    <div class="tab-pane fade in active" id="tab1">
        <div class="col-md-12">
            <!-- Line chart -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <i class="fa fa-bar-chart-o"></i>
                    <h3 class="box-title">已购买游戏的类型占比分析</h3>
                </div>
                <div class="box-body">
                    <div class="chart">
                        <div id="tagPercentChart" style="height:500px; width: 100%;"></div>
                    </div>
                </div>
                <!-- /.box-body-->
            </div>
        </div>
        <div class="col-md-12">
            <!-- Line chart -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <i class="fa fa-bar-chart-o"></i>
                    <h3 class="box-title">近15天游戏购买情况</h3>
                </div>
                <div class="box-body">
                    <div class="chart">
                        <div id="recentGamePurchase" style="height:500px; width: 100%;"></div>
                    </div>
                </div>
                <!-- /.box-body-->
            </div>
        </div>
        <div class="col-md-12">
            <!-- Line chart -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <i class="fa fa-bar-chart-o"></i>
                    <h3 class="box-title">标签喜好指数</h3>
                </div>
                <div class="box-body">
                    <div class="chart">
                        <div id="tagPreference" style="height:500px; width: 100%;"></div>
                    </div>
                </div>
                <!-- /.box-body-->
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    $(function () {
        initTagPercentage();
        initRecentGamePurchase();
        initTagPreference();
    });

    function initTagPercentage() {
        var tagPercentChart = echarts.init($("#tagPercentChart")[0]);
        var tagPercentOption = {
            tooltip: {
                trigger: 'item',
                formatter: " {b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left'
            },
            series: [
                {
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: [],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        $.ajax({
            url: "/analyse/tagPercentAnalyse",
            type: "post",
            dataType: "json",
            global: false,
            success: function (data) {
                if (data != null && data.code == 1) {
                    var dataArray = new Array();
                    var legendArray = new Array();
                    var map = data.resultMap;
                    for (var key in map) {
                        var obj = new Object();
                        obj["name"] = key;
                        obj["value"] = map[key];
                        dataArray.push(obj);
                        legendArray.push(key);
                    }
                    tagPercentOption.series[0].data = dataArray;
                    tagPercentOption.legend.data = legendArray;
                    tagPercentChart.setOption(tagPercentOption);
                } else {
                    layer.alert(data.note, {icon: 0});
                }
            }
        });
    }

    function initRecentGamePurchase() {
        var recentGameChart = echarts.init($("#recentGamePurchase")[0]);
        var recentGameOption = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#3398DB'
                    }
                }
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
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
                    boundaryGap: false,
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '购买游戏数：',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: []
                }
            ]
        };
        $.ajax({
            url: "/analyse/recentGameAnalyse",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    recentGameOption.xAxis[0].data = data.date;
                    recentGameOption.series[0].data = data.data;
                    recentGameChart.setOption(recentGameOption);
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function initTagPreference() {
        var tagPreferChart = echarts.init($("#tagPreference")[0]);
        var tagPreferChartOption = {
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
                        name: '标签指数'
                    }
                ]
            }]
        };
        $.ajax({
            url: "/analyse/tagPreference",
            type: "post",
            dataType: "json",
            global: false,
            success: function (data) {
                if (data != null && data.code == 1) {
                    var indicatorList = new Array();
                    var dataList = new Array();
                    var max = data.max;
                    var list = data.resultList;
                    for (var obj in list) {
                        for (var key in list[obj]) {
                            var newObj = new Object();
                            newObj["name"] = key;
                            newObj["max"] = max;
                            indicatorList.push(newObj);
                            dataList.push(list[obj][key]);
                        }
                    }
                    tagPreferChartOption.radar.indicator = indicatorList;
                    tagPreferChartOption.series[0].data[0].value = dataList;
                    tagPreferChart.setOption(tagPreferChartOption);
                } else {
                    layer.alert(data.note, {icon: 0});
                }
            }
        });

    }

</script>
</html>

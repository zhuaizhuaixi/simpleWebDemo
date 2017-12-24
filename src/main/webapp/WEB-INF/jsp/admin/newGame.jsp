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

    <div style="width:100%;height: 40%">
        <div style="width: 50%; text-align: right;display: inline-block;">
            <%--<img id="gameImg" style="width:70%;height:40%">--%>
            <div class="input-group" style="padding:0 20px;margin:20px 0;">
                <span class="input-group-addon" style="background-color: #00b4ef">游戏名称</span>
                <input id="gameName" class="form-control" style="max-width: 300px;">
            </div>
            <div class="input-group" style="padding:0 20px;margin:20px 0;">
                <span class="input-group-addon" style="background-color: #00b4ef">游戏图片</span>
                <input id="gameImg" class="form-control" style="max-width: 300px;">
            </div>
            <div class="input-group" style="padding:0 20px;margin:20px 0;">
                <span class="input-group-addon" style="background-color: #00b4ef">游戏价格</span>
                <input id="gamePrice" class="form-control" style="max-width: 300px;">
            </div>
        </div>
        <div style="width:50%;float:right;text-align: center">
            <div class="page-header">
                <small>游戏标签：</small>
            </div>
            <template style="padding: 10px 0 ">
                <el-checkbox-group v-model="checkedTags" :max="100">
                    <el-checkbox-button v-for="tag in tags" :label="tag" :key="tag">{{tag}}</el-checkbox-button>
                </el-checkbox-group>
            </template>
        </div>
    </div>
    <div>
        <div class="page-header" style="padding: 0 10px">
            <small>游戏描述：</small>
        </div>
        <div style="text-align: center">
            <textarea id="gameDescription" class="game-description"></textarea>
        </div>
    </div>
    <div style="text-align: center;margin:30px">
        <button type="button" class="smal-btn" onclick="changeGameInformation()" style="width:40%;">修改</button>
    </div>
</div>
</body>
<script type="text/javascript">
    var config = {
        el: '#app',
        data: {
            checkedTags: [],
            tags: []
        }
    };

    $(function () {
        getAllTags();
        new Vue(config);
    });

    function getAllTags() {
        $.ajax({
            url: ctx + "/user/allTags",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.data.tags = data.tags;
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }


    function changeGameInformation() {
        var gameName = $("#gameName").val();
        var gameImg = $("#gameImg").val();
        var gamePrice = $("#gamePrice").val();
        var gameDescription = $("#gameDescription").val();
        if (config.data.checkedTags.length < 3) {
            layer.alert("请至少选择3个标签。");
        } else {
            $.ajax({
                url: ctx + "/admin/addGame",
                type: "post",
                data: {
                    name: gameName,
                    description: gameDescription,
                    price: gamePrice,
                    image: gameImg,
                    checkedTags: config.data.checkedTags
                },
                success: function (data) {
                    if (data != null && data.code == 1) {
                        layer.alert(data.note, function () {
                            window.parent.config.methods.getData(window.parent.config.data.page, window.parent.pageSize);
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index);//再执行关闭
                        });
                    } else {
                        layer.alert(data.note);
                    }
                }
            });
        }
    }

</script>
</html>

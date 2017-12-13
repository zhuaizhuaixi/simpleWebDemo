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

<div id="app" style="width: 80%;margin:  auto;text-align: center">
    <template style="padding: 10px 0 ">
        <el-checkbox-group v-model="checkedTags" :max="100">
            <el-checkbox-button v-for="tag in tags" :label="tag" :key="tag">{{tag}}</el-checkbox-button>
        </el-checkbox-group>
    </template>
    <div style="text-align: center">
        <button type="button" class="smal-btn" onclick="setTags()" style="width:40%;">修改</button>
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
        getUserTags();
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

    function getUserTags() {
        $.ajax({
            url: ctx + "/user/userTags",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    config.data.checkedTags = data.tags;
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

    function setTags() {
        $.ajax({
            url: ctx + "/user/updateTags",
            type: "post",
            data: {
                checkedTags: config.data.checkedTags
            },
            success: function (data) {
                if (data != null && data.code == 1) {
                    window.parent.layer.alert("修改成功", function () {
                        window.parent.location.reload();
                    });

                } else {
                    layer.alert(data.note);
                }
            }
        });
    }


</script>
</html>

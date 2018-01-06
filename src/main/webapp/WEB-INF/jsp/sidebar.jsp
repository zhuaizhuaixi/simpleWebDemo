<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<aside class="main-sidebar" id="sidebar" style="overflow:hidden;overflow-y:auto;">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel panel-bottom">
            <div class="pull-left image">
                <img src="/icon" class="img-circle" style="width:45px;height:45px" alt="User Image">
            </div>
            <div class="pull-left info">
                <div class="form-group input-box">
                    <div class="form-control select-box" >
                        <option>${sessionScope.xgame.nickname}</option>
                    </div>
                </div>
                <%--<div class="select-mask mt-10">
                    <i id="statusPoint" class="icon icon-yuandian " style="color: gold"></i>
                    <span id="status" class="select-state">超级会员</span>
                </div>--%>
            </div>
            <div class="user-massage">
                <div class="massage-list">
                    <p>已购买游戏</p>
                    <span class="text-chengse" id="purchasedNumber"></span>
                </div>
                <div class="massage-list">
                    <p>收藏游戏</p>
                    <span class="text-chengse" id="staredNumber"></span>
                </div>
            </div>
        </div>
        <ul class="sidebar-menu">
            <li class="treeview font18"><a href="#" onclick="goPage('/myGames')">
                <span class="glyphicon glyphicon-user" style="color: green"></span>
                <span>我的游戏</span></a>
            </li>
            <li class="treeview font18"><a href="#" onclick="goPage('/staredGames')">
                <span class="glyphicon glyphicon-star" style="color: gold"></span>
                <span>关注游戏</span></a>
            </li>
            <li class="treeview font18"><a href="#" onclick="goPage('/userInfo')">
                <span class="glyphicon glyphicon-glyphicon glyphicon-cog" style="color: grey"></span>
                <span>个人信息管理</span></a>
            </li>
            <li class="treeview font18"><a href="#" onclick="goPage('/dataAnalyse')">
                <span class="glyphicon glyphicon-cloud" style="color: white"></span>
                <span>我的数据分析</span></a>
            </li>
            <li class="treeview font18"><a href="#" onclick="goPage('/commend')">
                <span class="glyphicon glyphicon-list-alt" style="color: blue"></span>
                <span>我的游戏推荐</span></a>
            </li>

            <li class="treeview font18"><a href="#">
                <span class="glyphicon glyphicon-list"></span>
                <span>我的足迹</span>
                <ul class="treeview-menu">
                    <li><a href="#" onclick="goPage('/purchaseHistory')"><i class="icon icon-yuankongxin"></i> 购买记录</a></li>
                    <li><a href="#" onclick="goPage('/visitHistory')"><i class="icon icon-yuankongxin"></i> 浏览记录</a></li>
                    <li><a href="#" onclick="goPage('/loginHistory')"><i class="icon icon-yuankongxin"></i> 登录记录</a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
</body>
<script>
    function goPage(url) {
        $("#centerFrame").attr("src", url);
    }


    $(function () {
        getGameNumber()
    });

    function getGameNumber() {
        $.ajax({
            url: ctx + "/gameNumber",
            contentType: "application/json; charset=utf-8",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data != null && data.code == 1) {
                    $("#purchasedNumber").html(data.purchaseNumber);
                    $("#staredNumber").html(data.starNumber);
                } else {
                    layer.alert(data.note);
                }
            }
        });
    }

</script>

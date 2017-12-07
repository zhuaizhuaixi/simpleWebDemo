<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<aside class="main-sidebar" id="sidebar" style="overflow:hidden;overflow-y:auto;">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel panel-bottom">
            <div class="pull-left image">
                <img src="/image/user.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <div class="form-group input-box">
                    <div class="form-control select-box" >
                        <option>zzx</option>
                    </div>
                </div>
                <div class="select-mask mt-10">
                    <i id="statusPoint" class="icon icon-yuandian " style="color: gold"></i>
                    <span id="status" class="select-state">超级会员</span>
                </div>
            </div>
            <div class="user-massage">
                <div class="massage-list">
                    <p>已购买游戏</p>
                    <span class="text-chengse" id="currentAnswer">999</span>
                </div>
                <div class="massage-list">
                    <p>收藏游戏</p>
                    <span class="text-chengse" id="noAnswer">44</span>
                </div>
            </div>
        </div>
        <ul class="sidebar-menu">
            <li class="treeview font18"><a href="#">
                <span class="glyphicon glyphicon-user" style="color: green"></span>
                <span>我的游戏</span>
                <span class="pull-right-container"> <span class="label pull-right bg-yellow">99</span> </span> </a>
            </li>
            <li class="treeview font18"><a href="#">
                <span class="glyphicon glyphicon-star" style="color: gold"></span>
                <span>关注游戏</span>
                <span class="pull-right-container"> <span class="label pull-right bg-red">99</span> </span> </a>
            </li>
            <li class="treeview font18"><a href="#" onclick="goPage('/userInfo')">
                <span class="glyphicon glyphicon-glyphicon glyphicon-cog" style="color: grey"></span>
                <span>个人信息管理</span></a>
            </li>
            <li class="treeview font18"><a href="#">
                <span class="glyphicon glyphicon-list-alt" style="color: blue"></span>
                <span>我的游戏推荐</span></a>
            </li>

            <li class="treeview font18"><a href="#">  <span>我的足迹</span>
                <span class="pull-right-container"> <i class="icon icon-fanhui-right pull-right"></i> </span> </a>
                <ul class="treeview-menu">
                    <li><a href="#"><i class="icon icon-yuankongxin"></i>购买记录</a></li>
                    <li><a href="#"><i class="icon icon-yuankongxin"></i> 浏览记录</a></li>
                    <li><a href="#"><i class="icon icon-yuankongxin"></i> 登录记录</a></li>
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

</script>

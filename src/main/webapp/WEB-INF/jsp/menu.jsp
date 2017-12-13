<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper wrapper-main" style="overflow:visible" id="menu">
    <header class="main-header header-item">
        <a href="#" class="logo" onclick="goPage('/homepage/index')">
            <span class="logo-lg">
                <span class="glyphicon glyphicon-fire" style="color: gold"></span>
                Xgame
            </span>
        </a>
        <nav class="navbar navbar-static-top">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control seach-input" id="navbar-search-input">
                </div>
            </form>
            <a href="#">
                <span class="glyphicon glyphicon-search font28" style="padding:10px 0;color:white;"></span>
            </a>

            <div class="nav-menur">
                <ul class="nav-list nav-pd">
                    <li onclick="setting()"><i class="icon font28 icon-shezhi"></i>设置</li>
                    <li onclick="logout()"><i class="icon font28 icon-zhuxiao1"></i>退出</li>
                </ul>
            </div>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown tasks-menu">
                        <a href="#" onclick="sendMessage()" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon font25 icon-xiaoxi"></i>
                        </a>
                    </li>

                </ul>
            </div>


        </nav>
    </header>
</div>
</body>
<script>
    function logout() {
        $.ajax({
            url:  ctx + "/logout",
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
</script>

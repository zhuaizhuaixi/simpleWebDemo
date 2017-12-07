<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <header class="main-header"><a href="#" class="logo">
        <span class="logo-lg">Xgame</span> </a>
        <nav class="navbar navbar-static-top">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control seach-input" id="navbar-search-input" placeholder="请输入电话号码">
                </div>
            </form>

            <div class="nav-menur">
                <ul class="nav-list">
                    <li><i class="icon font28 icon-shezhi"></i>设置</li>
                    <li><i class="icon font28 icon-zhuxiao1"></i>退出</li>
                </ul>
            </div>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown messages-menu"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i
                            class="icon font25 text-abaise icon-pingtaiguanli"></i> <span
                            class="label label-success">4</span> </a></li>
                    <li class="dropdown notifications-menu"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="icon font25 icon-xiaoxi"></i> <span class="label label-warning">10</span> </a></li>
                    <li class="dropdown tasks-menu"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i
                            class="icon font25 icon-tixing"></i> <span class="label label-danger">9</span> </a></li>
                </ul>
            </div>


        </nav>
    </header>
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel panel-bottom">
                <div class="pull-left image"><img src="image/user.png" class="img-circle" alt="User Image"></div>
                <div class="pull-left info">
                    <div class="form-group input-box">
                        <select class="form-control select-box">
                            <option><font><font>坐席007</font></font></option>
                            <option><font><font>签入</font></font></option>
                            <option><font><font>签出</font></font></option>
                            <option><font><font>切换角色</font></font></option>
                        </select>
                    </div>
                    <div class="select-mask mt-10">
                        <i class="icon icon-yuandian text-success"></i>
                        <span class="select-state">示闲</span>
                    </div>
                </div>
                <div class="user-massage">
                    <div class="massage-list">
                        <p>当前接听</p>
                        <span class="text-chengse">105</span>
                    </div>
                    <div class="massage-list">
                        <p>未接听</p>
                        <span class="text-chengse">10</span>
                    </div>
                    <div class="massage-list">
                        <p>在线排队</p>
                        <span class="text-chengse">3</span>
                    </div>
                </div>
            </div>
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="treeview font18"><a href="#"> <i class="icon font25 icon-callin"></i> <span>来电信息</span> <span
                        class="pull-right-container"> <span class="label pull-right bg-yellow">99</span> </span> </a>
                </li>
                <li class="treeview font18"><a href="#"> <i class="icon font25 icon-callout"></i> <span>待回访任务</span>
                    <span class="pull-right-container"> <span class="label pull-right bg-red">99</span> </span> </a>
                </li>
                <li class="treeview font18"><a href="widgets.html"> <i class="icon font28 icon-flow2"></i>
                    <span>代办流程</span> <span class="pull-right-container"> <small
                            class="label pull-right bg-green">99</small> </span> </a></li>
                <li class="treeview font18"><a href="#"> <i class="icon font25 icon-zhijian2"></i> <span>代办质检</span>
                    <span class="pull-right-container"><span class="label pull-right bg-blue">99</span> </span></a></li>
                <li class="treeview font18"><a href="#"> <i class="icon font25 icon-kehuqun"></i> <span>客户管理</span>
                    <span class="pull-right-container"> <i class="icon icon-fanhui-right pull-right"></i> </span> </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="icon icon-yuankongxin"></i>正式客户</a></li>
                        <li><a href="#"><i class="icon icon-yuankongxin"></i> 潜在客户</a></li>
                        <li><a href="#"><i class="icon icon-yuankongxin"></i> 客户级别</a></li>
                    </ul>
                </li>
                <li class="treeview font18"><a href="#"> <i class="icon font25 icon-fuwu"></i> <span>服务管理</span> <span
                        class="pull-right-container"><i class="icon icon-fanhui-right pull-right"></i></span> </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="icon icon-yuankongxin"></i>客户服务记录</a></li>
                        <li><a href="#"><i class="icon icon-yuankongxin"></i>短信模板</a></li>
                    </ul>
                </li>
                <li class="treeview font18"><a href="#"> <i class="icon font28 icon-zszhishi"></i> <span>知识库</span>
                    <span class="pull-right-container"><i class="icon icon-fanhui-right pull-right"></i></span></a></li>
                <li class="active font18"><a href="#"> <i class="icon font25 icon-chaxuntongji"></i> <span>统计查询</span>
                    <span class="pull-right-container"><i class="icon icon-fanhui-right pull-right"></i></span></a></li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <div class="content-wrapper" style="min-height: 916px;">
        <section class="content">
            <div class="row">
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box"><span class="info-box-icon bg-aqua"><i class="icon icon-callout"></i></span>
                        <div class="info-box-content padtop18"><span class="info-box-text font20">回访任务</span> <span
                                class="info-box-number"><span class="number-text-color">100</span>/200</span></div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box"><span class="info-box-icon bg-red"><i class="icon icon-flow2"></i></span>
                        <div class="info-box-content padtop18"><span class="info-box-text font20">流程中心</span> <span
                                class="info-box-number"><span class="number-text-color">100</span>/200</span></div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->

                <!-- fix for small devices only -->
                <div class="clearfix visible-sm-block"></div>
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box"><span class="info-box-icon bg-green"><i class="icon icon-zhijian2"></i></span>
                        <div class="info-box-content padtop18"><span class="info-box-text font20">代办质检</span> <span
                                class="info-box-number"><span class="number-text-color">100</span>/200</span></div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box"><span class="info-box-icon bg-yellow"><i
                            class="icon icon-chaxuntongji"></i></span>
                        <div class="info-box-content padtop18"><span class="info-box-text font20">报表中心</span> <span
                                class="info-box-number"><span class="number-text-color">100</span>/200</span></div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
            </div>
            <section class="content-header padbottom15">
                <h1><font><font> 关注任务 </font></font></h1>
                <ol class="breadcrumb">
                    <li class="active"><font><font>更多</font></font></li>
                </ol>
            </section>
            <div class="row card-row">
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box card-box">
                        <!--<img src="image/card-L.png">-->
                        <div class="info-box-top">
                            <span class="info-box-title"> 新开户回访</span>
                            <span class="info-icon"><i class="icon icon-chengse font20 icon-guanzhu"></i></span>
                        </div>
                        <div class="info-box-bottom">
                            <span class="info-number">总数 150</span>
                            <span class="info-undone">未完成  <span class="text-hongse">20</span></span>
                            <span class="info-task"> 分配任务 <span class="text-lvse">100</span></span>
                        </div>
                    </div>
                    <!-- /.info-box -->
                </div>
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box card-box">
                        <!--<img src="image/card-L.png">-->
                        <div class="info-box-top">
                            <span class="info-box-title"> 新开户回访</span>
                            <span class="info-icon"><i class="icon icon-chengse font20 icon-guanzhu"></i></span>
                        </div>
                        <div class="info-box-bottom">
                            <span class="info-number">总数 150</span>
                            <span class="info-undone">未完成  <span class="text-hongse">20</span></span>
                            <span class="info-task"> 分配任务 <span class="text-lvse">100</span></span>
                        </div>
                    </div>
                    <!-- /.info-box -->
                </div>
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="info-box card-box">
                        <!--<img src="image/card-L.png">-->
                        <div class="info-box-top">
                            <span class="info-box-title"> 新开户回访</span>
                            <span class="info-icon"><i class="icon icon-chengse font20 icon-guanzhu"></i></span>
                        </div>
                        <div class="info-box-bottom">
                            <span class="info-number">总数 150</span>
                            <span class="info-undone">未完成  <span class="text-hongse">20</span></span>
                            <span class="info-task"> 分配任务 <span class="text-lvse">100</span></span>
                        </div>
                    </div>
                    <!-- /.info-box -->
                </div>
            </div>
            <div class="row padtop25">
                <div class="col-md-6">
                    <!-- Line chart -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <i class="fa fa-bar-chart-o"></i>

                            <h3 class="box-title">当日时段呼入</h3>
                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                        class="fa fa-minus"></i>
                                </button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                        class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <div class="chart">
                                <canvas id="areaChart" style="height: 250px; width: 509px;" height="250"
                                        width="509"></canvas>
                            </div>
                        </div>
                        <!-- /.box-body-->
                    </div>
                </div>
                <div class="col-md-6">
                    <!-- Bar chart -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <i class="fa fa-bar-chart-o"></i>

                            <h3 class="box-title">当日地区呼入</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                        class="fa fa-minus"></i>
                                </button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                        class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <div id="bar-chart" style="height: 300px;"></div>
                        </div>
                        <!-- /.box-body-->
                    </div>

                </div>
                <div class="col-md-6">
                    <!-- Line chart -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <i class="fa fa-bar-chart-o"></i>

                            <h3 class="box-title">当日时段呼入</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                        class="fa fa-minus"></i>
                                </button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                        class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <div id="line-chart" style="height: 300px;"></div>
                        </div>
                        <!-- /.box-body-->
                    </div>
                </div>
                <div class="col-md-6">
                    <!-- Bar chart -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <i class="fa fa-bar-chart-o"></i>

                            <h3 class="box-title">当日地区呼入</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                        class="fa fa-minus"></i>
                                </button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                        class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <div id="bar-chart" style="height: 300px;"></div>
                        </div>
                        <!-- /.box-body-->
                    </div>

                </div>
                <!-- /.col -->
            </div>
        </section>
    </div>
</div>
</body>
<script>


</script>
</html>

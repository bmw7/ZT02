<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit"> <!-- 针对国产浏览器的设置 -->

    <!-- Font CSS -->
    <link rel='stylesheet' type='text/css' href='${base}/resource/admin/assets/skin/default_skin/css/font-opensans.css'>
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/font-roboto.css">

    <!-- Theme CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/ie9/html5shiv.js"></script>
    <script src="assets/js/ie9/respond.min.js"></script>
    <![endif]-->
</head>

<body>

    <!-- Start: Main -->
    <div id="main">

        <!-- Start: Header -->
        <header class="navbar navbar-fixed-top bg-primary">

            <div class="navbar-branding">
                <a class="navbar-brand" href="main"> <b>Admin</b>Panel </a>
                <span id="toggle_sidemenu_l" class="glyphicons glyphicons-show_lines" style="z-index:10000000000000"></span>
                <ul class="nav navbar-nav pull-right hidden">
                    <li>
                        <a href="#" class="sidebar-menu-toggle"><span class="octicon octicon-ruby fs20 mr10 pull-right "></span></a>
                    </li>
                </ul>
            </div>

            <!-- 全屏 -->
            <ul class="nav navbar-nav navbar-left">
            
            
            
                <li>
                    <a class="request-fullscreen toggle-active" href="#"><span class="octicon octicon-screen-full fs18"></span></a>
                </li>
            </ul>

            <!-- 搜索 -->
            <form class="navbar-form navbar-left navbar-search ml5">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="搜索..." style="margin-top:1px">
                </div>
            </form>

            <span class="glyphicons glyphicons-search fs15" style="margin-top:20px;cursor:pointer"></span>



            <ul class="nav navbar-nav navbar-right">

                <!-- 网站首页 -->
                <li class="dropdown dropdown-item-slide">
                    <a class="dropdown-toggle pl10 pr10" href="${base}" target="_blank">
                        <span class="glyphicons glyphicons-home fs14"></span>
                        <span class="fw600">网站首页</span>
                    </a>
                </li>

                <!-- 语言选择 -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicons glyphicons-more_items fs14"></span>
                        <span class="fw600">语言选择</span>
                    </a>
                    <ul class="dropdown-menu animated animated-short flipInX" role="menu">

                        <li>
                            <a href="javascript:void(0);" class="fw600">
                                <span class="flag-xs flag-tr mr10"></span> <span style="font-size:11px">中文简体</span> </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="fw600">
                                <span class="flag-xs flag-us mr10"></span> English </a>
                        </li>
                    </ul>
                </li>

                <!-- 管理设置 -->
                <li class="dropdown dropdown-item-slide">
                    <a class="dropdown-toggle pl10 pr10" data-toggle="dropdown" href="#">
                        <span class="glyphicons glyphicons-cogwheels fs14"></span>
                        <span class="fw600">管理设置</span>
                    </a>
                </li>

                <!-- 安全退出 -->
                <li class="dropdown">
                    <a class="pl10 pr10 special" href="${base}/admin/logout">
                        <span class="glyphicons glyphicons-power fs14"></span>
                        <span class="fw600">安全退出</span>
                    </a>
                </li>
            </ul>
        </header>
        <!-- End: Header -->

        <!-- Start: Sidebar -->
        <aside id="sidebar_left" class="nano  sidebar-light light">
            <div class="nano-content">
                <!-- sidebar menu -->
                <ul class="nav sidebar-menu">
                    <li class="sidebar-label pt20">欢迎访问</li>

                    <li class="active">
                        <a href="${base}/admin/index" target="iframe">
                            <span class="glyphicons glyphicons-home"></span>
                            <span class="sidebar-title">管理首页</span>
                        </a>
                    </li>

                    <li>
                        <a href="${base}/admin/docs" target="iframe">
                            <span class="glyphicons glyphicons-book_open"></span>
                            <span class="sidebar-title">帮助文档</span>
                        </a>
                    </li>


                    <li class="sidebar-label pt15">内容管理</li>
                    <li>
                        <a href="${base}/admin/article/add" target="iframe">
                            <span class="glyphicons glyphicons-edit"></span>
                            <span class="sidebar-title">文章发布</span>
                        </a>
                    </li>

                    <li>
                        <a href="${base}/admin/article/manage" target="iframe">
                            <span class="glyphicons glyphicons-file_import"></span>
                            <span class="sidebar-title">文章管理</span>
                        </a>
                    </li>

                    <li>
                        <a href="${base}/admin/article/category/list" target="iframe">
                            <span class="glyphicons glyphicons-list"></span>
                            <span class="sidebar-title">文章分类</span>
                        </a>
                    </li>
                    
                    <li>
                        <a href="${base}/admin/guestbook/list" target="iframe">
                            <span class="glyphicons glyphicons-comments"></span>
                            <span class="sidebar-title">留言管理</span>
                        </a>
                    </li>
                    
                    <li>
                        <a href="${base}/admin/friendlinks/list" target="iframe">
                            <span class="glyphicons glyphicons-link"></span>
                            <span class="sidebar-title">友情链接</span>
                        </a>
                    </li>

                    <!-- sidebar bullets -->
                    <li class="sidebar-label pt20">系统相关</li>


                    <li>
                        <a class="accordion-toggle" href="#">
                            <span class="glyphicons glyphicons-keys"></span>
                            <span class="sidebar-title">权限管理</span>
                            <span class="caret"></span>
                        </a>
                        <ul class="nav sub-nav">
                            <li>
                                <a href="${base}/admin/account/list" target="iframe">
                                    <span class="glyphicons glyphicons-group"></span> 账号管理 </a>
                            </li>
                            <li>
                                <a href="${base}/admin/account/role/list" target="iframe">
                                    <span class="glyphicons glyphicons-share_alt"></span> 角色管理 </a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="${base}/admin/setting/navigation/list" target="iframe">
                            <span class="glyphicons glyphicons-adjust_alt"></span>
                            <span class="sidebar-title">导航设置</span>
                        </a>
                    </li>
                    <li>
                        <a href="${base}/admin/setting/edit" target="iframe">
                            <span class="glyphicons glyphicons-settings"></span>
                            <span class="sidebar-title">系统设置</span>
                        </a>
                    </li>
 

                    <!-- sidebar progress bars -->
                    
                    
                </ul>


                <div class="sidebar-toggle-mini">
                    <a href="#">
                        <span class="fa fa-sign-out"></span>
                    </a>
                </div>
            </div>
        </aside>

        <!-- Start: Content -->
        <section id="content_wrapper">   
         	<section id="content">
                 <iframe id="iframe" name="iframe" src="${base}/admin/index" frameborder="0" width="100%" height="auto" scrolling="no" onLoad="iFrameHeight()"></iframe>
            </section>
        </section>

    </div>
    <!-- End: Main -->

    <!-- BEGIN: PAGE SCRIPTS -->

<!-- jQuery -->
<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery_ui/jquery-ui.min.js"></script>

<!-- angularjs -->
<script type="text/javascript" src="${base}/resource/admin/assets/js/angular.min.js"></script>

<!-- Bootstrap -->
<script type="text/javascript" src="${base}/resource/admin/assets/js/bootstrap/bootstrap.min.js"></script>

<!-- BootboxJS -->
<script type="text/javascript" src="${base}/resource/admin/vendor/plugins/bootboxjs/bootbox.min.js"></script>

<!-- Theme Javascript -->
<script type="text/javascript" src="${base}/resource/admin/assets/js/utility/utility.js"></script>
<script type="text/javascript" src="${base}/resource/admin/assets/js/main.js"></script>
<script type="text/javascript" src="${base}/resource/admin/assets/js/demo.js"></script>
<script type="text/javascript">
var setFrameHeight;
var setHeight;

// iframe高度
function iFrameHeight() {  
	var ifm = document.getElementById("iframe");   
	var subWeb = document.frames ? document.frames["iframe"].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight;
	   setHeight(ifm.height);
	} 
 
}  

// 获取浏览器可见区域高度
function clientHeight(){
	var height;
 	if(document.documentElement.clientHeight > 0){
 		height = document.documentElement.clientHeight;
 	}else{
 		height = document.body.clientHeight;
 	}
 	return height;
}

// bootbox弹出提示框
function bootAlert(info){ 
   bootbox.setDefaults({locale:"zh_CN"});
   bootbox.alert({ 
   		message:info,
   		callback:function(){ iframe.window.location.reload(); }
   }); 
}
 
// bootbox删除提示框
function bootConfirm(url,name){
   bootbox.setDefaults({locale:"zh_CN"});
   bootbox.confirm("确定要删除 " +name+ " 吗?", function(result) {  
      if (result) { iframe.window.location.href = url; } 
   }); 
}

jQuery(document).ready(function() {
    "use strict";    
    Core.init(); // Init Theme Core 
    Demo.init(); // Init Theme Core 
    
    setFrameHeight = function(data){
    	var height = parseInt($('#iframe').height()) + parseInt(data);
    	$('#iframe').css('height',height);
    }
    
    setHeight = function(data){
    	$('#iframe').css('height',data);
    }
});

</script>

</body>
</html>
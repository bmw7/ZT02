<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>信息超时 - 后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit"> 
  
    <link rel='stylesheet' type='text/css' href='${base}/resource/admin/assets/skin/default_skin/css/font-opensans.css'> <!-- Font CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/font-roboto.css">  <!-- Theme CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css"> <!-- Admin Forms CSS -->
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
   		<script src="${base}/resource/admin/assets/js/ie9/html5shiv.js"></script>
   		<script src="${base}/resource/admin/assets/js/ie9/respond.min.js"></script>
   <![endif]-->
</head>

<body class="external-page sb-l-c sb-r-c">

    <!-- Start: Main -->
    <div id="main" class="animated fadeIn">

        <!-- Start: Content -->
        <section id="content_wrapper">

            <!-- begin canvas animation bg -->
            <div id="canvas-wrapper">
                <canvas id="demo-canvas"></canvas>
            </div>

            <!-- Begin: Content -->
            <section id="content">

                <div class="admin-form theme-info mw500" style="margin-top: 10%;" id="login1">
                    <div class="row mb15 table-layout">

                        <div class="col-xs-6 center-block text-center va-m pln">
                            <a href="${base}/admin" title="返回登陆页面">
                                <img src="${base}/resource/admin/assets/img/logos/logo_white.png" title="返回登陆页面" class="img-responsive w250">
                            </a>
                        </div>

                        <div class="col-xs-6 text-right va-b pr5">
                            <div class="login-links">链接超时&nbsp;&nbsp;|&nbsp;&nbsp;后台管理系统</div>
                        </div>
                    </div>

                    <div class="panel panel-info mv10 heading-border br-n">

                    
                        <form method="post" action="${base}/reset/back" id="inputForm">
                            <div class="panel-body bg-white p15 pt25">
                                <p>&nbsp;</p><p>&nbsp;</p>
                                <div class="alert alert-micro alert-border-left alert-info pastel alert-dismissable mn">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                    <i class="fa fa-info pr10"></i> 提示信息<br><span id="forget"><h2>链接超时，请重新操作  <a href="${base}/reset/forget">重置密码</h2></a></span>
                                </div>
								<p>&nbsp;</p><p>&nbsp;</p>
                            </div>
                           
                        </form>

                    </div>

                </div>

            </section>
            <!-- End: Content -->

        </section>
        <!-- End: Content-Wrapper -->

    </div>
    <!-- End: Main -->

    <!-- BEGIN: PAGE SCRIPTS -->

    <!-- jQuery -->
    <script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery_ui/jquery-ui.min.js"></script>

    <!-- Bootstrap -->
    <script type="text/javascript" src="${base}/resource/admin/assets/js/bootstrap/bootstrap.min.js"></script>

    <!-- Page Plugins -->
    <script type="text/javascript" src="${base}/resource/admin/assets/js/pages/login/EasePack.min.js"></script>
    <script type="text/javascript" src="${base}/resource/admin/assets/js/pages/login/rAF.js"></script>
    <script type="text/javascript" src="${base}/resource/admin/assets/js/pages/login/TweenLite.min.js"></script>
    <script type="text/javascript" src="${base}/resource/admin/assets/js/pages/login/login.js"></script>

    <!-- Theme Javascript -->
    <script type="text/javascript" src="${base}/resource/admin/assets/js/utility/utility.js"></script>
    <script type="text/javascript" src="${base}/resource/admin/assets/js/main.js"></script>
    <script type="text/javascript" src="${base}/resource/admin/assets/js/demo.js"></script>

    <!-- Page Javascript -->
    <script type="text/javascript">
        jQuery(document).ready(function() {

            "use strict";

            // Init Theme Core      
            Core.init();

            // Init Demo JS
            Demo.init();

            // Init CanvasBG and pass target starting location
            CanvasBG.init({
                Loc: {
                    x: window.innerWidth / 2.1,
                    y: window.innerHeight / 2.2
                },
            });
            
			

        });
    </script>

    <!-- END: PAGE SCRIPTS -->

</body>

</html>

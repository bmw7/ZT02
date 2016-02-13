<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>忘记密码 - 后台管理系统</title>
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
                            <div class="login-links">忘记密码&nbsp;&nbsp;|&nbsp;&nbsp;后台管理系统</div>
                        </div>
                    </div>

                    <div class="panel panel-info mv10 heading-border br-n">

                        <!-- end .form-header section -->
                        <form method="post" action="${base}/reset/back" id="inputForm">
                            <div class="panel-body bg-white p15 pt25">

                                <div class="alert alert-micro alert-border-left alert-info pastel alert-dismissable mn">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                    <i class="fa fa-info pr10"></i> <span id="forget">请输入您的管理账号或邮箱，点击发送后，按提示进一步操作。</span>
                                </div>

                            </div>
                            <!-- end .form-body section -->
                            <div class="panel-footer p25 pv15">

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="section mn">
                                            <label for="username" class="field-label text-muted fs18 mb10 hidden">重置密码</label>
                                            <div class="smart-widget sm-right smr-80">
                                                <label for="email" class="field prepend-icon">
                                                    <input type="text" name="username" id="username" class="gui-input" placeholder="请输入账号或邮箱">
                                                    <label for="username" class="field-icon"><i class="fa fa-envelope-o"></i></label>
                                                </label>
                                                <label for="username" class="button" id="btn"> 发 送 </label>
                                            </div>
                                            <!-- end .smart-widget section -->
                                        </div>
                                        <!-- end section -->
                                    </div>

                                </div>

                            </div>
                            <!-- end .form-footer section -->
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
            
            
            // 重置密码
            $('#btn').click(function(){
		    	$.post('${base}/reset/back',{username:$("#username").val()},function(data){ 
					$("#forget").html(data);
				});
			});
			
			
        });
    </script>

    <!-- END: PAGE SCRIPTS -->

</body>

</html>

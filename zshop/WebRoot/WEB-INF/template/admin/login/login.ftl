<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel='stylesheet' type='text/css' href='${base}/resource/admin/assets/skin/default_skin/css/font-opensans.css'> <!-- Font CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/font-roboto.css">  <!-- Theme CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
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

                <div class="admin-form theme-info">

                    <div class="row mb15 table-layout">

                        <div class="col-xs-6 va-m pln">
                            <a href="${base}/admin">
                                <img src="${base}/resource/admin/assets/img/logos/logo_white.png" title="后台管理系统" class="img-responsive w250">
                            </a>
                        </div>

                        <div class="col-xs-6 text-right va-b pr5">
                            <div class="login-links">
                                <a href="${base}/reset/forget" class="active" title="忘记密码?">忘记密码 ? </a>&nbsp;&nbsp;|&nbsp;&nbsp;后台管理系统
                            </div>

                        </div>

                    </div>

                    <div class="panel panel-info mt10 br-n heading-border custom_tips_overflow">

                        <!-- 此处可放置提示性文字 -->
                        <div id="custom_tips">
                            <div class="col-sm-11 col-md-11 custom_tips_container">
                                <div class="alert alert-micro alert-border-left alert-info pastel alert-dismissable">
                                    <i class="fa fa-info pr10"></i> 提示信息<div id="failure"></div>
                                </div>
                            </div>
                        </div>

                        <!-- end .form-header section -->
                        <form method="post" action="${base}/admin/login" id="myform">
                        
                            <input type="hidden" id="encryptUsername" name="encryptUsername">  
                            <input type="hidden" id="encryptPassword" name="encryptPassword">
                            
                            <div class="panel-body bg-light p30">
                                <div class="row">
                                    <div class="col-sm-7 pr30">
                                        <div class="section">
                                            <label for="username" class="field-label text-muted fs18 mb10">账 号</label>
                                            <label for="username" class="field prepend-icon">
                                                <input type="text" name="username" id="username" class="gui-input" placeholder="账号或邮箱">
                                                <label for="username" class="field-icon"><i class="fa fa-user"></i>
                                                </label>
                                            </label>
                                        </div>
                                        <!-- end section -->

                                        <div class="section">
                                            <label for="password" class="field-label text-muted fs18 mb10">密 码</label>
                                            <label for="password" class="field prepend-icon">
                                                <input type="password" name="pwd" id="pwd" class="gui-input" placeholder="请输入密码">
                                                <label for="pwd" class="field-icon"><i class="fa fa-lock"></i>
                                                </label>
                                            </label>
                                        </div>
                                        <!-- end section -->

                                    </div>
                                    <div class="col-sm-5 br-l br-grey pl30" style="margin-top:3px">
                                        <label class="field-label text-muted fs18 mb10"> 请输入验证码</label>
                                        
                                            <img src="${base}/kaptcha.jpg" id="kaptchaImage"/>
                                            
                                            <label for="password" class="field prepend-icon" style="margin-top:22px">
                                                <input type="text" name="kaptcha" id="kaptcha" class="gui-input" placeholder="请输入验证码">
                                                <label for="kaptcha" class="field-icon"><i class="fa fa-qrcode"></i>
                                                </label>
                                            </label>
                                    </div>
                                </div>
                            </div>
                            <!-- end .form-body section -->
                            <div class="panel-footer clearfix p10 ph15">
                                <button type="submit" class="button btn-primary mr10 pull-right">登  陆</button>
                                <label class="switch block switch-primary pull-left input-align mt10">
                                    <input type="checkbox" name="remember" id="remember" checked>
                                    <label for="remember" data-on="是" data-off="否"></label>
                                    <span>记住我</span>
                                </label>
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
    <script type="text/javascript" src="${base}/resource/admin/js/jquery.cookie.js"></script>
    
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
    
    <!-- Vadlidate And RSA Encrypt Service -->
    <script type="text/javascript" src="${base}/resource/admin/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${base}/resource/admin/js/rsa/jsbn.js"></script>
	<script type="text/javascript" src="${base}/resource/admin/js/rsa/prng4.js"></script>
	<script type="text/javascript" src="${base}/resource/admin/js/rsa/rng.js"></script>
	<script type="text/javascript" src="${base}/resource/admin/js/rsa/rsa.js"></script>
	<script type="text/javascript" src="${base}/resource/admin/js/rsa/base64.js"></script> 

    <!-- Page Javascript -->
    <script type="text/javascript">
        jQuery(document).ready(function() {

            "use strict";     
            Core.init();  // Init Theme Core
            Demo.init();  // Init Demo JS

            // Init CanvasBG and pass target starting location
            CanvasBG.init({
                Loc: {
                    x: window.innerWidth / 2,
                    y: window.innerHeight / 3.3
                },
            });
            
            
            
            /* -----------------------------  用户密码保存cookie操作  ---------------------------- */ 
	    	
	    	var usernameCookieValue = $.cookie('username');
			var passwordCookieValue = $.cookie('password');
	    	
			
			// cookie中有登陆表单值，则填入登陆表单
			if((usernameCookieValue != '' && usernameCookieValue != null) && (passwordCookieValue != '' && passwordCookieValue != null)){
				$('#username').val(usernameCookieValue);
				$('#pwd').val(passwordCookieValue); 
			}
	    	
	    	// 点击 是否记住 登陆表单值 checkbox 操作
	    	$('#remember').click(function(){
	    		 if($(this).prop("checked")!=true){
	    		 	// 未选中,则在cookie值不为空的情况下,删除cookie
	    		 	if((usernameCookieValue != '' && usernameCookieValue != null) && (passwordCookieValue != '' && passwordCookieValue != null)){
				    	$.cookie('username', '', { expires: -1, path: '/' });
						$.cookie('password', '', { expires: -1, path: '/' });
				    }
	    		 }
	    	});
	    	
	    	
            
            /* -----------------------------  登陆表单RSA加密操作  ---------------------------- */ 
            
	    	$('#myform').submit(function(){
	    	
	    	    var $password = $('#pwd');
				var $encryptPassword = $('#encryptPassword');
			
				// 生成公钥
				var rsaKey = new RSAKey();
				rsaKey.setPublic(b64tohex("${modulus}"), b64tohex("${exponent}"));
				
				// 用公钥进行加密
				var encryptPassword = hex2b64(rsaKey.encrypt($password.val()));
			
				// 将加密之后的数据赋值给表单属性，以传给服务器解密
				$encryptPassword.val(encryptPassword);
				
				// 额外操作：cookie保存账号和密码
				var usernameInputValue = $('#username').val();
	    	    var passwordInputValue = $('#pwd').val();
				
		    	if($('#remember').prop("checked")==true){
					if(usernameInputValue != '' && passwordInputValue != ''){
						$.cookie('username',usernameInputValue, {expires:7,path:'/'});
						$.cookie('password',passwordInputValue, {expires:7,path:'/'});
					}
		    	}
		    	
	    	});
	    	
	    	// 弹出登陆错误信息
	    	if("${failure}"!=""){
	    	    $('#custom_tips').show();
	    		$('#failure').html("<h1>${failure}</h1>");
	    	}
	    	
	    	// 登陆表单验证
	    	$('#myform').validate({
	    	rules:{
	    		username:{required:true},
	    		pwd:{required:true},
	    		kaptcha:{required:true,
	    		         remote:{
	    		         	type:"post",
	    		         	url:"${base}/admin/kaptchaCheck",
	    		         	data:{ kaptcha:function(){ return $('#kaptcha').val();}}
	    		         }}
	    	},
	    	messages:{
	    		username:{required:"请输入用户名"},
	    		pwd:{required:"密码不能为空！"},
	    		kaptcha:{required:"验证码不能为空！",remote:jQuery.format("错误的验证码")}}
	    	});
	    	

			// 验证码
	    	$('#kaptchaImage').click(function () { 
	    		$(this).attr('src', '${base}/kaptcha.jpg?' + Math.floor(Math.random()*100) );
	    	});
	    	


        });
    </script>

    <!-- END: PAGE SCRIPTS -->

</body>
</html>

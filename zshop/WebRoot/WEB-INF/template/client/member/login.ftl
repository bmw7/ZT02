<!DOCTYPE html>
<html  lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆表单</title>
    <link rel="stylesheet" href="${base}/resource/client/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${base}/resource/client/css/common.css" />
    <style>
    	.row:nth-child(2){ margin-top:100px;margin-bottom:50px; }
    </style>
</head>
<body>

<div class="container">

	<#include "/client/include/header.ftl">
	
	<div class="row"><h1 class="text-center">登陆表单</h1></div>
    
    <div class="col-md-offset-3 col-md-6">
    <div class="form-horizontal">
        <p class="help-block"></p>   
        <div class="form-group">
            <label for="username" class="control-label col-md-4">账号</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="username" name="username" placeholder="用户名/邮箱/手机号码">
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="control-label col-md-4">密码</label>
            <div class="col-md-8">
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="remember">记住
                    </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <button type="submit" class="btn btn-default" id="submit"> 提 交  </button>
            </div>
        </div>

    </div>
	</div>
	
	
</div>

<!--[if lt IE 9]>
<script src="${base}/resource/client/js/html5shiv.min.js"></script>
<script src="${base}/resource/client/js/respond.min.js"></script>
<![endif]-->

<script src="${base}/resource/client/js/jquery.min.js"></script>
<script src="${base}/resource/client/js/bootstrap.min.js"></script>
<script src="${base}/resource/admin/js/jquery.cookie.js"></script>

<!--RSA Encrypt-->
<script src="${base}/resource/admin/js/rsa/jsbn.js"></script>
<script src="${base}/resource/admin/js/rsa/prng4.js"></script>
<script src="${base}/resource/admin/js/rsa/rng.js"></script>
<script src="${base}/resource/admin/js/rsa/rsa.js"></script>
<script src="${base}/resource/admin/js/rsa/base64.js"></script> 
<script language="javascript">
$(document).ready(function(){

     /* -----  登陆表单RSA加密操作  ---- */ 
     
	 $('#submit').click(function(){

	 	 // 获得加密参数 modulus 和 exponent,并加密数据
		 $.ajax({
		 	method: 'POST',
		 	url: '${base}/login/encrypt',
		 	success: function(data){
		 	
		 		// 获得加密参数 modulus 和 exponent
		 		var params   = data.split('[mavict]');
		 		var modulus  = params[0];
		 		var exponent = params[1];
	
		 		// 生成公钥
				var rsaKey = new RSAKey();
				rsaKey.setPublic(b64tohex(modulus), b64tohex(exponent));
			
				// 用公钥进行加密
				var encryptPassword = hex2b64(rsaKey.encrypt($('#password').val()));
				//alert(encryptPassword);

				// 提交登陆信息
				$.ajax({
					method: 'POST',
		 			url: '${base}/login/submit',
		 			data: {'username':$('#username').val(),'encryptPassword':encryptPassword},
		 			success: function(e){
		 				if(e == "success"){
		 					window.location.href='${base}/member/center.html';
		 				}else{
		 					$('.help-block').html(e);
		 				}
		 			}
				});
		 	}
		 	
		 });
	 	
	 });
	 
	/* -----  会员  ---- */ 
	 
	var member = $.cookie('member');
	
	if (member != null) {
		$('#header_member').text(member).show();
		$('#header_logout').show();
		$('#header_login').hide();
		$('#header_register').hide();
	} 

	/* -----  退出  ---- */
	
	$(document).on('click','#header_logout',function(){
		$.ajax({
			method: 'POST',
			url: '${base}/login/logout',
			success: function(data){
				window.location.href= '${base}/login.html';
			}
		});
	});

});  
</script>

</body>
</html>
<!DOCTYPE html>
<html  lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册表单</title>
    <link rel="stylesheet" href="${base}/resource/client/css/bootstrap.min.css" />
    <style>
    	.tab-content{ margin-top:30px; }
    	.row:first-child{ margin-top:100px;margin-bottom:30px; }
    </style>
</head>
<body>

<div class="container">

	<div class="row"><h1 class="text-center">注册表单</h1></div>
	<div class="row">
	
		<div class="col-md-offset-3 col-md-6">
			<ul id="myTab" class="nav nav-tabs">
				<li class="active"><a href="#home" data-toggle="tab">账号/邮箱注册</a></li>
				<li><a href="#tel" data-toggle="tab">手机注册</a></li>
			</ul>
			
			<div class="tab-content">
			    <!-- 账号/邮箱注册 -->
				<div class="tab-pane active" id="home">
					<form class="form-horizontal" method="post">
						
						<div class="form-group">
							<label class="control-label col-md-3">用户名/邮箱</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="username-email" id="username-email" placeholder="请输入用户名或邮箱"> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">密码</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="ue-password" id="ue-password" placeholder="请输入密码"> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">确认密码</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="ue-re-password" id="ue-re-password" placeholder="再次输入密码"> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">验证码</label>
							<div class="col-md-9">
								 
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-3 col-md-9">
								 <a href="javascript:;" id="usernameSubmit" class="btn btn-default"> 提交注册 </a>
							</div>
						</div>
						
					</form>
				</div>
				
				<!-- 手机注册 -->
				<div class="tab-pane" id="tel">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-md-3">手机号码</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="phone" id="phone" placeholder="请输入手机号码"> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">验证码</label>
							<div class="col-md-6">
								<input type="text" class="form-control" name="idCode" id="idCode" placeholder="请输入验证码">
							</div>
							<div class="col-md-3">
								<a href="javascript:;" class="btn btn-default" id="code"> 获取验证码 </a>
								<div id="codeTips" style="width:120px; height:40px; background-color:#ffffff;position:absolute;top:-1px;left:-1px;display:none"></div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">密码</label>
							<div class="col-md-9">
								<input type="password" class="form-control" name="password" id="password" placeholder="请输入密码"> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">确认密码</label>
							<div class="col-md-9">
								<input type="password" class="form-control" name="re-password" id="re-password" placeholder="再次输入密码"> 
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-md-offset-3 col-md-9">
								 <a href="javascript:;" class="btn btn-default" id="phoneSubmit"> 提交注册  </a>
							</div>
						</div>					
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
<script language="javascript">
$(document).ready(function(){
    var interval;
	// 发送验证码
	$('#code').click(function(){
		
		var phone = $('#phone').val();
		if(phone != null && phone != ""){
			$.ajax({
				type: 'POST',
				url: '${base}/member/phoneSMS',
				data: {'phone':phone},
				success:function(data){ alert(data); }	
			});	
			interval = setInterval(settime,1000);
		}else{
			alert("手机号码不能为空");
		}

	});
	
	var countdown=60; 
	function settime() { 
	    if (countdown == 0) {
	        $('#codeTips').hide();
	        clearInterval(interval); 
	        countdown=60;
	    } else { 
	    	$('#codeTips').show();
	        $('#codeTips').html(countdown+" 秒后重新发送"); 
	        countdown--; 
	    } 
	}

    // 输入框实时验证
	$('#idCode').bind('input propertychange', function(){
		if($(this).val() == $.cookie("idCode")){
			$('#idCode').parent().parent().addClass("has-success");
		}else{
			$('#idCode').parent().parent().addClass("has-error");
		}
	}); 

	

	
	// 手机提交注册
	$('#usernameSubmit').click(function(){
		$.ajax({
			type: 'POST',
			url: '${base}/member/registerUsername',
			data: {'username-email':$('#username-email').val(),'password':$('#ue-password').val()},
			success: function(data){ alert(data); }
		
		});
	});
	
	
	
	
	
});

</script>

</body>
</html>
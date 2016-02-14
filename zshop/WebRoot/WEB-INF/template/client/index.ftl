<!DOCTYPE html>
<html  lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>
    <link rel="stylesheet" href="${base}/resource/client/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${base}/resource/client/css/common.css" />
</head>
<body>
<div class="container">
    <#include "/client/include/header.ftl">
	<div class="jumbotron">
		<div class="container">
			<h1 class="text-center">沧海软件(北京)有限公司</h1>
		</div>
	</div>
	<#include "/client/include/footer.ftl">
</div>

<!--[if lt IE 9]>
<script src="${base}/resource/client/js/html5shiv.min.js"></script>
<script src="${base}/resource/client/js/respond.min.js"></script>
<![endif]-->

<script src="${base}/resource/client/js/jquery.min.js"></script>
<script src="${base}/resource/client/js/bootstrap.min.js"></script>
<script src="${base}/resource/admin/js/jquery.cookie.js"></script>
<script type="text/javascript">
$(document).ready(function(){
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
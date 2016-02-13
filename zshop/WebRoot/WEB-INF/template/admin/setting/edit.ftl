<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
    <!-- Admin Forms CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
     <script>
    	if("${success}" != ""){
    	parent.bootAlert("${success}");
	    }
     </script>
</head>
<body>


<div class="custom_main">
<div class="custom_title"><span class="glyphicons glyphicons-list"></span><span class="custom_title_word">系统设置</span></div>
<div class="custom_body">
                        
	<form class="form-horizontal" action="${base}/admin/setting/update" id="inputForm" method="post" role="form">
	    
	    <ul class="nav nav-pills mb20">
	        <li class="active"><a href="#tab18_1" data-toggle="tab">基本参数</a></li>
	        <li><a href="#tab18_2" data-toggle="tab">邮件设置</a></li>
	        <li class="pull-right">
	            <div class="admin-form"><input type="submit" class="custom_button button btn-primary mr10" value="确  定" style="height:36px;font-size:14px"/></div>
	        </li>
	    </ul>
	     
	    <!-- 基本参数 -->   
	    <div class="tab-content br-n pn">
	        <div id="tab18_1" class="tab-pane active">
	            <div class="row">
	               
	                <div class="col-md-12">                     
			            <div class="form-group">
			            <label for="siteName" class="col-md-1 col-sm-1  control-label">网站名称</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="siteName" id="siteName" class="form-control" value="${setting.siteName}">
			            </div>
			        </div>
        
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">网站网址</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="siteUrl" id="siteUrl" class="form-control" value="${setting.siteUrl}">
			            </div>
			        </div>
        
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">联系地址</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="address" id="address" class="form-control" value="${setting.address}">
			            </div>
			        </div>
        
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">联系电话</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="phone" id="phone" class="form-control" value="${setting.phone}">
			            </div>
			        </div>
			        
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">邮政编码</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="zipCode" id="zipCode" class="form-control" value="${setting.zipCode}">
			            </div>
			        </div>
			    
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">电子邮件</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="email" id="email" class="form-control" value="${setting.email}">
			            </div>
			        </div>
                                          
                </div>
            </div>
        </div>
                                
        <!-- 邮件设置 -->                      
        <div id="tab18_2" class="tab-pane">
            <div class="row">
                <div class="col-md-12">
 		            
 		            <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">发件邮箱</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="smtpFromMail" id="smtpFromMail" class="form-control" value="${setting.smtpFromMail}">
			            </div>
			        </div>
			        
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">邮箱密码</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="smtpPassword" id="smtpPassword" class="form-control" value="${setting.smtpPassword}">
			            </div>
			        </div>  
			        
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">SMTP地址</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="smtpHost" id="smtpHost" class="form-control" value="${setting.smtpHost}">
			            </div>
			        </div>
			        
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">SMTP端口</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="smtpPort" id="smtpPort" class="form-control" value="${setting.smtpPort}">
			            </div>
			        </div>               
			        
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">发件人名称</label>
			            <div class="col-md-5 col-sm-5">
			                <input type="text" name="smtpUsername" id="smtpUsername" class="form-control" value="${setting.smtpUsername}">
			            </div>
			        </div>   
			        
 
			        
			        <div class="form-group">
			            <label class="col-md-1 col-sm-1  control-label">邮件测试</label>
			            <div class="col-md-5 col-sm-5">
			            <div class="input-group">
			                <input type="text" name="toMail" id="toMail" class="form-control" placeholder="请输入测试邮箱">
			                <span class="input-group-addon" id="testMail" style="cursor:pointer">测试</span>
			             </div>   
			            </div>
			        </div> 
			                                                                                                          
	            </div>
	        </div>
	    </div>
             
	</form>   

</div>
</div>
              
<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery_ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${base}/resource/admin/js/jquery.validate.js"></script> 
<script type="text/javascript" src="${base}/resource/admin/assets/js/bootstrap/bootstrap.min.js"></script>

<!--Ueditor 编辑器-->
<script type="text/javascript" charset="utf-8" src="${base}/resource/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/resource/plugin/ueditor/ueditor.all.min.js"> </script>  
<script type="text/javascript" charset="utf-8" src="${base}/resource/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
   
<script type="text/javascript">
$(document).ready(function() {

    var $inputForm = $("#inputForm");

	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required"
		}
	});
	
	// 邮件测试
	$('#testMail').click(function(){
		var smtpFromMail = $('#smtpFromMail').val();
		var smtpPort = $('#smtpPort').val();
		var smtpHost = $('#smtpHost').val();
		var smtpUsername = $('#smtpUsername').val();
		var smtpPassword = $('#smtpPassword').val();
		var toMail = $('#toMail').val();
		
		$.ajax({
			type: 'POST',
			url: '${base}/admin/setting/mailTest',
			data: {'smtpFromMail':smtpFromMail,'smtpPort':smtpPort,'smtpHost':smtpHost,'smtpUsername':smtpUsername,'smtpPassword':smtpPassword,'toMail':toMail},
			success: function(data){ parent.bootAlert(data); }
		});
	});
	
});
</script>

</body>
</html>



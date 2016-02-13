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
	<div class="custom_title"><span class="custom_title_word">添加账号</span></div>
    <div class="custom_body">
                                <form class="form-horizontal" action="${base}/admin/account/save" id="inputForm" method="post" role="form">
                                    
                                 
                                    <div class="form-group">
                                        <label for="username" class="col-md-1 col-sm-1  control-label">管理账号</label>
                                        <div class="col-md-5 col-sm-5">
                                              <input type="text" name="username" id="username" class="form-control" placeholder="只允许使用数字、字母或下划线">  
                                        </div> 
                                    </div>
                                    
                                    
                                
                                    <div class="form-group">
                                        <label for="password" class="col-md-1 col-sm-1  control-label">设置密码</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="password" name="password" id="password" class="form-control">
                                        </div>  
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="rePassword" class="col-md-1 col-sm-1  control-label">确认密码</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="password" name="rePassword" id="rePassword" class="form-control">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="name" class="col-md-1 col-sm-1  control-label">账号别名</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="text" name="name" id="name" class="form-control" placeholder="可输入用户姓名">
                                        </div>
                                    </div>
                                    
                                   <div class="form-group">
                                        <label for="department" class="col-md-1 col-sm-1  control-label">所属部门</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="text" name="department" id="department" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="col-md-1 col-sm-1  control-label">电子邮件</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="text" name="email" id="email" class="form-control">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="role" class="col-md-1 col-sm-1  control-label">管理角色</label>
                                        <div class="col-md-5 col-sm-5">
                                            
                                            <span class="fieldSet">
												<#list accountRoles as accountRole>
													<input type="radio" value="${accountRole.id}" name="accountRoleId"/>${accountRole.name}
												</#list>
											</span>
                                            
                                        </div>
                                    </div>
                                    
                                    
                                    
                                    <div class="form-group">
                                        <label for="email" class="col-md-1 col-sm-1  control-label">账号状态</label>
                                        <div class="col-md-5 col-sm-5">
                                           <input type="radio" name="isEnabled" value="true" checked="checked" />可用
										   <input type="radio" name="isEnabled" value="false">不可用
                                        </div>
                                    </div>
                                    
                                  

                                    <div class="form-group">
                                        <label class="col-md-1 col-sm-1  control-label" for="textArea2"></label>
                                        <div class="admin-form col-md-5 col-sm-5">
                                            <button type="submit" class="button btn-primary mr10">提   交</button>
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
			username: {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: 2,
				maxlength: 20,
				remote: {
					url: "check", 
					cache: false
				}
			},
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			accountRoleId: "required"
		},
		messages: {
			username: {
				pattern: "${message("admin.validate.illegal")}",
				remote: "${message("admin.validate.exist")}"
			},
			password: {
				pattern: "${message("admin.validate.illegal")}"
			}
		}
	});
	
});
</script>

</body>
</html>



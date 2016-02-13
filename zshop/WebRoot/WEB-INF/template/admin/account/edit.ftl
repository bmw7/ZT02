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
	<div class="custom_title"><span class="custom_title_word">账号编辑</span></div>
    <div class="custom_body">
                                <form class="form-horizontal" action="${base}/admin/account/update" id="inputForm" method="post" role="form">
                                    
                                    <input type="hidden" name="id" value="${account.id}" />
									<input type="hidden" name="username" value="${account.username}" />
									<input type="hidden" name="password" value="${account.password}" />
									<input type="hidden" name="loginIp" value="${account.loginIp}" />
									<input type="hidden" name="loginCount" value="${account.loginCount}" />
									<input type="hidden" name="loginFailureCount" value="${account.loginFailureCount}" />
									<input type="hidden" name="loginDate" value="${account.loginDate}" />
									<input type="hidden" name="createDate" value="${account.createDate}" />
									<input type="hidden" name="modifyDate" value="${account.modifyDate}" />
							        <input type="hidden" name="lockedDate" value="${account.lockedDate}" />
                                    
                                    
                                    <div class="form-group">
                                        <label for="name" class="col-md-1 col-sm-1  control-label">管理账号</label>
                                        <div class="col-md-5 col-sm-5">
                                            <label class="control-label">${account.username}&nbsp;&nbsp;&nbsp;&nbsp;[创建时间&nbsp;&nbsp;${account.createDate}]</label>
                                        </div> 
                                    </div>
                                    
                                    
                                
                                    <div class="form-group">
                                        <label for="newPassword" class="col-md-1 col-sm-1  control-label">设置密码</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="password" name="newPassword" id="newPassword" class="form-control" placeholder="留空则不修改密码">
                                        </div>  
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="rePassword" class="col-md-1 col-sm-1  control-label">确认密码</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="password" name="rePassword" id="rePassword" class="form-control" placeholder="重复上述密码输入">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="name" class="col-md-1 col-sm-1  control-label">账号别名</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="text" name="name" id="name" class="form-control" value="${account.name}">
                                        </div>
                                    </div>
                                    
                                   <div class="form-group">
                                        <label for="department" class="col-md-1 col-sm-1  control-label">所属部门</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="text" name="department" id="department" class="form-control" value="${account.department}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="col-md-1 col-sm-1  control-label">电子邮件</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="text" name="email" id="email" class="form-control" value="${account.email}">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="role" class="col-md-1 col-sm-1  control-label">管理角色</label>
                                        <div class="col-md-5 col-sm-5">
                                            
                                            <span class="fieldSet">
												<#list roles as role>
													<label class="control-label"><input type="radio" name="roleId" value="${role.id}"<#if (account.accountRole.id) == (role.id)> checked="checked"</#if> />${role.name}</label>
												</#list>
											</span>
                                            
                                        </div>
                                    </div>
                                    
                                    
                                    
                                    <div class="form-group">
                                        <label for="email" class="col-md-1 col-sm-1  control-label">账号状态</label>
                                        <div class="col-md-5 col-sm-5">
                                           <label class="control-label"><input type="checkbox" name="isEnabled" <#if account.isEnabled> checked="checked"</#if> />启用</label>
                                           <label class="control-label"><input type="checkbox" name="isLocked"  <#if account.isLocked> checked="checked" </#if> />锁定</label>
                                        </div>
                                    </div>
                                    
                                  

                                    <div class="form-group">
                                        <label class="col-md-1 col-sm-1  control-label" for="textArea2"></label>
                                        <div class="admin-form col-md-5 col-sm-5">
                                            <button type="submit" class="button btn-primary mr10">确    定</button>
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
			newPassword: {
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			rePassword: {
				equalTo: "#newPassword"
			},
			email: {
				required: true,
				email: true
			},
			roleIds: "required"
		},
		messages: {
			password: {
				pattern: "${message("admin.validate.illegal")}"
			}
		}
	});
	
});
</script>

</body>
</html>



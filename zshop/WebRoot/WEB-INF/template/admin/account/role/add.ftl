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
	<div class="custom_title"><span class="custom_title_word">添加角色</span></div>
    <div class="custom_body">
                                <form class="form-horizontal" action="${base}/admin/account/role/save" id="inputForm" method="post" role="form">
                                    
                                 
                                    <div class="form-group">
                                        <label for="name" class="col-md-1 col-sm-1  control-label">角色名称</label>
                                        <div class="col-md-5 col-sm-5">
                                              <input type="text" name="name" id="name" class="form-control">  
                                        </div> 
                                    </div>
                                       
                                    <div class="form-group">
                                        <label for="description" class="col-md-1 col-sm-1  control-label">角色描述</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="text" name="description" id="description" class="form-control">
                                        </div>  
                                    </div>
                                 
                                 
                                 
                                    <div class="admin-form"><br>
                                    	<div class="section-divider"><span>权限分配</span></div>
                                    </div>
                                 
                                    
                                    <div class="form-group">
                                        <label for="rePassword" class="col-md-1 col-sm-1  control-label">内容管理</label>
                                        <div class="col-md-11 col-sm-11">
                                            	<label class="control-label">
													<input type="checkbox"  value="1"  checked="checked" name="accountPermIds"/>文章添加&nbsp;&nbsp;
													<input type="checkbox"  value="2"  checked="checked" name="accountPermIds"/>文章管理&nbsp;&nbsp;
													<input type="checkbox"  value="3"  checked="checked" name="accountPermIds"/>文章分类&nbsp;&nbsp;
						
												</label>
                                        </div>
                                    </div>
                                    
                                        
                                    <div class="form-group">
                                        <label for="name" class="col-md-1 col-sm-1  control-label">系统相关</label>
                                        <div class="col-md-11 col-sm-11">
                                            <label class="control-label">
													<input type="checkbox"  value="4"  checked="checked" name="accountPermIds"/>账号编辑&nbsp;&nbsp;
													<input type="checkbox"  value="5"  checked="checked" name="accountPermIds"/>账号添加&nbsp;&nbsp;
													<input type="checkbox"  value="6"  checked="checked" name="accountPermIds"/>角色管理&nbsp;&nbsp;
											        <input type="checkbox"  value="7"  checked="checked" name="accountPermIds"/>导航设置&nbsp;&nbsp;
													<input type="checkbox"  value="8"  checked="checked" name="accountPermIds"/>系统设置&nbsp;&nbsp;
											</label>
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="name" class="col-md-1 col-sm-1  control-label">数据操作</label>
                                        <div class="col-md-11 col-sm-11">
                                            <label class="control-label">
													<input type="checkbox"  value="9"  checked="checked" name="accountPermIds"/>保存权限&nbsp;&nbsp;
													<input type="checkbox"  value="10" checked="checked" name="accountPermIds"/>更新权限&nbsp;&nbsp;
													<input type="checkbox"  value="11" checked="checked" name="accountPermIds"/>删除权限&nbsp;&nbsp;
											     
											</label>
                                        </div>
                                    </div>
                                    
                                  
                                    
                                    <br>
                            

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
			username: { required: true }
		},
		messages: {
			username: { required: "不能为空"}
		}
	});
	
});
</script>

</body>
</html>



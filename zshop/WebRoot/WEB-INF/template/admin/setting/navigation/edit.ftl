<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
    <!-- Admin Forms CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
</head>
<body>

	<div class="custom_main">
	<div class="custom_title"><span class="glyphicons glyphicons-list"></span><span class="custom_title_word">编辑栏目</span></div>
    <div class="custom_body">
                                <form class="form-horizontal" action="${base}/admin/setting/navigation/update" id="inputForm" method="post" role="form">
                                    <input type="hidden" name="id" value="${navigation.id}">
                                    <input type="hidden" name="orders" value="${navigation.orders}">
                                    <input type="hidden" name="grade" value="${navigation.grade}">
                                    <input type="hidden" name="parentId" value="${navigation.parent.id}">
                                    
                                    <div class="form-group">

                                        <label for="name" class="col-md-1 col-sm-1  control-label">分类名称</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="text" name="name" id="name" class="form-control" value="${navigation.name}">
                                        </div>
                                         
                                    </div>
                                    
                                  
                                    
                                    <div class="form-group">

                                        <label for="seoTitle" class="col-md-1 col-sm-1  control-label">栏目网址</label>
                                        <div class="col-md-5 col-sm-5">
                                            <input type="text" name="url" id="url" class="form-control" value="${(navigation.url)!""}">
                                        </div>
                                         
                                    </div>
                                    
                                  
                                    <div class="form-group">
                                        <label class="col-md-1 col-sm-1  control-label" for="textArea2"></label>
                                        <div class="admin-form col-md-5 col-sm-5">
                                            <button type="submit" class="button btn-primary mr10">更   新</button>
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
	
});
</script>

</body>
</html>



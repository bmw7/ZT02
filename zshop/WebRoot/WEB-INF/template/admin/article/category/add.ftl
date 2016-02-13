<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
<script>if("${info}" != ""){ parent.bootAlert("${info}"); }</script>
</head>
<body>
<div class="custom_main">

	<div class="custom_title"> 
		<span class="custom_title_word">添加分类</span>
		<label class="pull-right"><span class="glyphicons glyphicons-rewind"></span>&nbsp;&nbsp;<a href="${base}/admin/article/category/list" class="custom_href">返回分类</a></label> 
	</div>
	
	<div class="custom_body"> 
        <form class="form-horizontal" action="${base}/admin/article/category/save" id="inputForm" method="post" role="form">  
        <div class="form-group">
            <label for="name" class="col-md-1 col-sm-1  control-label">分类名称</label>
            <div class="col-md-5 col-sm-5">
                <input type="text" name="name" id="name" class="form-control" placeholder="请输入分类名称">
            </div>         
        </div>    
            
        <div class="form-group">
         	<label for="inputStandard" class="col-md-1 col-sm-1  control-label">上级分类</label>
            <div class="col-md-2 col-sm-2  admin-form">
	            <label class="field select">
	                <select name="parentId" id="category">
	                    <option value="0" id="option0" name="0" >请选择上级分类</option>
	                   	<#list tree as category>
	                    	<option value="${category.id}" id="option${category.id}" name="${category.grade+1}">
								<#if category.grade != 0>
									<#list 1..category.grade as i>&nbsp;&nbsp;</#list>
								</#if>
								${category.name}
							</option>
						</#list>
	                </select>
	                <!-- grade -->
	                <input type="hidden" name="grade" id="grade" value="0" />
	            </label>
            </div>


            <label for="author" class="col-md-1 col-sm-1 control-label">分类类型</label>
            <div class="col-md-2 col-sm-2  admin-form"> 
	            <label class="field select">
				<select name="type" id="category">
					<option value="0">标题</option>	
					<option value="1">单篇</option>
					<option value="2">多篇</option>
					</select></label>
            </div>
		</div>
                       
        <div class="form-group">
            <label for="seoTitle" class="col-md-1 col-sm-1  control-label">页面标题</label>
            <div class="col-md-5 col-sm-5">
                <input type="text" name="seoTitle" id="seoTitle" class="form-control" placeholder="请输入页面标题">
            </div>                 
        </div>
                       
        <div class="form-group">
            <label for="seoKeywords" class="col-md-1 col-sm-1  control-label">页面关键词</label>
            <div class="col-md-5 col-sm-5">
                <input type="text" name="seoKeywords" id="seoKeywords" class="form-control" placeholder="请输入页面关键词">
            </div>                
        </div>

        <div class="form-group">
            <label for="seoDescription" class="col-md-1 col-sm-1  control-label">页面描述</label>
            <div class="col-md-5 col-sm-5">
                <input type="text" name="seoDescription" id="seoDescription" class="form-control" placeholder="请输入页面描述">
            </div>               
        </div>

        <div class="form-group">
            <label class="col-md-1 col-sm-1  control-label" for="textArea2"></label>
            <div class="admin-form col-md-5 col-sm-5">
                <button type="submit" class="button btn-primary mr10">添  加</button>
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
			name: "required",
			order: "digits"
		}
	});
	
	// 赋值grade
	$('#category').change(function(){
		var optionId = "option"+$(this).val();
		var optionName = $('#'+optionId).attr("name");
		$('#grade').val(optionName);
	});
	
});
</script>

</body>
</html>



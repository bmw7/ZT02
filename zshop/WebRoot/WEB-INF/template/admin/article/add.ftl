<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/resource/plugin/webuploader/dist/webuploader.css"> 
    <link rel="stylesheet" type="text/css" href="${base}/resource/plugin/webuploader/image-upload/style.css"> 
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">   
</head>
<body>

<div class="custom_main">
<form class="form-horizontal" action="${base}/admin/article/save" id="inputForm" method="post" role="form">
    
    <div class="custom_title">
        <span class="custom_title_word">内容发布</span>
        
        <span class="pull-right">
        <label class="pull-right">&nbsp;&nbsp;设置置顶</label>
		<div class="switch switch-primary switch-inline switch-xs pull-right">
            <input id="isTop" name="isTop" type="checkbox">
            <label for="isTop"></label>
        </div>
    	</span>
        
    	<span class="pull-right">
    	<label class="pull-right">&nbsp;&nbsp;搜索优化&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>  
        <div class="switch switch-primary switch-inline switch-xs pull-right">
            <input id="seo" type="checkbox">
            <label for="seo"></label>
        </div>
        </span>
            
    	<span class="pull-right">
        <label class="pull-right">&nbsp;&nbsp;图片上传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<div class="switch switch-primary switch-inline switch-xs pull-right">
            <input id="isImage" type="checkbox">
            <label for="isImage"></label>
        </div>
    	</span>  
    	
    </div>


    <div class="custom_body">
      
        <div class="form-group">
            <label class="col-md-1 col-sm-1  control-label">文章标题</label>
            <div class="col-md-7 col-sm-7">
                <input type="text" name="title" id="custom_title" class="form-control" placeholder="请输入文章标题">
            </div>
            
            <div class="col-md-2 col-sm-2  admin-form">
            	
                <label class="field select">        
                <select name="articleCategoryIdAndType" id="articleCategoryIdAndType" class="custom_select">
                    <option value="0and0"><span class="custom_option_title">请选择分类</span></option>
                    <#list articleCategoryTree as articleCategory>
                    	<option value="${articleCategory.id}and${articleCategory.type}">
    					<#if articleCategory.grade != 0>
    						<#list 1..articleCategory.grade as i>&nbsp;&nbsp;</#list>
    					</#if>
    					${articleCategory.name}
    					</option>
                    </#list>
                </select>
                <i class="arrow"></i>            
				</label>
            </div>
           
           
            <div class="col-md-2 col-sm-2 ">
                <input type="text" name="source" class="form-control" placeholder="请输入文章来源">
            </div>
                   
        </div>
        
        
        <div class="custom_seo">
	        <div class="form-group">
		        <label class="col-md-1 col-sm-1 control-label">关键词语</label>
	            <div class="col-md-11 col-sm-11">
	                <input type="text" name="seoKeywords" class="form-control" placeholder="请输入页面关键词">
	            </div>     
	        </div>
		
	        <div class="form-group">
	            <label class="col-md-1 col-sm-1 control-label">页面描述</label>
	            <div class="col-md-11 col-sm-11">
	                <input type="text" name="seoDescription" class="form-control" placeholder="请输入页面描述">
	            </div> 
	        </div>
        </div>
        
        
        <div class="custom_images">
	        <div class="form-group">
	        	<label class="col-md-1 col-sm-1 control-label">图片上传</label>
	            <div class="col-md-11 col-sm-11">

					<div id="wrapper">
				        <div id="container">
				            <!--头部，相册选择和格式选择-->
				            <div id="uploader">
				                <div class="queueList">
				                    <div id="dndArea" class="placeholder">
				                        <div id="filePicker"></div>
				                    </div>
				                </div>
				                <div class="statusBar" style="display:none;">
				                    <div class="progress">
				                        <span class="text">0%</span>
				                        <span class="percentage"></span>
				                    </div>
				                    <div class="info"></div>
				                    <div class="btns">
				                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
				                    </div>
				                </div>
				            </div>
				        </div>
				    </div>
         	
	            </div>     
	        </div>
        </div>
        

        <div class="form-group">
            <label class="col-md-1 col-sm-1 control-label">文章内容</label>
            <div class="col-md-11 col-sm-11">
                <script id="editor" name="content" type="text/plain"></script>
            </div>
        </div>

        <div class="form-group">
        	<label class="col-md-1 col-sm-1  control-label"></label>
            <div class="admin-form col-md-11 col-sm-11">
                <button type="submit" class="button btn-primary mr10">发 布</button>
            </div>
        </div>                 
    </div> 
    
</form>
</div>                   
                        
<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery_ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${base}/resource/admin/js/jquery.validate.js"></script> 
<script type="text/javascript" src="${base}/resource/admin/assets/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${base}/resource/plugin/webuploader/require.js" data-main="${base}/resource/admin/js/article.app.js"></script>

<!--Ueditor 编辑器-->
<script type="text/javascript" charset="utf-8" src="${base}/resource/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/resource/plugin/ueditor/ueditor.all.min.js"> </script>  
<script type="text/javascript" charset="utf-8" src="${base}/resource/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/resource/admin/js/article.js"></script>  
<script type="text/javascript">
// 信息提示
if("${info}" != ""){
    parent.bootAlert("${info}");
}

$(document).ready(function() {
	// Ueditor 编辑器
    var ue = UE.getEditor('editor');
	var $inputForm = $("#inputForm");
	
	// 表单验证
	$inputForm.validate({
		rules: { title: "required"},
		messages:{ title: "标题栏内容不能为空" }
	});	
	
	// seo 显示/隐藏
	$('#seo').click(function(){
		if($(this).prop('checked')){   
			$('.custom_seo').show(300);
			parent.setFrameHeight(120);
		}else{
		    $('.custom_seo').hide(300);
		    parent.setFrameHeight(-120);
		}
	});
	
	// 图片上传 显示/隐藏
	$('#isImage').click(function(){
		if($(this).prop('checked')){   
			$('.custom_images').animate({height:"300px"},300);
			parent.setFrameHeight(300);
		}else{
		    $('.custom_images').animate({height:"0px"},300);
		    parent.setFrameHeight(-300);
		}
	});
	
});

</script>

</body>
</html>



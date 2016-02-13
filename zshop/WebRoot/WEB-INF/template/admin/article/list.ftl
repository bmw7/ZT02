<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
</head>
<body>

	<div class="custom_main">
	<div class="custom_title">
		<span class="custom_title_word">文章管理</span>
		<a href="#" id="batchDel" class="pull-right" style="margin-left:20px"><span class="glyphicons glyphicons-bin"></span>&nbsp;批量删除</a> 
		<label class="pull-right">  
	    	<span class="glyphicons glyphicons-rewind"></span>&nbsp;&nbsp;<a href="javascript:;" class="custom_href" onclick="window.history.back()">返回上一步</a>
	    </label>
	    
	</div>
    <div class="custom_body">
	    <table class="table table-hover" id="datatable3" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th><input type="checkbox" id="all"></th>
	                <th width="60%">文章题目</th>
	                <th width="20%">发布时间</th>
	                <th width="10%">置顶</th>
	                <th width="10%">管理</th>    
	            </tr>
	        </thead>

        <tbody>
                                     
 		<#list pagedContent.content as article>
			<tr>
				<td><input type="checkbox" class="item" name="${article.id}"></td>
				<td>
				<#assign uptime = article.createDate?string("yyyy")>
				<#if (uptime?number > 3000)><span class="label label-xs bg-primary">置顶</span></#if>
				<a href="${base}/admin/article/edit/${article.id}">${article.title}</a>
				</td>
				<td><#if (uptime?number > 3000)>${uptime?number-1000}${article.createDate?string("-MM-dd hh:mm:ss")}<#else>${article.createDate}</#if></td>
				<td>
					<div class="switch switch-primary switch-inline switch-xs">
	            		<input id="${article.id}" name="${article.id}" class="stick" type="checkbox" <#if (uptime?number > 3000)>checked</#if>>
	            		<label for="${article.id}"></label>
	        		</div>
				</td>
				<td><a href="${base}/admin/article/edit/${article.id}">编辑</a>&nbsp;&nbsp;<a href="#" class="delete" onclick="del('${base}/admin/article/del/${article.id}',this.name)" name="${article.title}">删除</a></td>
		   </tr>
		</#list>
		                    
        </tbody>
        <tr class="custom_page"><td colspan="5">
        <@page pageNumber=pagedContent.pageNumber totalPages=pagedContent.totalPages pageUrl="${base}${pageUrl}">
        	<#include "/admin/include/pagination.ftl">
        </@page>
        </td></tr>
        </table>
      </div>
</div>
<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${base}/resource/admin/js/pagination.js"></script>
<script type="text/javascript">
// 删除确认
function del(url,name){
	parent.bootConfirm(url,name);
}

// 信息提示
if("${info}" != ""){
    parent.bootAlert("${info}");
}

$(document).ready(function(){
	// 置顶
	$('.stick').click(function(){
		var id = $(this).attr('id');
		
		if($(this).is(':checked')){   // 选中
			
			$.ajax({
			    type: 'POST',
			    url:  '${base}/admin/article/stick' ,
			    data: {'id':id,'checked':'true'},
			    success: function(data){ parent.bootAlert(data); }
			});
		
		}else{                       // 非选中
			
			$.ajax({
			    type: 'POST',
			    url:  '${base}/admin/article/stick' ,
			    data: {'id':id,'checked':'false'},
			    success: function(data){ parent.bootAlert(data); }
			});
		}
	});
	
	
	// 全选
 	$('#all').click(function() { 
     	$('.item').prop('checked', $(this).is(':checked')); 
 	}); 
	
	// 批量删除
 	$('#batchDel').click(function(){
 	    var ids = '#';
 		$('.item').each(function(){
 			if($(this).is(':checked')){
 				ids+= $(this).attr('name') + '#';
 			}
 		});
 		
 		if(ids != '#'){
 		
	 		$.ajax({
			    type: 'POST',
			    url:  '${base}/admin/article/batchDel' ,
			    data: {'ids':ids},
			    success: function(data){ parent.bootAlert(data); }
			});
				
 		}else{
 			parent.bootAlert("没有选择任何项目！");
 		}

 	});
	
	
	
});
</script>
</body>
</html>
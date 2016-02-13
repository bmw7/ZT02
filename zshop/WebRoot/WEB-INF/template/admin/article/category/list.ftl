<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
<link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery-1.11.1.min.js"></script>
<script>
    //消息提示框
	if("${info}" != ""){
	parent.bootAlert("${info}");
    }
    
	// 删除确认框 
	function del(url,name){
		parent.bootConfirm(url,name);
	}
	
$(document).ready(function(){

 	// 获取与点击<a>元素有相同grade的上一层的 <a>元素的父级<tr>元素   -- @param $self 为<a>元素; @param $prev为$self的父级<tr>元素的上级<tr>元素
 	function samePrev($self,$prev){
 	    // 若不是常规的列表中的tr,则跳出
 		if($prev.find('.movedown').attr('grade') == null){ return null; }
 		
        var selfGrade = $self.attr('grade');
    	var prevGrade = $prev.find('.moveup').attr('grade')
    	if(selfGrade == prevGrade){
    		return $prev;
    	}else{
    		var $pp = $prev.prev();
    		return samePrev($self,$pp);
    	}
    }
    
    
    // 获取与点击<a>元素有相同grade的下一层的 <a>元素的父级<tr>元素   -- @param $self 为<a>元素; @param $next为$self的父级<tr>元素的下级<tr>元素
 	function sameNext($self,$next){
 		// 若不是常规的列表中的tr,则跳出
 		if($next.find('.movedown').attr('grade') == null){ return null; }
 		
        var selfGrade = $self.attr('grade');
    	var nextGrade = $next.find('.movedown').attr('grade')
    	if(selfGrade == nextGrade){
    		return $next;
    	}else{
    		var $nn = $next.next();
    		return sameNext($self,$nn);
    	}
    }
 	
 	// 上移
 	$('.moveup').click(function(){    	    
 		var myGrade = $(this).attr('grade');
 		var rpGrade = $(this).parent().parent().prev().find('.moveup').attr('grade');    // 第一个parent()到<td> 第二个parent()到 <tr> prev() 到上一个<tr>
 		
 		if(myGrade > rpGrade){ 
 			parent.bootAlert('已经移动到本层级最顶端，无法再往上移动！'); 
 		}else{
 			var $self = $(this);
 			var $prev = samePrev($self,$self.parent().parent().prev());
 			
 		    var myId = $self.attr('id');
 	    	var rpId = $prev.find('.moveup').attr('id');

 	    	var myOrders = $self.attr('orders');
 	    	var rpOrders = $prev.find('.moveup').attr('orders');
 	    	
 	    	$.ajax({
		    	type: 'POST',
		    	url:  '${base}/admin/article/category/move' ,
		    	data: {'myId':myId,'rpId':rpId,'myOrders':myOrders,'rpOrders':rpOrders},
		    	success: function(data){ parent.bootAlert(data); }
			});	
 		}
 	});  
 	
 	// 下移
 	$('.movedown').click(function(){
 		var myGrade = $(this).attr('grade');
 		var rpGrade = $(this).parent().parent().next().find('.movedown').attr('grade');
 		if(myGrade > rpGrade){ 
 			parent.bootAlert('已经移动到本层级最末端，无法再往下移动！'); 
 		}else{
 		    var $self = $(this);
 			var $next = sameNext($self,$self.parent().parent().next());
 			
 		    var myId = $self.attr('id');
 	    	var rpId = $next.find('.movedown').attr('id');
 	    	
 	    	var myOrders = $self.attr('orders');
 	    	var rpOrders = $next.find('.movedown').attr('orders');
 	    	
 	    	$.ajax({
		    	type: 'POST',
		    	url:  '${base}/admin/article/category/move' ,
		    	data: {'myId':myId,'rpId':rpId,'myOrders':myOrders,'rpOrders':rpOrders},
		    	success: function(data){ parent.bootAlert(data); }
			});	
 		}
 	});

});
</script>
</head>
<body>                 
<div class="custom_main">
	<div class="custom_title">
		<span class="custom_title_word">文章分类</span>
		<div class="widget-menu pull-right mr10"><a href="${base}/admin/article/category/add"><i class="fa fa-plus-square"></i> &nbsp;添加分类</a></div>
	</div>
	
	<div class="custom_body">
        <table class="table table-hover" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th><span class="glyphicons glyphicons-expand"></span></th>
                    <th width="55%">分类名称</th>
                    <th width="15%">栏目类型</th>
                    <th width="15%">排序调整</th>
                    <th width="15%">管理操作</th>
                </tr>
            </thead>

            <tbody>                            
				<#list tree as articleCategory>
				<tr>
				    <td><span class="glyphicons glyphicons-unchecked"></span></td>
					<td><span style="margin-left: ${articleCategory.grade * 20}px">${articleCategory.name}</span></td>
					<td>
						<#if articleCategory.type == 0><span class="glyphicons glyphicons-text_underline">&nbsp;标题</span>
					    <#elseif articleCategory.type == 1><span class="glyphicons glyphicons-unchecked">&nbsp;单篇</span>
					    <#elseif articleCategory.type == 2><span class="glyphicons glyphicons-more_windows">&nbsp;多篇</span>
					    <#elseif articleCategory.type == 3><span class="glyphicons glyphicons-unchecked">&nbsp;单篇(图片)</span>
					    <#elseif articleCategory.type == 4><span class="glyphicons glyphicons-more_windows">&nbsp;多篇(图片)</span>
					    </#if>
					</td>
					
					<td>
					<a href="#" class="moveup" id="${articleCategory.id}" orders="${articleCategory.orders}" grade="${articleCategory.grade}"><span class="glyphicons glyphicons-up_arrow"></span>上</a>&nbsp;&nbsp;<a href="#" class="movedown" id="${articleCategory.id}" orders="${articleCategory.orders}" grade="${articleCategory.grade}"><span class="glyphicons glyphicons-down_arrow"></span>下</a>
					</td>
					
					<td>
					<a href="${base}/admin/article/category/edit/${articleCategory.id}">编辑</a>&nbsp;&nbsp;
					<a href="javascript:;" onclick="del('${base}/admin/article/category/del/${articleCategory.id}',this.name)" name="${articleCategory.name}">删除</a>						
					</td>
				</tr>
				</#list>             
		    </tbody>
		    <tr class="row-label"><td colspan="5">&nbsp;</td></tr>
		</table>
	</div>
</div>
</body>
</html>


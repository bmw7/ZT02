<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
    <!-- Admin Forms CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
    <script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery-1.11.1.min.js"></script>
    <script>
     // 弹出框提示
 	 if('${info}' != ""){
 		parent.bootAlert('${info}');
 	 }

     // 删除确认框 
     function del(url,name){
        parent.bootConfirm(url,name);
     }
     
	 $(document).ready(function(){
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
				    url:  '${base}/admin/setting/navigation/batchDel' ,
				    data: {'ids':ids},
				    success: function(data){ parent.bootAlert(data); }
				});	
     		}else{
     			parent.bootAlert("没有选择任何项目！");
     		}

     	});
     	
     	
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
			    	url:  '${base}/admin/setting/navigation/move' ,
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
			    	url:  '${base}/admin/setting/navigation/move' ,
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
	<span class="custom_title_word">导航设置</span>
	<a href="#" id="batchDel" class="pull-right"><span class="glyphicons glyphicons-bin"></span>&nbsp;批量删除</a>
	<a href="${base}/admin/setting/navigation/snippet" class="pull-right" style="margin-right:20px"><span class="glyphicons glyphicons-snowflake"></span>&nbsp;生成静态</a>
</div>
<div class="custom_body">
<table class="table table-hover" id="datatable3" cellspacing="0" width="100%">
    <thead>
        
<tr><!-- 添加一级项目 -->
	
	<form id="inputForm" class="form-horizontal" action="${base}/admin/setting/navigation/save" method="post">
	<div class="form-group">
	
	<label class="control-label pull-left" style="padding-top:20px;padding-left:42px;width:130px">添加一级项目:</label>
	<div class="col-md-3 col-sm-3" style="padding-top:15px">
		<select name="categoryId" class="form-control input-sm">  
			<option value="0#articleCategory">请选择</option>	
			<#list articleCategoryTree as category>
			<option value="${category.id}#articleCategory"><#if category.grade != 0><#list 1..category.grade as i>&nbsp;&nbsp;</#list></#if>${category.name}</option>
			</#list>
		</select>
	</div>
	
	<div class="col-md-2 col-sm-2" style="padding-top:15px">
		<input type="text" name="name" placeholder="一级栏目名称(选填)" class="form-control input-sm" />				
	</div>
	
	<div class="col-md-4 col-sm-4" style="padding-top:15px">
		<input type="text" name="url" placeholder="一级栏目网址(选填)" class="form-control input-sm" />				
	</div>
	<div class="admin-form">
	<input type="submit" class="btn input-sm" style="padding-top:5px;margin-top:15px" value="添  加"/ ></tr>
	</div>
	</div>
	</form>
	
	
</tr> 
         
         
            <tr>
                <th><input type="checkbox" id="all"></th>
                <th width="20%">导航栏目</th>
                <th width="60%">添加子栏目</th>
                <th width="10%">排序调整</th>
                <th width="10%">管理操作</th>
            </tr>
            
        </thead>

        <tbody>
                                                                                                                               
   		<#list navigationTree as navigation>
   		
			<tr>
			    <td><input type="checkbox" class="item" name="${navigation.id}"></td>
				<td>
					<span style="margin-left: ${navigation.grade * 20}px;<#if navigation.grade == 0> color: #000000;</#if>">
						${navigation.name}
					</span>
				</td>
				
				<td><!-- 添加子项目 -->
					
				
				<form id="inputForm" action="${base}/admin/setting/navigation/saveChild" method="post">

					<select name="categoryId" class="custom_input_sm">  
					<option value="0#${navigation.id}#articleCategory">添加子栏目</option>	
					<#list articleCategoryTree as category>
					<option value="${category.id}#${navigation.id}#articleCategory"><#if category.grade != 0><#list 1..category.grade as i>&nbsp;&nbsp;</#list></#if>${category.name}</option>
					</#list>
					</select>
					<input type="text" class="custom_input_sm" name="name" placeholder="子栏目名称(选填)" size="15" />
					<input type="text" class="custom_input_sm" name="url" placeholder="子栏目网址(选填)"  size="30" />				
					<input type="submit" class="custom_input_sm btn custom_input_btn" value="添 加"/>
				</form>
					
							
				</td>
				
				
				<td>
					<a href="#" class="moveup" id="${navigation.id}" orders="${navigation.orders}" grade="${navigation.grade}"><span class="glyphicons glyphicons-up_arrow"></span>上</a>&nbsp;&nbsp;<a href="#" class="movedown" id="${navigation.id}" orders="${navigation.orders}" grade="${navigation.grade}"><span class="glyphicons glyphicons-down_arrow"></span>下</a>
				</td>
				<td>
					<a href="${base}/admin/setting/navigation/edit/${navigation.id}">编辑</a>&nbsp;&nbsp;
					<a href="#" onclick="del('${base}/admin/setting/navigation/del/${navigation.id}',this.name)" name="${navigation.name}">删除</a>
				</td>
			</tr>
		</#list>
       </tbody>
       
</table>
</div>
</div>


</body>
</html>


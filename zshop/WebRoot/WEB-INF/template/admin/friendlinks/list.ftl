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
		<span class="custom_title_word">友情链接</span> 
		<label class="pull-right">  
	    	<a href="javascript:;" id="add"><span class="glyphicons glyphicons-circle_plus"></span>&nbsp;&nbsp;添加友情链接</a>
	    </label>   
	</div>
    <div class="custom_body">
	    <table class="table table-hover" id="datatable3" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th width="20%">网站名称</th>
	                <th width="40%">网站网址</th>
	                <th width="5%"  style="text-align:center">排序</th>
	                <th width="10%" style="text-align:center">上传LOGO</th>
	                <th width="10%" style="text-align:center">网站LOGO</th>
	                <th width="10%" style="text-align:center">提交</th>
	                <th width="5%"  style="text-align:center">删除</th>    
	            </tr>
	        </thead>

        <tbody>
 		<#list links as link>
			<tr>
			    <form action="${base}/admin/friendlinks/submit/${link.id}" method="post" enctype="multipart/form-data">
				<td><input type="text" class="link_item" value="${link.name}" name="name"></td>
				<td><input type="text" class="link_item" value="${link.url}" name="url"></td>
				<td align="center"><input type="text" name="orders" class="link_item" value="${(link.orders)!''}" style="text-align:center"></td>
				<td><input type="file" name="myfile" class="upload_input"></td>
				<td><@logo linksId="${link.id}">${imgTags}</@logo></td>
				<td align="center"><div class="admin-form"><input type="submit" class="custom_button button btn-primary mr10" value="提  交" style="height:36px;font-size:14px"/></div></td>
				<td align="center">
					<a href="javascript:;" id="${link.id}" name="${link.name}" onclick="del('${base}/admin/friendlinks/del/'+this.id,this.name)"><span style="font-size:16px" class="glyphicons glyphicons-remove_2"></span></a>
				</td>
		   		</form>
		   </tr>
		</#list>
		                    
        </tbody>

        </table>
      </div>
</div>

<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
// 删除确认
function del(url,name){
	parent.bootConfirm(url,name);
}

$(document).ready(function(){
	
	$('#add').click(function(){
		parent.setFrameHeight(120);
		$('#datatable3').before("<@compress single_line=true>
		<form action='${base}/admin/friendlinks/submit' method='post' enctype='multipart/form-data' class='add_form'>
		
		<table class='table table-striped table-bordered table-hover' cellspacing='0' width='100%'>
 			<thead>
	            <tr>
	                <th width='20%'>网站名称</th>
	                <th width='40%'>网站网址</th>
	                <th width='5%'  style='text-align:center'>排序</th>
	                <th width='10%' style='text-align:center'>上传LOGO</th>
	                <th width='10%' style='text-align:center'>网站LOGO</th>
	                <th width='10%' style='text-align:center'>提交</th>
	                <th width='5%'  style='text-align:center'>删除</th>    
	            </tr>
	        </thead>
		    <tbody>
				<tr>
					<td><input type='text' class='link_item'  name='name'></td>
					<td><input type='text' class='link_item'  name='url'></td>
					<td align='center'><input type='text' name='orders' class='link_item' style='text-align:center'></td>
					<td><input type='file' name='myfile' class='upload_input'></td>
					<td></td>
					<td align='center'><div class='admin-form'><input type='submit' class='custom_button button btn-primary mr10' value='提&nbsp;&nbsp;交' style='height:36px;font-size:14px'/></div></td>
					<td align='center'>
						<a href='javascript:;' class='link_itemAdd'><span style='font-size:16px' class='glyphicons glyphicons-remove_2'></span></a>
					</td>
			   </tr>                   
           </tbody>
        </table>	
		</form>
		</@compress>");
	});
	
	
	$(document).on("click",".link_itemAdd",function(){
		$(this).closest("form").remove();
		parent.setFrameHeight(-120);
	});
	

});


</script>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
    <!-- Admin Forms CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">

</head>
<body>


                            
                            
    <div class="custom_main">
	<div class="custom_title"><span class="custom_title_word">角色管理</span>
									<div class="widget-menu pull-right mr10">
                                        <a href="${base}/admin/account/role/add"><i class="fa fa-plus-square"></i> &nbsp;添加角色</a>&nbsp;&nbsp;&nbsp;&nbsp; 
                                    </div>
	</div>
    <div class="custom_body">                      
                            
                            
                                <table class="table table-hover" id="datatable3" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th><input type="checkbox"></th>
                                            <th width="20%">角色名称</th>
                                            <th width="40%">角色描述</th>
                                            <th width="20%">创建日期</th>
                                            <th width="20%">管理操作</th>
                                        </tr>
                                    </thead>
                        
                                    <tbody>
                                    
                                    
                                    
                                    
                                    
                                    
         <#list pagedContent.content as accountRole>
			<tr>
			    <td><input type="checkbox"></td>
				<td>${accountRole.name}</td>
				<td>${accountRole.description}</td>
				<td>${accountRole.createDate}</td>
				<td><a href="${base}/admin/account/role/edit/${accountRole.id}">编辑</a>&nbsp;&nbsp;<a href="#" onclick=del("${base}/admin/account/role/del/${accountRole.id}",this.name) name="${accountRole.name}">删除</a></td>
			</tr>
		</#list>
                                                                          
         
        </tbody>
         <tr class="row-label"><td colspan="5">		         
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
// 删除确定
function del(url,name){
  parent.bootConfirm(url,name);
}

// 信息提示
if('${info}'!=""){
	parent.bootAlert('${info}');
}
</script>
</body>
</html>


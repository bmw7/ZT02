<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
    <!-- Admin Forms CSS -->
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
     
     <script>
    	if("${delete}" != ""){
    	parent.bootAlert("${delete}");
	    }
     </script>
</head>
<body>


                           
                           
                           
                           
    <div class="custom_main">
	<div class="custom_title"><span class="custom_title_word">账号管理</span>
	<div class="widget-menu pull-right mr10">
                                        <a href="${base}/admin/account/add"><i class="fa fa-plus-square"></i> &nbsp;添加账号</a>&nbsp;&nbsp;&nbsp;&nbsp; 
                                    </div>
	
	</div>
    <div class="custom_body">                     
                           
                                <table class="table table-hover" id="datatable3" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th><input type="checkbox"></th>
                                            <th width="10%">管理账号</th>
                                            <th width="10%">账号别名</th>
                                            <th width="10%">权限角色</th>
                                            <th width="20%">电子邮件</th>
                                            <th width="15%">所属部门</th>
                                            <th width="10%">最后登陆地点</th>
                                            <th width="15%">最后登陆时间</th>
                                            <th width="5%">状态</th>
                                            <th width="5%">编辑</th>
                                        </tr>
                                    </thead>
                        
                                    <tbody>
                                    
                                    
                                    
                                    
                                    
                                    
         <#list pagedContent.content as account>
			<tr>
			    <td><input type="checkbox"></td>
				<td>${account.username}</td>
				<td>${account.name}</td>
				<td>${account.accountRole.name}</td>
				<td>${account.email}</td>
				<td>${account.department}</td>
				<td>${account.loginIp}</td>
				<td>${account.loginDate}</td>
				<td>
				
					<#if !account.isEnabled>
						未启用
					<#elseif account.isLocked>
						锁定
					<#else>
						正常
					</#if>
				
				</td>
				<td><a href="${base}/admin/account/edit/${account.id}">编辑</a></td>
			</tr>
		</#list>
                                                                          
         
        </tbody>
         <tr class="row-label"><td colspan="10">		         
			<@page pageNumber = pagedContent.pageNumber totalPages = pagedContent.totalPages>
				<#include "/admin/include/pagination.ftl">
			</@page>
		</td></tr>
    </table>
</div>
</div>


</body>
</html>


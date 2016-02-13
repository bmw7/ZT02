<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/skin/default_skin/css/theme_inner.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/css/custom.css">
    <link rel="stylesheet" type="text/css" href="${base}/resource/admin/assets/admin-tools/admin-forms/css/admin-forms.css">
</head>
<body>

<div class="custom_main">
<div class="custom_title"><span class="custom_title_word">文章管理</span></div>
<div class="custom_body">

<table class="table table-hover" cellspacing="0" width="100%">
	
	<thead>
	    <tr>
	        <th><span class="glyphicons glyphicons-expand"></span></th>
	        <th width="55%">分类名称</th>
	        <th width="15%">栏目类型</th>
	        <th width="30%">进入管理</th>    
	    </tr>
	</thead>
	
	<tbody>                      
		<#list articleCategoryTree as articleCategory>
		<tr>
		<td><span class="glyphicons glyphicons-unchecked"></span></td>
		<td><span style="margin-left: ${articleCategory.grade * 20}px"><#if articleCategory.type != 0><a href="${base}/admin/article/manage/${articleCategory.id}"></#if>${articleCategory.name}<#if articleCategory.type != 0></a></#if></span></td>	
		<td><#if articleCategory.type == 0><span class="glyphicons glyphicons-text_underline">&nbsp;标题</span>
		<#elseif articleCategory.type == 1><span class="glyphicons glyphicons-unchecked">&nbsp;单篇</span>
		<#elseif articleCategory.type == 2><span class="glyphicons glyphicons-more_windows">&nbsp;多篇</span>
		</#if>
		 </td>
	
		<td>
		<#if articleCategory.type != 0><a href="${base}/admin/article/manage/${articleCategory.id}">进入管理</a></#if>
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


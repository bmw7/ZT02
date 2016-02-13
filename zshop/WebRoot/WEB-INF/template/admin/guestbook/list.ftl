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
<div class="custom_title">
	<span class="custom_title_word">留言管理</span>
	<a href="${base}/admin/guestbook/unlist" id="unreply" class="pull-right" style="margin-left:20px"><span class="glyphicons glyphicons-bin"></span>&nbsp;未回复留言</a> 
</div>
<div class="custom_body">

<table class="table table-hover" cellspacing="0" width="100%">
	
	<thead>
	    <tr style="background-color:#f9f9f9">
	        <th width="20%"><b>基本信息</b></th>
	        <th width="70%"><b>留言内容</b></th>
	        <th width="5%" style="text-align:center"><b>回复</b></th>
	        <th width="5%" style="text-align:center"><b>删除</b></th>  
	    </tr>
	</thead>
	
	<tbody> 
	    <tr style="display:none"></tr>                     
		<#list guestbooks.content as guestbook>
		<tr class="receive" style="cursor:pointer">
		<td>
			发布时间：&nbsp;&nbsp;${guestbook.createDate}<br>
			联系邮件：&nbsp;&nbsp;${(guestbook.email)!""}<br>
			联系电话：&nbsp;&nbsp;${(guestbook.phone)!""}<br>
			来源地址：&nbsp;&nbsp;${guestbook.ip}	
		</td>
			
		<td>${guestbook.message}</td>
		
		<td align="center">
			<#if (guestbook.reply)??>
			<a href="javascript:;" id="${guestbook.id}" class="item"><span style="font-size:16px" class="glyphicons glyphicons-ok_2"></span></a>
			<#else>
			<a href="javascript:;" id="${guestbook.id}" class="item">未回复</a>
			</#if>
		</td>
		<td align="center">
			<a href="javascript:;" id="${guestbook.id}" onclick="del('${base}/admin/guestbook/del/'+this.id,'本条留言')"><span style="font-size:16px" class="glyphicons glyphicons-remove_2"></span></a>
		</td>
		</tr>
		
		<tr style="display:none;color:#fc439f;font-size:16px" class="reply">
			<form action="${base}/admin/guestbook/reply/${guestbook.id}">
			<td colspan="2"><textarea name="reply" style="width:100%;height:100px;border:solid 1px #e5e5e5">${guestbook.reply!""}</textarea></td>
			<td colspan="2" align="center"><div class="admin-form"><input type="submit" class="custom_button button btn-primary mr10" value="提  交" style="height:36px;font-size:14px"/></div></td>
			</form>
		</tr>
		</#list>
    </tbody>
    <tr class="custom_page"><td colspan="4">
        <@page pageNumber=guestbooks.pageNumber totalPages=guestbooks.totalPages pageUrl="${base}${pageUrl}">
        	<#include "/admin/include/pagination.ftl">
        </@page>
    </td></tr>
</table>
                         
</div>
</div>

<script type="text/javascript" src="${base}/resource/admin/vendor/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${base}/resource/admin/js/pagination.js"></script>
<script language="javascript">
// 删除确认
function del(url,name){
	parent.bootConfirm(url,name);
}

// 回复
function reply(id,content){
	parent.bootGuestbook(id,content);
}

$(document).ready(function(){

	$('.receive').click(function(){
		var $tr = $(this).next();
		if($tr.css('display') == 'none'){
			parent.setFrameHeight(120);
			$tr.show();
		}else{
			parent.setFrameHeight(-120);
			$tr.hide();
		}
	});
	
});
</script>
</body>
</html>


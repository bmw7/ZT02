<input type="hidden" id="pageUrl" value="${pageUrl}">

<#if (totalPages>1)>
<ul class="pagination" style="margin-top:0px;margin-bottom:-4px;padding:0px">
<li class="pagination_button"><a href="#" id="pagination_prev">上一页</a></li>
<#if (totalPages < 6)>
	
	<#list 1..totalPages as n>
		<li class="pagination_button <#if n == pageNumber>active</#if>"><a href="#" class="pagination_item">${n}</a></li>
	</#list>

<#else>

	<#if (5 <= pageNumber)>
		<li class="pagination_button"><a href="#" class="pagination_item">1...</a></li>
		<#list (pageNumber-2)..pageNumber as n>
			<li class="pagination_button <#if n == pageNumber>active</#if>"><a href="#" class="pagination_item">${n}</a></li>
		</#list>	
	<#else>
		<#list 1..pageNumber as n>
			<li class="pagination_button <#if n == pageNumber>active</#if>"><a href="#" class="pagination_item">${n}</a></li>
		</#list>
	</#if>
	
	<#if (pageNumber < (totalPages-3))>
		<li class="pagination_button"><a href="#" class="pagination_item">${pageNumber+1}</a></li>
		<li class="pagination_button"><a href="#" class="pagination_item">${pageNumber+2}</a></li>
		<li class="pagination_button"><a href="#" class="pagination_item">${totalPages}<span class="pull-left">...</span></a></li>
	<#else>
		<#if ((pageNumber+1)<=totalPages)>
			<#list (pageNumber+1)..totalPages as n>
				<li class="pagination_button"><a href="#" class="pagination_item">${n}</a></li>
			</#list>
		</#if>
	</#if>

</#if>

<li class="pagination_button"><a href="#" id="pagination_next">下一页</a></li>
</ul>
</#if>


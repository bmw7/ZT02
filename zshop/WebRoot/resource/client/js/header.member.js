$(document).ready(function(){
	var member = $.cookie('member');

	if (member != null) {
		$('#header_member').text(member).show();
		$('#header_logout').show();
		$('#header_login').hide();
		$('#header_register').hide();
	} 
});
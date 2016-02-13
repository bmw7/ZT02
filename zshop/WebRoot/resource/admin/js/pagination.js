$(document).ready(function($) {
	
	var url = $('#pageUrl').attr('value');

	$('.pagination_item').click(function(){
		var pageNumber = parseInt($(this).text());
		window.location.href=url+'?pageNumber='+pageNumber;
	});

	 $('#pagination_prev').click(function() {
	 	var prevNumber = parseInt($('.pagination').find('.active').children().eq(0).text()) - 1;
	 	if (prevNumber >= 1) {
	 		window.location.href=url+'?pageNumber='+prevNumber;
	 	};
	 });

	$('#pagination_next').click(function() {
		 var totalPages = parseInt($(this).parent().prev().children().eq(0).text())
		 var nextNumber = parseInt($('.pagination').find('.active').children().eq(0).text()) + 1;
		 if (nextNumber <= totalPages) {
		 	window.location.href=url+'?pageNumber='+nextNumber;
		 };
		
	});

});
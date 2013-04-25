$(document).ready(function(){
	
	var error = $.url().param('error');
	if (error == true) {
		$(".errorblock").show(" <b>Appended text</b>.");
	}
});
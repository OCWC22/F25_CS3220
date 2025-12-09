// add.js - Handles addition of two numbers
$(document).ready(function() {
	$('#addBtn').click(function() {
		// Get values from inputs, default to 0 if empty or NaN
		var num1 = Number($('#num1').val()) || 0;
		var num2 = Number($('#num2').val()) || 0;
		
		// Calculate sum and display result
		var sum = num1 + num2;
		$('#result').text(sum);
	});
});

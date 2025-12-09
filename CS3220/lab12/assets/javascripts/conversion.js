// conversion.js - Handles unit conversion from inches
$(document).ready(function() {
	// Conversion factors from inches to other units
	var conversionFactors = {
		'cm': 2.54,
		'inch': 1,
		'feet': 1 / 12,
		'meter': 0.0254,
		'yard': 1 / 36
	};

	// Function to perform conversion and update result
	function updateConversion() {
		var inputValue = Number($('#inputNumber').val()) || 0;
		var selectedUnit = $('#measure').val();
		var factor = conversionFactors[selectedUnit];
		var result = inputValue * factor;
		
		// Round to 2 decimal places for cleaner display
		result = Math.round(result * 100) / 100;
		$('#result').text(result);
	}

	// Update on input change (typing)
	$('#inputNumber').on('input', function() {
		updateConversion();
	});

	// Update on dropdown change
	$('#measure').on('change', function() {
		updateConversion();
	});
});

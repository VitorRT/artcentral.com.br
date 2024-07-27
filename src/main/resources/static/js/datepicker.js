$(document).ready(function(){
	class ArtDeadLine {
		_startDate;
		_endDate;
		
		constructor(start, end) {
			this._startDate = start;
			this._endDate = end;
		}	
		
		getStartDate() {
			return this._startDate;
		}
		
		getEndDate() {
			return this._endDate
		}
	}
	
	
	const datePickerConfig = {
	    locale: {
	        cancelLabel: 'Clear'
    	}
	}
	
    $('input[name="datePicker"]').daterangepicker(datePickerConfig, function(start, end, label) {
    	let d = new ArtDeadLine(start.format('YYYY-MM-DD'), end.format('YYYY-MM-DD'));
    	console.log(d);
  	});
})
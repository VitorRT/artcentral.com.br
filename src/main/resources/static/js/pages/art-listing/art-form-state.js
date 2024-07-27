$(document).ready(function() {
	$("#isThereADeadline").on('change', function() {
		if ($(this).is(':checked')) {
            showDeadlineInputs();
        } else {
            hideDeadlineInputs();
        }
	})
	
	function showDeadlineInputs() {
		$("#deadlineRow").removeClass('d-none')
	}
	
	function hideDeadlineInputs() {
		$("#deadlineRow").addClass('d-none')
	}
})
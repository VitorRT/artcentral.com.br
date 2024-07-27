document.addEventListener('DOMContentLoaded', function() {
	var button = document.getElementById('tp-info');
	var tooltip = document.getElementById('tooltip');

	var popperInstance = Popper.createPopper(button, tooltip, {
		placement: 'top',
		modifiers: [
			{
				name: 'offset',
				options: {
					offset: [0, 8],
				},
			},
		],
	});

	button.addEventListener('mouseenter', () => {
		tooltip.style.visibility = 'visible';
		tooltip.style.opacity = '1';
	});

	button.addEventListener('mouseleave', () => {
		tooltip.style.visibility = 'hidden';
		tooltip.style.opacity = '0';
	});
});
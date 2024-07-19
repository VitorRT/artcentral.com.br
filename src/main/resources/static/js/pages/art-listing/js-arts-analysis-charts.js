$(document).ready(function() {
	const ctx = $('#myChart');
	const ctx2 = $('#myChart2');
	const ctx3 = $('#myChart3');
	
	initializeChart(ctx);
	initializeChart(ctx2);
	initializeChart(ctx3);
	
	function initializeChart(ctx) {
		new Chart(ctx, {
		type: 'bar',
		data: {
			labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
			datasets: [{
				label: '# of Votes',
				data: [12, 19, 3, 5, 2, 3],
				borderWidth: 1
			}]
		},
		options: {
			responsive: true, // Garante que o gráfico seja responsivo
			maintainAspectRatio: false, // Permite que o gráfico ajuste a altura de acordo com o contêiner
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	});
	}
})
$(document).ready(function() {
	var selectedImages = []; // Lista para armazenar as imagens selecionadas

	// Função para verificar e atualizar a área de imagens
	function updateImagePreview() {
		var imagesArea = $('.imageListUploader-images-area');
		imagesArea.empty(); // Limpa a área de pré-visualização

		if (selectedImages.length > 0) {
			// Remove o componente #inputFileAux
			$(".imageListUploader #inputFileAux").removeClass('d-none');
			$(".imageListUploader .inputFile").remove();

			selectedImages.forEach(function(image, index) {
				var imgElement = $('<img>').attr('src', URL.createObjectURL(image)).attr('data-index', index);
				$(".imageListUploader .imageListUploader-images-area").append(imgElement);
			});
		} else {
			$(".imageListUploader #inputFileAux").addClass('d-none');
			// Adiciona o componente #inputFileAux de volta
			var inputFile = $('<input type="file" class="inputFile" multiple>');
			inputFile.css({ "opacity": 0 });
			$(".imageListUploader .imageListUploader-images-area").before(inputFile); // Adiciona antes do input .inputFile

			var emptyHtml = `
		        <div class="default-message">
		            <span class="material-symbols-outlined">upload_file</span>
		            <span>Arraste sua imagem aqui</span>
		            <span>Ou você pode selecionar <strong>clicando aqui</strong></span>
		        </div>`;
			$(".imageListUploader .imageListUploader-images-area").html(emptyHtml);
		}
	}

	// Quando uma nova imagem é selecionada
	$('.inputFile').on('change', function(event) {
		console.log("Teste de chnage ai")
		var files = event.target.files;

		// Adiciona as novas imagens à lista
		for (var i = 0; i < files.length; i++) {
			selectedImages.push(files[i]);
		}

		updateImagePreview();
	});

	// Suporte para arrastar e soltar
	$('.imageListUploader-images-area').on('dragover', function(event) {
		event.preventDefault();
		event.stopPropagation();
		$(this).addClass('dragging');
	});

	$('.imageListUploader-images-area').on('dragleave', function(event) {
		event.preventDefault();
		event.stopPropagation();
		$(this).removeClass('dragging');
	});

	$('.imageListUploader-images-area').on('drop', function(event) {
		event.preventDefault();
		event.stopPropagation();
		$(this).removeClass('dragging');

		var files = event.originalEvent.dataTransfer.files;

		// Adiciona as imagens arrastadas à lista
		for (var i = 0; i < files.length; i++) {
			selectedImages.push(files[i]);
		}

		updateImagePreview();
	});

	// Remover imagem ao clicar
	$(document).on('click', '.imageListUploader-images-area img', function() {
		var index = $(this).data('index');
		selectedImages.splice(index, 1); // Remove a imagem da lista
		updateImagePreview(); // Atualiza a área de pré-visualização
	});

	// Abertura do seletor de arquivo ao clicar na área de imagens
	$(document).on('click', '.imageListUploader-images-area', function() {
		$('.inputFile').click();
	});

	// Chama a função inicialmente para configurar o estado inicial
	updateImagePreview();
});

document.addEventListener("DOMContentLoaded", function() {
	const images = [
		"https://i.pinimg.com/564x/a5/b4/31/a5b4319bd921d246d7e32e4313f395d8.jpg",
		"https://i.pinimg.com/736x/07/86/65/078665e1c6f9ac5505bb33907250ece6.jpg"
	];
	
	function carregaFundoCard(imageList) {
		const imagesContainer = document.querySelector('.card-art-image-bg-container');
		const columns = imageList.length;
		imageContainer.style.gridTemplateColumns = `repeat(${columns}, 1fr)`;
		imageList.forEach(img => {
	        const imgDiv = document.createElement('div')
	        imgDiv.style.backgroundImage = `url(${src})`
	        imgDiv.style.backgroundSize = 'cover'
	        imgDiv.style.backgroundPosition = 'center'
	        imagesContainer.appendChild(imgDiv)
		})
	}
	
	carregaFundoCard(images);
})
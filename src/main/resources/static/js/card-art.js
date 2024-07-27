document.addEventListener("DOMContentLoaded", function() {
	const imagesMock1 = [
		"https://i.pinimg.com/564x/a5/b4/31/a5b4319bd921d246d7e32e4313f395d8.jpg",
		"https://i.pinimg.com/736x/07/86/65/078665e1c6f9ac5505bb33907250ece6.jpg",
		"https://i.pinimg.com/564x/b7/e1/8a/b7e18a28198f0b2cae87db8ba2218018.jpg",
		"https://i.pinimg.com/564x/53/71/af/5371af2708bb9af2bd3a2cbfe1d0a4c4.jpg",
		"https://i.pinimg.com/564x/e8/28/2f/e8282fea8300ae3240bd730c6a6a6a77.jpg"
	];

	const imagesMock2 = [
		"https://i.pinimg.com/736x/0b/be/c9/0bbec9bd4d4de97409c25a6b3d626d8b.jpg",
		"https://i.pinimg.com/564x/8d/0f/c1/8d0fc167eda603f0732b16c6d676d2eb.jpg",
		"https://i.pinimg.com/736x/b4/67/cb/b467cb2a8e76876c1a1577a48b92b366.jpg"
	];


	const imagesMock3 = [
		"https://i.pinimg.com/736x/df/32/df/df32dfd1cee4533390c44fdbae6a3ad7.jpg",
		"https://i.pinimg.com/564x/9d/0c/00/9d0c00da687c841a41b70ea0104edb68.jpg"
	];

	function carregaFundoCard(card, imageList) {
		const imagesContainer = card.querySelector('.card-art-image-bg-container');
		const columns = imageList.length;
		let gridTemplate;

		if (columns === 2) {
			gridTemplate = '1fr 1fr / 1fr'
		} else if (columns === 3) {
			gridTemplate = '1fr 1fr / 1fr 1fr'
		} else if (columns === 4) {
			gridTemplate = '1fr 1fr / 1fr 1fr'
		} else {
			const rows = Math.ceil(imageList.length / 2)
			gridTemplate = '1fr '.repeat(rows).trim() + ' / 1fr 1fr'
		}

		imagesContainer.style.gridTemplateRows = gridTemplate.split(' / ')[0];
		imagesContainer.style.gridTemplateColumns = gridTemplate.split(' / ')[1];

		imagesContainer.innerHTML = '';  // Clear previous images

		imageList.map((src, index) => {
			const imgDiv = document.createElement('div')
			imgDiv.style.backgroundImage = `url(${src})`
			imgDiv.style.backgroundSize = 'cover'
			imgDiv.style.backgroundPosition = 'center'

			if (!isPair(columns) && isPair(index) && indexIsLastOfList(imageList, index)) {
				imgDiv.classList.add('full-width-img-div');
			}

			imagesContainer.appendChild(imgDiv)
		})
	}

	function isPair(number) {
		return number % 2 === 0;
	}

	function indexIsLastOfList(list, index) {
		return (list.length - 1) === index;
	}

	function addCardList(cardId, list) {
		carregaFundoCard(document.querySelector(`#${cardId}`), list);
	}


	addCardList('card1', imagesMock1);
	addCardList('card2', imagesMock2);
	addCardList('card3', imagesMock3);
})
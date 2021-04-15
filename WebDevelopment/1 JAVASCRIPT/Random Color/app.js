const button = document.querySelector('button');

button.addEventListener('click', ()=>{
	const body = document.querySelector('body');
	const r = Math.floor(Math.random()*255);
	const g = Math.floor(Math.random()*255);
	const b = Math.floor(Math.random()*255);
	body.style.backgroundColor=`rgb(${r},${g},${b})`;
})
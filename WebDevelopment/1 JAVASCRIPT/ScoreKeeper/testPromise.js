const fakeRequest = (url) => {
    return new Promise ((resolve, reject) =>{
        const rand = Math.random();
        setTimeout(()=>{
            if(rand < 0.7){
                resolve('your fake data here');
            }
            reject('request error')
        }, 1000)
    })
}


fakeRequest('/dogs/1')
        .then((data)=>{
            console.log('done with request')
            console.log('data is:', data)
        })
        .catch((err)=>{
            console.log('oh no!', err)
        })


const delayedColorChange = (color, delay) => {
    return new Promise((resolve, reject)=>{
        setTimeout(()=>{
            document.body.style.backgroundColor = color;
            resolve();
        }, delay)
    })
}

delayedColorChange('red', 1000)
.then(() => delayedColorChange('orange', 1000)) 
.then(() => delayedColorChange('blue', 1000)) 
.then(() => delayedColorChange('green', 1000)) 
.then(() => delayedColorChange('purple', 1000)) 
.then(() => delayedColorChange('indigo', 1000)) 
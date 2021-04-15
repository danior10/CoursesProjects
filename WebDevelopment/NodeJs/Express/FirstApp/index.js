const express = require('express');
const app = express();
// console.dir(app)

app.listen(3000, ()=> {
    console.log('listening on port 3000!')
});

app.get('/', (req, res)=>{
    res.send('Hello World!')
})

app.get('/deep', (req, res)=>{
    res.send('You went deep deep into rabit hole')
}   )
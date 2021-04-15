const p1Button = document.querySelector('#p1Button');
const p2Button = document.querySelector('#p2Button');
const resetButton = document.querySelector('#reset');
const playTo = document.querySelector('#playTo');


const p1Score = document.querySelector('#p1Score');
const p2Score = document.querySelector('#p2Score');

let p1S=0;
let p2S=0;
let winningScore = playTo.value;


playTo.addEventListener('change', ()=>{
    winningScore = playTo.value;
    reset();
})

p1Button.addEventListener('click', function(){
    if(p1S != winningScore && p2S != winningScore){
        p1S++;
        p1Score.textContent = p1S;

        if(p1S == winningScore){
            p1Score.classList.toggle('has-text-success');
            p2Score.classList.toggle('has-text-danger');
            p1Button.disabled = true;
            p2Button.disabled = true;

        }
    }
});

p2Button.addEventListener('click', function(){
    if(p2S != winningScore && p1S != winningScore){
        p2S++;
        p2Score.innerText = p2S;
        
        if(p2S == winningScore){
            p2Score.classList.toggle('has-text-success');
            p1Score.classList.toggle('has-text-danger');
            p1Button.disabled = true;
            p2Button.disabled = true;

        }
    }
});

resetButton.addEventListener('click', reset)

function reset(){
  p1S=0;
    p2S=0;
    p1Score.textContent = 0;
    p2Score.textContent = 0;
    p2Score.classList.remove('has-text-success', 'has-text-danger');
    p1Score.classList.remove('has-text-success','has-text-danger');
    p1Button.disabled = false;
    p2Button.disabled = false;

};





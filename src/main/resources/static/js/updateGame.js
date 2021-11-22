'use strict';

//DOM selectors
const existingGame = document.querySelector('#existingnName');
const GameName = document.querySelector('#name');
const GameDescript = document.querySelector('#descript');
const pc = document.querySelector('#pc');
const playstation = document.querySelector('#playstation');
const xbox = document.querySelector('#xbox');
const nintendo = document.querySelector('#nintendo');
const gameImage = document.querySelector('#image');
const submitBtn = document.querySelector('#submitBtn');
const form = document.querySelector('#form');

// Function to validate form
const checkForm = () => {
  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation');

  // Loop over them and check validation
  Array.prototype.slice.call(forms).forEach(function (form) {
    form.addEventListener(
      'input',
      function (event) {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      },
      false
    );
  });
};

// Function to prevent default form behaviour

const handleForm = (event) => {
  event.preventDefault();
};

//Functions to handle post request

const postLogic = (obj) => {
  fetch(`http://localhost:9002/game/update/${existingGame.value}`, {
    method: 'PUT',
    headers: {
      'content-type': 'application/JSON',
    },
    body: JSON.stringify(obj),
  }).then((response) => {
    if (response.status !== 202) {
      alert('Sorry, no game with that name exists in the library - please try again');
      return;
    }
    alert('Game successfully updated');
  });
};

const postData = () => {
  const newGame = {
    title: GameName.value,
    descript: GameDescript.value,
    pc: pc.checked,
    playstation: playstation.checked,
    xbox: xbox.checked,
    nintendo: nintendo.checked,
    image: gameImage.value,
  };
  postLogic(newGame);
};

//EventListener(s)
GameName.addEventListener('input', checkForm);
GameDescript.addEventListener('input', checkForm);
gameImage.addEventListener('input', checkForm);
form.addEventListener('submit', handleForm);
submitBtn.addEventListener('click', postData);

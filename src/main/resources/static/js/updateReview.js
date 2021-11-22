'use strict';

//DOM selectors
const reviewId = document.querySelector('#reviewId');
const gameId = document.querySelector('#gameId');
const username = document.querySelector('#username');
const score = document.querySelector('#score');
const review = document.querySelector('#review');
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
  fetch(`http://localhost:9002/reviews/update/${reviewId.value}`, {
    method: 'PUT',
    headers: {
      'content-type': 'application/JSON',
    },
    body: JSON.stringify(obj),
  }).then((response) => {
    if (response.status !== 202) {
      alert('Sorry, no review with that id exists - please try again');
      return;
    }
    alert('Review successfully updated');
  });
};

const postData = () => {
  const newReview = {
    username: username.value,
    score: score.value,
    review: review.value,
    game: {
      id: gameId.value,
    },
  };
  postLogic(newReview);
};

//EventListener(s)
username.addEventListener('input', checkForm);
review.addEventListener('input', checkForm);
form.addEventListener('submit', handleForm);
submitBtn.addEventListener('click', postData);

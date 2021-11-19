//DOM selectors
const gameSearchInputField = document.querySelector('#gameTitle');
const main = document.querySelector('main');
const scoreContainer = document.querySelector('#score-container');

const fetchdata = () => {
  fetch('http://localhost:9002/reviews/getAllGames').then((response) => {
    if (response.status !== 200) {
      console.error(`error status: ${response.status}`);
    }
    response
      .json()
      .then((data) => {
        let v = 0; // alert validatior counter
        let i = 0; // index
        let scoreArray = []; // array to aggregate review scores.
        main.innerHTML = '';
        scoreContainer.innerHTML = '';
        for (let review of data) {
          if (gameSearchInputField.value.toUpperCase() === data[i].game.title.toUpperCase()) {
            createReviewCard(review);
            scoreArray.push(review.score);
            v++;
            i++;
          } else {
            i++;
          }
        }
        calculateAvgReviewScore(scoreArray);
        if (
          v ? '' : alert('Sorry, either no game found with that name or no reviews have been written for that game.')
        );
      })
      .catch((error) => {
        `connection works but error: ${error}`;
      });
  });
};

// function to capitalise first letter in string
function captialise(str) {
  let result = str[0].toUpperCase();
  for (let i = 1; i < str.length; i++) {
    if (str[i - 1] === ' ') {
      result += str[i].toUpperCase();
    } else {
      result += str[i];
    }
  }

  return result;
}

// Function to calculate review score average as a % and append to DOM.
const calculateAvgReviewScore = (array) => {
  let totalscore = 0;
  let avgScore = 0;
  for (let i = 0; i < array.length; i++) {
    totalscore += array[i];
  }
  avgScore = totalscore / array.length / 0.1;

  // Create HTML elements
  const divContainer = document.createElement('div');
  const head1 = document.createElement('h4');
  const head2 = document.createElement('h4');

  //Add classlists to HTML elements
  divContainer.classList = 'd-flex justify-content-between mb-3';
  head1.classList = 'ms-3';
  head2.classList = 'me-5';

  // DOM manipulation
  let formatInput = captialise(gameSearchInputField.value);
  head1.textContent = formatInput;
  head2.textContent = `Community Game Review Score: ${avgScore}%`;

  // Append everything
  divContainer.appendChild(head1);
  divContainer.appendChild(head2);

  scoreContainer.appendChild(divContainer);
};

const createReviewCard = (data) => {
  // Create HTML elements
  const parentArticle = document.createElement('article');
  const parentDiv = document.createElement('div');
  const imageSection = document.createElement('section');
  const image = document.createElement('img');
  const contentSection = document.createElement('section');
  const bodyDiv = document.createElement('div');
  const cardHeader = document.createElement('h5');
  const para1 = document.createElement('p');
  const para2 = document.createElement('p');
  const para3 = document.createElement('p');
  const anchor1 = document.createElement('a');
  const anchor2 = document.createElement('a');

  //Add classlists to HTML elements
  parentArticle.classList = 'card mb-3 ms-3 me-3';
  parentDiv.classList = 'row';
  bodyDiv.classList = 'card-body';
  cardHeader.classList = 'card-title';
  para1.classList = 'card-text';
  para2.classList = 'card-text';
  para3.classList = 'card-text';
  anchor1.classList = 'me-3';

  // DOM manipulation
  cardHeader.textContent = data.username;
  para1.textContent = `#${data.id}`;
  para2.textContent = data.review;
  para3.textContent = `${data.score}/10`;
  anchor1.textContent = 'Update review';
  anchor1.href = 'updateReview.html';
  anchor2.textContent = 'Delete review';
  anchor2.href = 'deleteReview.html';

  // Append everything
  bodyDiv.appendChild(cardHeader);
  bodyDiv.appendChild(para1);
  bodyDiv.appendChild(para2);
  bodyDiv.appendChild(para3);
  bodyDiv.appendChild(anchor1);
  bodyDiv.appendChild(anchor2);

  contentSection.appendChild(bodyDiv);
  imageSection.appendChild(image);

  parentDiv.appendChild(imageSection);
  parentDiv.appendChild(contentSection);
  parentArticle.appendChild(parentDiv);

  main.appendChild(parentArticle);
};

//EventListener(s)
gameSearchInputField.addEventListener('keypress', function (e) {
  if (e.key === 'Enter') {
    fetchdata();
  }
});

//DOM selectors
const userInputField = document.querySelector('#username');
const main = document.querySelector('main');

const fetchdata = () => {
  fetch(`http://localhost:9002/reviews/getAllReviewsByUsername/${userInputField.value}`).then((response) => {
    if (response.status !== 200) {
      console.error(`error status: ${response.status}`);
    }
    response
      .json()
      .then((data) => {
        let v = 0; // alert validatior counter
        main.innerHTML = '';
        for (let review of data) {
          createReviewCard(review);
          v++;
        }
        if (v ? '' : alert('Sorry, no user with that username exists'));
      })
      .catch((error) => {
        `connection works but error: ${error}`;
      });
  });
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
userInputField.addEventListener('keypress', function (e) {
  if (e.key === 'Enter') {
    fetchdata();
  }
});

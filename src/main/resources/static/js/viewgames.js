'use strict';

//DOM variables
const main = document.querySelector('main');

fetch('http://localhost:9002/game/getAll').then((response) => {
  if (response.status !== 200) {
    console.error(`error status: ${response.status}`);
  }
  response
    .json()
    .then((data) => {
      console.log(data);
      for (let game of data) {
        createGameCard(game);
      }
    })
    .catch((error) => {
      `connection works but error: ${error}`;
    });
});

const createGameCard = (data) => {
  //Helper functions
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

  //Add classlists to HTML elements
  parentArticle.classList = 'card mb-3 ms-3 me-3';
  parentDiv.classList = 'row';
  imageSection.classList = 'col-md-3';
  contentSection.classList = 'col-md-9';
  bodyDiv.classList = 'card-body';
  cardHeader.classList = 'card-title';
  para1.classList = 'card-text';
  para2.classList = 'card-text';
  para3.classList = 'card-text';
  image.classList = 'image-size';

  // DOM manipulation
  image.src = data.image;
  image.alt = 'game art';
  cardHeader.textContent = data.title;
  para1.textContent = data.descript;
  // text content contains ternary logic to only append platform keys e.g. PC - that are true in the database.
  para2.textContent = `Platform: ${data.pc ? Object.keys(data)[3].toUpperCase() : ''} ${
    data.playstation ? captialise(Object.keys(data)[4]) : ''
  } ${data.xbox ? captialise(Object.keys(data)[5]) : ''} ${data.nintendo ? captialise(Object.keys(data)[6]) : ''}`;
  para3.textContent = `#${data.id}`;
  anchor1.textContent = 'Write review';
  anchor1.href = 'createReview.html';

  // Append everything
  bodyDiv.appendChild(cardHeader);
  bodyDiv.appendChild(para1);
  bodyDiv.appendChild(para2);
  bodyDiv.appendChild(para3);
  bodyDiv.appendChild(anchor1);

  contentSection.appendChild(bodyDiv);
  imageSection.appendChild(image);

  parentDiv.appendChild(imageSection);
  parentDiv.appendChild(contentSection);
  parentArticle.appendChild(parentDiv);

  main.appendChild(parentArticle);
};

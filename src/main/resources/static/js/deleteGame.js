'use strict';

//DOM selectors
const gameId = document.querySelector('#gameId');
const submitBtn = document.querySelector('#submitBtn');

//Function(s)
const deleteByPenguinId = () => {
  fetch(`http://localhost:9002/game/delete/${gameId.value}`, {
    method: 'DELETE',
  }).then((response) => {
    if (response.status !== 204) {
      alert(
        'Unable to delete, either no game with inputted id exists or game has associated reviews so cannot be deleted'
      );
      return;
    }
    alert('Game successfully deleted');
  });
};

//EventListener(s)
submitBtn.addEventListener('click', deleteByPenguinId);

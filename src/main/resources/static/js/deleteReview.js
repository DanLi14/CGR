'use strict';

//DOM selectors
const reviewId = document.querySelector('#reviewId');
const submitBtn = document.querySelector('#submitBtn');

//Function(s)
const deleteByReviewId = () => {
  fetch(`http://localhost:9002/reviews/delete/${reviewId.value}`, {
    method: 'DELETE',
  }).then((response) => {
    if (response.status !== 204) {
      alert('Unable to delete, no review with that id exists');
      return;
    }
    alert('Review successfully deleted');
  });
};

//EventListener(s)
submitBtn.addEventListener('click', deleteByReviewId);

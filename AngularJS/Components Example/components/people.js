angular.module('demoApp').component('people', {
  bindings: { people: '<' },
  
  templateUrl: 'partials/people.html'
});
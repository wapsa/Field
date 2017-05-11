angular.module('demoApp').service('PeopleService', function($http) {
	
  var service = {
    getAllPeople: function() {
      return $http.get('data/people.json', { cache: true }).then(function(resp) {
        return resp.data;
      });
    }
  }
  return service;
})
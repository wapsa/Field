var myApp = angular.module('helloworld', [ 'ui.router' ]);

myApp.config(function($stateProvider) {
	var helloState = {
		name : 'hello',
		url : '/hello',
		templateUrl : 'hello.html',
		controller : 'indexController'
	}

	var aboutState = {
		name : 'about',
		url : '/about',
		templateUrl : 'about.html',
		controller : 'indexController'
	}

	var peopleState = {
		name : 'people',
		url : '/people',
		templateUrl : 'people.html',
		controller : 'peopleController'
	}

	var personState = {
		name : 'people.person',
		url : '/{personId}',
		templateUrl : 'person.html',
		controller : 'personController'
	}

	$stateProvider.state(helloState);
	$stateProvider.state(aboutState);
	$stateProvider.state(peopleState);
	$stateProvider.state(personState);
});

myApp.controller('indexController', ['$scope', function($scope) {
	console.log("inside indexController");
	$scope.world = 'world';
}]);

myApp.controller('peopleController', [
		'$scope',
		'PeopleService',
		function($scope, PeopleService) {
			console.log("inside peopleController");
			 PeopleService.getAllPeople().then(function(data) {
				 $scope.people = data.data;		
				 PeopleService.setPeople($scope.people);
			});
			console.log("leaving peopleController"
					+ JSON.stringify($scope.people));
		} ]);

myApp.controller('personController', [ '$scope', '$stateParams',
		'PeopleService', function($scope, $stateParams, PeopleService) {
			console.log("inside personController");
			var people = PeopleService.getPeople();

			$scope.person = people.find(function(person) {
				return person.id === $stateParams.personId;
			});
		} ]);

myApp.service('PeopleService', [ '$http', '$q', function($http, $q) {

	var property = [];

	return {

		getAllPeople : function() {

			var deferred = $q.defer();
			var data = {};
			$http({
				method : "GET",
				url : "data/people.json"
			}).then(function mySucces(data) {
				deferred.resolve(data);
			});
			return deferred.promise;
		},
		
		setPeople : function(data) {
			property = data;
		},
		
		getPeople: function() {
			return property;
		}

	}

} ]);

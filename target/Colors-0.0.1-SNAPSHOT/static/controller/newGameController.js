app.controller('newGameCtl', function($rootScope, $scope, $location, $http) {
	$scope.message = false;
	
	$scope.securCheck = function() {
		if ($rootScope.user == null) {
			console.log("denied");
			accessGranted = false;
			$location.path('/');
		} else {
			console.log("granted");
			accessGranted = true;
		}
	}

	$scope.createGame = function() {
		
		$http({
			url : 'Game/Create.do',
			method : 'GET',
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			},
			params : {
				name : $scope.gameName,
				userId: $rootScope.user.id
			}
		}).then(function success(response) {
			$scope.updateDisplay = true;
			$scope.message = "Game created";
			$location.path('/home');
		}, function error(response) {
			$scope.message = 'Error';
		});
	}
	
	$scope.securCheck();
});
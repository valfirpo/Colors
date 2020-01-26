app.controller('stringGameOverCtl', function($rootScope, $scope, $location, $http) {

	$scope.securCheck = function() {
		if ($rootScope.user == undefined) {
			console.log("denied");
			accessGranted = false;
			$location.path('/');
		} else {
			console.log("granted");
			accessGranted = true;
		}
	}
	
	$scope.backToLobby = function() {
		
		$location.path('/lobby');

	}
	
	$scope.securCheck();

});
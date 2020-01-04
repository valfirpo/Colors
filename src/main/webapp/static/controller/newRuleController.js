app.controller('newRuleCtl', function($rootScope, $scope, $location, $http) {
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

	$scope.createRule = function() {

		$http({
			url : 'Rule/Create.do',
			method : 'POST',
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			},
			params : {
				rule : $scope.rule,
				drinks : $scope.drinks,
				gameId : $rootScope.gameSelected.id
			}
		}).then(function success(response) {
			$scope.updateDisplay = true;
			$scope.message = "Rule created";
			$location.path('/viewGame');
		}, function error(response) {
			$scope.message = 'Error';
		});
	}
	
	$scope.securCheck();
});
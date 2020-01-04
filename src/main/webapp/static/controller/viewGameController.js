app.controller('viewGameCtl', function($rootScope, $scope, $location, $http) {
	$scope.rules;
	$scope.gameName;
	$scope.hide = true;
	var acessgranted;

	$scope.securCheck = function() {
		console.log("here");
		if ($rootScope.user == null) {
			console.log("denied");
			accessGranted = false;
			$location.path('/');
		} else {
			console.log("granted");
			accessGranted = true;
		}
	}

	$scope.getRules = function() {

		$http({
			url : 'Rule/GetRulesByGame.do',
			method : 'GET',
			params : {
				id : $rootScope.gameSelected.id
			}
		}).then(function success(response) {
			$scope.rules = response.data;
		}, function error(response) {
			$scope.rules = null;
		});
	}

	$scope.editRule = function(rule) {
		$rootScope.ruleSelected = rule;
		console.log(rule.rule);
		console.log($rootScope.ruleSelected);
		$location.path('/editRule');
	}
	
	$scope.securCheck();
	if (accessGranted) {
		$scope.getRules();
		if ($rootScope.gameSelected.userId != -1) {
			$scope.hide = false;
		}
		$scope.gameName = $rootScope.gameSelected.name;
	}
});
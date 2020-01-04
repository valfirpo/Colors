app.controller('editRuleCtl', function($rootScope, $scope, $location, $http) 
{
	$scope.message = false;
	$scope.rule;
	$scope.ruleNew;
	$scope.drinksNew;
	$scope.drinksNew = 1;
	
	var acessgranted;
	
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
	
	$scope.updateRule = function() {

		$http({
			url : 'Rule/Update.do',
			method : 'POST',
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			},
			params : {
				ruleId : $rootScope.ruleSelected.id,
				rule : $scope.ruleNew,
				used: $rootScope.ruleSelected.used,
				drinks : $scope.drinksNew,
				gameId : $rootScope.gameSelected.id
			}
		}).then(function success(response) {
			$scope.updateDisplay = true;
			$scope.message = "Rule updated";
			$location.path('/viewGame');
		}, function error(response) {
			$scope.message = 'Error';
		});
	}
	
	$scope.securCheck();
	if(accessGranted)
	{
		$scope.rule = $rootScope.ruleSelected.rule;
		$scope.ruleNew = $rootScope.ruleSelected.rule;
		$scope.drinksNew = $rootScope.ruleSelected.drinks;
	}
});
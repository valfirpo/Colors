app.controller('playGameCtl', function($rootScope, $scope, $location, $http) {

	$scope.hide = true;
	$scope.drinksNow;
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
				id : $rootScope.gameInPlay.id
			}
		}).then(function success(response) {
			$scope.rules = response.data;
		}, function error(response) {
			$scope.rules = null;
		});
	}

	$scope.endGame = function() {

		var ruleList = $scope.rules;
		var ruleStr = "";

		for (var i = 0; i < ruleList.length; i++) {
			console.log(ruleList[i]);
			ruleStr += ruleList[i].id;
			ruleStr += ",";
			ruleStr += ruleList[i].rule;
			ruleStr += ",";
			ruleStr += ruleList[i].used;
			ruleStr += ",";
			ruleStr += ruleList[i].drink;
			ruleStr += ",";
			ruleStr += ruleList[i].gameId;
			ruleStr += ":";
		}

		$http({
			url : 'Rule/SaveRules.do',
			method : 'POST',
			params : {
				rules : ruleStr
			}
		}).then(function success(response) {
			$location.path('/home');
		}, function error(response) {
			$scope.rules = null;
		});
	}

	$scope.drink = function(rule) {
		var r = rule.drink;
		rule.used += r;
		$scope.drinksNow += r;
		$rootScope.drinksNow = $scope.drinksNow;
	}
	
	$scope.securCheck();
	if (accessGranted) {
		$scope.getRules();
		if ($rootScope.gameInPlay.userId != -1) {
			$scope.hide = false;
		}
		$scope.drinksNow = $rootScope.drinksNow;
	}
});
app.controller('homeCtl', function($rootScope, $scope, $location, $http) 
{
	$rootScope.games;
	$rootScope.gameSelected;
	$rootScope.gameInPlay;
	$rootScope.ruleSelected;
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
	
	$scope.getGames = function() {
		var userId = $rootScope.user.id;
		
			$http({
				url : 'Game/GetByUser.do',
				method : 'GET',
				params : {id : userId}
			}).then(function success(response) {
				$rootScope.games = response.data;
			}, function error(response) {
				$rootScope.games = null;
			});
	}
	
	$scope.viewGame = function(game){
		$rootScope.gameSelected = game;
		$location.path('/viewGame');
	}
	
	$scope.playGame = function(game) {
		if(($rootScope.gameInPlay == null))
		{
			$rootScope.gameInPlay = game;
			$rootScope.drinksNow = 0;
		}
		else if($rootScope.gameInPlay.id != game.id)
		{
			$rootScope.gameInPlay = game;
			$rootScope.drinksNow = 0;
		}
		
		$location.path('/playGame');
	}
	
	$scope.editGame = function(game) {
		$rootScope.gameSelected = game;
		$location.path('/editGame');
	}
	
	$scope.securCheck();
	if(accessGranted)
	{
		$scope.getGames();
	}
	
});
app.controller('publicGameCtl', function($rootScope, $scope, $location, $http) 
{
	$scope.pubGames = null;
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
	
	$scope.getPublicGames = function() {
		
			$http({
				url : 'Game/GetByPublic.do',
				method : 'GET'
			}).then(function success(response) {
				$scope.pubGames = response.data;
			}, function error(response) {
				$scope.pubGames = null;
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

	
	$scope.securCheck();
	if(accessGranted)
	{
		$scope.getPublicGames();
	}
	
});
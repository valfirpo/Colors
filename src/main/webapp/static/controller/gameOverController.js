app.controller('gameOverCtl', function($rootScope, $scope, $location, $http) {
	
	$scope.gameLoser = null;

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
	
	$scope.getGameLoser = function() {
		if ($rootScope.game.winner == "player1") {
			$scope.gameLoser = "player2";
		}
		else {
			$scope.gameLoser = "player1";
		}
		console.log("LOSER", $scope.gameLoser);
	}
	
	$scope.backToLobby = function() {
		
		$location.path('/lobby');

	}
	
	$scope.securCheck();
	$scope.getGameLoser();

});
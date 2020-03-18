app.controller('ticTacWoeGameCtl', function($rootScope, $scope, $location, $http) {

	$scope.myTurn;
	$scope.spotSelected = null;
	var inter;
	
	$scope.setSpotSelected = function(index) {
		$scope.spotSelected = index;
		console.log("SPOT SELECTED", $scope.spotSelected);
	}
	
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

//	{
//		player1: "pemp",
//		player2: "temp",
//		turn: null,
//		status: "JOINED",
//		type: "Tic_Tac_Woe",
//		board: ["0", "1", "2", "3", "4", "5", "6", "7", "8"],
//		winner: null,
//		gameOver: false,
//	}
	

	
	$scope.getGame = function(username) {
		
		$http({
			url : 'Lobby/getGame.do',
			method : 'GET',
			params : {
				username : username
			}
		}).then(function success(response) {
			$rootScope.game = response.data;
			console.log($rootScope.game);
			
			if ($rootScope.game.status == 'OVER'){
				clearInterval(inter);
				$location.path('/gameOver');
			} else if (isMyTurn()) {
				clearInterval(inter);
				unlock();
			} else {
				$scope.message = "Sorry, still waiting on an opponent.";
			}
		}, function error(response) {
			console.log('Error checking match.');
			$scope.message = 'Error checking match.';
		});

	}
	
	function isMyTurn() {
		
		if($rootScope.user.username == $rootScope.game.turn){
			$scope.myTurn = true;
		} else {
			$scope.myTurn = false;
		}

		return $scope.myTurn;
	}
	
	$scope.isMyTurnFirst = function() {
		
		console.log($rootScope.user.username + " " + $rootScope.game.turn)

		if($rootScope.user.username == $rootScope.game.turn){
			$scope.myTurn = true;
		} else {
			lock();
			$scope.myTurn = false;
			refreshGame($rootScope.game.player1);
		}

	}
	
	function refreshGame(username) {
		
		inter = setInterval(
				function() {
					$scope.getGame(username);
				}, 5000)
	}

	$scope.updateGame = function() {
		
		$http({
			url : 'Game/TicTacWoe/updateGame.do',
			method : 'POST',
			params : {
				username: $rootScope.game.player1,
				turn: $rootScope.user.username,
				spot: $scope.spotSelected
			}
		}).then(function success(response) {
			console.log('Successfully joined lobby');
			$scope.message = 'Successfully joined the lobby.';
			$rootScope.game = response.data;
			console.log($rootScope.game);
			$scope.isMyTurnFirst();

		}, function error(response) {
			console.log('Error joining lobby');
			$scope.message = 'Error, unable to join lobby.';
		});
	}
	
	function lock(){
		//show waiting
		console.log('lock');
	}
	
	function unlock(){
		//display board
		console.log('unlock');
	}

	$scope.securCheck();
	$scope.isMyTurnFirst();

});
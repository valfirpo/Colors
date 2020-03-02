app.controller('ticTacWoeGameCtl', function($rootScope, $scope, $location, $http) {

	$scope.myTurn;
	var inter;

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

//	$scope.getGame = function(username) {
//
//		$http({
//			url : 'Lobby/getGame.do',
//			method : 'GET',
//			params : {
//				username : username
//			}
//		}).then(function success(response) {
//			$scope.message = 'Success game found.';
//			$rootScope.game = response.data;
//			console.log($rootScope.game);
//			$scope.isMyTurn();
//		}, function error(response) {
//			console.log('Error checking match.');
//			$scope.message = 'Error checking match.';
//		});
//
//	}
	
//	$scope.getGame = function(username) {
//		
//		$http({
//			url : 'Lobby/getGame.do',
//			method : 'GET',
//			params : {
//				username : username
//			}
//		}).then(function success(response) {
//			$rootScope.game = response.data;
//			console.log($rootScope.game);
//			if ($rootScope.game.status == 'OVER'){
//				clearInterval(inter);
//				$location.path('/stringGameOver');
//			} else if (isMyTurn()) {
//				clearInterval(inter);
//			} else {
//				$scope.message = "Sorry, still waiting on an opponent.";
//			}
//		}, function error(response) {
//			console.log('Error checking match.');
//			$scope.message = 'Error checking match.';
//		});
//
//	}
	
//	function isMyTurn() {
//		
//		console.log($rootScope.user.username + " " + $rootScope.game.turn)
//
//		if($rootScope.user.username == $rootScope.game.turn){
//			$scope.myTurn = true;
//		} else {
//			$scope.myTurn = false;
//		}
//
//	}
	
//	$scope.isMyTurnFirst = function() {
//		
//		console.log($rootScope.user.username + " " + $rootScope.game.turn)
//
//		if($rootScope.user.username == $rootScope.game.turn){
//			$scope.myTurn = true;
//		} else {
//			$scope.myTurn = false;
//			refreshGame($rootScope.game.player1);
//		}
//
//	}
	
//	function refreshGame(username) {
//		
//		inter = setInterval(
//				function() {
//					$scope.getGame(username);
//				}, 5000)
//	}

//	$scope.updateGame = function(username, newWord) {
//		
//		$http({
//			url : 'Game/StringGame/updateGame.do',
//			method : 'POST',
//			params : {
//				username : username,
//				newWord : newWord
//			}
//		}).then(function success(response) {
//			console.log('Successfully joined lobby');
//			$scope.message = 'Successfully joined the lobby.';
//			$rootScope.game = response.data;
//			console.log($rootScope.game);
//			$scope.isMyTurnFirst();
//
//		}, function error(response) {
//			console.log('Error joining lobby');
//			$scope.message = 'Error, unable to join lobby.';
//		});
//	}

	$scope.securCheck();
	//$scope.isMyTurnFirst();

});
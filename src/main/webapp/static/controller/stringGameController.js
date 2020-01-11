app.controller('stringGameCtl', function($rootScope, $scope, $location, $http) {

	$scope.myTurn;

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

	$scope.getGame = function(username) {

		$http({
			url : 'Lobby/getGame.do',
			method : 'GET',
			params : {
				username : username
			}
		}).then(function success(response) {
			$scope.message = 'Success game found.';
			$rootScope.game = response.data;
			console.log($rootScope.game);
			$scope.isMyTurn();
		}, function error(response) {
			console.log('Error checking match.');
			$scope.message = 'Error checking match.';
		});

	}
	
	$scope.isMyTurn = function(username) {
		
		console.log($rootScope.user.username + " " + $rootScope.game.turn)

		if($rootScope.user.username == $rootScope.game.turn){
			$scope.myTurn = true;
		} else {
			$scope.myTurn = false;
		}

	}

	$scope.updateGame = function(username, newWord) {
		
		$http({
			url : 'Game/StringGame/updateGame.do',
			method : 'POST',
			params : {
				username : username,
				newWord : newWord
			}
		}).then(function success(response) {
			console.log('Successfully joined lobby');
			$scope.message = 'Successfully joined the lobby.';
			$rootScope.game = response.data;
			console.log($rootScope.game);
			$scope.isMyTurn();

		}, function error(response) {
			console.log('Error joining lobby');
			$scope.message = 'Error, unable to join lobby.';
		});
	}

	$scope.securCheck();
	$scope.isMyTurn();

});
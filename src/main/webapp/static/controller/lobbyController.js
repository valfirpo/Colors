app.controller('lobbyCtl', function($rootScope, $scope, $location, $http) {

	$scope.lobby;
	$scope.create = true;
	$scope.join = true;
	$scope.availableGames = false;
	$rootScope.game;
	var acessgranted;

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
	
	function isGameJoined(username) {

		console.log(username);
		function checkIsJoined(username) {
			console.log("Checking for an opponent");
			$scope.getGame(username);
		}
		var inter = setInterval(function() {
			
			if($rootScope.game.status === "JOINED") {
				$scope.message = "Someone has joined the game";
				clearInterval(inter);
			}
			else {
				$scope.message = "Sorry, still waiting on an opponent.";
			}
			checkIsJoined(username);
		}, 5000)

//		$scope.message = "Game joined.";
		//$rootScope.game.status != "JOINED"
	}

	$scope.createGame = function() {

		$http({
			url : 'Lobby/createGame.do',
			method : 'POST',
			params : {
				username : $rootScope.user.username
			}
		}).then(function success(response) {
			console.log('Success');
			$scope.message = 'Successfully created game, waiting on an opponent.';
			$rootScope.game = response.data;
			$scope.join = false;
			console.log($rootScope.game);
			isGameJoined($rootScope.game.player1);
			//isGameJoined($rootScope.game.player1);
		}, function error(response) {
			console.log('Error creating game');
			$scope.message = 'rror creating game';
		});

		

	}

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
		}, function error(response) {
			console.log('Error checking match.');
			$scope.message = 'Error checking match.';
		});

	}



	$scope.join = function(userToJoin) {

		$http({
			url : 'Lobby/joinGame.do',
			method : 'POST',
			params : {
				username : $rootScope.user.username,
				userToJoin : userToJoin
			}
		}).then(function success(response) {
			console.log('Successfully joined lobby');
			$scope.message = 'Successfully joined the lobby.';
			$rootScope.game = response.data;
			console.log($rootScope.game);

		}, function error(response) {
			console.log('Error joining lobby');
			$scope.message = 'Error, unable to join lobby.';
		});
	}
	
	$scope.setGameStarted = function(username) {

		$http({
			url : 'Lobby/setGameStarted.do',
			method : 'POST',
			params : {
				username : username
			}
		}).then(function success(response) {
			
			$rootScope.game = response.data;
			console.log($rootScope.game);
			if($rootScope.game.status == 'STARTED'){
				$location.path('/stringGame');
			}

		}, function error(response) {
			console.log('Failed');
			$scope.message = 'Failed';
		});
	}

	$scope.getLobby = function() {

		$http({
			url : 'Lobby/getLobby.do',
			method : 'GET'
		}).then(function success(response) {
			console.log('Successfully joined lobby');
			$scope.message = 'Successfully joined the lobby.';
			$scope.lobby = response.data;
			$scope.create = false;
			$scope.availableGames = true;
		}, function error(response) {
			console.log('Error joining lobby');
			$scope.message = 'Error, unable to join lobby.';
		});

	}
	
	$scope.goToGame = function() {

		console.log($rootScope.game.status);
		if($rootScope.game.status == 'STARTED'){
			$location.path('/stringGame');
		}

	}

	$scope.securCheck();
	// if(accessGranted)
	// {
	// $scope.game = $rootScope.gameSelected;
	// $scope.gameNewName = $rootScope.gameSelected.name;
	// $scope.gameNewPub = $rootScope.gameSelected.pub;
	//		
	// if($scope.game.pub == 1)
	// {
	// $scope.game.pub = true;
	// }
	// else
	// {
	// $scope.game.pub = false;
	// }
	// }
});
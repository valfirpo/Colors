app.controller('lobbyCtl', function($rootScope, $scope, $location, $http) {

	$scope.lobby;
	$scope.create = true;
	$scope.join = true;
	$scope.availableGames = false;
	$scope.startGame = false;
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

		var inter = setInterval(function() {
			
			if($rootScope.game.status === "JOINED") {
				$scope.message = "Someone has joined the game";
				$scope.startGame = true;
				clearInterval(inter);
			}
			else {
				$scope.message = "Sorry, still waiting on an opponent.";
			}
			$scope.getGame(username);
		}, 5000)
	}
	
	function isGameStarted(username) {

		console.log(username);

		var inter = setInterval(function() {
			
			if($rootScope.game.status === "STARTED") {
				$scope.message = "Game has started";
				$scope.startGame = true;
				clearInterval(inter);
			}
			else {
				$scope.message = "Sorry, still waiting on game to start.";
			}
			$scope.getGame(username);
		}, 5000)
	}

	$scope.createGame = function() {

		$http({
			url : 'Lobby/createGame.do',
			method : 'POST',
			params : {
				username : $rootScope.user.username
			}
		}).then(function success(response) {
			//console
			console.log('Successfully created game, waiting on an opponent.');
			
			//message
			$scope.message = 'Successfully created game, waiting on an opponent.';
			
			//response
			$rootScope.game = response.data;
			
			//status change
			statusChange('createGame');
			
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



	$scope.joinGame = function(userToJoin) {

		$http({
			url : 'Lobby/joinGame.do',
			method : 'POST',
			params : {
				username : $rootScope.user.username,
				userToJoin : userToJoin
			}
		}).then(function success(response) {
			//console
			console.log('Successfully joined game');
			
			//message
			$scope.message = 'Successfully joined game';
			
			//response
			$rootScope.game = response.data;
			
			//status change
			statusChange('joinGame');

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
			
			//console
			console.log($rootScope.game);
			
			//message
			
			//response
			$rootScope.game = response.data;
			
			//status change
			statusChange('setGameStarted');

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
			//console
			console.log('Successfully got lobby');
			
			//message
			$scope.message = 'Successfully got lobby';
			
			//response
			$scope.lobby = response.data;
			
			//status change
			statusChange('getLobby');
			
		}, function error(response) {
			console.log('Error getting lobby');
			$scope.message = 'Error getting lobby';
		});

	}
	
	$scope.goToGame = function() {

		console.log($rootScope.game.status);
		if($rootScope.game.status == 'STARTED'){
			$location.path('/stringGame');
		}

	}

	$scope.securCheck();
	
	function statusChange(expression){
		
		console.log('Status Changeeee');
		
		switch(expression) {
		  case 'createGame':
				$scope.join = false;
				isGameJoined($rootScope.game.player1);
		    break;
		  case 'getLobby':
			  	$scope.join = false;
			  	$scope.create = false;
				$scope.availableGames = true;
		    break;
		  case 'joinGame':
			  $scope.availableGames = false;
			  isGameStarted($rootScope.game.player1);
		    break;
		  case 'setGameStarted':
			  if($rootScope.game.status == 'STARTED'){
					$location.path('/stringGame');
				}
			break;

		  default:
		    // code block
		}
		
	}
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
app.controller('lobbyCtl',
	function($rootScope, $scope, $location, $http) {

		$scope.lobby;
		$scope.create = true;
		$scope.join = true;
		$scope.availableGames = false;
		$scope.startGame = false;
		$scope.goToGame_button = false;
		$scope.header = 'Welcome';
		$scope.message = 'Let\'s play! Select an option to get started.';
		$rootScope.game;
		var acessgranted;
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

		function isGameJoinedLooper(username) {

			console.log(username);

			inter = setInterval(function() {
				isGameJoined(username);
			}, 5000)
		}

		function isGameStartedLooper(username) {

			console.log(username);

			inter = setInterval(function() {
				isGameStarted(username);
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
				// response
				$rootScope.game = response.data;

				// status change
				statusChange('createGame');

			}, function error(response) {
				console.log('Error creating game');
				$scope.message = 'Error creating game';
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

		function isGameJoined(username) {

			$http({
				url : 'Lobby/getGame.do',
				method : 'GET',
				params : {
					username : username
				}
			}).then(function success(response) {
				$rootScope.game = response.data;
				console.log($rootScope.game);
				if ($rootScope.game.status === "JOINED") {
					statusChange("isGameJoined");
					clearInterval(inter);
				} else {
					$scope.header = "Loading...";
					$scope.message = "Sorry, still waiting on an opponent.";
				}
			},
			function error(response) {
				console
						.log('Error checking match.');
				$scope.message = 'Error checking match.';
			});
		}

		function isGameStarted(username) {

			$http({
				url : 'Lobby/getGame.do',
				method : 'GET',
				params : {
					username : username
				}
			}).then(
				function success(response) {
					$rootScope.game = response.data;
					console.log($rootScope.game);
					if ($rootScope.game.status === "STARTED") {
						statusChange("isGameStarted");
						clearInterval(inter);
					} else {
						$scope.header = "Loading...";
						$scope.message = "Sorry, still waiting on game to start.";
					}
	
				},
				function error(response) {
					console
							.log('Error checking match.');
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
				// response
				$rootScope.game = response.data;

				// status change
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
				// response
				$rootScope.game = response.data;

				// status change
				if ($rootScope.game.status == 'STARTED') {
					statusChange('setGameStarted');
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

				// response
				$scope.lobby = response.data;

				// status change
				statusChange('getLobby');

			}, function error(response) {
				console.log('Error getting lobby');
				$scope.message = 'Error getting lobby';
			});

		}

		$scope.goToGame = function() {

			console.log($rootScope.game.status);
			if ($rootScope.game.status == 'STARTED') {
				$location.path('/stringGame');
			}

		}

		$scope.securCheck();

		function statusChange(expression) {

			console.log('Status Changeeee');

			switch (expression) {
			case 'createGame':
				$scope.header = "Success!";
				$scope.message = "Successfully created game, waiting on an opponent.";
				$scope.create = false;
				$scope.join = false;
				isGameJoinedLooper($rootScope.game.player1);
				break;
			case 'getLobby':
				$scope.header = "Welcome to the lobby";
				$scope.message = "You may now join an available game.";
				$scope.join = false;
				$scope.create = false;
				$scope.availableGames = true;
				break;
			case 'joinGame':
				$scope.header = "Success!";
				$scope.message = "You have successfully joined the game.";
				$scope.availableGames = false;
				isGameStartedLooper($rootScope.game.player1);
				break;
			case 'setGameStarted':
				$scope.message = "Success!";
				$scope.message = "Now starting a new game.";
				$location.path('/stringGame');
				break;
			case 'isGameJoined':
				$scope.header = "Success!";
				$scope.message = "Someone has joined the game";
				$scope.startGame = true;
				break;
			case 'isGameStarted':
				$scope.header = "Start Playing";
				$scope.message = "You will be redirected to the game.";
				$scope.goToGame_button = true;
				break;

			default:
				// code block
			}

		}
	});
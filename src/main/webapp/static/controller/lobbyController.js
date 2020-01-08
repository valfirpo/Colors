app.controller('lobbyCtl', function($rootScope, $scope, $location, $http) {
	
	$scope.lobby;
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
	
	$scope.create = function() {

		$http({
			url : 'Lobby/Create.do',
			method : 'POST',
			params : {
				username : $rootScope.user.username
			}
		}).then(function success(response) {
			console.log('Success');
			$scope.lobby = response.data;
			$scope.message = 'Success! You may now select a game.';
			console.log($scope.lobby);
		}, function error(response) {
			console.log('Error creating game');
			$scope.message = 'Unable to select a game.';
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
			console.log('Success game found.');
			$scope.message = 'Success game found.';
			$rootScope.game = response.data;
			console.log($rootScope.game);
		}, function error(response) {
			console.log('Error checking match.');
			$scope.message = 'Error checking match.';
		});
		
	}
	
$scope.isGameStarted = function(username) {
		
	$scope.getGame(username);
	console.log('check status');

//		if($rootScope.game.status == 'MATCHED'){
//			$scope.message = 'Matched: ' + $rootScope.game.oponent;
//		} else {
//			$scope.message = 'Waiting on oponent.';
//		}
	}
	
	$scope.join = function(userToJoin) {
		
		$http({
			url: 'Lobby/Join.do',
			method: 'POST',
			params : {
				username : $rootScope.user.username,
				userToJoin : userToJoin
			}
		}).then(function success(response) {
			console.log('Successfully joined lobby');
			$scope.message = 'Successfully joined the lobby.';
		}, function error(response) {
			console.log('Error joining lobby');
			$scope.message = 'Error, unable to join lobby.';
		});
		
	}
	
	$scope.getLobby = function() {
		
		$http({
			url: 'Lobby/getLobby.do',
			method: 'GET'
		}).then(function success(response) {
			console.log('Successfully joined lobby');
			$scope.message = 'Successfully joined the lobby.';
			$scope.lobby = response.data;
		}, function error(response) {
			console.log('Error joining lobby');
			$scope.message = 'Error, unable to join lobby.';
		});
		
	}
	
	
	$scope.securCheck();
//	if(accessGranted)
//	{
//		$scope.game = $rootScope.gameSelected;
//		$scope.gameNewName = $rootScope.gameSelected.name;
//		$scope.gameNewPub = $rootScope.gameSelected.pub;
//		
//		if($scope.game.pub == 1)
//		{
//			$scope.game.pub = true;
//		}
//		else
//		{
//			$scope.game.pub = false;
//		}
//	}
});
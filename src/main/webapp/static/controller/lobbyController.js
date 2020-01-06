app.controller('lobbyCtl', function($rootScope, $scope, $location, $http) {
	
	var acessgranted;
	
	console.log('lobby controller');
	console.log($rootScope.user);
	console.log($rootScope.user.username);
	
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
	
	$scope.create = function() {

		$http({
			url : 'Lobby/Create.do',
			method : 'POST',
			params : {
				username : $rootScope.user.username
			}
		}).then(function success(response) {
			console.log('Success');
			$scope.message = 'Success! You may now create a new game.'
		}, function error(response) {
			console.log('Error creating game');
			$scope.message = 'Unable to create game.';
		});
		
	}
	
	$scope.join = function() {
		
		$http({
			url: 'Lobby/Join.do',
			method: 'GET'
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
		}, function error(response) {
			console.log('Error joining lobby');
			$scope.message = 'Error, unable to join lobby.';
		});
		
	}
	
//	$scope.securCheck();
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
app.controller('editGameCtl', function($rootScope, $scope, $location, $http) {
	
	$scope.game;
	$scope.gameNewName;
	$scope.gameNewPub;
	
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
	
	$scope.updateGame = function() {

		$http({
			url : 'Game/Update.do',
			method : 'GET',
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			},
			params : {
				newName : $scope.gameNewName,
				gameId: $rootScope.gameSelected.id,
				gamePlays: $rootScope.gameSelected.plays,
				userId : $rootScope.user.id,
				gamePub : ($scope.gameNewPub ? 1 : 0)
			}
		}).then(function success(response) {
			$scope.updateDisplay = true;
			$scope.message = "Game Updated";
			$location.path('/home');
		}, function error(response) {
			$scope.message = 'Error';
		});
	}
	
	$scope.securCheck();
	if(accessGranted)
	{
		$scope.game = $rootScope.gameSelected;
		$scope.gameNewName = $rootScope.gameSelected.name;
		$scope.gameNewPub = $rootScope.gameSelected.pub;
		
		if($scope.game.pub == 1)
		{
			$scope.game.pub = true;
		}
		else
		{
			$scope.game.pub = false;
		}
	}
});
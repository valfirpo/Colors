app.controller('colorCtrl', function($rootScope, $scope, $location, $http) {
	
	var acessgranted;
	var list = [];
	
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
	
	$scope.blue = function() {

		$http({
			url : 'Color/Blue.do',
			method : 'GET'
		}).then(function success(response) {
			console.log('Succ Blue');
			console.log(response);
			list = response.data;
			$scope.message = list[0];
		}, function error(response) {
			console.log('Error Blue');
			$scope.message = 'Error Blue';
		});
		
	}
	
	$scope.red = function() {
		
		$http({
			url: 'Color/Red.do',
			method: 'GET'
		}).then(function success(response) {
			console.log('Succ Red');
			console.log(response);
			list = response.data;
			$scope.message = list[0];
		}, function error(response) {
			console.log('Error Red');
			$scope.message = 'Error Red';
		});
		
	}
	
	$scope.yellow = function() {
		
		$http({
			url: 'Color/Yellow.do',
			method: 'GET'
		}).then(function success(response) {
			console.log('Succ Yellow');
			console.log(response);
			list = response.data;
			$scope.message = list[0];
		}, function error(response) {
			console.log('Error Yellow');
			$scope.message = 'Error Yellow';
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
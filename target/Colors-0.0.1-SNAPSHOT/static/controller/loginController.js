app.controller('loginCtl', function($rootScope, $scope, $location, $http) {
	$rootScope.user;
	$scope.message = false;

	$scope.logIn = function() {
		
		var user = {
			email : $scope.email,
			password : $scope.password
		};

		$http({
			url : 'User/Authenticate.do',
			method : 'POST',
			data : user,
			params : {
				email : user.email,
				password : user.password,
			}
		}).then(function success(response) {
			$rootScope.user = response.data;
			console.log($rootScope.user);
			$location.path('/lobby');
		}, function error(response) {
			$scope.message = 'Wrong credentials';
		});
	}
});
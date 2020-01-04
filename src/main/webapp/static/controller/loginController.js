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
			console.log('Success');
			$rootScope.user = response.data;
			$location.path('/colors');
		}, function error(response) {
			$scope.message = 'Wrong credentials';
		});
	}
});
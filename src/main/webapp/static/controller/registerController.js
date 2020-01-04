app.controller('registerCtl', function($rootScope, $scope, $location, $timeout,$http) {
	$scope.message = false;

	$scope.register = function() {
		
		if ($scope.password == $scope.confirm_password) {

			var user = {
				email : $scope.email,
				password : $scope.password
			};

			$http({
				url : 'User/Register.do',
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				},
				params : {
					email : user.email,
					password : user.password,
				}
			}).then(function success(response) {
				$scope.updateDisplay = true;
				$scope.message = "Account created";
				$timeout(function() {$location.path('/');}, 3000);
			}, function error(response) {
				$scope.message = 'Email is already in use';
			});

		} else {
			$scope.message = 'Passwords do not match';
		}

	}

});
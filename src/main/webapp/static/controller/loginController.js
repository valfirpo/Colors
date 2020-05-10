app.controller('loginCtl', function($rootScope, $scope, $location, $http) {
	$rootScope.user;
	$scope.message = false;
	
//	var emailInput = document.getElementById("user_email");
//	var passwordInput = document.getElementById("user_password");
//	
//	emailInput.addEventListener("keyup", function(event) {
//		if (event.keycode === 13) {
//			event.preventDefault();
//			document.getElementById("submit_button").click();
//		}
//	})
//	
//	passwordInput.addEventListener("keyup", function(event) {
//		if (event.keycode === 13) {
//			event.preventDefault();
//			document.getElementById("submit_button").click();
//		}
//	})

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
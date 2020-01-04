app.controller('editUserCtl', function($rootScope, $scope, $location, $http) 
{
	$scope.message = false;
	$scope.userNameNew;
	$scope.userPass1;
	$scope.userPass2;
	
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
	
	$scope.updateUser = function() {

		if($scope.userPass1 == $scope.userPass2)
		{
			$http({
				url : 'User/Update.do',
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				},
				params : {
					username : $scope.userNameNew,
					pass : $scope.userPass1,
					userId : $rootScope.user.id,
					email : $rootScope.user.email
				}
			}).then(function success(response) {
				$scope.updateDisplay = true;
				$scope.message = "Profile updated";
				$rootScope.user = response.data;
				$location.path('/home');
			}, function error(response) {
				$scope.message = 'Error';
			});
		}
		else
		{
			$scope.message = 'Password must match';
		}
	}
	
	$scope.securCheck();
	if(accessGranted)
	{
		$scope.userNameNew = $rootScope.user.username;
		$scope.userPass1 = $rootScope.user.password;
		$scope.userPass2 = $rootScope.user.password;
	}
});
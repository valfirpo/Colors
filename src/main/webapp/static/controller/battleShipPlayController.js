app.controller('battleShipPlayCtl',function($rootScope, $scope, $location, $http, BattleShipResource
		) {

	$scope.userName = $rootScope.user.username;
	$scope.battleShipObjects;
	$scope.off;
	$scope.def;
	$scope.boats;
	$scope.selectedBoat = null;
	$scope.overlap = false;


	
	function setUp(){
		console.log($rootScope.game);
		if($rootScope.game.player1Set.player == $rootScope.user.username){
			console.log("player1Set");
			$scope.off = $rootScope.game.player1Set.off;
			$scope.def = $rootScope.game.player1Set.def;
			$scope.boats = $rootScope.game.player1Set.boats;
		} else if($rootScope.game.player2 == $rootScope.user.username){
			console.log("player2Set");
			$scope.off = $rootScope.game.player2Set.off;
			$scope.def = $rootScope.game.player2Set.def;
			$scope.boats = $rootScope.game.player2Set.boats;
		}

	}
	
	setUp();

});

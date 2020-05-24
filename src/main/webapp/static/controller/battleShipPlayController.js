app.controller('battleShipPlayCtl',function($rootScope, $scope, $location, $http, BattleShipResource
		) {

	$scope.userName = $rootScope.user.username;
	$scope.battleShipObjects;
	$scope.off;
	$scope.def;
	$scope.boats;
	$scope.weapons;
	$scope.hitStreak;
	$scope.selectedBoat = null;
	$scope.overlap = false;
	$scope.myTurn = false;
	$scope.showUpDateGame = false;
	$scope.spotSelected = 'A,1';
	var inter;

	function setUp(){
		console.log($rootScope.game);
		if($rootScope.game.player1Set.player == $rootScope.user.username){
			console.log("player1Set");
			$scope.off = $rootScope.game.player1Set.off;
			$scope.def = $rootScope.game.player1Set.def;
			$scope.boats = $rootScope.game.player1Set.boats;
			$scope.weapons = $rootScope.game.player1Set.weapons;
			$scope.hitStreak = $rootScope.game.player1Set.hitStreak;
		} else if($rootScope.game.player2 == $rootScope.user.username){
			console.log("player2Set");
			$scope.off = $rootScope.game.player2Set.off;
			$scope.def = $rootScope.game.player2Set.def;
			$scope.boats = $rootScope.game.player2Set.boats;			
			$scope.weapons = $rootScope.game.player2Set.weapons;
			$scope.hitStreak = $rootScope.game.player2Set.hitStreak;

		}
		
		console.log('weapons!!');
		console.log($scope.weapons);

	}
	
	$scope.getCellStatusOn = function(cell) {
		var status = cell.status;
		for ( var key in $scope.boats) {
			//if ($scope.boats[key].status === 'set') {
				for ( var key2 in $scope.boats[key].cells) {
					if (cellCompare(cell,$scope.boats[key].cells[key2])){
							//status = $scope.boats[key].name;
							//status = 'boat';
						console.log();
							status = $scope.boats[key].cells[key2].status;
					}
				}
			//}
		}
		return status;
	}
	
	$scope.getCellStatusOff = function(cell) {
		var status = cell.status;
		return status;
	}
	
	function cellCompare(cell1, cell2) {
		var flag = false;
		if (cell1.coordinate.x == cell2.coordinate.x) {
			if (cell1.coordinate.y == cell2.coordinate.y) {
				flag = true;
			}
		}
		return flag;
	}
	
	$scope.isMyTurnFirst = function() {
		
		if($rootScope.user.username == $rootScope.game.turn){
			$scope.myTurn = true;
			unlock();
		} else {
			lock();
			$scope.myTurn = false;
			refreshGame($rootScope.game.player1);
		}

	}
	
	function refreshGame(username) {
		
		inter = setInterval(
				function() {
					$scope.getGame(username);
				}, 5000)
	}
	
$scope.getGame = function(username) {
		
		$http({
			url : 'Lobby/getGame.do',
			method : 'GET',
			params : {
				username : username
			}
		}).then(function success(response) {
			$rootScope.game = response.data;
			isMyTurn();
			setUp();
			
			if ($rootScope.game.status == 'OVER'){
				clearInterval(inter);
				$location.path('/gameOver');
			} else if ($scope.myTurn) {
				clearInterval(inter);
				unlock();
			} else {
				$scope.message = "Sorry, still waiting on an opponent.";
			}
		}, function error(response) {
			console.log('Error checking match.');
			$scope.message = 'Error checking match.';
		});

	}

$scope.setSelectedCell = function(cell) {
	console.log(cell.coordinate);
	$scope.spotSelected = cell.coordinate;
}

$scope.updateGame = function() {
	
	var spots = [$scope.spotSelected, $scope.spotSelected];
	
	$http({
		url : 'BattleShip/updateGame.do',
		method : 'POST',
		params : {
			username: $rootScope.game.player1,
			turn: $rootScope.user.username,
			spot: spots
		}
	}).then(function success(response) {
		$scope.message = 'Successfully joined the lobby.';
		$rootScope.game = response.data;
		console.log($rootScope.game);
		$scope.isMyTurnFirst();

	}, function error(response) {
		console.log('Error updating game');
		$scope.message = 'Error updating game';
	});
}

	function isMyTurn() {
	
		if($rootScope.user.username == $rootScope.game.turn){
			$scope.myTurn = true;
		} else {
			$scope.myTurn = false;
		}

	}
	
	function lock(){
		//show waiting
		console.log('lock');
		setUp();
		$scope.showUpDateGame = false;
	}

	function unlock(){
		//display board
		console.log('unlock');
		setUp();
		$scope.showUpDateGame = true;
	}
	
	setUp();
	$scope.isMyTurnFirst();
});


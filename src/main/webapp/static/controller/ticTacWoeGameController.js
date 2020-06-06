app.controller('ticTacWoeGameCtl', function($rootScope, $scope, $location, $http) {

	$scope.showUpDateGame = false;
	$scope.myTurn = false;
	$scope.spotSelected = null;
	$scope.isSelected = false;
	var inter;
	
	
	
	$scope.setSpotSelected = function(index) {
		
		if($scope.myTurn){
			$scope.spotSelected = index;
			$scope.isSelected = true;
			console.log("SPOT SELECTED", $scope.spotSelected);
		} else {
			console.log("not your turn");
		}
		
		
	}
	
	$scope.securCheck = function() {
		if ($rootScope.user == undefined) {
			console.log("denied");
			accessGranted = false;
			$location.path('/');
		} else {
			console.log("granted");
			accessGranted = true;
		}
	}

//	{
//		player1: "pemp",
//		player2: "temp",
//		turn: null,
//		status: "JOINED",
//		type: "Tic_Tac_Woe",
//		board: ["0", "1", "2", "3", "4", "5", "6", "7", "8"],
//		winner: null,
//		gameOver: false,
//	}
	

	
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
			boardValues();
			
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
	
	function isMyTurn() {
		
		if($rootScope.user.username == $rootScope.game.turn){
			$scope.myTurn = true;
		} else {
			$scope.myTurn = false;
		}

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

	$scope.updateGame = function() {
		
		$http({
			url : 'Game/TicTacWoe/updateGame.do',
			method : 'POST',
			params : {
				username: $rootScope.game.player1,
				turn: $rootScope.user.username,
				spot: $scope.spotSelected
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
	
	function lock(){
		//show waiting
		console.log('lock');
		boardValues();
		$scope.showUpDateGame = false;
	}
	
	function unlock(){
		//display board
		console.log('unlock');
		boardValues();
		$scope.showUpDateGame = true;
	}
	
	function boardValues(){
		$scope.isSelected = false;
		
		var spot;
		for(i = 0; i < 9; i++){
			if($scope.game.board[i] == $scope.game.player1){
				$scope.game.board[i] = 'X';
			} else if($scope.game.board[i] == $scope.game.player2){
				$scope.game.board[i] = 'O';
			}
		}
	}

	$scope.securCheck();
	$scope.isMyTurnFirst();

});
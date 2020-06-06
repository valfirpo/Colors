app.controller('battleShipPlayCtl', function($rootScope, $scope, $location,
		$http, BattleShipResource) {

	$scope.userName;
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
	$scope.spotSelected = '';
	$scope.weaponSelected = 'Cannon';
	var inter;

	$scope.securCheck = function() {
		if ($rootScope.user == undefined) {
			$location.path('/');
		}
	}

	function setUp() {

		console.log($rootScope.game);

		$scope.userName = $rootScope.user.username;

		if ($rootScope.game.player1Set.player == $rootScope.user.username) {
			console.log("player1Set");
			$scope.off = $rootScope.game.player1Set.off;
			$scope.def = $rootScope.game.player1Set.def;
			$scope.boats = $rootScope.game.player1Set.boats;
			$scope.weapons = $rootScope.game.player1Set.weapons;
			$scope.hitStreak = $rootScope.game.player1Set.hitStreak;

		} else if ($rootScope.game.player2 == $rootScope.user.username) {
			console.log("player2Set");
			$scope.off = $rootScope.game.player2Set.off;
			$scope.def = $rootScope.game.player2Set.def;
			$scope.boats = $rootScope.game.player2Set.boats;
			$scope.weapons = $rootScope.game.player2Set.weapons;
			$scope.hitStreak = $rootScope.game.player2Set.hitStreak;
		}

		console.log($scope.weapons);

	}

	$scope.isCellselected = function(cell) {
		let border = '1px solid black'
		if (cellSelected(cell)) {
			border = '1px solid powderblue'
		}
		return border;
	}

	$scope.weaponBorder = function(weapon) {

		let border = '1px solid black';
		if (!enoughAmo(weapon)) {
			border = '1px solid red';
		} else if (weapon.type == $scope.weaponSelected) {
			border = '1px solid powderblue';
		}
		return border;
	}

	function enoughAmo(weapon) {
		if (weapon.count > 0) {
			return true;
		}
		return false;
	}

	function cellSelected(cell) {

		for ( var key in $scope.spotSelected) {

			if (cellCompare(cell, $scope.spotSelected[key])) {
				return true;
			}
		}

		return false;
	}

	$scope.weaponSelect = function(weapon) {
		if (enoughAmo(weapon)) {
			$scope.weaponSelected = weapon.type;
		}
	}

	$scope.getCellStatusOn = function(cell) {
		var status = cell.status;
		for ( var key in $scope.boats) {
			for ( var key2 in $scope.boats[key].cells) {
				if (cellCompare(cell, $scope.boats[key].cells[key2])) {
					status = $scope.boats[key].cells[key2].status;
				}
			}
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

		if ($rootScope.user.username == $rootScope.game.turn) {
			$scope.myTurn = true;
			unlock();
		} else {
			lock();
			$scope.spotSelected = '';
			$scope.weaponSelected = 'Cannon';
			$scope.myTurn = false;
			refreshGame($rootScope.game.player1);
		}

	}

	function refreshGame(username) {

		inter = setInterval(function() {
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

			if ($rootScope.game.status == 'OVER') {
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
		console.log(cell);
		console.log($scope.weaponSelected);
		if (cell.status == 'open') {
			$scope.spotSelected = [];

			if ($scope.weaponSelected == 'Cannon') {
				$scope.spotSelected = [ cell ];
			} else if ($scope.weaponSelected == 'Air_Strike') {

			} else if ($scope.weaponSelected == 'Lazer') {
				$scope.spotSelected = [ cell ];
			} else if ($scope.weaponSelected == 'Torpedo') {

				let cell1 = {
					status : cell.status,
					coordinate : {
						x : cell.coordinate.x,
						y : cell.coordinate.y
					}
				};

				cell1.coordinate.x += 1;

				let cell2 = {
					status : cell.status,
					coordinate : {
						x : cell.coordinate.x,
						y : cell.coordinate.y
					}
				};

				cell.coordinate.x -= 1;

				$scope.spotSelected = [ cell, cell1, cell2 ];
			}

			console.log($scope.spotSelected);
		}

	}

	function nextChar(c) {
		return String.fromCharCode(c.charCodeAt(0) + 1);
	}

	function charToNum(c) {
		return c.charCodeAt(0) - 65;
	}

	function prevChar(c) {
		return String.fromCharCode(c.charCodeAt(0) - 1);
	}

	$scope.updateGame = function() {

		if ($scope.spotSelected == '') {
			console.log('Select Spot');
			return;
		}

		console.log($scope.spotSelected);
		var coordinates = [];
		for ( var key in $scope.spotSelected) {
			console.log($scope.spotSelected[key].coordinate);
			coordinates[key] = $scope.spotSelected[key].coordinate;
		}
		console.log(coordinates);

		$http({
			url : 'BattleShip/updateGame.do',
			method : 'POST',
			params : {
				username : $rootScope.game.player1,
				turn : $rootScope.user.username,
				weapon : $scope.weaponSelected,
				spot : coordinates
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

		if ($rootScope.user.username == $rootScope.game.turn) {
			$scope.myTurn = true;
		} else {
			$scope.myTurn = false;
		}

	}

	function lock() {
		//show waiting
		console.log('lock');
		setUp();
		$scope.showUpDateGame = false;
	}

	function unlock() {
		//display board
		console.log('unlock');
		setUp();
		$scope.showUpDateGame = true;
	}

	$scope.securCheck();
	setUp();
	$scope.isMyTurnFirst();
});

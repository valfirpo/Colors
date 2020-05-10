app.controller('battleShipSetUpCtl',function($rootScope, $scope, $location, $http, BattleShipResource
		) {

	$scope.userName = $rootScope.user.username;
	$scope.battleShipObjects;
	$scope.off;
	$scope.def;
	$scope.boats;
	$scope.selectedBoat = null;
	$scope.overlap = false;
	var inter;

	//var battleShipObjectsRec = BattleShipResource.query();
	
//	battleShipObjectsRec.$promise.then(function success() {
//		$scope.battleShipObjects = battleShipObjectsRec[0];
//		$scope.off = $scope.battleShipObjects.off;
//		$scope.def = $scope.battleShipObjects.def;
//		$scope.boats = $scope.battleShipObjects.boats;
//		console.log($scope.battleShipObjects);
//	});
	
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

	$scope.selectBoat = function(boat) {
		if(boat.status != 'set'){
			boat.status = 'set';
		}
		$scope.selectedBoat = boat;
	}
	
	$scope.boatIsSelected = function(boat) {
		var flag = false;
		if ($scope.selectedBoat != null) {
			if (boat.name == $scope.selectedBoat.name) {
				flag = true;
			}
		}
		return flag;
	}

	function allBoatsSet() {
		var flag = false;
		for ( var key in $scope.boats) {
			if ($scope.boats[key].status == 'notSet') {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	function lockShips() {
		console.log('lock ships');
	}
	
	function unLockShips() {
		console.log('unlock ships');
	}

	$scope.isReady = function() {
		var flag = true;
		if (!allBoatsSet()){
			if (!boatOverlap()){
				flag = false;
			}
		}
		return flag;
	}
	
	$scope.setPlayerReady = function (){
		
		//Add code to lock boats before HTTP call 
		//lockBoats($scope.boats, $scope.def);
		
		lockShips();
		
		$http({
			url : 'BattleShip/setPlayerReady.do',
			method : 'POST',
			params : {
				owner : $rootScope.game.player1,
				username : $scope.userName,
			}
		}).then(function success(response) {
			$rootScope.game = response.data;
			console.log($rootScope.game);
			refreshGame($rootScope.game.player1);
		}, function error(response) {
			$scope.message = 'Error set Player Ready';
			unLockShips();
		});
		
//		$http({
//			url : 'BattleShip/setPlayerReady2.do',
//			method : 'POST',
//			params : {
//				owner : $rootScope.game.player1,
//				username : $scope.userName,
//				Bomber : $scope.boats[0],
//				Carrier : $scope.boats[1],
//				Cruiser : $scope.boats[2],
//				Destroyer : $scope.boats[3],
//				Submarine : $scope.boats[4],
//			}
//		}).then(function success(response) {
//			$rootScope.game = response.data;
//			console.log($rootScope.game);
//			$location.path('/battleShipPlay');
//		}, function error(response) {
//			$scope.message = 'Error set Player Ready';
//		});
	}
	
	function boatOverlap() {
		var flag = false;
		var boatCoordinates = [];
		
		for ( var key in $scope.boats) {
			for ( var key2 in $scope.boats[key].cells) {
				if(containsCell(boatCoordinates,$scope.boats[key].cells[key2])){
					flag = true;
					return flag;
				}
				else{
					boatCoordinates.push($scope.boats[key].cells[key2]);
				}
			}
		}
		
		return flag;
	}
	
	$scope.getCellStatus = function(cell) {
		var status = cell.status;
		for ( var key in $scope.boats) {
			if ($scope.boats[key].status === 'set') {
				for ( var key2 in $scope.boats[key].cells) {
					if (cellCompare(cell,$scope.boats[key].cells[key2])){
							status = $scope.boats[key].name;
							status = 'boat';
					}
				}
			}
		}
		if(checkOverlap(cell, $scope.boats)){
			status = 'conflict'
		}
		return status;
	}

	$scope.moveBoatUp = function(boat) {
		moveBoatUp(boat);
	}

	$scope.moveBoatDown = function(boat) {
		moveBoatDown(boat);
	}

	$scope.moveBoatLeft = function(boat) {
		moveBoatLeft(boat);
	}

	$scope.moveBoatRight = function(boat) {
		moveBoatRight(boat);
	}
	
	$scope.flip = function(boat) {
		flip(boat);
		adjustBoat(boat);
	}
	
	$scope.selectCell = function(cell) {
		if($scope.selectedBoat){
			$scope.selectedBoat.cells[0] = JSON.parse(JSON.stringify(cell));
			flip($scope.selectedBoat);
			flip($scope.selectedBoat);
			adjustBoat($scope.selectedBoat);
		}
		
	}
	
	$scope.parseChar = function(int){
		console.log('pc');
		//return parseChar(int);
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
			console.log('after get gaame update ');
			console.log($rootScope.game);

			
			if ($rootScope.game.status == 'STARTED2'){
				clearInterval(inter);
				$location.path('/gameOver');
			} else {
				console.log($rootScope.game.status);
				$scope.message = "Sorry, still waiting on an opponent.";
			}
		}, function error(response) {
			console.log('Error checking match.');
			$scope.message = 'Error checking match.';
		});

	}
});

function adjustBoat(boat){
	//Horizontal
	if(boat.horizontal){		
		while(boat.cells[boat.length - 1].coordinate.x > 9){
			moveBoatLeft(boat);
		}
	}
	
	//Vertical
	else{
		while(boat.cells[boat.length - 1].coordinate.y > 'J'){
			moveBoatUp(boat);
		}
	}
}

function flip(boat) {
	// boat.cells[key].coordinate.x
	if (!boat.horizontal) {
		// Vertical
		var point = JSON.parse(JSON
				.stringify(boat.cells[0].coordinate));
		for ( var key in boat.cells) {
			boat.cells[key].coordinate.x = point.x;
			boat.cells[key].coordinate.y = point.y;
			point.x++;
			// point.y = nextChar(point.y);
		}
		boat.horizontal = true;
	} else {
		// Horizontal
		var point = JSON.parse(JSON
				.stringify(boat.cells[0].coordinate));
		for ( var key in boat.cells) {
			boat.cells[key].coordinate.x = point.x;
			boat.cells[key].coordinate.y = point.y;
			point.y = nextChar(point.y);
		}
		boat.horizontal = false;
	}
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

function nextChar(c) {
	return String.fromCharCode(c.charCodeAt(0) + 1);
}

function charToNum(c){
	return c.charCodeAt(0) - 65;
}

function prevChar(c) {
	return String.fromCharCode(c.charCodeAt(0) - 1);
}

//function getChar(int){
//	return parseChar(int);
//}

function atLeftLimit(point) {
	var flag = false;
	if (point.x == 0) {
		flag = true;
	}
	return flag;
}

function atRightLimit(point) {
	var flag = false;
	if (point.x >= 9) {
		flag = true;
	}
	return flag;
}

function atTopLimit(point) {
	var flag = false;
	if (point.y <= 'A') {
		flag = true;
	}
	return flag;
}

function atBottomLimit(point) {
	var flag = false;
	if (point.y >= 'J') {
		flag = true;
	}
	return flag;
}

function checkOverlap(cell, boats){
	var foundOnce = false;
	var foundTwice = false;
	for(var key1 in boats){
		if(boats[key1].status == 'set'){
			for(var key2 in boats[key1].cells){
				if(cellCompare(cell, boats[key1].cells[key2]) && foundOnce){
					foundTwice = true;
				}
				if(cellCompare(cell, boats[key1].cells[key2]) && !foundOnce){
					foundOnce = true;
				}
			}
		}
	}
	return foundTwice;
}

function containsCell(cellArray, cell){
	var flag = false;
	
	for (var key in cellArray) {
		if(cellCompare(cellArray[key],cell)){
			flag = true;
			return flag;
		}
	}
	return flag;
}

function moveBoatLeft(boat) {
	if (!atLeftLimit(boat.cells[0].coordinate)) {
		for ( var key in boat.cells) {
			boat.cells[key].coordinate.x -= 1;
		}
	}
}

function moveBoatUp(boat) {
	if (!atTopLimit(boat.cells[0].coordinate)) {
		for ( var key in boat.cells) {
			boat.cells[key].coordinate.y = prevChar(boat.cells[key].coordinate.y);
		}
	}
}

function moveBoatDown(boat) {
	if (!atBottomLimit(boat.cells[boat.length - 1].coordinate)) {
		for ( var key in boat.cells) {
			boat.cells[key].coordinate.y = nextChar(boat.cells[key].coordinate.y);
		}
	}
}

function moveBoatRight(boat) {
	if (!atRightLimit(boat.cells[boat.length - 1].coordinate)) {
		for ( var key in boat.cells) {
			boat.cells[key].coordinate.x += 1;
		}
	}
}

function lockBoats(boats, board){
	console.log(boats);
	console.log(board);
	
	for ( var key in boats) {
		for ( var key2 in boats[key].cells) {
			var cell = board.cells[charToNum(boats[key].cells[key2].coordinate.y)][boats[key].cells[key2].coordinate.x];
			cell.status = 'boat';
		}
	}
	return status;
}
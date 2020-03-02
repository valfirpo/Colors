var app = angular.module('yourules', ['ngRoute']);
app.config(function($routeProvider, $locationProvider){
	$locationProvider.html5Mode(false).hashPrefix('');
	$routeProvider
	.when("/",{
		templateUrl: "static/pages/login.html",
		controller: 'loginCtl'
	})
	.when("/home",{
		templateUrl: "static/pages/home.html",
		controller: 'homeCtl'
	})
	.when("/viewGame",{
		templateUrl: "static/pages/viewGame.html",
		controller: 'viewGameCtl'
	})
	.when("/register",{
		templateUrl: "static/pages/colors.html",
		controller: 'registerCtl'
	})
	.when("/newGame",{
		templateUrl: "static/pages/newGame.html",
		controller: 'newGameCtl'
	})
	.when("/newRule",{
		templateUrl: "static/pages/newRule.html",
		controller: 'newRuleCtl'
	})
	.when("/playGame",{
		templateUrl: "static/pages/playGame.html",
		controller: 'playGameCtl'
	})
	.when("/editGame",{
		templateUrl: "static/pages/editGame.html",
		controller: 'editGameCtl'
	})
	.when("/editRule",{
		templateUrl: "static/pages/editRule.html",
		controller: 'editRuleCtl'
	})
	.when("/editUser",{
		templateUrl: "static/pages/editUser.html",
		controller: 'editUserCtl'
	})
	.when("/publicGame",{
		templateUrl: "static/pages/publicGame.html",
		controller: 'publicGameCtl'
	})
	.when("/colors",{
		templateUrl: "static/pages/colors.html",
		controller: 'colorCtrl'
	})
	.when("/lobby",{
		templateUrl: "static/pages/lobby.html",
		controller: 'lobbyCtl'
	})
	.when("/stringGame",{
		templateUrl: "static/pages/stringGame.html",
		controller: 'stringGameCtl'
	})
	.when("/stringGameOver",{
		templateUrl: "static/pages/stringGameOver.html",
		controller: 'stringGameOverCtl'
	})
	.when("/ticTacWoeGame",{
		templateUrl: "static/pages/ticTacWoe.html",
		controller: 'ticTacWoeGameCtl'
	})
	.otherwise({redirectTo: '/'});
});
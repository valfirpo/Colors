app.factory('BattleShipResource', function($resource) {
	
	url =  'BattleShip.do';

	return $resource(url,{game:"@game"});
});
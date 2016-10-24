var app = angular.module('jasperFiap', ["ngRoute"]);


app.controller('mainController', [ '$scope', function($scope) {
	$scope.header = {
		name : 'header',
		url : 'views/templates/header.html'
	}
	
	$scope.baseURL = 'https://localhost:9000/jasperfiap'

}]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "views/templates/home.html"
    })
    .when("/clientes", {
        templateUrl : "views/clientes.html"
    })
    .when("/boletos", {
        templateUrl : "views/boletos.html"
    })
    .when("/notas", {
        templateUrl : "views/notas.html"
    });
});
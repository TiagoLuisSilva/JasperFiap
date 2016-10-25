var app = angular.module('jasperFiap', ["ngRoute"]);


app.controller('mainController', function($scope) {
	$scope.header = {
		name : 'header',
		url : 'views/templates/header.html'
	}
	
	$scope.erro = "";
	
});


app.controller('clienteController', function($scope, $http){
	$http({
        method : "GET",
        url : "cliente/consultar"
    }).then(function mySucces(response) {
        $scope.clientes = response.data;
    }, function myError(response) {
        $scope.erro = response.statusText;
    });
});

app.controller('boletoController', function($scope, $http){
	$http({
        method : "GET",
        url : "boleto/consultar"
    }).then(function mySucces(response) {
        $scope.boletos = response.data;
    }, function myError(response) {
        $scope.erro = response.statusText;
    });
});

app.controller('notaController', function($scope, $http){
	$http({
        method : "GET",
        url : "nota/consultar"
    }).then(function mySucces(response) {
        $scope.notas = response.data;
    }, function myError(response) {
        $scope.erro = response.statusText;
    });
});


app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "views/templates/home.html"
    })
    .when("/clientes", {
        templateUrl : "views/clientes.html",
        controller : "clienteController"
    })
    .when("/boletos", {
        templateUrl : "views/boletos.html",
        controller : "boletoController"
    })
    .when("/notas", {
        templateUrl : "views/notas.html",
        controller : "notaController"
    });
    
});
var app = angular.module('jasperFiap', [ "ngRoute" ]);

app.controller('mainController', function($scope) {
	$scope.header = {
		name : 'header',
		url : 'views/templates/header.html'
	}

	$scope.erro = "";
	$scope.clientes = [];
	$scope.boletos = [];
});

app.controller('clienteController', function($scope, $http, downloadService) {
	$http({
		method : "GET",
		url : "cliente/consultar"
	}).then(function mySucces(response) {
		$scope.clientes = response.data;
	}, function myError(response) {
		$scope.erro = response.statusText;
	});

	$scope.imprimeBoleto = false;

	$scope.impGeral = function() {
		return $http({
			method : "GET",
			url : "cliente/gerarRelatorio",
			responseType : 'arraybuffer'
		}).then(function mySucces(response) {
			console.log("Imprimindo Relatório Geral");
			downloadService.download("Relatório Geral", response);
		}, function myError(response) {
			console.log("Erro ao imprimir relatório geral");
			$scope.erro = "Erro ao imprimir: Status - " + response.status + "Msg - " + response.statusText;
			alert($scope.erro);
		});
	}

	$scope.impCliente = function(cliente) {
		return $http({
			method : "POST",
			url : "notafiscal/gerarRelatorio",
			responseType : 'arraybuffer',
			data : {
				"idCliente" : cliente,
				"geraBoletos" : $scope.imprimeBoleto
			}
		}).then(function mySucces(response) {
			console.log("Imprimindo Relatório Notas Cliente " + cliente);
			downloadService.download("Relatório Notas Clientes", response);
		}, function myError(response) {
			console.log("Erro ao imprimir relatório geral");
			$scope.erro = "Erro ao imprimir: Status - " + response.status + "Msg - " + response.statusText;
			alert($scope.erro);
		});
	}

});

app.controller('boletoController', function($scope, $http, downloadService) {
	$http({
		method : "GET",
		url : "boleto/consultar"
	}).then(function mySucces(response) {
		$scope.boletos = response.data;
	}, function myError(response) {
		$scope.erro = "Erro ao imprimir: Status - " + response.status + "Msg - " + response.statusText;
	});
	
	$scope.impBoletoId = function(id) {
		console.log("Boleto " + id);
		return $http({
			method : "POST",
			url : "boleto/gerarRelatorio",
			data : {
				"idBoleto" : id
			},
			responseType : 'arraybuffer'
		}).then(function mySucces(response) {
			console.log("Imprimindo Relatório Boleto");
			downloadService.download("Relatório Boleto " + id, response);
		}, function myError(response) {
			console.log("Erro ao imprimir relatório Boleto");
			$scope.erro = "Erro ao imprimir: Status - " + response.status + "Msg - " + response.statusText;
			alert($scope.erro);
		});
	}

});

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "views/templates/home.html"
	}).when("/clientes", {
		templateUrl : "views/clientes.html",
		controller : "clienteController"
	}).when("/boletos", {
		templateUrl : "views/boletos.html",
		controller : "boletoController"
	});
});

app.service("downloadService", function() {

	this.download = function(nomeArq, response) {
		var fileName = nomeArq + ".pdf";
		var a = document.createElement("a");
		var file = new Blob([ response.data ], {
			type : 'application/pdf'
		});
		var fileURL = URL.createObjectURL(file);
		a.href = fileURL;
		a.download = fileName;
		a.click();
	}
})

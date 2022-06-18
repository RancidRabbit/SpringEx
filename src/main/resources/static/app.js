angular.module('market-front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8050/app/';


    $scope.i = 1;
   
    $scope.loadProducts = function (pageIndex) {
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
        });
    }

    $scope.deleteProduct = function (productID) {
        $http.get(contextPath + 'products/delete/' + productID);
        $scope.loadProducts();
    }

    $scope.next = function(pageIndex) {
        $scope.loadProducts(pageIndex + 1);
        $scope.i = $scope.i + 1;
    }

    $scope.previous = function(pageIndex) {
        $scope.loadProducts(pageIndex - 1);
        $scope.i = $scope.i - 1;
    }

    $scope.loadProducts(1);

});
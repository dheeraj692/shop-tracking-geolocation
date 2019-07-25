var app = angular.module('app', []);
app.controller('postcontroller', function($scope, $http, $location) {
	var config = {
            headers : {
                'Content-Type': 'application/json;charset=utf-8;'
            }
    }
	$scope.submitForm = function(){
		$scope.postNearestDataResult = undefined;
		var url = $location.absUrl() + "shop";
		
		var data = {
            shopAddress: $scope.shopAddress,
            shopName: $scope.shopName,
            shopPostalcode: $scope.shopPostalcode
        };
		
		
		$http.post(url, data, config).then(function (response) {
			$scope.existingShop = response.data.data;
			$scope.postResultMsg = {
					status: response.data.status,
					msg: response.data.msg
			};
		}, function (response) {
			$scope.postResultMsg = {
					status: "error",
					msg: "Request Failed !!!"
			};
		});
		
		$scope.postResultMsg = {
				suc: "",
				msg: ""
		};
	}
		
		$scope.getNearestShop = function() {
			$scope.postResultMsg = undefined;
			navigator.geolocation.getCurrentPosition(showPosition);
			function showPosition(position) {
				$scope.nearestdata = {
						latitude: position.coords.latitude,
						longitude: position.coords.longitude
			        };
				var url = $location.absUrl() + "nearestShop";
				
				$http.post(url, $scope.nearestdata, config).then(function (response) {
					$scope.nearestShopData = response.data.data;
					$scope.postNearestDataResult = {
							status: response.data.status,
							msg: response.data.msg
					};
				}, function (response) {
					$scope.nearestShopData = undefined;
					$scope.postNearestDataResult = {
							status: "error",
							msg: "Request Failed !!!"
					};
				});
			}
			
			
			
		}

});
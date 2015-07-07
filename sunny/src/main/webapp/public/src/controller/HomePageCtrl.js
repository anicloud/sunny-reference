/**
 * Created by zhaoyu on 15-6-10.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.HomePageCtrl = function ($scope, $rootScope, $http) {
    $scope.isstart = true;
    $scope.name = "";
    $scope.style = "error";
    $rootScope.config = {isstart: true, isopen: true};
    $http.get("public/json/device.json")
        .success(function (data, status, headers, config) {
            $scope.devices = data;
        });
    $http.get("public/json/strategy.json")
        .success(function (data, status, headers, config) {
            $scope.strategies = data;
        });
    $scope.setLength = function(stage, len) {
        return {width: (stage/len*100) + '%'};
    };
    $scope.setBadgeColor = function(fNum, stage) {
        return {'background-color' : fNum<=stage ? '#33aa33':'default'};
    };
}
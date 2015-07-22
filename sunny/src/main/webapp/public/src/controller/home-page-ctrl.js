/**
 * Created by zhaoyu on 15-6-10.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};


anicloud.sunny.controller.HomePageCtrl = function ($scope, $rootScope, $http, $cookies, StrategyService, DeviceService) {
    if (!$rootScope.strategies) {
        $rootScope.strategies = [];
        $rootScope.devices = [];
        $rootScope.features = [];
        $rootScope.triggers = [];

        StrategyService.getStrategies(function (data) {
            $rootScope.strategies = data;
        });

        DeviceService.getDevices(function (data) {
            $rootScope.devices = data;
        });

        DeviceService.getDeviceFeatures(function(data){
            for (var i=0; i<data.length; i++) {
                var key = data[i].deviceFormDto.id;
                var value = data[i].deviceFeatureFormDtoList;
                $rootScope.features[key] = value;
            }
        });

        DeviceService.getFeatureTrigger(function (data) {
            $rootScope.triggers = data;
        });
    }

//    For Dashboard page
    $scope.setLength = function (stage, len) {
        return {width: (stage / len * 100) + '%'};
    };
    $scope.setBadgeColor = function (fNum, stage) {
        return {'background-color': fNum < stage ? '#33aa33' : 'default'};
    };

    $scope.deviceModal = {
        "deviceId": "",
        "name": "",
        "deviceType": "",
        "deviceState": "",
        "deviceGroup": ""
    }

    $scope.isToggled = true;
    $scope.curDevice = null;
    $scope.toggleDeviceDetail = function (device) {
        if ($scope.curDevice == null) {
            $scope.isToggled = !$scope.isToggled;
        }
        else if ($scope.curDevice.id == device.id) {
            $scope.isToggled = !$scope.isToggled;
        }else {
            $scope.isToggled = false;
        }
        $scope.curDevice = device;

    }
}


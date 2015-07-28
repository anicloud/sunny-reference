/**
 * Created by zhaoyu on 15-6-10.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};


anicloud.sunny.controller.HomePageCtrl = function ($scope, $rootScope, $cookies, WebSocketService, StrategyService, DeviceService) {
//    For Dashboard page
    $scope.setLength = function (stage, len) {
        return {width: (stage / len * 100) + '%'};
    };
    $scope.setBadgeColor = function (fNum, stage) {
        return {'background-color': fNum < stage ? '#33aa33' : 'default'};
    };

    $scope.deviceTemplate = {
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


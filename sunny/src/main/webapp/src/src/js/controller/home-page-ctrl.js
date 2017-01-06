/**
 * Created by zhaoyu on 15-6-10.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};


anicloud.sunny.controller.HomePageCtrl = function ($scope, $rootScope, $cookies, WebSocketService, StrategyService, DeviceService) {
    $rootScope.routeIndex=0;
//    For Dashboard page

    //
    //$scope.deviceTemplate = {
    //    "deviceId": "",
    //    "name": "",
    //    "deviceType": "",
    //    "deviceState": "",
    //    "deviceGroup": ""
    //}
    //
    //$scope.isToggled = true;
    //$scope.curDevice = null;
    //$scope.toggleDeviceDetail = function (device) {
    //    if ($scope.curDevice == null) {
    //        $scope.isToggled = !$scope.isToggled;
    //    }
    //    else if ($scope.curDevice.id == device.id) {
    //        $scope.isToggled = !$scope.isToggled;
    //    }else {
    //        $scope.isToggled = false;
    //    }
    //    $scope.curDevice = device;
    //
    //}
    //$scope.toggleStatus = [];
    //
    //$scope.toggleOpen = function(index) {
    //    $scope.toggleStatus[index] = !$scope.toggleStatus[index];
    //}

    //$scope.deviceDetail.toggle = function (device) {
    //    if ($scope.deviceDetail.device == null) {
    //        $scope.deviceDetail.isToggled = !$scope.deviceDetail.isToggled;
    //    }
    //    else if ($scope.deviceDetail.device.id == device.id) {
    //        $scope.deviceDetail.isToggled = !$scope.deviceDetail.isToggled;
    //    }else {
    //        $scope.deviceDetail.isToggled = false;
    //    }
    //    $scope.deviceDetail.device = device;
    //    $scope.deviceDetail.featureChosen = null;
    //}
    $scope.showIndex=-1;
    $scope.getAbsTime=function (time) {
        return moment(time).format('MM-DD HH:mm');
    };
    $scope.getRelTime=function(time){
        return moment(time).from(moment(1));
    }
    $scope.classLamp='icon-appliances-lamp';
};


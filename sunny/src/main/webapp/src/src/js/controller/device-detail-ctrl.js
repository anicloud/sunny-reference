/**
 * Created by zhangdongming on 16-11-16.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.DeviceDetailCtrl = function ($rootScope, $scope, ManagerService, Notify,DeviceService){
    console.log($scope.deviceDetail);
    if(!$scope.deviceDetail.device&&$rootScope.devices.length>0) $scope.deviceDetail.device=$rootScope.devices[0];
    $scope.featureList=$scope.features[$scope.deviceDetail.device.id];
    $scope.powerOnObj=$rootScope.queryObjectByPropertyValue($scope.featureList,'featureName','Power On');
    $scope.powerOffObj=$rootScope.queryObjectByPropertyValue($scope.featureList,'featureName','Power Off');
    $scope.showLightSwitch=$scope.powerOnObj&&$scope.powerOffObj?true:false;
    if($scope.powerOnObj&&$scope.powerOffObj){
        $scope.switchFeatures=[$scope.powerOnObj[1],$scope.powerOffObj[1]];
    }
    $scope.readOnlyFeature=$scope.featureList.filter(function(feature,index){
        return feature.privilegeType==='READ';
    });
    console.log($scope.readOnlyFeature);

};
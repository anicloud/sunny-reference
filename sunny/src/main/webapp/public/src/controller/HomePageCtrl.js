/**
 * Created by zhaoyu on 15-6-10.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.HomePageCtrl = function ($scope, $rootScope, $http, $cookies,MyService,StrategyService,DeviceService) {
    $scope.isstart = true;
    $scope.name = "";
    $scope.style = "error";
    $rootScope.config = {isstart: true, isopen: true};

    $scope.strategies = [];
    $scope.devices = [];
    $scope.features = [];
    $scope.triggers = [];


//read data
    StrategyService.getStrategies(function(data){
        $scope.strategies = data;
    });

    DeviceService.getDevices(function(data){
        $scope.devices = data;
    });

   /* $scope.getDeviceFeatures=function(deviceId){
        DeviceService.getDeviceFeatures(function(data){
            $scope.features = data;
        },deviceId);
    };*/

    DeviceService.getDeviceFeatures(function(data){
        $scope.features = data;
    });

    DeviceService.getFeatureTrigger(function(data){
        $scope.triggers = data;
    });

//    For Dashboard page
    $scope.setLength = function (stage, len) {
        return {width: (stage / len * 100) + '%'};
    };
    $scope.setBadgeColor = function (fNum, stage) {
        return {'background-color': fNum < stage ? '#33aa33' : 'default'};
    };


//    For Strategy page

    $scope.strategyModal = {
        "strategyId": "",
        "strategyName": "",
        "strategyState": "",
        "strategyDescription": "",
        "strategyStage": "",
        "featureList": []
    };

    $scope.featureModal = {
        "featureId": "",
        "deviceId": "",
        "trigger": {
            "triggerValue": "",
            "triggerType": ""
        },
        "function":{
            "functionValue":"",
            "functionArg":""
        }
    };

    $scope.deviceModal = {
        "deviceId": "",
        "name":"",
        "deviceType":"",
        "deviceState":"",
        "deviceGroup":""
    }


    $scope.featureModal.clearAll = function () {
        $scope.featureModal.deviceId = "";
        $scope.featureModal.featureId = "";
        $scope.featureModal.trigger.triggerType = "";
        $scope.featureModal.trigger.triggerValue = "";
        $scope.featureModal.functionValues = "";
    }; 

    $scope.addFeature = function (strategy) {
        var feature = $scope.getFeature();
        strategy.featureList.push(feature);
        console.log(strategy);
/*
        for (var i = 0; i < featureList.length; i++) {
            if (featureList[i].featureId == $scope.featureModal.featureId) {
                feature = $scope.jsonClone(featureList[i]);
                feature.device = $scope.jsonClone(device);
                feature.triggerList.push($scope.jsonClone(trigger));
                strategy.featureList.push(feature);
            }
        }*/
    };

    $scope.getFeature = function(){
        for(var i=0;i<$scope.features.length;i++){
            if($scope.featureModal.featureId == $scope.features[i].featureId){
                var device = $scope.getDeviceById();
                return new anicloud.sunny.model.FeatureInstance(
                    $scope.features[i].featureId,
                    $scope.features[i].featureName,
                    device,
                    [],
                    {"triggerType":$scope.featureModal.trigger,"value":$scope.featureModal.trigger.triggerValue}
                );
            }
        }
    }

    $scope.deleteFeature = function (index, strategy) {
        var featureList = strategy.featureList;
        if (isNaN(index) || index >= featureList.length || index < 0)
            return;
        featureList.splice(index, 1);
    }

    $scope.getDeviceById = function () {
        for (var i = 0; i < $scope.devices.length; i++) {
            if ($scope.devices[i].id == $scope.featureModal.deviceId) {
                return $scope.devices[i];
            }
        }
    };

    $scope.jsonClone = function (obj) {
        return JSON.parse(JSON.stringify(obj));
    };

    $scope.addStrategy = function() {
        $scope.strategyModal.strategyId = $scope.strategies.length + 1;
        $scope.strategies.push($scope.jsonClone($scope.strategyModal));
    };

    $scope.strategyModal.clearAll = function () {
        $scope.strategyModal.strategyName = "";
        $scope.strategyModal.strategyDescription = "";
    };

    $scope.deleteStrategy = function(index) {
        if (isNaN(index) || index >= $scope.strategies.length || index < 0)
            return;
        $scope.strategies.splice(index, 1);
    }


}


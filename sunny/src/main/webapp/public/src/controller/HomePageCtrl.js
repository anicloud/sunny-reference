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

    $scope.strategies = [];
    $scope.devices = [];
    $scope.features = [];
    $scope.triggers = [];


//read data
    $http.get("public/json/strategy.json")
        .success(function (data, status, headers, config) {
            $scope.strategies = data;
        });
    $http.get("public/json/device.json")
        .success(function (data, status, headers, config) {
            $scope.devices = data;
        });
    $http.get("public/json/feature.json")
        .success(function (data, status, headers, config) {
            $scope.features = data;
        });
    $http.get("public/json/trigger.json")
        .success(function (data, status, headers, config) {
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

    $scope.featureModal = {
        "featureId": "",
        "deviceId": "",
        "trigger": {
            "triggerValue": "",
            "triggerType": ""
        }
    };

    $scope.featureModal.clearAll = function () {
        $scope.featureModal.deviceId = "";
        $scope.featureModal.featureId = "";
        $scope.featureModal.trigger.triggerType = "";
        $scope.featureModal.trigger.triggerValue = "";
    }; 

    $scope.addFeature = function (strategy) {
        var featureList = $scope.featureModal.getDeviceFeatures();
        var feature = null;
        var device = $scope.getDeviceById();
        var trigger = $scope.featureModal.trigger;
        for (var i = 0; i < featureList.length; i++) {
            if (featureList[i].featureId == $scope.featureModal.featureId) {
                feature = $scope.jsonClone(featureList[i]);
                feature.device = $scope.jsonClone(device);
                feature.triggerList.push($scope.jsonClone(trigger));
                strategy.featureList.push(feature);
            }
        }
    };

    $scope.deleteFeature = function (index, strategy) {
        var featureList = strategy.featureList;
        if (isNaN(index) || index >= featureList.length || index < 0)
            return;
        featureList.splice(index, 1);
    }

    $scope.featureModal.getDeviceFeatures = function () {
        for (var i = 0; i < $scope.features.length; i++) {
            if ($scope.features[i].deviceId == $scope.featureModal.deviceId) {
                return $scope.features[i].featureList;
            }
        }
    };

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


    $scope.strategyModal = {
        "strategyId": "",
        "strategyName": "",
        "strategyState": "",
        "strategyDescription": "",
        "strategyStage": "",
        "featureList": []
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


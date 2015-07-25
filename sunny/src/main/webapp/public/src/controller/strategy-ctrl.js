/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.StrategyCtrl = function ($rootScope, $scope, $http, StrategyService, DeviceService) {
    //    For Strategy page
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

    // feature modal
    $scope.featureModal = {
        "featureId": "",
        "deviceId": "",
        "trigger": {
            "triggerValue": "",
            "triggerType": ""
        },
        "function": {
            "functionValue": "",
            "functionArg": ""
        }
    };


    $scope.featureModal.clearAll = function () {
        $scope.featureModal.deviceId = "";
        $scope.featureModal.featureId = "";
        $scope.featureModal.trigger.triggerType = "";
        $scope.featureModal.trigger.triggerValue = "";
        $scope.featureModal.functionValues = "";
    };

    $scope.featureModal.getDeviceFeatures = function () {
        return $rootScope.features[$scope.featureModal.deviceId];
    };


    $scope.featureModal.getDevice = function () {
        for (var i=0; i<$rootScope.devices.length; i++)  {
            if ($rootScope.devices[i].id == $scope.featureModal.deviceId) {
                return $rootScope.devices[i];
            }
        }
        return null;
    }

    $scope.featureModal.getFeature = function () {
        for (var i=0; i<$rootScope.features[$scope.featureModal.deviceId].length; i++) {
            if ($rootScope.features[$scope.featureModal.deviceId][i].featureId == $scope.featureModal.featureId) {
                return $rootScope.features[$scope.featureModal.deviceId][i];
            }
        }
        // Todo : add feature args
        return null;
    }

    $scope.addFeature = function (strategy) {
        var feature = $scope.featureModal.getFeature();
        var device = $scope.featureModal.getDevice();
        var trigger = $scope.featureModal.trigger;
        console.log(trigger);
        if (feature != null) {
            var featureInstance = new anicloud.sunny.model.FeatureInstance(
                feature.featureId,
                feature.featureName,
                device,
                [],
                [jsonClone(trigger)]
            );
            strategy.featureList.push(featureInstance);
            console.log(strategy);
        }
    };

    $scope.deleteFeature = function (index, strategy) {
        var featureList = strategy.featureList;
        if (isNaN(index) || index >= featureList.length || index < 0)
            return;
        featureList.splice(index, 1);
    };

    // strategy modal
    $scope.strategyModal = {
        "strategyId": "",
        "strategyName": "",
        "strategyState": "",
        "strategyDescription": "",
        "strategyStage": "",
        "featureList": []
    };

    $scope.strategyModal.clearAll = function () {
        $scope.strategyModal.strategyName = "";
        $scope.strategyModal.strategyDescription = "";
    };

    $scope.addStrategy = function () {
        $scope.strategyModal.strategyId = $rootScope.strategies.length + 1;//写死的长度
        $rootScope.strategies.push(jsonClone($scope.strategyModal));
    };

    $scope.deleteStrategy = function (index) {
        if (isNaN(index) || index >= $rootScope.strategies.length || index < 0)
            return;
        $rootScope.strategies.splice(index, 1);
    };


    //
    var jsonClone = function (obj) {
        return JSON.parse(JSON.stringify(obj));
    };


}
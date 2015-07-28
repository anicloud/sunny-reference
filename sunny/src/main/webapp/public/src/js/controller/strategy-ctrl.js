/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.StrategyCtrl = function ($rootScope, $scope, ngDialog, StrategyService, DeviceService) {
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

        DeviceService.getDeviceFeatures(function (data) {
            for (var i = 0; i < data.length; i++) {
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
        },
        "valid" : true,
        "error" : ""
    };


    $scope.featureModal.clearAll = function () {
        $scope.featureModal.deviceId = "";
        $scope.featureModal.featureId = "";
        $scope.featureModal.trigger.triggerType = "";
        $scope.featureModal.trigger.triggerValue = "";
        $scope.featureModal.functionValues = "";
    };

    $scope.featureModal.getDeviceFeatures = function () {
        if ($scope.featureModal.deviceId == '') {
            return null;
        }
        return $rootScope.features[$scope.featureModal.deviceId];
    };


    $scope.featureModal.getDevice = function () {
        if ($scope.featureModal.deviceId == '') {
            return null;
        }
        for (var i = 0; i < $rootScope.devices.length; i++) {
            if ($rootScope.devices[i].id == $scope.featureModal.deviceId) {
                return $rootScope.devices[i];
            }
        }
        return null;
    }

    $scope.featureModal.getFeature = function () {
        if ($scope.featureModal.featureId == '') {
            return null;
        }
        for (var i = 0; i < $rootScope.features[$scope.featureModal.deviceId].length; i++) {
            if ($rootScope.features[$scope.featureModal.deviceId][i].featureId == $scope.featureModal.featureId) {
                return $rootScope.features[$scope.featureModal.deviceId][i];
            }
        }
        // Todo : add feature args
        return null;
    }

    $scope.featureModal.open = function() {
        ngDialog.open(
            {
                template: 'public/src/view/feature.html',
                scope: $scope
            });
    }

    $scope.addFeature = function (strategy) {
        $scope.featureModal.valid = true;
        var feature = $scope.featureModal.getFeature();
        var device = $scope.featureModal.getDevice();
        var trigger = $scope.featureModal.trigger;
        if (device == null) {
            $scope.featureModal.valid = false;
            $scope.featureModal.error = "设备不能为空";
            return false;
        }
        if (feature == null) {
            $scope.featureModal.valid = false;
            $scope.featureModal.error = "任务不能为空";
            return false;
        }
        if (trigger.triggerType == '') {
            $scope.featureModal.valid = false;
            $scope.featureModal.error = "触发条件不能为空";
            return false;
        }
        if (trigger.triggerType == 'TIMER' && trigger.triggerValue == '') {
            $scope.featureModal.valid = false;
            $scope.featureModal.error = "选择时间";
            return false;
        }

        var featureInstance = new anicloud.sunny.model.FeatureInstance(
            feature.featureId,
            feature.featureName,
            device,
            [],
            [jsonClone(trigger)]
        );
        strategy.featureList.push(featureInstance);
        return true;

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
        "featureList": [],
        "valid" : true,
        "error" : ""
    };

    $scope.strategyModal.clearAll = function () {
        $scope.strategyModal.strategyName = "";
        $scope.strategyModal.strategyDescription = "";
        $scope.strategyModal.strategyState = "";
    };

    $scope.addStrategy = function () {
        $scope.strategyModal.valid = true;
        if ($scope.strategyModal.strategyName.length == 0) {
            $scope.strategyModal.valid = false;
            $scope.strategyModal.error = "名称不能为空";
            return;
        }
        if ($scope.strategyModal.featureList.length == 0) {
            $scope.strategyModal.valid = false;
            $scope.strategyModal.error = "任务不能为空";
            return;
        }
        $scope.strategyModal.strategyId = $rootScope.strategies.length + 1;//写死的长度
        $scope.strategyModal.strategyState = "running";
        $rootScope.strategies.push(jsonClone($scope.strategyModal));
    };

    $scope.deleteStrategy = function (index) {
        if (isNaN(index) || index >= $rootScope.strategies.length || index < 0)
            return;
        $rootScope.strategies.splice(index, 1);
    };

    $scope.startStrategy = function (strategy) {
        console.log("start strategy: ");
        console.log(strategy);
    };

    $scope.stopStrategy = function (strategy) {
        console.log("stop strategy: ");
        console.log(strategy);
    };

    $scope.pauseStrategy = function (strategy) {
        console.log("pause strategy: ");
        console.log(strategy);
    };
    //
    var jsonClone = function (obj) {
        return JSON.parse(JSON.stringify(obj));
    };


}
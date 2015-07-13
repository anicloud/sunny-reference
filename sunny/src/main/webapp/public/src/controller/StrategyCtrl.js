/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.StrategyCtrl = function ($scope, $http,DeviceService) {
    $scope.isCollapsed = true;
    $scope.strategies = [];
    $scope.devices = [];
    $scope.features = [];
    $scope.triggers = [];
    $scope.featureModal = {
        "feature": "",
        "deviceId": "",
        "trigger": {
            "triggerType" : "",
            "triggerValue" : ""
        }
    };



    $http.get("public/json/trigger.json")
        .success(function(data, status, headers, config){
            $scope.triggers = data;
        });

    $scope.clearFeatureTemplate = function() {
        $scope.featureModal.deviceId = "";
    };

    $scope.addFeature = function(strategy) {
        alert("add feature dummy");
        var dummy = {
                "featureNum": 3,
                "featureName": "调节空调温度33333",
                "device": {
                    "id": "12345",
                    "name": "空调",
                    "deviceState": "connected",
                    "deviceType": "",
                    "deviceGroup": "客厅"
                },
                "functionChosen": 0,
                "triggerChosen": 1,
                "functionList": [
                    {
                        "functionGroup": "温度",
                        "functionName": "升高"
                    },
                    {
                        "functionGroup": "温度",
                        "functionName": "降低"
                    }
                ],
                "triggerList": [
                    {
                        "triggerType": "time",
                        "triggerValue": "30"
                    },
                    {
                        "triggerType": "time",
                        "triggerValue": "30"
                    }
                ]
            };
        strategy.featureList.push(dummy);
    };

    $scope.addDevice = function(deviceName) {
        featureModal.device = 'deviceName';
        alert('OK');
    };

    /*$scope.featureModal.getDeviceFeatures = function() {
        for (var i= 0; i<$scope.features.length; i++) {
            if ($scope.features[i].deviceId == $scope.featureModal.deviceId) {
                return $scope.features[i].featureList;
            }
        }
    };*/


    //$scope.num = 1;
    //$scope.addStrategy = function() {
    //    //var strategyList = $('#strategyList');
    //    //var strategyDiv = $('<div class="col-sm-3" style="border: 2px; color: red; background-color: #204d74; padding: 10px;">xxxx</div>');
    //    strategyDiv.text("button" + $scope.num);
    //    strategyList.append(strategyDiv);
    //    $scope.num++;
    //
    //    //var c = document.getElementById("myCanvas");
    //    //var ctx = c.getContext("2d");
    //    //ctx.beginPath();
    //    //ctx.moveTo(0, 0);
    //    //ctx.lineTo(300, 150);
    //    //ctx.stroke();
    //}
    //$scope.deleteStrategy = function() {
    //    strategyList.remove(strategyDiv);
    //}

}
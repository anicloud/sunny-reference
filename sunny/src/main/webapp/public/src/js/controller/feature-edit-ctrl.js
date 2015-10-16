/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.FeatureEditCtrl = function ($rootScope, $scope, ManagerService) {
    // feature Template
    $scope.device = null;
    $scope.feature = null;
    $scope.trigger = {
        "triggerValue": "",
        "triggerType": ""
    };
    $scope.triggerTimer = {
        "startTime": new Date(),
        "repeatInterval": 0,
        "repeatCount": 0
    };
    $scope.isCalenderOpen = true;
    $scope.argumentMap = {};
    $scope.valid = true;
    $scope.error = "";

    $scope.getDeviceFeatures = function () {
        if ($scope.device == null) {
            return null;
        }
        return $rootScope.features[$scope.device.id];
    };

    $scope.addFeature = function (strategy) {
        $scope.valid = true;

        if ($scope.device == null) {
            $scope.valid = false;
            $scope.error = "设备不能为空";
            return false;
        }
        if ($scope.feature == null) {
            $scope.valid = false;
            $scope.error = "任务不能为空";
            return false;
        }
        if ($scope.trigger.triggerType == '') {
            $scope.valid = false;
            $scope.error = "触发条件不能为空";
            return false;
        }

        var argumentList = [];
        for (var arg in $scope.argumentMap) {
            var obj = {};
            obj.argName = arg;
            obj.value = $scope.argumentMap[arg];
            argumentList.push(obj);
        }
        console.log(argumentList);

        if ($scope.trigger.triggerType == "TIMER") {
            console.log($scope.triggerTimer);
            $scope.trigger.triggerValue = JSON.stringify($scope.triggerTimer);
            console.log($scope.trigger.triggerValue);
        }

        var featureInstance = new anicloud.sunny.model.FeatureInstance(
            "",
            $scope.device,
            $scope.feature,
            argumentList,
            [$scope.trigger],
            false
        );
        ManagerService.addFeature(featureInstance, strategy);
        return true;
    };

    $scope.openCalendar = function(e) {
        e.preventDefault();
        e.stopPropagation();

        $scope.isCalenderOpen = true;
    }
    //
    var jsonClone = function (obj) {
        return JSON.parse(JSON.stringify(obj));
    };

        $scope.mytime = new Date();

        $scope.hstep = 1;
        $scope.mstep = 10;

        $scope.options = {
            hstep: [1, 2, 3],
            mstep: [1, 5, 10, 15, 25, 30]
        };

        $scope.ismeridian = true;
        $scope.toggleMode = function() {
            $scope.ismeridian = ! $scope.ismeridian;
        };


}
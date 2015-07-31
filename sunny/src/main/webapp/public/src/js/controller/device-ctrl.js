/**
 * Created by libiya on 15/7/1.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.DeviceCtrl = function ($rootScope, $scope, ManagerService) {
    // main
    $scope.getGroups = function () {
        var json = {"default": 1};
        var groups = ["default"];
        for (var i = 0; i < $rootScope.devices.length; i++) {
            if (!json[$rootScope.devices[i].deviceGroup]) {
                groups.push($rootScope.devices[i].deviceGroup);
                json[$rootScope.devices[i].deviceGroup] = 1;
            }
        }
        return groups;
    };

    $scope.selectedGroup = "default";
    $scope.setDeviceFilter = function(group) {
        $scope.selectedGroup = group;
    }

    $scope.deviceFilterByGroup = function (device) {
        if ($scope.selectedGroup == "default")
            return true;
        if (device.deviceGroup == $scope.selectedGroup)
            return true;
        else
            return false;
    }

    // device detail
    $scope.deviceDetail = {}
    $scope.deviceDetail.isToggled = true;
    $scope.deviceDetail.device = null;
    $scope.deviceDetail.toggle = function (device) {
        if ($scope.deviceDetail.device == null) {
            $scope.deviceDetail.isToggled = !$scope.deviceDetail.isToggled;
        }
        else if ($scope.deviceDetail.device.id == device.id) {
            $scope.deviceDetail.isToggled = !$scope.deviceDetail.isToggled;
        }else {
            $scope.deviceDetail.isToggled = false;
        }
        $scope.deviceDetail.device = device;
        $scope.deviceDetail.featureChosen = null;
    }

    $scope.deviceDetail.nameEditable = false;
    $scope.deviceDetail.nameInput = "";
    $scope.deviceDetail.editName = function() {
        $scope.deviceDetail.device.name = $scope.deviceDetail.nameInput;
        $scope.deviceDetail.nameEditable = false;
    }

    $scope.deviceDetail.getGroups = function () {
        var groups = $scope.getGroups();
        groups.push("添加新组");
        return groups;
    };

    $scope.deviceDetail.groupEditable = false;
    $scope.deviceDetail.groupChosen = "";
    $scope.deviceDetail.groupInput = "";
    $scope.deviceDetail.editGroup = function() {
        if($scope.deviceDetail.groupChosen=='添加新组'){
            $scope.deviceDetail.device.deviceGroup = $scope.deviceDetail.groupInput;
        }
        else{
            $scope.deviceDetail.device.deviceGroup = $scope.deviceDetail.groupChosen;
        }
        $scope.deviceDetail.groupEditable = false;
        $scope.deviceDetail.groupChosen = "";
        $scope.deviceDetail.groupInput = "";
    }

    $scope.deviceDetail.getFeatureList = function() {
        if ($scope.deviceDetail.device != null) {
            return $rootScope.features[$scope.deviceDetail.device.id];
        }
    }

    $scope.deviceDetail.featureChosen = null;
    $scope.deviceDetail.getArgumentList = function() {
        $scope.deviceDetail.arguments = {};
        if ($scope.deviceDetail.featureChosen != null) {
            return $scope.deviceDetail.featureChosen.argDtoList;
        }
    }

    $scope.deviceDetail.arguments = {};

    $scope.deviceDetail.start = function () {
        var argumentMap = $scope.deviceDetail.arguments;
        var argumentList = [];
        for (var arg in argumentMap) {
            var obj = {};
            obj[arg] = argumentMap[arg];
            argumentList.push( obj );
        }
        var featureInstance = new anicloud.sunny.model.FeatureInstance(
            "",
            featureChosen.featureName,
            device,
            featureChosen,
            argumentList,
            [],
            true
        );

        var strategyInstance = new anicloud.sunny.model.StrategyInstance(
            "",
            "_PHONY_STRATEGY_",
            null,
            null,
            [].push(jsonClone(featureInstance)));

        ManagerService.addStrategy(strategyInstance, ManagerService.updateStrategy);
    }
    $scope.deviceDetail.pause = function () {
        var key = $rootScope.deviceDetail.device.id;
        var strategy = $rootScope.phonyStrategyMap[key];
        ManagerService.pause(strategy);
    }
    $scope.deviceDetail.stop = function () {
        var key = $rootScope.deviceDetail.device.id;
        var strategy = $rootScope.phonyStrategyMap[key];
        ManagerService.stop(strategy);
    }
    $scope.deviceDetail.resume = function () {
        var key =  $rootScope.deviceDetail.device.id;
        var strategy = $rootScope.phonyStrategyMap[key];
        ManagerService.resume(strategy);
    }
}





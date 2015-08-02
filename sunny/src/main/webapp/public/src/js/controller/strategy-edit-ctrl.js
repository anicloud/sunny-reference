/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.StrategyEditCtrl = function ($rootScope, $scope, ngDialog, ManagerService) {
    $scope.openFeatureEditTemplate = function () {
        ngDialog.open(
            {
                template: 'public/src/view/feature-edit.html',
                scope: $scope,
                controller: 'FeatureEditCtrl'
            });
    }

    $scope.deleteFeature = function (index, strategy) {
        ManagerService.deleteFeature(index, strategy);
    };

    // strategy Template
    $scope.strategyTemplate = {
        "strategyId": "",
        "strategyName": "",
        "strategyState": "",
        "strategyDescription": "",
        "strategyStage": "",
        "featureList": [],
        "valid": true,
        "error": ""
    };

    $scope.strategyTemplate.clearAll = function () {
        $scope.strategyTemplate.strategyName = "";
        $scope.strategyTemplate.strategyDescription = "";
        $scope.strategyTemplate.strategyState = "";
    };

    $scope.addStrategy = function () {
        $scope.strategyTemplate.valid = true;
        if ($scope.strategyTemplate.strategyName.length == 0) {
            $scope.strategyTemplate.valid = false;
            $scope.strategyTemplate.error = "名称不能为空";
            return false;
        }
        if ($scope.strategyTemplate.featureList.length == 0) {
            $scope.strategyTemplate.valid = false;
            $scope.strategyTemplate.error = "任务不能为空";
            return false;
        }
        var strategyInstance = new anicloud.sunny.model.StrategyInstance(
            $scope.strategyTemplate.strategyId,
            $scope.strategyTemplate.strategyName,
            null,
            null,
            jsonClone($scope.strategyTemplate.featureList));

        ManagerService.addStrategy(strategyInstance);
        return true;
    };

    //
    var jsonClone = function (obj) {
        return JSON.parse(JSON.stringify(obj));
    };

}
/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.StrategyCtrl = function ($rootScope, $scope, ngDialog, StrategyService, DeviceService, ManagerService) {

    $scope.deleteStrategy = function (index, strategy) {
        ManagerService.deleteStrategy(index, strategy);
        return true;
    };

    $scope.resumeStrategy = function (index, strategy) {
        ManagerService.resumeStrategy(strategy);
        return true;
    };

    $scope.stopStrategy = function (index, strategy) {
        ManagerService.stopStrategy(strategy);
        return true;
    };

    $scope.pauseStrategy = function (index, strategy) {
        ManagerService.pauseStrategy(strategy);
        return true;
    };

    $scope.toggleStatus = [];

    $scope.toggleOpen = function(index) {
        $scope.toggleStatus[index] = !$scope.toggleStatus[index];
    }

    $scope.setProgressLength = function (stage, len) {
        return {width: (stage / len * 100) + '%'};
    };
    $scope.setBadgeColor = function (fNum, stage) {
        return {'background-color': fNum < stage ? '#33aa33' : 'default'};
    };

}
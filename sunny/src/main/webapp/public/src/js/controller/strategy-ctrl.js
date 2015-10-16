/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.StrategyCtrl = function ($rootScope, $scope, ngDialog, ManagerService) {

    $scope.deleteStrategy = function (index, strategy) {
        ManagerService.stopStrategy(strategy);
        ManagerService.deleteStrategy(index, strategy);
        return true;
    };

    $scope.resumeStrategy = function (index, strategy) {
        ManagerService.resumeStrategy(strategy);
        return true;
    };

    $scope.stopStrategy = function (index, strategy) {
        if(strategy.state == 'RUNNING') {
            ManagerService.stopStrategy(strategy);
            return true;
        }
    };

    $scope.pauseStrategy = function (index, strategy) {
        if(strategy.state == 'RUNNING') {
            ManagerService.pauseStrategy(strategy);
            return true;
        }
    };

    $scope.toggleStatus = [];

    $scope.toggleOpen = function(index) {
        $scope.toggleStatus[index] = !$scope.toggleStatus[index];
    };

    $scope.setProgressLength = function (strategy) {
        var stage = strategy.stage;
        var len = strategy.featureList.length;
        return {width: (stage / len * 100) + '%'};
    };
    $scope.setBadgeColor = function (fNum, stage) {
        return {'background-color': fNum < stage ? '#eba963' : 'default'};
    };
    $scope.showState = function(strategy) {
        if(strategy.state == "NONE") {

        }else if(strategy.state == "RUNNING") {
            var stage = parseInt(strategy.stage);
            var info = strategy.featureList[stage].deviceFeature.featureName;
            return "正在执行:" + info;
        }else if(strategy.state == "SUSPENDED") {
            return "暂停";
        }else if(strategy.state == "DONE") {
            return "已完成";
        }
    };

    $scope.strategyFilterDone = function (strategy) {
        if (strategy.state == "DONE" && strategy.strategyName != "_PHONY_STRATEGY_") {
            return true;
        } else {
            return false;
        }
    };

    $scope.strategyFilterRunning = function (strategy) {
        if ((strategy.state == "RUNNING" || strategy.state == "SUSPENDED") && strategy.strategyName != "_PHONY_STRATEGY_") {
            return true;
        } else {
            return false;
        }
    };
    $scope.changeToObj = function (str) {
        return JSON.parse(str);
    }

    $scope.triggerTimerToDate = function(value) {
        var obj = JSON.parse(value);
        return obj.startTime;
    }

    $scope.setOpStyle = function(strategy) {
        if(strategy.state == 'DONE')
            return {color: '#eaeaea', cursor:'not-allowed' };
    }
};
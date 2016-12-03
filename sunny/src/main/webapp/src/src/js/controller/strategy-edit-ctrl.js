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
                template: 'src/view/feature-edit.html',
                scope: $scope,
                controller: 'FeatureEditCtrl'
            });
    }
    
    $scope.openRepeatTemplate = function () {
        ngDialog.open(
            {
                template: 'src/view/repeat.html',
                scope: $scope,
                controller: 'RepeatCtrl'
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
        "error": "",
        "repeatConfig": {
            "week": [],
            "end":{
                "type":"count",
                "value":"1"
            }
        }
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
        //sort featureList by time
        $scope.strategyTemplate.featureList.sort(function (strategy1,strategy2) {
           var time1=moment(strategy1.triggerDtoList[0].triggerValue);
            var time2=moment(strategy2.triggerDtoList[0].triggerValue);
            console.log('time',time1,time2);
            if(time1.isBefore(time2))return -1;
            else  return 1;
        });
        var strategyInstance = new anicloud.sunny.model.StrategyInstance(
            $scope.strategyTemplate.strategyId,
            $scope.strategyTemplate.strategyName,
            null,
            null,
            jsonClone($scope.strategyTemplate.featureList));
    
        ManagerService.addStrategy(strategyInstance);
        return true;
    };
    $scope.triggerTimerToDate = function(value) {
        var obj = JSON.parse(value);
        return obj.startTime;
    };
    
    //
    var jsonClone = function (obj) {
        return JSON.parse(JSON.stringify(obj));
    };
    
    $scope.dynamicPopover = {
        content: 'Hello, World!',
        templateUrl: 'myPopoverTemplate.html',
        title: 'Title'
    };
    $scope.initTimer=function () {
       
    };
    $scope.strategyRepeat={ //init
        isRepeat:false, //once,week
        weekRepeat:[],  //[] if isRepeat is once
        startTime:moment(),
        isScheduleNow:false   //
    }
   
};
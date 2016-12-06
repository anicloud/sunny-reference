/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.RepeatCtrl = function ($rootScope, $scope, ManagerService,$timeout) {
    $timeout(function () {
        console.log('timeout',document.querySelector('#datetimepicker12'));
        $('#datetimepicker12').on('dp.change',function (e) {
            $scope.tempRepeat.startTime= e.date.locale('zh-cn');
            $scope.$apply();
        });
        $scope.tempRepeat={
            isRepeat:$scope.strategyRepeat.isRepeat,
            startTime:$scope.strategyRepeat.startTime,
            name:$scope.strategyTemplate.strategyName,
            nameRequired:true,
            weekRequired:true
        };
        $scope.tempRepeat.repWeek=[false,false,false,false,false,false,false];
        $scope.strategyRepeat.weekRepeat.forEach(function(item,index){
            $scope.tempRepeat.repWeek[item]=true;
        });
        $scope.save=function(){
            if(!$scope.tempRepeat.name){
                $scope.tempRepeat.nameRequired=false;
                return false;
            }
            if($scope.tempRepeat.isRepeat&&$scope.tempRepeat.repWeek.toString()===[false,false,false,false,false,false,false].toString()){
                $scope.tempRepeat.weekRequired=false;
                return false
            }
            $scope.strategyTemplate.strategyName=$scope.tempRepeat.name;
            $scope.strategyRepeat.startTime=$scope.tempRepeat.startTime;
            $scope.strategyRepeat.weekRepeat=[];
            $scope.strategyRepeat.isRepeat=$scope.tempRepeat.isRepeat;
            $scope.tempRepeat.repWeek.forEach(function(item,index){
                return item?$scope.strategyRepeat.weekRepeat.push(index):null;
            });
            localStorage.setItem('currentStrategyTime',$scope.tempRepeat.startTime.toJSON());
            $scope.closeThisDialog();
        }
    },100);
};
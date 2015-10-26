/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.RepeatCtrl = function ($rootScope, $scope, ManagerService) {
    $scope.repweek = {
        sun: false,
        mon: false,
        tue: false,
        wed: false,
        thu: false,
        fri: false,
        sat: false
    };
    $scope.end = {
        type: "forever",
        value: ""
    }
    $scope.count = null;
    $scope.date = new Date();
    $scope.save = function() {
        if($scope.end.type == "count"){
            $scope.end.value = $scope.count;
        }else if($scope.end.type == "date") {
            $scope.end.value = $scope.date;
        }
        console.log($scope.end);
    }

}
/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.MainCtrl = function ($rootScope, $scope) {
    $scope.isAsideCollapsed = false;
    $scope.setAsideCollapse = function () {
        $scope.isAsideCollapsed = !$scope.isAsideCollapsed;
    }
}
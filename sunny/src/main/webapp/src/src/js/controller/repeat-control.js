/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.RepeatCtrl = function ($rootScope, $scope, ManagerService,$timeout) {
    $('#datetimepicker12').on('dp.change',function (e) {
        console.log(e.date,e.oldDate);
    });
    $timeout(function () {
        console.log(document.querySelector('#datetimepicker12'));
    },100);
};
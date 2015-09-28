/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.UserCtrl = function ($rootScope, $scope, $window, UserService) {
    $scope.logout = function () {
        UserService.logout(function(data) {
            if (data.status == "success") {
                $window.location.href = "/sunny";
            } else {
                console.log("user logout error.");
            }
        });
    }
}
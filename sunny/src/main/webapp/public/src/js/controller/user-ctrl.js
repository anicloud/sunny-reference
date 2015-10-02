/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.UserCtrl = function ($rootScope, $scope, $window, UserService) {
    var user = $.cookie('sunny_user');
    if (user != undefined) {
        var usercookie = JSON.parse(user);
        $scope.currentUser = usercookie;
    }

    $scope.logout = function () {
        UserService.logout(function(data) {
            if (data.status == "success") {
                $window.location.href = "logout";
            } else {
                console.log("user logout error.");
            }
        });
    }

    $scope.switchUser = function() {
        $window.location.href = "switchUser?hashUserId=" + $scope.currentUser.hashUserId;
    }
}
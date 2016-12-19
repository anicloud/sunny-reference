/**
 * Created by sirhuoshan on 2015/7/1.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.UserService = function($http, $cookies) {
    return {
        logout:function(callback){
            //var user = $cookies['sunny_user'];
            var user = $.cookie('sunny_user');
            if (user != undefined) {
                var usercookie = JSON.parse(user);
                var hashUserId = usercookie.hashUserId;
                $http({
                    method:'POST',
                    url: 'logout',
                    params: {hashUserId:hashUserId}
                }).then(function(data){
                    callback(data);
                },function(data){
                    console.log('logout failures');
                });
            } else {
                console.log("cookie user not exist.");
            }

        }
    }
}
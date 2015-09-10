/**
 * Created by sirhuoshan on 2015/7/6.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.DeviceService = function($http,$cookies){
    return{
        getDevices:function(callback){
            var user = $.cookie('sunny_user');
            if (user != undefined) {
                var usercookie = JSON.parse(user);
                var hashUserId = usercookie.hashUserId;
                $http({
                    method:'GET',
                    url: 'devices',
                    params: {hashUserId:hashUserId}
                }).success(function(data){
                    callback(data);
                }).error(function(data){
                    console.log('get devices failures');
                })
            }

        },
        getDeviceFeatures:function(callback){
            $http({
                method:'GET',
                url: 'features'
            }).success(function(data){
                callback(data);
            }).error(function(data){
                console.log('get feature failures');
            })
        },
        getDeviceFeatureByID:function(callback,deviceId){
            $http({
                method:'GET',
                url: 'feature',
                params: {deviceId:deviceId}
            }).success(function(data){
                callback(data);
            }).error(function(data){
                console.log('get feature failures');
            })
        },
        getFeatureTrigger:function(callback){
            $http({
                method:'GET',
                url: 'triggers'
            }).success(function(data){
                callback(data);
            }).error(function(data){
                console.log('get feature failures');
            })
        }
    }
}
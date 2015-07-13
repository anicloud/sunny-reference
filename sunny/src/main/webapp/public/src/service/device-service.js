/**
 * Created by sirhuoshan on 2015/7/6.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.DeviceService = function($http,$cookies){
    return{
        getDevices:function(callback){
            var usercookie = JSON.parse(JSON.parse( $cookies['sunny_user']));
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
        },
        getDeviceFeatures:function(callback,deviceId){
            $http({
                method:'GET',
                url: 'features',
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
        },
        getFeatureArgs:function(callback){
            $http({
                method:'GET',
                url: 'featrueArgs'
            }).success(function(data){
                callback(data);
            }).error(function(data){
                console.log('get feature failures');
            })
        }

    }
}
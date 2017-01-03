/**
 * Created by sirhuoshan on 2015/7/6.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.DeviceService = function ($http, $cookies) {
    return {
        getDevices: function (callback) {
            var user = $.cookie('sunny_user');
            if (user != undefined) {
                var usercookie = JSON.parse(user);
                var hashUserId = usercookie.hashUserId;
                $http({
                    method: 'GET',
                    url: 'devices',
                    params: {hashUserId: hashUserId}
                }).then(function (data) {
                    callback(data.data);
                }, function () {
                    console.log('get devices failures');
                })
            }

        },
        editGroup: function (deviceId, groupName) {
            $http({
                method: 'POST',
                url: 'device/' + deviceId,
                data: angular.toJson({deviceGroup: groupName})
            }).then(function (data) {
                if (data)console.log('edit device_group successful');
            },function (data) {
                console.log('edit device_group failures');
            })
        },
        editName: function (deviceId, deviceName) {
            $http({
                method: 'POST',
                url: 'device/' + deviceId,
                data: angular.toJson({deviceName: deviceName})
            }).then(function (data) {
                if (data)console.log('edit device_name successful');
            },function (data) {
                console.log('edit device_name failures');
            })
        },
        getDeviceFeatures: function () {
            return $http({
                method: 'GET',
                url: 'features'
            }).then(function (data) {
                return data.data;
            },function (data) {
                console.log('get feature failures');
            })
        },
        getDeviceFeatureByID: function (callback, deviceId) {
            $http({
                method: 'GET',
                url: 'feature',
                params: {deviceId: deviceId}
            }).then(function (data) {
                callback(data);
            },function (data) {
                console.log('get feature failures');
            })
        },
        getFeatureTrigger: function (callback) {
            $http({
                method: 'GET',
                url: 'triggers'
            }).then(function (data) {
                callback(data);
            },function (data) {
                console.log('get feature failures');
            })
        },
        getDeviceInitParam: function (callback, deviceId) {
            $http({
                method: 'GET',
                url: 'initParam/' + deviceId,
                params: {deviceId: deviceId}
            }).then(function (data) {
                callback(data);
            },function (data) {
                console.log('get device init param failures');
            })
        }
    }
}
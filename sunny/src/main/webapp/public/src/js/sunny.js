/**
 * Created by zhaoyu on 15-06-10.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {}
anicloud.sunny.service = anicloud.sunny.service || {}
anicloud.sunny.config = anicloud.sunny.config || {}
anicloud.sunny.directive = anicloud.sunny.directive || {}
anicloud.sunny.global = anicloud.sunny.global || {}


if (window.console) {
    console.log(" Sunny Project is powered by Anicloud Smart Home Application, Platform Group(Beijing), Anicloud Limited");
}



anicloud.sunny.global.loadApp = function (config, controller, service, directive, appName) {
    var app = angular.module(appName, [
        'ngRoute',
        'ngCookies',
        'ngDialog',
        'ui.bootstrap',
        'ui.router'
    ]);

    //config
    for (var key in config) {
        app.config(config[key]);
    }

    //service
    for (var key in service) {
        app.factory(key, service[key]);
    }

    //controller
    for (var key in controller) {
        app.controller(key, controller[key]);
    }

    //directives
    for (var key in directive) {
        app.directive(key, directive[key]);
    }

    app.run(function($rootScope, StrategyService, DeviceService,WebSocketService) {
        $rootScope.strategies = [];
        $rootScope.devices = [];
        $rootScope.features = [];
        $rootScope.triggers = [];

        StrategyService.getStrategies(function (data) {
            $rootScope.strategies = data;
        });

        DeviceService.getDevices(function (data) {
            $rootScope.devices = data;
        });

        DeviceService.getDeviceFeatures(function (data) {
            for (var i = 0; i < data.length; i++) {
                var key = data[i].deviceFormDto.id;
                var value = data[i].deviceFeatureFormDtoList;
                $rootScope.features[key] = value;
            }
        });

        DeviceService.getFeatureTrigger(function (data) {
            $rootScope.triggers = data;
        });
    });
}

anicloud.sunny.global.loadApp(
    anicloud.sunny.config,
    anicloud.sunny.controller,
    anicloud.sunny.service,
    anicloud.sunny.directive,
    'sunny');

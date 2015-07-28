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

}

anicloud.sunny.global.loadApp(
    anicloud.sunny.config,
    anicloud.sunny.controller,
    anicloud.sunny.service,
    anicloud.sunny.directive,
    'sunny');

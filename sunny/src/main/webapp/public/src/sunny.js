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

anicloud.sunny.global.loadApp = function (controllers, services, configs, directives, appName) {
    var app = angular.module(appName, [
        'ngRoute',
        'ngCookies',
        'ui.bootstrap'
    ]);

    //services
    for (var serviceName in services) {
        app.factory(serviceName, services[serviceName]);
    }

    //controllers
    for (var ctrlName in controllers) {
        app.controller(ctrlName, controllers[ctrlName]);
    }

    //directives
    for (var directiveName in directives) {
        app.directive(directiveName, directives[directiveName]);
    }

    //configs
    for (var configName in configs) {
        app.config([configName, configs[configName]]);
    }

}

anicloud.sunny.global.loadApp(
    anicloud.sunny.controller,
    anicloud.sunny.service,
    anicloud.sunny.config,
    anicloud.sunny.directive,
    'sunny');
/**
 * Created by zhaoyu on 15-06-10.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {}
anicloud.sunny.service = anicloud.sunny.service || {}
anicloud.sunny.global = anicloud.sunny.global || {}

if (window.console) {
    console.log(" Suuny Project is powered by Anicloud Smart Home Application, Platform Group(Beijing), Anicloud Limited");
}

anicloud.sunny.global.loadApp = function (controllerObj, serviceObj, configServiceObj, appName) {
    var curApp = angular.module(appName, [
        'ngRoute',
        'ngCookies',
        'ui.bootstrap'
    ]);
    //system services
    for (var oneSysServiceName in configServiceObj) {
        curApp.config([
            oneSysServiceName,
            configServiceObj[oneSysServiceName]
        ]);
    }
    //services
    for (var oneServiceName in serviceObj) {
        curApp.factory(oneServiceName, serviceObj[oneServiceName]);
    }
    //controllers
    for (var oneCtrlName in controllerObj) {
        curApp.controller(oneCtrlName, controllerObj[oneCtrlName]);
    }
}

anicloud.sunny.global.loadApp(anicloud.sunny.controller, anicloud.sunny.service, anicloud.sunny.config.service, 'sunny');
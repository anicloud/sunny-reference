/**
 * Created by zhaoyu on 15-06-10.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};
anicloud.sunny.service = anicloud.sunny.service || {};
anicloud.sunny.config = anicloud.sunny.config || {};
anicloud.sunny.directive = anicloud.sunny.directive || {};
anicloud.sunny.global = anicloud.sunny.global || {};


if (window.console) {
    console.log(" Sunny Project is powered by Anicloud Smart Home Application, Platform Group(Beijing), Anicloud Limited");
}


anicloud.sunny.global.loadApp = function (config, controller, service, directive, appName) {
    var app = angular.module(appName, [
        'ngRoute',
        'ngCookies',
        'ngDialog',
        'ui.bootstrap',
      //  'ui.bootstrap.datetimepicker',
        'ui.router',
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

    //app.config(['$qProvider', function ($qProvider) {
    //    $qProvider.errorOnUnhandledRejections(false);
    //}]);
    app.run(function($rootScope, StrategyService, DeviceService, WebSocketService, ManagerService) {
        $rootScope.strategies = [];
        $rootScope.devices = [];
        $rootScope.features = [];
        $rootScope.triggers = [];
        $rootScope.phonyStrategyMap = {};
        $rootScope.busyDeviceMap = {};
        $rootScope.initParam = {};

        $rootScope.deviceDetailVisible=false;
        $rootScope.DOMClickHasRun=false;
        $rootScope.queryObjectByPropertyValue=function (body,property,value) {
            function propertyInArray (body,property,value) { //body can be group device account,etc
                for(var i=0;i<body.length;i++){
                    if(body[i][property]==value){
                        return [i,body[i]];
                    }
                }
                return null;
            };
            function propertyInObject (body,property,value) {
                for(var key in body){
                    if(key[property]==value){
                        return [key,body[key]];
                    }
                }
                return null;
            }
            if(Object.prototype.toString.call(body).indexOf('Array')>-1){
                return propertyInArray(body,property,value);
            }else if(Object.prototype.toString.call(body).indexOf('Object')>-1){
                return propertyInObject(body,property,value);
            }
        };
        //$rootScope.sidebarToggle=function(event){
        //  console.log('sidebarToggle',event)
        //};
        document.addEventListener('click',function(e){
            //console.log(e.target.scope())
            var sidebarObj=angular.element('#sidebar');
            if(sidebarObj.length>0) var sidebarCtrl=sidebarObj.scope();
            var clickCtrl=angular.element(e.target).scope();
            var result=null;
            function scopeBelong(sidebarCtrl,clickCtrl){
                if(!clickCtrl.$parent) return result=false;
                else if(clickCtrl===sidebarCtrl) return result=true;
                else scopeBelong(sidebarCtrl,clickCtrl.$parent)
            }
            if(sidebarCtrl&&$rootScope.deviceDetailVisible&&!scopeBelong(sidebarCtrl,clickCtrl)&&result!==true)
                $rootScope.deviceDetailVisible=false;
            $rootScope.$apply();
            //console.log(angular.element(e.target).scope(),sidebarCtrl)
        });
        StrategyService.getStrategies().then(function (data) {
            $rootScope.strategies = data;
            for (var strategy in data) {
                if (strategy.strategyName == "_PHONY_STRATEGY_") {
                    var key = strategy.featureList[0].device.id;
                    $rootScope.phonyStrategyMap[key] = strategy;
                }

                if (strategy.state == "RUNNING" || strategy.state == "PAUSE") {
                    $rootScope.busyDeviceMap[strategy.featureList[strategy.stage].device.id] = 1;
                }
            }
        });

        DeviceService.getDevices(function (data) {
            $rootScope.devices = data;
            for (var i = 0; i < $rootScope.devices.length; i++) {
                var device=$rootScope.devices[i].initParam;
                if(device!==null&&device!=="")
                    $rootScope.devices[i].initParam = JSON.parse($rootScope.devices[i].initParam);
            }
        });

        DeviceService.getDeviceFeatures().then(function (data) {
            for (var i = 0; i < data.length; i++) {
                var key = data[i].deviceFormDto.id;
                var value = data[i].deviceFeatureFormDtoList;
                $rootScope.features[key] = value;
            }
        });

        DeviceService.getFeatureTrigger(function (data) {
            $rootScope.triggers = data;
        });
        $rootScope.defaultPath="";
        $rootScope.config={
            dev:{
                defaultPath:'',
                wsPath:'ws://localhost:9000/'
            },
            prod:{
                defaultPath:'public',
                wsPath:"ws://localhost:8080/sunny/socket/strategy"
            }
        };
        $rootScope.currentConfig=$rootScope.config.prod;
        WebSocketService.openSocket(
            // "ws://localhost:8080/sunny/socket/strategy",
            $rootScope.currentConfig.wsPath,
            null,
            null,
            null,
            ManagerService.updateFrontInfo
        );

    });
    moment.locale('zh-cn');
};

anicloud.sunny.global.loadApp(
    anicloud.sunny.config,
    anicloud.sunny.controller,
    anicloud.sunny.service,
    anicloud.sunny.directive,
    'sunny');

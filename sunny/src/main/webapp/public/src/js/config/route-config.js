/**
 * Created by zhaoyu on 15-06-10.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.config = anicloud.sunny.config || {};

anicloud.sunny.config.route = ['$stateProvider', '$locationProvider', '$urlRouterProvider',
    function ($stateProvider, $locationProvider, $urlRouterProvider) {
        'use strict';

        // Set the following to true to enable the HTML5 Mode
        // You may have to set <base> tag in index and a routing configuration in your server
        $locationProvider.html5Mode(false);

        // defaults to dashboard
        $urlRouterProvider.otherwise('/app/dashboard');

        // Application Routes
        var basepath = 'public/src/view/';
        $stateProvider
            .state('app', {
                url: '/app',
                templateUrl: basepath + 'app.html'
            })
            .state('app.dashboard', {
                url: '/dashboard',
                templateUrl: basepath + 'home.html'
            })
            .state('app.device', {
                url: '/device',
                templateUrl: basepath + 'device.html'
            })
            .state('app.strategy', {
                url: '/strategy',
                templateUrl: basepath + 'strategy-running.html'
            })
            .state('app.edit_strategy', {
                url: '/edit_strategy',
                templateUrl: basepath + 'strategy-edit.html'
            })
            .state('app.error', {
                url: '/error',
                templateUrl: basepath + 'error.html'
            });

}]
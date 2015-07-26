/**
 * Created by zhaoyu on 15-06-10.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.config = anicloud.sunny.config || {};

var routeMap = {
    '/': [
        'src/view/home.html',
        'HomePageCtrl'
    ],
    //error page
    '/error': [
        'src/view/error.html'
    ],
    '/device': [
        'src/view/device.html',
        'DeviceCtrl'
    ],
    '/device_detail': [
        'src/view/device_detail.html'
    ],
    '/strategy': [
        'src/view/strategy.html',
        'StrategyCtrl'
    ],
    '/test': [
        'src/view/test.html'
    ],
    '/edit_strategy': [
        'src/view/edit_strategy.html',
        'StrategyCtrl'
    ]
}

anicloud.sunny.config.$routeProvider = function ($routeProvider) {
    for (var route in routeMap) {
        $routeProvider.when(
            route,
            {
                templateUrl: 'public/' + routeMap[route][0],
                controller: routeMap[route][1]
            }
        );
    }
    $routeProvider.otherwise({redirectTo: '/error'});
}
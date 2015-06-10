/**
 * Created by zhaoyu on 15-06-10.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.config = anicloud.sunny.config || {};

anicloud.sunny.config.route = {
    '/': [
        'src/view/home.html',
        'HomePageCtrl'
    ],
    //error page
    '/error': [
        'src/view/error.html',
        'HomePageCtrl'
    ]
}
/**
 * Created by zhaoyu on 15-06-10.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.config = anicloud.sunny.config || {};

anicloud.sunny.config.route = {
    '/': [
        'src/view/home.html',
        ''
    ],
    //error page
    '/error': [
        'src/view/error.html'
    ],
    '/device': [
        'src/view/device.html'
    ],
    '/strategy': [
        'src/view/strategy.html'
    ],
    '/test': [
        'src/view/test.html'
    ]


}
/**
 * Created by zhaoyu on 15-06-10.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.config = anicloud.sunny.config || {};
anicloud.sunny.config.service = anicloud.sunny.config.service || {};

anicloud.sunny.config.service = {
    /*$translateProvider: function ($translateProvider) {
        for (var oneTranslation in anicloud.sunny.config.language.translations) {
            $translateProvider.translations(
                oneTranslation,
                anicloud.sunny.config.language.translations[oneTranslation]
            );
        }
        $translateProvider
            //.preferredLanguage('en')
            .determinePreferredLanguage();
    },*/
    $routeProvider: function ($routeProvider) {
        for (var oneRoute in anicloud.sunny.config.route) {
            $routeProvider.when(
                oneRoute,
                {
                    templateUrl: 'public/' + anicloud.sunny.config.route[oneRoute][0],
                    controller: anicloud.sunny.config.route[oneRoute][1]
                }
            );
        }
        $routeProvider.otherwise({redirectTo: '/error'});
    }
}
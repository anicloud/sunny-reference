/**
 * Created by zhaoyu on 15-06-10.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service = {
    /*$translateProvider: function ($translateProvider) {
     for (var oneTranslation in anicloud.sunny.language.translations) {
     $translateProvider.translations(
     oneTranslation,
     anicloud.sunny.language.translations[oneTranslation]
     );
     }
     $translateProvider
     //.preferredLanguage('en')
     .determinePreferredLanguage();
     },*/
    dataService: function ($http) {
        //read data
        var strategies = [];
        var devices = [];
        var features = [];
        var triggers = [];


        $http.get("public/json/feature.json")
            .success(function (data, status, headers, config) {
                features = data;
            });
        $http.get("public/json/trigger.json")
            .success(function (data, status, headers, config) {
                triggers = data;
            });
        return {
            "strategies" : function() {
                $http.get("public/json/strategy.json")
                    .success(function (data, status, headers, config) {
                        return data;
                    });
            },
            "getDevices" : function() {
                $http.get("public/json/device.json")
                    .success(function (data, status, headers, config) {
                        devices = data;
                        alert(devices[0].name);
                    });
            },
            "features" : features,
            "triggers" : triggers
        };
    }

}
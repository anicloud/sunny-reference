/**
 * Created by libiya on 15/7/22.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.directive = anicloud.sunny.directive || {};
anicloud.sunny.directive.focus = function () {
    return {
        link: function (scope, element, attrs) {
            element.focus();
        }
    }
};

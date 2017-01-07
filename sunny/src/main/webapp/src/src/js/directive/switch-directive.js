/**
 * Created by zhangdongming on 2017/1/5.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.directive = anicloud.sunny.directive || {};
anicloud.sunny.directive.switchButton = function ($rootScope) {
    return {
        restrict: 'AEC',
        scope:{
            switchFeatures:'=',
            deviceDetail:'='
        },
        templateUrl:$rootScope.currentConfig.defaultPath+'/src/view/switch-template.html',
        link: function (scope, element, attrs) {
            scope.powerOnObjFeature=$rootScope.queryObjectByPropertyValue(scope.switchFeatures,'featureName','Power On');
            scope.powerOffObjFeature=$rootScope.queryObjectByPropertyValue(scope.switchFeatures,'featureName','Power Off');
            $("[name='deviceDetail-switch']").bootstrapSwitch('size','mini');
            $("[name='deviceDetail-switch']").on('switchChange.bootstrapSwitch',function(e,state){
                console.log(e);
                if(state===true){
                    scope.deviceDetail.start(scope.powerOnObjFeature[1]);
                }else{
                    scope.deviceDetail.start(scope.powerOffObjFeature[1]);
                }
            })
        }
    }
};
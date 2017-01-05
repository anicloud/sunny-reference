/**
 * Created by zhangdongming on 2017/1/4.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.directive = anicloud.sunny.directive || {};
anicloud.sunny.directive.deviceInfo = function ($rootScope) {
        return {
        restrict: 'AEC',
        scope:{
          device:'=',
          deviceDetail:'=',
            showIndex:'=',
            features:'=',
            index:"="
        },
            templateUrl:$rootScope.currentConfig.defaultPath+'/src/view/deviceInfo-template.html',
        link: function (scope, element, attrs) {
            scope.deviceToggle=function ($event) {
                $event.stopPropagation();
                scope.showIndex===scope.index?scope.showIndex=-1:scope.showIndex=scope.index;
                scope.deviceDetail.device=scope.device;
                $rootScope.deviceDetailVisible=true;
            };
            var $Box=$(element[0]).find('.device-box');
            var $BoxBlockTop=$(element[0]).find('.device-block-top');
            var $BoxBlockBottom=$(element[0]).find('.device-block-bottom');
            var $BoxToggle=$(element[0]).find('.device-toggle');
           //function initFirstParam(){
           //     var deviceFeatures=scope.features[scope.device.id];
           //     for(var i=0;i<deviceFeatures.length;i++){
           //         var feature=deviceFeatures[i];
           //         if(feature.argDtoList.length>0){
           //             var firstParam=scope.device.initParam[feature.argDtoList[0].name];
           //             firstParam=firstParam||firstParam===0?firstParam:'未获取';
           //             return scope.firstParamName=feature.argDtoList[0].screenName,
           //                    scope.firstParam=firstParam;
           //         }
           //     }
           // };
           // scope.$watch('device.initParam',function(newVal,oldVal){
           //     if(!oldVal||scope.features.length===0) return;
           //     initFirstParam();
           // },true);
           // scope.$watch('features[0]',function(newVal,oldVal){
           //     console.log(newVal,oldVal);
           //     if(oldVal.length===0||!scope.device) return;
           //     initFirstParam();
           // },true);
            //if(scope.device.id&&scope.features.length>0){
            //    initFirstParam();
            //}
           // console.log($Box,$BoxBlockBottom,$BoxBlockTop,$BoxToggle);

            //$Box.find('.device-box').(function(e){
            //    if($(element[0]).find('.device-cel-disconnect')[0]) return;
            //    $(element[0]).find('.device-toggle').removeClass('device-hide').addClass('device-show');
            //    scope.$apply()
            //},function(e){
            //    if($(element[0]).find('.device-cel-disconnect')[0]) return;
            //    $(element[0]).find('.device-toggle').removeClass('device-show').addClass('device-hide');
            //    scope.$apply()
            //});
            $BoxBlockBottom.click(function(event){
                if($(element[0]).find('.device-cel-disconnect')[0]) return;
                    $BoxToggle.removeClass('device-hide').addClass('device-show');
            });
            $BoxToggle.click(function(event){
                if($(element[0]).find('.device-cel-disconnect')[0]) return;
                $BoxToggle.removeClass('device-show').addClass('device-hide');
            })
        }
    }
};
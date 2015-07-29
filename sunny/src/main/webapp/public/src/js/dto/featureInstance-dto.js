/**
 * Created by sirhuoshan on 2015/7/13.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {}
anicloud.sunny.model = anicloud.sunny.model || {}

anicloud.sunny.model.FeatureInstance = function(){}
anicloud.sunny.model.FeatureInstance = function (
    featureId,
    featureName,
    device,
    functionValueDtoList,
    triggerDtoList,
    isScheduleNow
) {
    var _this = this;
    _this.featureId=featureId;
    _this.featureName=featureName;
    _this.device=device;
    _this.functionValueDtoList=functionValueDtoList;
    _this.triggerDtoList=triggerDtoList;
    _this.isScheduleNow=isScheduleNow;
}




/**
 * Created by sirhuoshan on 2015/7/13.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {}
anicloud.sunny.model = anicloud.sunny.model || {}

anicloud.sunny.model.FeatureInstance = function(){}
anicloud.sunny.model.FeatureInstance = function (
    featureInstanceId,
    device,
    deviceFeature,
    featureArgValueDtoList,
    triggerDtoList,
    isScheduleNow
) {
    var _this = this;
    _this.featureInstanceId=featureInstanceId;
    _this.device=device;
    _this.deviceFeature=deviceFeature;
    _this.featureArgValueDtoList=featureArgValueDtoList;
    _this.triggerDtoList=triggerDtoList;
    _this.isScheduleNow=isScheduleNow;
}




/**
 * Created by huangbin on 7/28/15.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.model = anicloud.sunny.model || {};

anicloud.sunny.model.StrategyInstance = function() {};

anicloud.sunny.model.StrategyInstance = function (
    strategyId,
    strategyName,
    state,
    stage,
    featureList,
    strategyRepeat
) {
    var _this = this;
    _this.strategyId = strategyId;
    _this.strategyName = strategyName;
    _this.state = state;
    _this.stage = stage;
    _this.featureList = featureList;
    _this.startTime=strategyRepeat.startTime;
    _this.isScheduleNow=strategyRepeat.isScheduleNow;
    _this.isRepeat=strategyRepeat.isRepeat;
    _this.repeatWeek=strategyRepeat.weekRepeat
};
/**
 * Created by sirhuoshan on 2015/7/25.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {}
anicloud.sunny.model = anicloud.sunny.model || {}

anicloud.sunny.model.StrategyInfo = function(){}
anicloud.sunny.model.StrategyInfo = function (
    strategyId,
    strategyName,
    state,
    stage,
    action
) {
    var _this = this;
    _this.strategyId=strategyId;
    _this.strategyName=strategyName;
    _this.state=state;
    _this.stage=stage;
    _this.action=action;
}

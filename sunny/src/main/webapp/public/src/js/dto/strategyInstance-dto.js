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
    strategyState,
    strategyStage,
    featureList
) {
    var _this = this;
    _this.strategyId = strategyId;
    _this.strategyName = strategyName;
    _this.strategyState = strategyState;
    _this.strategyStage = strategyStage;
    _this.featureList = featureList;
};
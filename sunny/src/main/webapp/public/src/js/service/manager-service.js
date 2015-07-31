/**
 * Created by sirhuoshan on 2015/7/1.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.ManagerService = function($rootScope, StrategyService){
    var jsonClone = function (obj) {
        return JSON.parse(JSON.stringify(obj));
    };

    return {
        addFeature: function (feature, strategy) {
            strategy.featureList.push(jsonClone(feature));
        },

        deleteFeature: function (index, strategy) {
            strategy.featureList.splice(index, 1);
        },

        addStrategy : function (strategy, updateStrategy) {
            StrategyService.saveStrategies(strategy, function (data) {
                if (data.status == "success") {
                    console.log("add strategy ok:");
                    console.log(data.strategy);
                    //updateStrategy(data.strategy);
                } else if (data.status == "error") {
                    console.error("add strategy error: ");
                    console.error(data.message);
                }
            });
        },

        deleteStrategy : function (index, strategy) {
            StrategyService.deleteStrategy(strategy.strategyId, function (data) {
                if (data.status == "success") {
                    console.log("delete strategy ok:");
                    console.log(data.message);
                    $rootScope.strategies.splice(index, 1);
                } else if (data.status == "error") {
                    console.error("delete strategy error: ");
                    console.error(data.message);
                }
            });
        },

        resumeStrategy : function (strategy) {
            StrategyService.operateStrategy(strategy.strategyId, "resume", function (data) {
                if (data.status == "success") {
                    console.log("resume strategy ok:");
                    console.log(strategy);
                } else if (data.status == "error") {
                    console.error("resume strategy error: ");
                    console.error(data.message);
                }
            });
        },

        stopStrategy : function (strategy) {
            StrategyService.operateStrategy(strategy.strategyId, "stop", function (data) {
                if (data.status == "success") {
                    console.log("stop strategy ok:");
                    console.log(strategy);
                } else if (data.status == "error") {
                    console.error("resume strategy error: ");
                    console.error(data.message);
                }
            });
        },

        pauseStrategy : function (strategy) {
            StrategyService.operateStrategy(strategy.strategyId, "pause", function (data) {
                if (data.status == "success") {
                    console.log("pause strategy ok:");
                    console.log(strategy);
                } else if (data.status == "error") {
                    console.error("pause strategy error: ");
                    console.error(data.message);
                }
            });
        },

        updateStrategy : function (strategy) {
            if (strategy.strategyName == "_PHONY_STRATEGY_") {
                $rootScope.phonyStrategyMap[strategy.featureList[0].device.id] = strategy;
                console.log("update phony strategy:");
                console.log(strategy);
            }

            var isNew = true;
            for (var i = 0; i < $rootScope.strategies.length; i++) {
                if ($rootScope.strategies[i].strategyId == strategy.strategyId) {
                    $rootScope.strategies.splice(i, 1, JSON.parse(JSON.stringify(strategy)));
                    isNew = false;
                    console.log("update strategy:");
                    console.log(strategy);
                }
            }
            if (isNew) {
                $rootScope.strategies.push(strategy);
            }

            var stage = parseInt(strategy.stage);
            if (strategy.state == "RUNNING" || strategy.state == "SUSPENDED") {
                if (stage > 0 &&
                    strategy.featureList != null &&
                    strategy.featureList[stage - 1].device.id in $rootScope.busyDeviceMap ) {  // delete previous device since it is not busy now
                    delete $rootScope.busyDeviceMap[strategy.featureList[stage - 1].device.id];
                }
                // add current device to busy map
                if (strategy.featureList != null) {
                    $rootScope.busyDeviceMap[strategy.featureList[stage].device.id] = 1;
                }
            } else if (strategy.state == "DONE") {
                if (stage > 0 &&
                    strategy.featureList != null &&
                    strategy.featureList[stage - 1].device.id in $rootScope.busyDeviceMap) {  // delete previous device since it is not busy now
                    delete $rootScope.busyDeviceMap[strategy.featureList[stage - 1].device.id];
                }
            }
        }
    };
};
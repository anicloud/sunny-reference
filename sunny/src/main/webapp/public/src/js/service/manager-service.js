/**
 * Created by sirhuoshan on 2015/7/1.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.ManagerService = function ($rootScope, StrategyService) {
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

        addStrategy: function (strategy) {
            StrategyService.saveStrategies(strategy, function (data) {
                if (data.status == "success") {
                    console.log("add strategy ok:");
                    //updateStrategy(data.strategy);
                } else if (data.status == "error") {
                    console.error("add strategy error: ");
                    console.error(data.message);
                }
            });
        },

        deleteStrategy: function (index, strategy) {
            $rootScope.strategies.splice(index, 1);
            StrategyService.deleteStrategy(strategy.strategyId, function (data) {
                if (data.status == "success") {
                    console.log("delete strategy ok:");
                    console.log(data.message);
                } else if (data.status == "error") {
                    console.error("delete strategy error: ");
                    console.error(data.message);
                }
            });
        },

        resumeStrategy: function (strategy) {
            StrategyService.operateStrategy(strategy.strategyId, "RESUME", function (data) {
                if (data.status == "success") {
                    console.log("resume strategy ok:");
                } else if (data.status == "error") {
                    console.error("resume strategy error: ");
                    console.error(data.message);
                }
            });
        },

        stopStrategy: function (strategy) {
            StrategyService.operateStrategy(strategy.strategyId, "STOP", function (data) {
                if (data.status == "success") {
                    console.log("stop strategy ok:");
                } else if (data.status == "error") {
                    console.error("resume strategy error: ");
                    console.error(data.message);
                }
            });
        },

        pauseStrategy: function (strategy) {
            StrategyService.operateStrategy(strategy.strategyId, "PAUSE", function (data) {
                if (data.status == "success") {
                    console.log("pause strategy ok:");
                } else if (data.status == "error") {
                    console.error("pause strategy error: ");
                    console.error(data.message);
                }
            });
        },

        updateStrategy: function (strategy) {
            // handle normal strategy
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
                console.log("update strategy:");
                console.log(strategy);
                $rootScope.strategies.push(strategy);
            }


            // handle phony strategy
            if (strategy.strategyName == "_PHONY_STRATEGY_") {
                var deviceId = strategy.featureList[0].device.id;
                if (strategy.state == "RUNNING" || strategy.state == "SUSPENDED") {
                    $rootScope.phonyStrategyMap[deviceId] = strategy;
                } else if (strategy.state == "DONE") {
                    if (deviceId in  $rootScope.phonyStrategyMap) {
                        delete $rootScope.phonyStrategyMap[deviceId];
                    }
                }
                console.log("update phony strategy:");
                console.log(strategy);
            }
        }
    };
};
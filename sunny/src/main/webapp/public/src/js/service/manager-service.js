/**
 * Created by sirhuoshan on 2015/7/1.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.ManagerService = function ($rootScope, StrategyService, Notify) {
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
                    var notifyMsg = "计划已添加";
                    var notifyOpts = {
                        status: 'info',
                        pos: 'bottom-center'
                    };
                    Notify.alert(notifyMsg, notifyOpts);
                    console.log("add strategy ok:");
                    //updateStrategy(data.strategy);
                } else if (data.status == "error") {
                    console.error("add strategy error: ");
                    console.error(data.message);
                }
            });
        },

        deleteStrategy: function (index, strategy) {
            StrategyService.deleteStrategy(strategy.strategyId, function (data) {
                if (data.status == "success") {
                    var deletedStrategy = $rootScope.strategies.splice(index, 1)[0];
                    var notifyMsg = "计划已删除";
                    var notifyOpts = {
                        status: 'info',
                        pos: 'bottom-center'
                    };
                    Notify.alert(notifyMsg, notifyOpts);
                    console.log("delete strategy ok:");
                } else if (data.status == "error") {
                    console.error("delete strategy error: ");
                    console.error(data.message);
                }
            });
        },

        resumeStrategy: function (strategy) {
            StrategyService.operateStrategy(strategy.strategyId, "RESUME", function (data) {
                if (data.status == "success") {
                    var notifyMsg = "计划已恢复";
                    var notifyOpts = {
                        status: 'info',
                        pos: 'bottom-center'
                    };
                    Notify.alert(notifyMsg, notifyOpts);
                    console.log("resume strategy ok:");
                } else if (data.status == "error") {
                    console.error("resume strategy error: ");
                    console.error(data.message);
                }
            });
        },

        stopStrategy: function (strategy) {
            console.log("pre delete:");
            console.log(strategy);
            StrategyService.operateStrategy(strategy.strategyId, "STOP", function (data) {
                if (data.status == "success") {
                    var notifyMsg = "计划已停止";
                    var notifyOpts = {
                        status: 'info',
                        pos: 'bottom-center'
                    };
                    Notify.alert(notifyMsg, notifyOpts);
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
                    var notifyMsg = "计划已暂停";
                    var notifyOpts = {
                        status: 'info',
                        pos: 'bottom-center'
                    };
                    Notify.alert(notifyMsg, notifyOpts);
                    console.log("pause strategy ok:");
                } else if (data.status == "error") {
                    console.error("pause strategy error: ");
                    console.error(data.message);
                }
            });
        },

        updateStrategy: function (obj) {
           if(obj.stage != null) {
               alert(obj);
               // handle normal strategy
               var isNew = true;
               for (var i = 0; i < $rootScope.strategies.length; i++) {
                   if ($rootScope.strategies[i].strategyId == obj.strategyId) {
                       $rootScope.strategies.splice(i, 1, JSON.parse(JSON.stringify(obj)));
                       isNew = false;
                       console.log("update strategy:");
                       console.log(obj);
                   }
               }
               if (isNew) {
                   console.log("update strategy:");
                   console.log(obj);
                   $rootScope.strategies.push(obj);
               }


               // handle phony strategy
               if (obj.strategyName == "_PHONY_STRATEGY_") {
                   var deviceId = obj.featureList[0].device.id;
                   if (obj.state == "RUNNING" || obj.state == "SUSPENDED") {
                       $rootScope.phonyStrategyMap[deviceId] = obj;
                   } else if (obj.state == "DONE") {
                       if (deviceId in $rootScope.phonyStrategyMap) {
                           delete $rootScope.phonyStrategyMap[deviceId];
                       }
                       var notifyMsg = "设备任务完成";
                       var notifyOpts = {
                           status: 'info',
                           pos: 'bottom-center'
                       };
                       Notify.alert(notifyMsg, notifyOpts);
                   }
                   console.log("update phony strategy:");
                   console.log(obj);
               }
           } else {
               alert(obj);
               var isNew = true;
               for (var i = 0; i < $rootScope.devices.length; i++) {
                   if ($rootScope.devices[i].id == obj.id) {
                       $rootScope.devices.splice(i, 1, JSON.parse(JSON.stringify(obj)));
                       isNew = false;
                       console.log("update device:");
                       console.log(obj);
                   }
               }
               if (isNew) {
                   console.log("update device:");
                   console.log(obj);
                   $rootScope.devices.push(obj);
                   console.log(obj);
               }
           }
        }
    };
}
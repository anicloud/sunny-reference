/**
 * Created by sirhuoshan on 2015/7/1.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.ManagerService = function ($rootScope, StrategyService, Notify,DeviceService) {
    var jsonClone = function (obj) {
        return JSON.parse(JSON.stringify(obj));
    };

    return {
        addFeature: function (feature, strategy) {
         //   strategy.featureList.push(jsonClone(feature));
            strategy.featureList.push(feature);
        },

        deleteFeature: function (index, strategy) {
            strategy.featureList.splice(index, 1);
        },

        addStrategy: function (strategy) {
            return StrategyService.saveStrategies(strategy).then(function (data) {
                console.log(data);
                if (data.status == "success") {
                    var notifyMsg = "计划已添加";
                    var notifyOpts = {
                        status: 'info',
                        pos: 'bottom-center'
                    };
                    Notify.alert(notifyMsg, notifyOpts);
                    console.log("add strategy ok:");
                    // updateStrategy(data.strategy);
                } else if (data.status == "error") {
                    console.error("add strategy error: ");
                    console.error(data.message);
                }
                return data;
            });
        },

        deleteStrategy: function (index, strategy) {
            StrategyService.deleteStrategy(strategy.strategyId, function (data) {
                if (data.status == "success") {
                    var strategyObj=$rootScope.queryObjectByPropertyValue($rootScope.strategies,'strategyId',strategy.strategyId);
                    if(strategyObj)  $rootScope.strategies.splice(strategyObj[0], 1);
                    else console.log('delete fail');
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

        // updateStrategyDevice: function (obj) {
        //    // alert(JSON.stringify(obj));
        //    if(obj.kind == 0) {//device update
        //        var device = obj.instance;
        //        var isNew = true;
        //        for (var i = 0; i < $rootScope.devices.length; i++) {
        //            if ($rootScope.devices[i].id == device.id) {
        //                $rootScope.devices.splice(i, 1, JSON.parse(JSON.stringify(device)));
        //                isNew = false;
        //                console.log("update device:");
        //                console.log(device);
        //            }
        //        }
        //        if (isNew) {
        //            console.log("update device:");
        //            console.log(device);
        //            $rootScope.devices.push(device);
        //            console.log(device);
        //        }
        //        //location.reload();
        //    } else if(obj.kind == 1){//strategy update
        //        // handle normal strategy
        //        var isNew = true;
        //        var strategy = obj.instance;
        //        for (var i = 0; i < $rootScope.strategies.length; i++) {
        //            if ($rootScope.strategies[i].strategyId == strategy.strategyId) {
        //                $rootScope.strategies.splice(i, 1, JSON.parse(JSON.stringify(strategy)));
        //                isNew = false;
        //                console.log("update strategy:");
        //                console.log(strategy);
        //            }
        //        }
        //        if (isNew) {
        //            console.log("update strategy:");
        //            console.log(strategy);
        //            $rootScope.strategies.push(strategy);
        //        }
        //
        //        // handle phony strategy
        //        if (strategy.strategyName == "_PHONY_STRATEGY_") {
        //            var deviceId = strategy.featureList[0].device.id;
        //            if (strategy.state == "RUNNING" || obj.state == "SUSPENDED") {
        //                $rootScope.phonyStrategyMap[deviceId] = strategy;
        //            } else if (strategy.state == "DONE") {
        //                if (deviceId in $rootScope.phonyStrategyMap) {
        //                    delete $rootScope.phonyStrategyMap[deviceId];
        //                }
        //                var notifyMsg = "设备任务完成";
        //                var notifyOpts = {
        //                    status: 'info',
        //                    pos: 'bottom-center'
        //                };
        //                Notify.alert(notifyMsg, notifyOpts);
        //            }
        //            console.log("update phony strategy:");
        //            console.log(strategy);
        //        }
        //    }
        // },

        updateFrontInfo:function (obj) {
            switch (obj.kind) {
                case 0:
                    //deviceinfo update
                    var msgDevice = obj.instance;
                    var updatingDevice=$rootScope.devices.filter(function (device,index) {
                        return msgDevice.id==device.id;
                    })[0];
                    Object.keys(updatingDevice).forEach(function (key) {
                        if(key==='initParam') msgDevice['initParam']=JSON.parse(msgDevice['initParam']);
                       updatingDevice[key]=msgDevice[key];
                    });
                    console.log("update device:");
                    console.log(updatingDevice);
                  //  location.reload(); ///??????
                  //  if(angular.element("#device-list").scope())angular.element("#device-list").scope().$apply();
                  //  $rootScope.$apply();
                    break;
                case 1:
                    //strategy update
                    // handle normal strategy
                    var isNew = true;
                    var strategy = obj.instance;
                    for (var i = 0; i < $rootScope.strategies.length; i++) {
                        if ($rootScope.strategies[i].strategyId == strategy.strategyId) {
                            $rootScope.strategies.splice(i, 1, JSON.parse(JSON.stringify(strategy)));
                            isNew = false;
                            console.log("update strategy:");
                            console.log(strategy);
                       //     if(angular.element("#strategy-list").scope())angular.element("#strategy-list").scope().$apply();
                            console.log('apply delete');
                        }
                    }
                    if (isNew) {
                        console.log("update strategy:");
                        console.log(strategy);
                        $rootScope.strategies.push(strategy);
                    //    if(angular.element("#strategy-list").scope())angular.element("#strategy-list").scope().$apply();
                        console.log('apply');
                    }

                    // handle phony strategy
                    if (strategy.strategyName == "_PHONY_STRATEGY_") {
                        var deviceId = strategy.featureList[0].device.id;
                        if (strategy.state == "RUNNING" || obj.state == "SUSPENDED") {
                            $rootScope.phonyStrategyMap[deviceId] = strategy;
                        } else if (strategy.state == "DONE") {
                            if (deviceId in $rootScope.phonyStrategyMap) {
                                var clientStrategy=$rootScope.phonyStrategyMap[deviceId];
                                var argList=clientStrategy.featureList[0].featureArgValueDtoList;
                                var clientDevice=$rootScope.devices.filter(function (item) {
                                    return item.id===deviceId
                                })[0];
                                argList.forEach(function (argObj) {
                                    clientDevice.initParam[argObj.argName]=argObj.value;
                                });
                                console.log(clientDevice);
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
                        console.log(strategy);
                    }
                    //location.reload();
                    break;
                case 2: //deviceAdd
                    var msgDevices = obj.instance;
                    msgDevices.map(function(item,index){
                        item.initParam=JSON.parse(item.initParam);
                        $rootScope.devices.push(item);
                    });
                    DeviceService.getDeviceFeatures().then(function (data) {
                        for (var i = 0; i < data.length; i++) {
                            var key = data[i].deviceFormDto.id;
                            var value = data[i].deviceFeatureFormDtoList;
                            $rootScope.features[key] = value;
                        }
                        notifyMsg = "设备添加成功";
                        notifyOpts = {
                            status: 'info',
                            pos: 'bottom-center'
                        };
                        Notify.alert(notifyMsg, notifyOpts);
                    });
                    break;
                case 4: //deviceShare
                    var msgDevices = obj.instance;
                    msgDevices.map(function(item,index){
                        item.initParam=JSON.parse(item.initParam);
                        $rootScope.devices.push(item);
                    });
                    DeviceService.getDeviceFeatures().then(function (data) {
                        for (var i = 0; i < data.length; i++) {
                            var key = data[i].deviceFormDto.id;
                            var value = data[i].deviceFeatureFormDtoList;
                            $rootScope.features[key] = value;
                        }
                        notifyMsg = "有新设备共享给您";
                        notifyOpts = {
                            status: 'info',
                            pos: 'bottom-center'
                        };
                        Notify.alert(notifyMsg, notifyOpts);
                    });
                    break;
                case 3:
                    var msgIdAry=obj.instance;
                    msgIdAry.map(function(item,index){
                        var deviceObj=$rootScope.queryObjectByPropertyValue($rootScope.devices,'id',item);
                        if(deviceObj) $rootScope.devices.splice(deviceObj[0]);
                        delete $rootScope.features[item];
                    });
                    notifyMsg = "设备移除成功";
                    notifyOpts = {
                        status: 'info',
                        pos: 'bottom-center'
                    };
                    Notify.alert(notifyMsg, notifyOpts);
                    break;
                case 5:
                    var msgIdAry=obj.instance;
                    msgIdAry.map(function(item,index){
                        var deviceObj=$rootScope.queryObjectByPropertyValue($rootScope.devices,'id',item);
                        if(deviceObj) $rootScope.devices.splice(deviceObj[0]);
                        delete $rootScope.features[item];
                    });
                    notifyMsg = "有设备被取消共享";
                    notifyOpts = {
                        status: 'info',
                        pos: 'bottom-center'
                    };
                    Notify.alert(notifyMsg, notifyOpts);
                    break;
            }
            $rootScope.$apply();
        }
    };
}
/**
 * Created by zhangdongming on 16-11-22.
 */
var strategy1 = {
    "kind": 1,
    "instance": {
        "strategyId": "f19422674c3b4d09badde1b0ae206049",
        "strategyName": "11111",
        "strategyDescription": null,
        "state": "RUNNING",
        "stage": 0,
        "action": "START",
        "featureList": [{
            "featureInstanceId": "",
            "device": {
                "id": "6827881482365236091:-1",
                "name": "公司控制中心",
                "deviceState": "CONNECTED",
                "deviceType": "Light",
                "deviceGroup": "default",
                "initParam": null
            },
            "deviceFeature": {
                "featureId": "1",
                "featureName": "Power On",
                "description": "light power on feature.",
                "argDtoList": [
                    {
                        "name": "brightnessLux",
                        "dataType": "FLOAT",
                        "screenName": "Bright Lux"
                    }
                ]
            },
            "featureArgValueDtoList": [],
            "triggerDtoList": [
                {
                    "triggerType": "TIMER",
                    "value": "{\"startTime\":\"2016-11-26T14:44:41.169Z\",\"repeatInterval\":0,\"repeatCount\":0}"
                }],
            "isScheduleNow": false
        }]
    }
};
var strategy2 = {
    "kind": 1,
    "instance": {
        "strategyId": "f19422674c3b4d09badde1b0ae206049",
        "strategyName": "11111",
        "strategyDescription": null,
        "state": "DONE",
        "stage": 0,
        "action": "START",
        "featureList": [{
            "featureInstanceId": "",
            "device": {
                "id": "6827881482365236091:-1",
                "name": "公司控制中心",
                "deviceState": "CONNECTED",
                "deviceType": "Light",
                "deviceGroup": "default",
                "initParam": null
            },
            "deviceFeature": {
                "featureId": "1",
                "featureName": "Power On",
                "description": "light power on feature.",
                "argDtoList": [
                    {
                        "name": "brightnessLux",
                        "dataType": "FLOAT",
                        "screenName": "Bright Lux"
                    }
                ]
            },
            "featureArgValueDtoList": [],
            "triggerDtoList": [
                {
                    "triggerType": "TIMER",
                    "value": "{\"startTime\":\"2016-11-26T14:44:41.169Z\",\"repeatInterval\":0,\"repeatCount\":0}"
                }],
            "isScheduleNow": false
        }]
    }
};
var strategy3 = {
    "kind": 1,
    "instance": {
        "strategyId": "fbffe8bb8c3744aaa132f313c99cdaa7",
        "strategyName": "_PHONY_STRATEGY_",
        "strategyDescription": null,
        "state": "RUNNING",
        "stage": 0,
        "action": "START",
        "featureList": [{
            "featureInstanceId": "",
            "device": {
                "id": "6827881482365236091:-1",
                "name": "公司控制中心",
                "deviceState": "CONNECTED",
                "deviceType": "Light",
                "deviceGroup": "default",
                "initParam": null
            },
            "deviceFeature": {
                "featureId": "1",
                "featureName": "Power On",
                "description": "light power on feature.",
                "argDtoList": [{"name": "brightnessLux", "dataType": "FLOAT", "screenName": "Bright Lux"}]
            },
            "featureArgValueDtoList": [{"argName": "brightnessLux", "value": "70.5"}],
            "triggerDtoList": [],
            "isScheduleNow": true
        }]
    }
};
var strategy4 = {
    "kind": 1,
    "instance": {
        "strategyId": "fbffe8bb8c3744aaa132f313c99cdaa7",
        "strategyName": "11111",
        "strategyDescription": null,
        "state": "RUNNING",
        "stage": 0,
        "action": "START",
        "isRepeat":false,
        "isScheduleNow":true,
        "repeatWeek":[],
        "name":'test1',
        "startTime":1481513857788,
        "featureList": [{
            "featureInstanceId": "",
            "intervalTime": 1200000,
            "device": {
                "id": "6827881482365236091:-1",
                "name": "公司控制中心",
                "deviceState": "CONNECTED",
                "deviceType": "Light",
                "deviceGroup": "default",
                "initParam": null
            },
            "deviceFeature": {
                "featureId": "1",
                "featureName": "Power On",
                "description": "light power on feature.",
                "argDtoList": [{"name": "brightnessLux", "dataType": "FLOAT", "screenName": "Bright Lux"}]
            },
            "featureArgValueDtoList": [{"argName": "brightnessLux", "value": "70.5"}],
            "triggerDtoList": [{
                "triggerType":"TIMER",
                "triggerValue":'{"startTime":"2016-12-12T07:56:58.459Z","repeatInterval":0,"repeatCount":0}'
            }],
            "isScheduleNow": false
        },
            {
                "featureInstanceId": "",
                "intervalTime": 1500000,
                "device": {
                    "id": "6827881482365236091:-1",
                    "name": "公司控制中心",
                    "deviceState": "CONNECTED",
                    "deviceType": "Light",
                    "deviceGroup": "default",
                    "initParam": null
                },
                "deviceFeature": {
                    "featureId": "1",
                    "featureName": "Power On",
                    "description": "light power on feature.",
                    "argDtoList": [{"name": "brightnessLux", "dataType": "FLOAT", "screenName": "Bright Lux"}]
                },
                "featureArgValueDtoList": [{"argName": "brightnessLux", "value": "70.5"}],
                "triggerDtoList": [],
                "isScheduleNow": false
            }]
    }
};
var strategyDeviceParamUpdate={
    "kind":0,
    "instance":{
        id: "6827881482365236091:-1",
        name: "公司控制中心",
        deviceState: "CONNECTED",
        deviceType: "Light",
        deviceGroup: "default",
        logoUrl:'flaticon-lamp',
        initParam: '{"brightnessLux":"40.1","humidity":"78%","temperature":"25°C"}'
    }
};
module.exports = {
    strategyNew: strategy1,
    strategyFinish: strategy2,
    strategy3: strategy3,
    strategyNew: strategy4,
    strategyDeviceParamUpdate:strategyDeviceParamUpdate
};
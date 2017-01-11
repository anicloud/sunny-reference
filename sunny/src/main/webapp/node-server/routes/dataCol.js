/**
 * Created by zhangdongming on 16-11-16.
 */
var dataCol = function () {
    var devices = [
        {
            id: "6827881482365236091:-1",
            name: "公司控制中心",
            deviceState: "CONNECTED",
            deviceType: "Light",
            deviceGroup: "default",
            logoUrl:'flaticon-lamp',
            initParam: '{"brightnessLux":null,"humidity":780000000000,"temperature":25}'
        },
        {
            id: "4281056279305915936:-1",
            name: "haier_router",
            deviceState: "DISCONNECTED",
            deviceType: "Light",
            deviceGroup: "default",
            logoUrl:'flaticon-router',
            initParam: '{"brightnessLux":22.2,"humidity":77,"temperature":24}'
        },
        {
            id: "4281056279305915936:14402508",
            name: "light2",
            deviceState: "CONNECTED",
            deviceType: "Light2",
            deviceGroup: "default",
            logoUrl:'',
            initParam: '{"brightnessLux":33.3,"humidity":76,"temperature":3}'
        }
    ];
    var features = [
        {
                deviceFormDto:
                {
                    id: "6827881482365236091:-1",
                    name: "公司控制中心",
                    deviceState: "CONNECTED",
                    deviceType: "Light",
                    deviceGroup: "default",
                    initParam: "{'brightnessLux':'33.3'}",
                },
                deviceFeatureFormDtoList: [
                {
                    featureId: "1",
                    featureName: "Power On",
                    description: "light power on feature.",
                    privilegeType: "EXECUTE",
                    argDtoList: [
                        {
                            name: "brightnessLux",
                            dataType: "FLOAT",
                            screenName: "Bright Lux"
                        }
                    ]
                },
                {
                    featureId: "2",
                    featureName: "Power Off",
                    description: "light power off feature.",
                    privilegeType: "EXECUTE",
                    argDtoList: []
                },
                {
                    featureId:"3",
                    featureName:"temperature",
                    description:"temperature Sensor.",
                    privilegeType: "READ",
                    argDtoList: [
                        {
                            "name": "temperature",
                            "dataType": "FLOAT",
                            "screenName": "temperature"
                        }
                    ]
                },
                {
                    featureId:"4",
                    featureName:"humidity",
                    description:"humidity Sensor.",
                    privilegeType: "READ",
                    argDtoList: [
                        {
                            "name": "humidity",
                            "dataType": "FLOAT",
                            "screenName": "humidity"
                        }
                    ]
                }
            ]
        },
        {
            deviceFormDto: {
                id: "4281056279305915936:-1",
                name: "haier_router",
                deviceState: "CONNECTED",
                deviceType: "Light",
                deviceGroup: "default",
                initParam: null
            },
            deviceFeatureFormDtoList: [
                {
                    featureId: "1",
                    featureName: "Power On",
                    description: "light power on feature.",
                    argDtoList: [
                        {
                            name: "brightnessLux",
                            dataType: "FLOAT",
                            screenName: "Bright Lux"
                        }
                    ]
                }
            ]
        },
        {
            deviceFormDto: {
                id: "4281056279305915936:14402508",
                name: "light2",
                deviceState: "CONNECTED",
                deviceType: "Light",
                deviceGroup: "default",
                initParam: null
            },
            deviceFeatureFormDtoList: [
                {
                    featureId: "1",
                    featureName: "Power On",
                    description: "light power on feature.",
                    argDtoList: [
                        {
                            name: "brightnessLux",
                            dataType: "FLOAT",
                            screenName: "Bright Lux"
                        }
                    ]
                },
                {
                    featureId: "2",
                    featureName: "Power Off",
                    description: "light power off feature.",
                    argDtoList: []
                }
            ]
        },
        {
            deviceFormDto:
            {
                id: "4281056279305915936:489159965",
                name: "light1",
                deviceState: "CONNECTED",
                deviceType: "Light",
                deviceGroup: "default",
                initParam: null
            },
            deviceFeatureFormDtoList: [
                {
                    featureId: "1",
                    featureName: "Power On",
                    description: "light power on feature.",
                    argDtoList: [{"name": "brightnessLux", "dataType": "FLOAT", "screenName": "Bright Lux"}]
                },
                {
                    featureId: "2",
                    featureName: "Power Off",
                    description: "light power off feature.",
                    argDtoList: []
                }
            ]
        },
        {
            deviceFormDto:
            {
                id: "4281056279305915936:982231566",
                name: "light1",
                deviceState: "CONNECTED",
                deviceType: "Light",
                deviceGroup: "default",
                initParam: null
            },
            deviceFeatureFormDtoList: [
                {
                    featureId: "1",
                    featureName: "Power On",
                    description: "light power on feature.",
                    argDtoList: [
                        {
                            name: "brightnessLux",
                            dataType: "FLOAT", screenName: "Bright Lux"
                        }
                    ]
                },
                {
                    featureId: "2",
                    featureName: "Power Off",
                    description: "light power off feature.",
                    argDtoList: []
                }
            ]
        },
        {
            deviceFormDto: {
                id: "4281056279305915936:2094239718",
                name: "light2",
                deviceState: "CONNECTED",
                deviceType: "Light",
                deviceGroup: "default",
                initParam: null
            },
            deviceFeatureFormDtoList: [
                {
                    featureId: "1",
                    featureName: "Power On",
                    description: "light power on feature.",
                    argDtoList: [
                        {
                            name: "brightnessLux",
                            dataType: "FLOAT",
                            screenName: "Bright Lux"
                        }
                    ]
                },
                {
                    featureId: "2",
                    featureName: "Power Off",
                    description: "light power off feature.",
                    argDtoList: []
                }
            ]
        }
    ];
var featuresAdd=[
    {
        deviceFormDto:
        {
            id: "6827881482365236092:-1",
            name: "公司控制中心",
            deviceState: "CONNECTED",
            deviceType: "Light",
            deviceGroup: "default",
        },
        deviceFeatureFormDtoList: [
            {
                featureId: "1",
                featureName: "Power On",
                description: "light power on feature.",
                privilegeType: "EXECUTE",
                argDtoList: [
                    {
                        name: "brightnessLux",
                        dataType: "FLOAT",
                        screenName: "Bright Lux"
                    }
                ]
            },
            {
                featureId: "2",
                featureName: "Power Off",
                description: "light power off feature.",
                privilegeType: "EXECUTE",
                argDtoList: []
            },
            {
                featureId:"3",
                featureName:"temperature",
                description:"temperature Sensor.",
                privilegeType: "READ",
                argDtoList: [
                    {
                        "name": "temperature",
                        "dataType": "FLOAT",
                        "screenName": "temperature"
                    }
                ]
            },
            {
                featureId:"4",
                featureName:"humidity",
                description:"humidity Sensor.",
                privilegeType: "READ",
                argDtoList: [
                    {
                        "name": "humidity",
                        "dataType": "FLOAT",
                        "screenName": "humidity"
                    }
                ]
            }
        ]
    },
    {
        deviceFormDto: {
            id: "4281056279305915937:-1",
            name: "haier_router",
            deviceState: "CONNECTED",
            deviceType: "Light",
            deviceGroup: "default",
        },
        deviceFeatureFormDtoList: [
            {
                featureId: "1",
                featureName: "Power On",
                description: "light power on feature.",
                argDtoList: [
                    {
                        name: "brightnessLux",
                        dataType: "FLOAT",
                        screenName: "Bright Lux"
                    }
                ]
            }
        ]
    },
]
    var strategies=[];
    var triggers=["TIMER"];
    return {
        devices: devices,
        features:features,
        strategies:strategies,
        triggers:triggers,
        featuresRefresh:features.concat(featuresAdd)
    }
};

module.exports = dataCol();
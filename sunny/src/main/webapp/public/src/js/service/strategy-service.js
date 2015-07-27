/**
 * Created by sirhuoshan on 2015/7/1.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.StrategyService = function($http,$cookies){
    return{
        getStrategies:function(callback){
            var usercookie = JSON.parse(JSON.parse( $cookies['sunny_user']));
            var hashUserId = usercookie.hashUserId;
            $http({
                method:'GET',
                url: 'strategies',
                params: {hashUserId:hashUserId}
            }).success(function(data){
                callback(data);
            }).error(function(data){
                console.log('get strategies failures');
            })
        },
        saveStrategies:function(callback){
        var usercookie = JSON.parse(JSON.parse( $cookies['sunny_user']));
        var hashUserId = usercookie.hashUserId;
        $http({
            method:'POST',
            url: 'strategies',
            params: {hashUserId:hashUserId,strategies:strategies}
        }).success(function(data){
            callback(data);
        }).error(function(data){
            console.log('save strategies failures');
        })
    },
    operateStrategy:function(strategyId, action){
        $http({
            method:'GET',
            url: 'operateStrategy',
            params: {strategyId:strategyId,action:action}
        }).success(function(data){
            if(data.status == 'success'){
                console.log('operate strategy success');
            }else{
                console.log('operate strategy failed');
            }
        }).error(function(data){
            console.log('operate strategy failed');
        })
    }
}
}
/**
 * Created by sirhuoshan on 2015/7/1.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.StrategyService = function($http, $cookies){
    return{
        getStrategies:function(callback){
            //var user = $cookies['sunny_user'];
            var user = $.cookie('sunny_user');
            if (user != undefined) {
                var usercookie = JSON.parse(user);
                var hashUserId = usercookie.hashUserId;
                $http({
                    method:'GET',
                    url: 'strategies',
                    params: {hashUserId:hashUserId}
                }).success(function(data){
                    callback(data);
                }).error(function(data){
                    console.log('get strategies failures');
                });
            } else {
                console.log("cookie user not exist.");
            }

        },
        saveStrategies:function(strategyInstance,callback){
            var user = $.cookie('sunny_user');
            if (user != undefined) {
                var usercookie = JSON.parse(user);
                var hashUserId = usercookie.hashUserId;
                $http({
                    method:'POST',
                    url: 'strategy',
                    params: {hashUserId:hashUserId,strategyInstance:strategyInstance}
                }).success(function(data){
                    callback(data);
                }).error(function(data){
                    console.log('save strategies failures');
                });
            }
    },
    deleteStrategy:function(strategyId,callback){
        var user = $.cookie('sunny_user');
        if (user != null) {
            var usercookie = JSON.parse(user);
            var hashUserId = usercookie.hashUserId;
            $http({
                method:'GET',
                url: 'strategy',
                params: {hashUserId:hashUserId, strategyId:strategyId}
            }).success(function(data){
                if(data.status == 'success'){
                    callback(data);
                    console.log('delete strategy success');
                }else{
                    console.log('delete strategy failed');
                }
            }).error(function(data){
                console.log('delete strategy failed');
            });
        }
    },
    operateStrategy:function(strategyId, action, callback){
        $http({
            method:'GET',
            url: 'operateStrategy',
            params: {strategyId:strategyId,action:action}
        }).success(function(data){
            if(data.status == 'success'){
                console.log('operate strategy success');
                callback(data);
            }else{
                console.log('operate strategy failed');
            }
        }).error(function(data){
            console.log('operate strategy failed');
        })
    }
}
}
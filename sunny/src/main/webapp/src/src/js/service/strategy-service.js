/**
 * Created by sirhuoshan on 2015/7/1.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.StrategyService = function($http, $cookies){
    return{
        getStrategies:function(){
            //var user = $cookies['sunny_user'];
            var user = $.cookie('sunny_user');
            if (user != undefined) {
                var usercookie = JSON.parse(user);
                var hashUserId = usercookie.hashUserId;
               return $http({
                    method:'GET',
                    url: 'strategies',
                    params: {hashUserId:hashUserId}
                }).then(function(data){
                    return data.data;
                },function(data){
                    console.log('get strategies failures');
                });
            } else {
                console.log("cookie user not exist.");
            }

        },
        saveStrategies:function(strategyInstance){
                var user = $.cookie('sunny_user');
                if (user != undefined) {
                    var usercookie = JSON.parse(user);
                    var hashUserId = usercookie.hashUserId;
                  return  $http({
                        method:'POST',
                        url: 'strategy',
                        params: {hashUserId:hashUserId,strategyInstance:strategyInstance}
                    }).then(function (res) {
                        return res.data;
                      console.log('save strategies successful')
                      },function(data){
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
            }).then(function(data){
                if(data.data.status == 'success'){
                    callback(data.data);
                    console.log('delete strategy success');
                }else{
                    console.log('delete strategy failed');
                }
            },function(data){
                console.log('delete strategy failed');
            });
        }
    },
    operateStrategy:function(strategyId, action, callback){
        $http({
            method:'GET',
            url: 'operateStrategy',
            params: {strategyId:strategyId,action:action}
        }).then(function(data){
            if(data.status == 'success'){
                console.log('operate strategy success');
                callback(data);
            }else{
                console.log('operate strategy failed');
            }
        },function(data){
            console.log('operate strategy failed');
        })
    }
}
}
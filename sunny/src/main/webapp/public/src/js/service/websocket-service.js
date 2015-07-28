/**
 * Created by sirhuoshan on 2015/7/15.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.WebSocketService=function(){
    var sock  = new WebSocket("ws://localhost:8080/sunny/socket/strategy");
    var opened = false;
    var _this = this;

    // Open the connection
    sock.onopen = function() {
        opened = true;
    };

    // On connection close
    sock.onclose = function() {
        console.log('close');
    };

    sock.onmessage = function(e) {
        // Get the content
        var content = e.data;
        var strategyJson = JSON.parse(content);
        var strategyInstance = new anicloud.sunny.model.StrategyInstance(
            strategyJson.strategyId,
            strategyJson.strategyName,
            strategyJson.strategyInstance.state,
            strategyJson.strategyInstance.stage,
            strategyJson.deviceFeatureInstanceList
        );
        console.log(strategyInstance);
    };
    return {
        sendMessage:function(message) {
            // The object to send
            /*var send = {
                hashUserId: hashUserId,
                message: message
            };*/

            // Send it now
            //sock.send(JSON.stringify(message));
            sock.send(message);
        }
    }
}

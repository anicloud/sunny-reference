/**
 * Created by sirhuoshan on 2015/7/15.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.WebSocketService=function(){
    console.log("test");
    var sock  = new WebSocket("ws://localhost:8080/sunny/socket/strategy");
    var opened = false;
    var _this = this;

    // Open the connection
    sock.onopen = function() {
        console.log('open');
        opened = true;
        sock.send("test front send message to background");
    };

    // On connection close
    sock.onclose = function() {
        console.log('close');
    };

    sock.onmessage = function(e) {
        // Get the content
        var content = e.data;
        console.log(content);
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

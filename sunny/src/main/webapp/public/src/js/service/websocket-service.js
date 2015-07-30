/**
 * Created by sirhuoshan on 2015/7/15.
 */
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.service = anicloud.sunny.service || {};

anicloud.sunny.service.WebSocketService = function () {
    var _this = this;
    var sock = null;
    var isOpen = false;
    return {
        openSocket: function (url, onOpen, onClose, onError, onMessage) {
            if (sock && isOpen) {
                sock.close();
            }

            sock = new WebSocket(url);

            sock.onopen = function () {
                isOpen = true;
                if (onOpen != null) {
                    onOpen();
                }
            };

            sock.onclose = function (event) {
                if (onClose != null) {
                    onClose(event.data);
                }
            };

            sock.onerror = function (event) {
                if (onError != null) {
                    onError(event.data);
                }
            }

            sock.onmessage = function (event) {
                // Get the content
                var content = event.data;
                var strategyJson = JSON.parse(content);
                var strategyInstance = new anicloud.sunny.model.StrategyInstance(
                    strategyJson.strategyId,
                    strategyJson.strategyName,
                    strategyJson.strategyInstance.state,
                    strategyJson.strategyInstance.stage,
                    strategyJson.deviceFeatureInstanceList
                );
                if (onMessage != null) {
                    onMessage(strategyInstance);
                }
            };
        },
        closeSocket: function () {
            if (isOpen) {
                sock.close();
            }
        },
        sendMessage: function (message) {
            if (isOpen) {
                sock.send(message);
            }
        }
    }
}

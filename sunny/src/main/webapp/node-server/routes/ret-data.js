/**
 * Created by zhangdongming on 16-9-6.
 */
var RetData = function (status, message, data) {
    var self = this;
    this.status = status;
    this.message = message;
    this.data = data;
};
module.exports=RetData;
/**
 * Created by zhangdongming on 16-11-15.
 */
'use strict';
var server = require('http').createServer()
    , url = require('url')
    , WebSocketServer = require('ws').Server
    , wss = new WebSocketServer({ server: server })
    , express = require('express')
    , app = express()
    , port = 9000;
var mime=require('mime');
var path=require('path');
var formidable=require('formidable');
var queryString = require('querystring');
var bodyParser=require('body-parser');
var wsData=require('./websocketData');
//router
var routes = require('./routes/index');
var features=require('./routes/features');
var strategies=require('./routes/strategies');
var strategy=require('./routes/strategy');
var devices = require('./routes/devices');
var triggers=require('./routes/triggers');
var operateStrategy=require('./routes/operateStrategy');
// var login=require('./routes/login');

//var socketData=require('./socketData');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.get('/',function (req,res) {
    res.cookie('sunny_user','{"hashUserId":"testCookie"}');
    res.sendFile(path.join(__dirname, '../dev/index.html'));
});
app.use(express.static(path.join(__dirname, '../dev')));
app.use('/',routes);
app.use('/featuresOfUser',features);
app.use('/strategies',strategies);
app.use('/devices',devices);
app.use('/triggers',triggers);
app.use('/strategy',strategy);
app.use('/operateStrategy',operateStrategy);
// app.use('/app',application);
wss.on('connection', function connection(ws) {
    var location = url.parse(ws.upgradeReq.url, true);
    // you might use location.query.access_token to authenticate or share sessions
    // or ws.upgradeReq.headers.cookie (see http://stackoverflow.com/a/16395220/151312)
    ws.on('message', function incoming(message) {
    });
    //setTimeout(function () {
    //    ws.send(JSON.stringify(wsData.addDevice));
    //},2000);
    // setTimeout(function () {
    //     ws.send(JSON.stringify(wsData.deleteDevice));
    // },5000);
     setTimeout(function () {
         ws.send(JSON.stringify(wsData.strategyNew));
     },5000);
    // setTimeout(function () {
    //     ws.send(JSON.stringify(wsData.strategyDeviceParamUpdate));
    // },5000);
});
server.on('request', app);
server.listen(port, function () { console.log('Listening on ' + server.address().port) });
// app.listen(3000);

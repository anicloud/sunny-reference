/**
 * Created by zhangdongming on 16-11-15.
 */
var express = require('express');
var path=require('path');
//创建一个路由容器
var router = express.Router();

router.get('/', function (req, res, next) {
    console.log('in entry');
    console.log(path.join(__dirname,  '../../dev/entry.html'));
    res.sendFile(path.join(__dirname, '../../public/index.html'));
});

module.exports = router;
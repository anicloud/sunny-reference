/**
 * Created by zhangdongming on 16-11-16.
 */
var express=require('express');
var dataCol=require('./dataCol');
var RetData=require('./ret-data');
var router=express.Router();

router.post('/',function (req,res) {
    res.send(new RetData('success','',null));
});
module.exports=router;
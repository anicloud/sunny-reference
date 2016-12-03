/**
 * Created by zhangdongming on 16-11-16.
 */
var express=require('express');
var dataCol=require('./dataCol');
var router=express.Router();

router.get('/',function (req,res) {
    res.send(dataCol.features);
});
module.exports=router;
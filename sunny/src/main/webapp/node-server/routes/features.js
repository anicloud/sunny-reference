/**
 * Created by zhangdongming on 16-11-16.
 */
var express=require('express');
var dataCol=require('./dataCol');
var router=express.Router();

router.get('/',function (req,res) {
    //setTimeout(function(){
    //    res.send(dataCol.features);
    //},5000);
    res.send(dataCol.features);
});
router.get('/refresh',function(req,res){
   res.send(dataCol.featuresRefresh)
});
module.exports=router;
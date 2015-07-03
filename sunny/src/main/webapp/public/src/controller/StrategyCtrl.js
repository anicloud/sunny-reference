/**
 * Created by libiya on 15/6/27.
 */

var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.StrategyCtrl = function ($scope) {
    $scope.isCollapsed = true;
    $scope.num = 1;

    $scope.addStrategy = function() {
        var strategyList = $('#strategyList');
        var strategyDiv = $('<div class="col-sm-3" style="border: 2px; color: red; background-color: #204d74; padding: 10px;">xxxx</div>');
        strategyDiv.text("button" + $scope.num);
        strategyList.append(strategyDiv);
        $scope.num++;

        //var c = document.getElementById("myCanvas");
        //var ctx = c.getContext("2d");
        //ctx.beginPath();
        //ctx.moveTo(0, 0);
        //ctx.lineTo(300, 150);
        //ctx.stroke();
    }

}
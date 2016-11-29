
var anicloud = anicloud || {};
anicloud.sunny = anicloud.sunny || {};
anicloud.sunny.controller = anicloud.sunny.controller || {};

anicloud.sunny.controller.RangeSliderCtrl = function ($rootScope, $scope) {

    function getDomElement(element, all) {
        if (all && all !== undefined) {
            return document.querySelectorAll(element);
        } else {
            return document.querySelector(element);
        }
    };

    function ngElement(element) {
        return angular.element(element);
    };

    var fromRange = getDomElement('.fromRange'),
        toRange = getDomElement('.toRange'),
        rngCont = getDomElement('.rangeContainer'),
        rangeLeft = getDomElement('.rangeLeft'),
        rangeRight = getDomElement('.rangeRight'),
        spinLeft = getDomElement('.spinLeft'),
        spinRight = getDomElement('.spinRight');

    $scope.minRange = +fromRange.dataset.min;
    $scope.maxRange = +toRange.dataset.max;

    $scope.fromRange = $scope.minRange;
    $scope.toRange = $scope.maxRange;

    function setStartPositionFromRange() {
        var leftStartPosition = Math.round(($scope.fromRange * 100) / $scope.maxRange);
        rangeLeft.style.width = leftStartPosition + '%';
        spinRight.style.zIndex = '0';
        spinLeft.style.zIndex = '1';
    };

    function setStartPositionToRange() {
        var rightStartPosition = Math.round(100 - (($scope.toRange * 100) / $scope.maxRange));
        rangeRight.style.width = rightStartPosition + '%';
        spinRight.style.zIndex = '1';
        spinLeft.style.zIndex = '0';
    };

    $scope.minus = function (direction) {
        if (direction == 'from' && $scope.fromRange > $scope.minRange) {
            $scope.fromRange--;
            setStartPositionFromRange();
        }
        if (direction == 'to' && $scope.fromRange != $scope.toRange) {
            $scope.toRange--;
            setStartPositionToRange();
        }
    };

    $scope.plus = function (direction) {
        if (direction == 'from' && $scope.toRange != $scope.fromRange) {
            $scope.fromRange++;
            setStartPositionFromRange();
        }
        if (direction == 'to' && $scope.toRange < $scope.maxRange) {
            $scope.toRange++;
            setStartPositionToRange();
        }
    };

    (function setStartPositions() {
        var rightStartPosition = Math.round(100 - (($scope.maxRange * 100) / $scope.maxRange));
        var leftStartPosition = Math.round(($scope.minRange * 100) / $scope.maxRange);
        rangeRight.style.width = rightStartPosition + '%';
        rangeLeft.style.width = leftStartPosition + '%';
    })();

    fromRange.onchange = function () {
        var thisVal = +fromRange.value;
        if (!isNaN(thisVal) && thisVal >= $scope.minRange) {

            if (thisVal >= $scope.toRange) {
                fromRange.value = $scope.toRange;
                $scope.fromRange = $scope.toRange;
                setStartPositionFromRange();
            } else {
                $scope.fromRange = thisVal;
                setStartPositionFromRange();
            }

        } else {
            fromRange.value = $scope.minRange;
            $scope.fromRange = $scope.minRange;
            setStartPositionFromRange();
        }
    };

    toRange.onchange = function () {
        var thisVal = +toRange.value;
        if (!isNaN(thisVal) && thisVal <= $scope.maxRange) {

            if (thisVal <= $scope.fromRange) {
                toRange.value = $scope.fromRange;
                $scope.toRange = $scope.fromRange;
                setStartPositionToRange();
            } else {
                $scope.toRange = thisVal;
                setStartPositionToRange();
            }

        } else {
            toRange.value = $scope.maxRange;
            $scope.toRange = $scope.maxRange;
            setStartPositionToRange();
        }
    };

    function fixEvent(event) {
        event = event || window.event;
        if (!event.target) event.target = event.srcElement;

        if (event.pageX == null && event.clientX != null) {
            var html = document.documentElement,
                body = document.body;
            event.pageX = event.clientX + (html.scrollLeft || body && body.scrollLeft || 0);
            event.pageX -= html.clientLeft || 0;
        }

        if (!event.which && event.button) {
            event.which = event.button & 1 ? 1 : ( event.button & 2 ? 3 : ( event.button & 4 ? 2 : 0 ) )
        }

        return event;
    };


    function getCoords(elem) {
        var box = elem.getBoundingClientRect();
        var body = document.body;

        var docElem = document.documentElement;
        var scrollLeft = window.pageXOffset || docElem.scrollLeft || body.scrollLeft;
        var clientLeft = docElem.clientLeft || body.clientLeft || 0;
        var left = box.left + scrollLeft - clientLeft;

        return Math.round(left);
    };

    //=========================================================================//
    var sliderBoxCoordsLeft = getCoords(rngCont);
    var containerWidth = rngCont.offsetWidth || rngCont.clientWidth;
    //=========================================================================//

    function positions(spin) {
        document.onmousemove = function (event) {
            event = fixEvent(event);
            var positionPercent = ((event.pageX - sliderBoxCoordsLeft) / containerWidth) * 100;
            var newPosRightSpin = 100 - positionPercent;
            var range = Math.round(($scope.maxRange / 100) * positionPercent);

            if (spin == 'left' && positionPercent <= 100) {
                range = (range <= 0) ? 0 : range;
                if (range >= $scope.minRange && range <= $scope.toRange) {
                    fromRange.value = range;
                    $scope.fromRange = range;
                    rangeLeft.style.width = positionPercent + '%';
                }
            }

            if (spin == 'right' && newPosRightSpin <= 100) {
                range = (range >= $scope.maxRange) ? $scope.maxRange : range;
                if (range >= $scope.fromRange) {
                    toRange.value = range;
                    $scope.toRange = range;
                    rangeRight.style.width = newPosRightSpin + '%';
                }
            }
        }

        document.onmouseup = function () {
            ngElement(spinRight).removeClass('active');
            ngElement(spinLeft).removeClass('active');
            document.onmousemove = document.onmouseup = null;
        }
    };

    spinLeft.onmousedown = function () {
        spinRight.style.zIndex = '0';
        spinLeft.style.zIndex = '1';
        ngElement(spinLeft).addClass('active');
        positions('left');
        return false;
    };

    spinRight.onmousedown = function () {
        spinRight.style.zIndex = '1';
        spinLeft.style.zIndex = '0';
        ngElement(spinRight).addClass('active');
        positions('right');
        return false;
    };

};
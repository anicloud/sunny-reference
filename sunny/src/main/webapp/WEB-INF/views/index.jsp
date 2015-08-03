<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zh-CN" ng-app="sunny">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>
        <fmt:bundle basename="messages/messages">
            <fmt:message key="system.index.title"/>
        </fmt:bundle>
    </title>

    <link rel="shortcut icon" type="image/png"
          href="${pageContext.request.contextPath}/public/images/logo/ani_logo.png">

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/timeline.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/simple-sidebar.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/material-design-iconic-font.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/angularjs/css/ngDialog.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/angularjs/css/ngDialog-theme-default.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/angularjs/css/ngDialog-theme-plain.css" rel="stylesheet">

    <!-- Sunny css -->
    <link href="${pageContext.request.contextPath}/public/src/css/app.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/src/css/sunny.css" rel="stylesheet">


    <!--websocket-->
    <script src="//cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>

    <!-- jquery -->
    <script src="${pageContext.request.contextPath}/public/lib/jquery/javascript/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/jquery/javascript/jquery.md5.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/jquery/javascript/md5.js" type="text/javascript"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/public/lib/bootstrap/js/bootstrap.min.js"></script>

    <!-- websocket.js -->
    <script src="${pageContext.request.contextPath}/public/lib/websocket/sockjs-0.3.4.min.js"></script>

    <!-- angularjs -->
    <script src="${pageContext.request.contextPath}/public/lib/angular/angular.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angular/angular-route.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angular/angular-cookies.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angular/angular-translate.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angularjs/js/angular-ui-router.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angularjs/js/ui-bootstrap-tpls-0.13.0.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angularjs/js/ngDialog.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angularjs/js/datetime-picker.js" type="text/javascript"></script>


    <!-- config -->
    <script src="${pageContext.request.contextPath}/public/src/js/config/language-config.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/config/route-config.js" type="text/javascript"></script>

    <!-- services -->
    <script src="${pageContext.request.contextPath}/public/src/js/service/strategy-service.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/service/device-service.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/service/websocket-service.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/service/manager-service.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/service/notify-service.js" type="text/javascript"></script>

    <!-- controllers -->
    <script src="${pageContext.request.contextPath}/public/src/js/controller/home-page-ctrl.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/controller/strategy-ctrl.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/controller/strategy-edit-ctrl.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/controller/feature-edit-ctrl.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/controller/device-ctrl.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/controller/main-ctrl.js" type="text/javascript"></script>

    <!-- dto -->
    <script src="${pageContext.request.contextPath}/public/src/js/dto/featureInstance-dto.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/js/dto/strategyInstance-dto.js" type="text/javascript"></script>

    <!-- main -->
    <script src="${pageContext.request.contextPath}/public/src/js/sunny.js" type="text/javascript"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="layout-fixed"  ng-class="{'aside-collapsed':isAsideCollapsed}" ng-controller="MainCtrl">
<div data-ui-view="" data-autoscroll="false" class="wrapper" >
</div>

</body>
</html>


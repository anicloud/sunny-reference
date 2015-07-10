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
            <fmt:message key="system.index.title" />
        </fmt:bundle>
    </title>

    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/public/images/logo/ani_logo.png">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <%--<link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/sb-min2.css" rel="stylesheet">--%>
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/sunny.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/timeline.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/public/lib/font-awesome-4.3.0/css/font-awesome.min.css">


    <!--websocket-->
    <script src="//cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>

    <!-- jquery -->
    <script src="${pageContext.request.contextPath}/public/lib/jquery/javascript/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/jquery/javascript/jquery.md5.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/jquery/javascript/md5.js" type="text/javascript"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/public/lib/bootstrap/js/bootstrap.min.js"></script>

    <!-- angularjs -->
   <%-- <script src="http://cdn.bootcss.com/angular.js/1.3.16/angular.js"></script>
    <script src="http://cdn.bootcss.com/angular.js/1.3.16/angular-route.js"></script>
    <script src="http://cdn.bootcss.com/angular.js/1.3.16/angular-cookies.js"></script>--%>

    <script src="${pageContext.request.contextPath}/public/lib/angular/angular.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angular/angular-route.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angular/angular-cookies.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angular/angular-translate.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/angularjs/javascript/ui-bootstrap-tpls-0.13.0.js" type="text/javascript"></script>

    <!-- config -->
    <script src="${pageContext.request.contextPath}/public/src/config/sunny-service-config.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/config/language-config.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/config/route-config.js" type="text/javascript"></script>

    <!-- services -->
    <script src="${pageContext.request.contextPath}/public/src/service/system-service.js" type="text/javascript"></script>

    <!-- controllers -->
    <script src="${pageContext.request.contextPath}/public/src/controller/setting-ctrl.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/controller/HomePageCtrl.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/controller/StrategyCtrl.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/src/controller/DeviceCtrl.js" type="text/javascript"></script>

    <!-- main -->
    <script src="${pageContext.request.contextPath}/public/src/sunny.js" type="text/javascript"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body >
        <!--header-->
        <header>
          <nav class="navbar navbar-default navbar-fixed-top">
              <%--<div class="container-fluid">--%>
                  <div class="navbar-header">
                      <a class="navbar-brand" href="#/">Sunny</a>
                      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                      <span class="glyphicon glyphicon-menu-hamburger"></span>
                      </button>
                  </div>

                  <ul class="nav navbar-sunny navbar-right">
                      <li><a href="#"><span class="glyphicon glyphicon-plus"></span></a></li>
                      <li><a href="#"><span class="glyphicon glyphicon-play"></span></a></li>
                      <li><a href="#"><span class="glyphicon glyphicon-pause"></span></a></li>
                      <li><a href="#"><span class="glyphicon glyphicon-remove"></span></a></li>
                  </ul>

                  <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                      <li class="active"><a href="#/">Dashboard</a></li>
                      <li><a href="#strategy">Strategy</a></li>
                      <li><a href="#device">Device</a></li>
                    </ul>
                  </div>

              <%--</div>--%>
          </nav>
        </header>

        <div class="container-fluid" ng-view ng-controller="HomePageCtrl">
        </div>

        <div>{{device}}</div>
        <footer class="navbar-fixed-bottom">
            <div id="foot"><p>copyright: Anicloud</p></div>
        </footer>
</body>
</html>


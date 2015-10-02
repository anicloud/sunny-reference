<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" />

<!DOCTYPE html>
<html lang="zh-CN">
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

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/public/lib/bootstrap/js/bootstrap.min.js"></script>

    <!-- angularjs -->
    <%-- <script src="http://cdn.bootcss.com/angular.js/1.3.16/angular.js"></script>
    <script src="http://cdn.bootcss.com/angular.js/1.3.16/angular-route.js"></script>
    <script src="http://cdn.bootcss.com/angular.js/1.3.16/angular-cookies.js"></script>--%>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row" style="margin-top: 20px;">
        <div class="col-sm-offset-2 col-sm-8">
            <div class="row">
                <div class="col-sm-12">
                    <img width="100px" height="100px" src="${pageContext.request.contextPath}/public/images/logo/strategy.png" />
                    <hr/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <h2><span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>您已成功退出&nbsp;
                        <a href='<c:url value="/"/> ' type="button" class="btn btn-primary">重新登录</a></h2>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
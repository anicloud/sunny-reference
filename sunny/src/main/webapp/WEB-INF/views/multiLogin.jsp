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
<div class="jumbotron">
    <h1>I'm Sorry, User has logged in others place!</h1>
    <p>...</p>
    <p><a class="btn btn-primary btn-lg" href="#" role="button">RE LOGIN</a></p>
</div>
</body>
</html>





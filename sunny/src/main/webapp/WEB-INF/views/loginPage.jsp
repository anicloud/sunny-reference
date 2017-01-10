<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<!DOCTYPE html>
<html lang="zh-CN">
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

    <link rel="shortcut icon" type="image/icon"
          href="${pageContext.request.contextPath}/public/images/logo/favicon.ico">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/public/lib/bootstrap/css/bootstrap-theme.css" rel="stylesheet">

    <!--websocket-->
    <script src="${pageContext.request.contextPath}/public/lib/websocket/sockjs-0.3.4.min.js"></script>

    <!-- jquery -->
    <script src="${pageContext.request.contextPath}/public/lib/jquery/javascript/jquery.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/jquery/javascript/jquery.md5.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/public/lib/jquery/javascript/md5.js"
            type="text/javascript"></script>

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
    <script>
        function submitForm() {
            var email = $("#email").val();
            if (email == "" || email.length <= 0) {
                alert("邮箱不能为空!");
                return;
            } else {
                $("#loginForm").submit();
            }
        }
    </script>
</head>
<body>
<div class="container" style="font-family: Helvetica Neue, hiragino sans gb w3, Microsoft Yahei, Arial, sans-serif;">
    <div class="row">
        <div class="col-sm-12">
            <h1 style="text-align: center; margin: 60px auto 20px; font-kerning: normal">欢迎来到Anicloud智能家居应用</h1>
        </div>
    </div>

    <div style="width: 400px; margin:0px auto; padding: 20px 20px; background-color: #f6f7f9">

        <c:if test="${not empty errorMsg}">
            <p class="bg-warning"><c:out value="${errorMsg}"/></p>
        </c:if>


        <div style="text-align: center">
            <h4 style="margin-bottom: 14px; color: #818587">已授权账号登录</h4>
            <form id="loginForm" class="form-inline" action='<c:url value="login"/>' method="post">
                <%--<div class="form-group">--%>
                <input type="email" name="email" value='<c:out value="${previousUser.email}"/>'
                       class="form-control" id="email" placeholder="Email" style=" width:240px; margin-bottom: 12px">
                <%--</div>--%>
                <div>
                    <button type="button" onclick="submitForm();" class="btn btn-primary" style="background-image: linear-gradient(to bottom, #ffb36f 0%, #eba963 100%);
    background-color: #eba963;
    border-color: #eba963; width:240px; margin-bottom: 12px">登录</button>
                </div>
            </form>
            <a href="http://localhost:8081/service-bus/oauth/authorize?client_id=1058595963104900977&redirect_uri=http://localhost:8080/sunny/redirect&response_type=code&scope=read write" style="color: #bbbfc3;">未授权,使用Anicloud
                账号登录
            </a>
        </div>
    </div>
</div>
</body>
</html>



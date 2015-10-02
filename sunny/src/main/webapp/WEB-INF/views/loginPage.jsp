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
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
        function submitForm() {
            var email = $("#email").val();
            if (email == "" || email.length <=0) {
                alert("邮箱不能为空!");
                return;
            } else {
                $("#loginForm").submit();
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <h1>Welcome to Anicloud Sunny Smart Home Application!</h1>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <c:if test="${not empty errorMsg}">
                    <p class="bg-warning"><c:out value="${errorMsg}"/> </p>
                </c:if>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-6">
                <form id="loginForm" class="form-inline" action='<c:url value="login"/>' method="post">
                    <div class="form-group">
                        <div class="col-sm-5">
                            <input type="email" name="email" value='<c:out value="${previousUser.email}"/>' class="form-control" id="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-1">
                            <button type="button" onclick="submitForm();" class="btn btn-primary">已经授权,直接登录</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-sm-offset-1 col-sm-5">
                <a href="http://dev.anicloud.cn:8222/rect-manager/oauth/authorize?client_id=sunny-client&redirect_uri=http://localhost:8080/sunny/redirect&response_type=code&scope=read write" class="btn btn-success">未授权,用Anicloud 账号登录</a>
            </div>
        </div>
    </div>
</body>
</html>



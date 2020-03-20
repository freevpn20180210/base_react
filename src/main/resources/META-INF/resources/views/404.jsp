<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="<%=path%>/my/404.css">
</head>

<body>

<div class="error">
    <p class="remind">您访问的页面不存在，请返回主页！</p>
    <p><a class="button" href="<%=path%>/index">返回主页</a></p>
</div>

<script>
    //判断当前页面时否是顶层页面
    var a = document.getElementsByTagName('a')[0]
    //如果当前页是顶层页面
    if (self == top) {
        //跳转到自己
        a.target = '_self'
    } else {
        //跳转到顶层页面
        a.target = '_top'
    }
</script>
</body>
</html>

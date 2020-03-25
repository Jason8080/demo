<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jason
  Date: 2020/3/25
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人脸识别P图神器</title>
</head>
<body>
    <div>
        <form action="/cv" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <c:if test="${adj!=null}">
                <input name="ratio" value="${adj.ratio}" placeholder="该值越大, 图片越小, 默认15">
                <input name="left" value="${adj.left}" placeholder="该值越大, 往左边移的越多, 默认10">
                <input name="top" value="${adj.top}" placeholder="该值越大, 往上边移的越多, 默认5">
            </c:if>
            <c:if test="${adj==null}">
                <input name="ratio" placeholder="该值越大, 图片越小, 默认15">
                <input name="left" placeholder="该值越大, 往左边移的越多, 默认10">
                <input name="top" placeholder="该值越大, 往上边移的越多, 默认5">
            </c:if>
            <input type="submit">
        </form>
    </div>
    <div>
        <img src="/img/preview.jpg">
    </div>
</body>
</html>

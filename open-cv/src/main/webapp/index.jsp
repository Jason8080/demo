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
                左移: <input type="number" name="left" value="${adj.left}" value="10" step="10"/>
                放大: <input type="number" v name="ratio" value="${adj.ratio}" value="0.07" step="0.02"/>
                上移: <input type="number" name="top" value="${adj.top}" value="5" step="5"/>
            </c:if>
            <c:if test="${adj==null}">
                左移: <input type="number" name="left" value="10" step="10"/>
                放大: <input type="number" name="ratio" value="0.07" step="0.01"/>
                上移: <input type="number" name="top" value="5" step="5"/>
            </c:if>
            <button type="button" onclick="come()">提交</button>
            <span id="res"></span>
        </form>
    </div>
    <div>
        <img id="img" src="/img/preview.jpg">
    </div>
</body>
<style>
    input {
        height: 30px;
    }
    button {
        height: 30px;
    }
</style>
<script>
    //  如果我们要使用 ajax2.0 结合FormData 来提交数据 必须使用 post
    // document.querySelector('button[value=button]').onclick = function () {
    function come () {
        //1.创建对象
        var xhr = new XMLHttpRequest();
        //2.设置请求行(get请求数据写在url后面)
        xhr.open('post', '/cv');
        //3.设置请求头(get请求可以省略,post不发送数据也可以省略)
        // 如果使用的时 formData可以不写 请求头 写了 无法正常上传文件
        //  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        //3.5注册回调函数
        xhr.onload = function () {
            let response = xhr.responseText;
            var res = document.getElementById("res");
            if(response){
                res.innerHTML = "OK";
                var img = document.getElementById("img");
                img.src = "/img/preview.jpg?t="+Math.random();
            }else {
                res.innerHTML = "NO";
            }
        }
        // XHR2.0新增
        var data = new FormData(document.querySelector('form'));
        //4.请求主体发送(get请求为空，或者写null，post请求数据写在这里，如果没有数据，直接为空或者写null)
        xhr.send(data);
    }
</script>
</html>

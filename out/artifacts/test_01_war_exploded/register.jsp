<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/10/25
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>简易留言板</title>
</head>
<body bgcolor="#e3e3e3">
    <form action="registerServlet" method="get">
        <table>
            <caption>用户注册</caption>
            <tr>
                <td>登录名：</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"></td>
            </tr>
        </table>
        <input type="submit" value="注册">
        <input type="reset" value="重置">
    </form>
</body>
</html>

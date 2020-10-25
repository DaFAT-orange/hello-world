<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/10/25
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>简易留言板</title>
  </head>
  <body bgcolor="#e3e3e3">
    <form action="mainServlet" method="get">
      <table>
        <caption>用户登录</caption>
        <tr>
          <td>用户名：</td>
          <td>
            <input type="text" name="username" size="20">
          </td>
        </tr>
        <tr>
          <td>密码：</td>
          <td>
            <input type="password" name="password" size="20">
          </td>
        </tr>
      </table>
      <input type="submit" value="登陆">
      <input type="reset" value="重置">
    </form>
    如果没有注册请单击<a href="register.jsp">这里</a>注册！
  </body>
</html>

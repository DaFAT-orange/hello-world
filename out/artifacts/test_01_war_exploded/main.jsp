<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/10/25
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,java.sql.*,
org.easybooks.test.model.vo.*,org.easybooks.test.jdbc.*" %>
<html>
<head>
    <title>留言板信息</title>
</head>
<body bgcolor="e3e3e3">
    <form action="liuyan.jsp" method="post">
        <table border="1">
            <caption>留言板信息</caption>
            <tr>
                <th>留言人姓名</th><th>留言时间</th><th>留言标题</th><th>留言内容</th>
            </tr>
            <%
                PreparedStatement pstmt = null;
                SqlSrvDBConn sqlsrvdb = new SqlSrvDBConn();
                Connection ct = sqlsrvdb.getConn();
                ArrayList al = (ArrayList) session.getAttribute("al");
                Iterator iter = al.iterator();
                while(iter.hasNext()){
                    LyTable ly = (LyTable)iter.next();
                    String usr = null;
                    try { pstmt = ct.prepareStatement("select username from userTable where id=?");
                        pstmt.setInt(1, ly.getUserId());
                        ResultSet rs = pstmt.executeQuery();
                        while(rs.next()){
                            usr = rs.getString(1);
                        }
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
            %>
            <tr>
                <td><%=usr%></td>
                <td><%=ly.getDate().toString()%></td>
                <td><%=ly.getTitle()%></td>
                <td><%=ly.getContent()%></td>
            </tr>
            <%
                }
            %>
        </table>
        <input type="submit" balue="留言">
    </form>

</body>
</html>

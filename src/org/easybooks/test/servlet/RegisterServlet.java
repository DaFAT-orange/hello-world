package org.easybooks.test.servlet;

import org.easybooks.test.jdbc.SqlSrvDBConn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("gb2312");
        String usr = req.getParameter("username");
        String pwd = req.getParameter("password");

        PreparedStatement pstmt = null;
        SqlSrvDBConn sqlsrvdb = new SqlSrvDBConn();
        Connection ct = sqlsrvdb.getConn();
        try {
            pstmt = ct.prepareStatement("insert into userTable values(?,?)");
            pstmt.setString(1, usr);
            pstmt.setString(2, pwd);
            pstmt.executeUpdate();
            resp.sendRedirect("login.jsp");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

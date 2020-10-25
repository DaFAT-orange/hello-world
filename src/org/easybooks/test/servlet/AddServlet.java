package org.easybooks.test.servlet;

import org.easybooks.test.jdbc.SqlSrvDBConn;
import org.easybooks.test.model.vo.LyTable;
import org.easybooks.test.model.vo.UserTable;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("gb2312");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        HttpSession session = req.getSession();
        UserTable usr = (UserTable)session.getAttribute("user");
        LyTable ly = new LyTable();
        ly.setUserId(usr.getId());
        ly.setDate(new Date(System.currentTimeMillis()));
        ly.setTitle(title);
        ly.setContent(content);
        ArrayList al =  (ArrayList)session.getAttribute("al");
        al.add(ly);

        PreparedStatement pstmt = null;
        SqlSrvDBConn sqlsrvdb = new SqlSrvDBConn();
        Connection ct = sqlsrvdb.getConn();
        try {
            pstmt = ct.prepareStatement("insert into lyTable values(?,?,?,?)");
            pstmt.setInt(1, ly.getUserId());
            pstmt.setDate(2, ly.getDate());
            pstmt.setString(3, ly.getTitle());
            pstmt.setString(4, ly.getContent());
            pstmt.executeUpdate();
            resp.sendRedirect("main.jsp");
        }catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("liuyan.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

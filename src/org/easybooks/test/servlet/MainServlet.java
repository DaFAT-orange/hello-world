package org.easybooks.test.servlet;
import org.easybooks.test.jdbc.SqlSrvDBConn;
import org.easybooks.test.model.vo.LyTable;
import org.easybooks.test.model.vo.UserTable;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("gb2312");
        String usr = req.getParameter("username");
        String pwd = req.getParameter("password");
        boolean validated = false;
        SqlSrvDBConn sqlsrvdb = new SqlSrvDBConn();
        HttpSession session = req.getSession();
        UserTable user = null;
        user = (UserTable) session.getAttribute("user");
        if(user == null) {
            String sql = "select * from userTable";
            ResultSet rs = sqlsrvdb.executeQuery(sql);
            try {
                while(rs.next()) {
                    if(rs.getString("username").trim().compareTo(usr) == 0 &&
                            (rs.getString("password").compareTo(pwd)) == 0) {
                        user = new UserTable();
                        user.setId(rs.getInt(1));
                        user.setUsername(rs.getString(2));
                        user.setPassword(rs.getString(3));
                        session.setAttribute("user", user);
                        validated = true;
                    }
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
            sqlsrvdb.closeStmt();
        }
        else {
            validated = true;
        }
        if(validated) {
            ArrayList al = new ArrayList();
            try {
                String sql = "select * from lyTable";
                ResultSet rs = sqlsrvdb.executeQuery(sql);
                while(rs.next()) {
                    LyTable ly = new LyTable();
                    ly.setId(rs.getInt(1));
                    ly.setUserId(rs.getInt(2));
                    ly.setDate(rs.getDate(3));
                    ly.setTitle(rs.getString(4));
                    ly.setContent(rs.getString(5));
                    al.add(ly);
                }
                rs.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
            sqlsrvdb.closeStmt();
            session.setAttribute("al", al);
            resp.sendRedirect("main.jsp");
        }
        else {
            resp.sendRedirect("error.jsp");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

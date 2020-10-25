package org.easybooks.test.jdbc;

import javax.xml.transform.Result;
import java.sql.*;

public class SqlSrvDBConn {
    private Statement stmt;
    private Connection conn;
    ResultSet rs;

    public SqlSrvDBConn() {
        stmt = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=TEST","test","123");
        } catch(Exception e) {
            e.printStackTrace();
        }
        rs = null;
    }

    public Connection getConn() {
        return this.conn;
    }

    public ResultSet executeQuery(String sql) {
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);
        }
        catch(SQLException e){
            System.out.println("Data.executeQuery:" + e.getMessage());
        }
        return rs;
    }

    public void closeStmt() {
        try{
            stmt.close();
        }
        catch(Exception e) {
            System.out.println("Data.executeQuery:" + e.getMessage());
        }
    }

    public void closeConn() {
        try{
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Data.executeQuery:" + e.getMessage());
        }
    }
}

package com.trailerHunt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        if (email == null || email.isEmpty()) {
            response.sendRedirect("users.jsp");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/trailerhunt";
        String userName = "root";
        String passkey = "sagar@120";
        String query = "DELETE FROM users WHERE umail = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, passkey);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully: " + email);
            }

            response.sendRedirect("users");

        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBC Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error while deleting user");
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                	pstmt.close();
                }
                if (con != null) {
                	con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
    }
}
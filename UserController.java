package com.trailerHunt;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/users") 
public class UserController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/trailerhunt";
        String userName = "root";
        String passkey = "sagar@120";
        String query = "SELECT * FROM users";

        try {	
        	
        		Class.forName("com.mysql.cj.jdbc.Driver");
        		
        		Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/trailerhunt?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "sagar@120"
        				);
        		
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query);
           

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(rs.getString("name"), rs.getString("umail"), rs.getString("password")));
            }

            request.setAttribute("users", users);
            RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
            rd.forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

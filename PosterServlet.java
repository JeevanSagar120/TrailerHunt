package com.trailerHunt;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/poster")
public class PosterServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/trailerhunt";
    private static final String USER = "root";
    private static final String PASSWORD = "sagar@120";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        
        if (id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing movie ID");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("SELECT poster FROM movies WHERE id = ?");
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                byte[] imgData = rs.getBytes("poster"); // Assuming BLOB data type in MySQL
                response.setContentType("image/jpeg");
                OutputStream os = response.getOutputStream();
                os.write(imgData);
                os.flush();
                os.close();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

package com.trailerHunt;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/addtrailer")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class addTrailer extends HttpServlet {

    private final String usrName = "root";
    private final String password = "sagar@120";
    private final String url = "jdbc:mysql://localhost:3306/trailerhunt";
    private String sqlQuery = "insert into movies (title, cast, genre, year, rating, trailer_url, poster) values (?,?,?,?,?,?,?)";

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Connection connection = null;
        PreparedStatement psmt = null;
        InputStream inputStream = null;

        String title = request.getParameter("title");
        String cast = request.getParameter("cast");
        String genre = request.getParameter("genre");
        int year = Integer.parseInt(request.getParameter("year"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        String trailerUrl = request.getParameter("trailerurl");
        Part filePart = request.getPart("poster");
        inputStream = filePart.getInputStream();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Corrected driver class name
            connection = DriverManager.getConnection(url, usrName, password);

            psmt = connection.prepareStatement(sqlQuery);
            psmt.setString(1, title);
            psmt.setString(2, cast);
            psmt.setString(3, genre);
            psmt.setInt(4, year);
            psmt.setDouble(5, rating);
            psmt.setString(6, trailerUrl);
            psmt.setBlob(7, inputStream);

            int rows = psmt.executeUpdate();

            // Encode the title before setting it as a cookie value
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.toString());
            Cookie Titlecookie = new Cookie("title", encodedTitle);
            response.addCookie(Titlecookie);

            Cookie statusCookie = null;

            if (rows > 0) {
                System.out.println("Trailer Details Added Successfully");
                RequestDispatcher rd = request.getRequestDispatcher("addtrailer");
                rd.include(request, response);
                statusCookie = new Cookie("status", "true");
                response.addCookie(statusCookie);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("success");
                rd.forward(request, response);
                statusCookie = new Cookie("status", "false");
                response.addCookie(statusCookie);
                System.out.println("Failed to add Trailer Details");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Problem Occurred While Creating the Driver Class");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Problem Occurred While Opening the Connection");
            e.printStackTrace();
        } finally {
            try {
                if (psmt != null) psmt.close();
                if (connection != null) connection.close();
                if (inputStream != null) inputStream.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
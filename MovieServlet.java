package com.trailerHunt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/movies")
public class MovieServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/trailerhunt";
    private static final String USER = "root";
    private static final String PASSWORD = "sagar@120";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movies = new ArrayList<>();
        Connection conn = null; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, title, cast, genre, year, rating, trailer_url, poster FROM movies");

            while (rs.next()) {
                movies.add(new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("cast"),
                    rs.getString("genre"),
                    rs.getInt("year"),
                    rs.getDouble("rating"),
                    rs.getString("trailer_url"),
                    rs.getString("poster") 
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            try {
                if (conn != null) conn.close(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Debugging Log
        System.out.println("Movies fetched: " + movies.size());

        request.setAttribute("movies", movies);
        request.getRequestDispatcher("movies.jsp").forward(request, response);
    }
}

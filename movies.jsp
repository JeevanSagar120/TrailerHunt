<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.trailerHunt.Movie"%>
<!DOCTYPE html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link rel="stylesheet" href="main.css">
<script src="https://kit.fontawesome.com/33bbd11d25.js"
	crossorigin="anonymous"></script>
<html>
<head>
<title>TrailerHunt / Movies</title>
<style>
.movie-container {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	justify-content: center;
}

.movie-card {
	margin-top: 50px;
	margin-left: 20px;
	margin-right: 20px; width : 350px;
	border: 1px solid #ddd;
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	padding: 10px;
	background-color: white;
	width: 350px;
	color: black;
    transition: 0.2s linear;
}

.movie-card:hover{
	transform : translateY(-15px);
}

.movie-card img {
	margin-right:3px;
	justify-content:center;
	width: 360px;
	height: 200px;
	object-fit: cover;
}
body{
color: white;
}
</style>
</head>
<body>
	<!-- Nav Bar  -->
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<img src="./images/AppLogo.jpg" alt="logo" height="50px"
				width="200px">
			<div class="box">
				<form class="d-flex" role="search">
					<input id="searchitems" class="form-control me-2" type="search"
						placeholder="Search Movies" aria-label="Search"
						onkeyup="filterMovies()">
				</form>
			</div>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="connatiner-fluid">
				<div class="container-fluid collapse navbar-collapse "
					id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">

						<!-- Home Bar -->
						<li class="nav-item"><a class="nav-link" aria-current="page" href="<%= request.getContextPath() %>/movies"><i class="fa fa-home"></i> Home</a></li>


						<!-- About Us Bar -->
						<li class="nav-item"><a class="nav-link" href="about.html"><i
								class="fa fa-user"></i> About us</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> More </a>
							<ul class="dropdown-menu">
								<!-- Mail Bar  -->
								<li><a class="dropdown-item"
									href="mailto:22ag5a6602@gmail.com"><i
										class="fa fa-envelope"></i> Mail us</a></li>

								<!-- Customer Care bar -->
								<li><a class="dropdown-item" href="tel:+919390634012"><i
										class="fa fa-phone"></i> Call Customer Care</a></li>

								<!-- logout Bar -->
								<li><a class="dropdown-item" href="login.html"><i
										class="fa fa-sign-out"></i> Logout</a></li>
							</ul></li>
					</ul>
				</div>
			</div>

		</div>
	</nav>




	<div class="movie-container">
		<%
		List<Movie> movies = (List<Movie>) request.getAttribute("movies");
		if (movies != null && !movies.isEmpty()) {
			for (Movie movie : movies) {
		%>
		<div class="movie-card">
			<img src="poster?id=<%=movie.getId()%>"
				alt="<%=movie.getTitle()%>">
			<h3><%=movie.getTitle()%></h3>
			<p>
				<strong>Genre:</strong>
				<%=movie.getGenre()%></p>
			<p>
				<strong>Cast:</strong>
				<%=movie.getCast()%></p>
			<p>
				<strong>Year:</strong>
				<%=movie.getYear()%></p>
			<p>
				<strong>Rating:</strong>
				<%=movie.getRating()%>
				⭐
			</p>
			<a href="<%=movie.getTrailerUrl()%>" target="_blank" class="btn btn-primary">Watch Trailer</a>
		</div>
		<%
		}
		} else {
		%>
		<p>No movies found.</p>
		<%
		}
		%>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
<script>
	function filterMovies() {
		let input = document.getElementById("searchitems").value.toLowerCase();
		let movieCards = document.getElementsByClassName("movie-card");

		for (let i = 0; i < movieCards.length; i++) {
			let title = movieCards[i].getElementsByTagName("h3")[0].innerText
					.toLowerCase();

			if (title.includes(input)) {
				movieCards[i].style.display = "block";
			} else {
				movieCards[i].style.display = "none";
			}
		}
	}
</script>

</html>

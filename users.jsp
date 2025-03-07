<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.trailerHunt.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
</head>
<style>
body {
	background-color: black;
	color: white;
}

table {
	background-color: rgb(243, 239, 242);
	color: black;
	border: none;
	border-radius: 10px;
	margin-top: 100px;
}

img {
	margin-top: 50px;
	height: 90px;
	width: 300px;
}
</style>
<body>
	<center>
		<img alt="logo" src="./images/LoginpageLogo.jpg">
	</center>
	<div>
		<table border="1" cellspacing="0" cellpadding="15" align="center">
			<tr>
				<td colspan="4" align="center">
					<h2>Users</h2>
				</td>
			</tr>
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Password</th>
				<th>Action</th>
			</tr>
			<%
			List<User> users = (List<User>) request.getAttribute("users");
			if (users != null && !users.isEmpty()) {
				for (User u : users) {
			%>
			<tr>
				<td><center><%=u.getName()%></center></td>
				<td><center><%=u.getEmail()%></center></td>
				<td><center><%=u.getPassword()%></center></td>
				<td><center>
						<a href="delete?email=<%=u.getEmail()%>"
							style="text-decoration: none; height: 20px; width: 100px; border-radius: 10px; background-color: red; display: inline-block; text-align: center; color: white; padding: 5px 10px;">
							Delete
						</a>
					</center>
				</td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="4">No users found</td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>

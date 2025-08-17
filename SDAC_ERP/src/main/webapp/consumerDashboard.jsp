<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Pojo.Products"%>
<%@ page import="Products_Dao.ViewProductsDao"%>
<%
// Retrieve port_id from the session
String portID = (String) session.getAttribute("port_id");
if (portID == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Consumer Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: #FAF9F6; /* Light background color for contrast */
	min-height: 100vh;
	padding-top: 60px;
}

.navbar {
	background-color: #343a40; /* Dark grey navbar */
	height: 60px;
	padding: 10px 15px;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
}

.sidebar {
	width: 250px;
	height: calc(100vh - 60px); /* Full height minus navbar */
	position: fixed;
	top: 60px; /* Adjusted for navbar height */
	left: 0; /* Sidebar always visible */
	background-color: #495057; /* Dark grey sidebar */
	box-shadow: 5px 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	z-index: 1000;
	transition: left 0.3s ease;
}

.sidebar.hidden {
	left: -250px; /* Hide sidebar */
}

.profile-section {
	margin-bottom: 20px;
	padding: 15px;
	border-radius: 10px;
	background-color: #f7f7f7;
	text-align: center;
}

.nav-link {
	color: #ffffff; /* White text for links */
	margin-bottom: 10px; /* Gap between buttons */
}

.nav-link:hover {
	background-color: #6c757d; /* Darker grey on hover */
	border-radius: 5px; /* Rounded corners */
}

.content {
	margin-left: 270px; /* Space for the sidebar */
	transition: margin-left 0.3s ease;
	padding: 20px; /* Padding for content */
}

.content.shifted {
	margin-left: 20px; /* Shift content when sidebar is hidden */
}

.profile-circle {
	display: inline-block;
	width: 35px;
	height: 35px;
	border-radius: 50%;
	background-color: #007bff; /* Blue background for profile icon */
	color: #fff;
	font-weight: bold;
	display: flex;
	align-items: center;
	justify-content: center;
}

.btn-logout {
	margin-right: 10px; /* Gap between logout and profile button */
}

.card {
	background-color: #495057;
	border-radius: 15px; /* Rounded edges for card */
}

td {
	color: white;
}
</style>
</head>
<body>

	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg fixed-top">
		<div class="container-fluid">
			<button class="btn btn-dark" id="sidebarToggle">☰</button>
			<!-- Sidebar toggle button -->
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link active"
						href="consumerDashboard.jsp"
						style="font-weight: 600; margin-top: 10px; color: white; font-size: x-large;">Consumer
							Dashboard</a></li>
				</ul>
				<!-- Logout Button -->
				<form action="login.jsp" method="post" style="display: inline;">
					<button type="submit" class="btn btn-danger">Logout</button>
				</form>
				<!-- Profile Icon with Dropdown -->
				<div class="dropdown">
					<a href="#" id="profileDropdown" data-bs-toggle="dropdown"
						aria-expanded="false">
						<div class="profile-circle" style="margin-left: 15px;">U</div>
					</a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="profileDropdown">
						<li><h6 class="dropdown-header">User Profile</h6></li>
						<li><span class="dropdown-item-text">PortId: <%=portID%></span></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="profile.jsp">Edit
								Profile</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="deleteConsumerProfile.jsp">Delete
								Profile</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>

	<!-- Sidebar -->
	<div class="sidebar" id="sidebar">
		<div class="profile-section">
			<h5>Welcome, User</h5>
			<p>
				PortId:
				<%=portID%></p>
		</div>
		<nav class="nav flex-column">

			<a class="btn btn-primary" href="viewOrdersByConsumer.jsp" style="margin-bottom: 10px;">Track Orders</a> 
			<a class="btn btn-primary" href="consumerOrderSummary.jsp"style="margin-bottom: 10px;">Report Product</a> 
			<a class="btn btn-primary" href="viewReportedProductsConsumer.jsp"style="margin-bottom: 10px;">Reported Products</a> 
	

		</nav>
	</div>

	<!-- Main Content -->
	<div class="content" id="content"> 
		<div class="container mt-3">
			<div class="card">
				<div class="card-header mt-2">
					<center>
						<h5 style="color: white">All Products</h5>
					</center>
				</div>
				<div class="card-body">
					<table class="table  text-center">
						<thead class="table-light">
							<tr>
								<th scope="col">Product ID</th>
								<th scope="col">Product Name</th>
								<th scope="col">Quantity</th>
								<th scope="col">Price</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							ViewProductsDao dao = new ViewProductsDao();
							List<Products> productsList = dao.viewProducts();

							if (productsList != null && !productsList.isEmpty()) {
								for (Products product : productsList) {
							%>
							<tr>
								<td><%=product.getProductID()%></td>
								<td><%=product.getProductName()%></td>
								<td><%=product.getQuantity()%></td>
								<td><%=product.getPrice()%></td>
								<td>
									<form action="placeOrder.jsp" method="get">
										<input type="hidden" name="productID"
											value="<%=product.getProductID()%>"> <input
											type="hidden" name="productName"
											value="<%=product.getProductName()%>"> <input
											type="hidden" name="price" value="<%=product.getPrice()%>">
										<button type="submit" class="btn btn-success">Place
											Order</button>
											<input type="hidden" name="quantity" value="<%=product.getQuantity()%>">
									</form>
								</td>
							</tr>
							<%
							}
							} else {
							%>
							<tr>
								<td colspan="5">No products available.</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		document.getElementById('sidebarToggle').addEventListener(
				'click',
				function() {
					document.getElementById('sidebar').classList
							.toggle('hidden'); // Toggle sidebar visibility
					document.getElementById('content').classList
							.toggle('shifted'); // Adjust content position
				});
	</script>
</body>
</html>

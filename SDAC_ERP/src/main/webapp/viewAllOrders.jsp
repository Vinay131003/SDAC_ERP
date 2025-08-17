<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Pojo.Orders"%>
<%@ page import="Order_Dao.ViewAllOrdersDao"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%
// Retrieve port_id from the session
String portID = (String) session.getAttribute("port_id");
if (portID == null) {
	// If portID is not found in session, redirect to login page
	response.sendRedirect("login.jsp");
	return; // Ensure the rest of the JSP does not execute
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View All Orders</title>
<!-- Bootstrap CSS -->
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
	margin-top: 18px;
}

td {
	color: white;
}

.tick {
	color: #88E788;
	font-size: 30px; /* Adjust size as needed */
}

.cross {
	color: #EE4B2B;
	font-size: 30px; /* Adjust size as needed */
}
</style>
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg fixed-top">
		<div class="container-fluid">
			<button class="btn btn-dark" id="sidebarToggle">☰</button>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link active"
						href="sellerDashboard.jsp"
						style="font-weight: 600; margin-top: 10px; font-size: x-large; color: white;">Seller
							Dashboard</a></li>
				</ul>

			</div>
		</div>
	</nav>

	<!-- Sidebar -->
	<div class="sidebar" id="sidebar">
		<!-- Profile Section -->
		<div class="profile-section">
			<h5>Welcome, Seller</h5>
			<p>
				SellerID:
				<%=portID%></p>
		</div>

		<!-- Links Section -->
		<nav class="nav flex-column">
			<a class="btn btn-primary" href="addProduct.jsp"
				style="margin-bottom: 10px;">Add Product</a> <a
				class="btn btn-primary" href="viewReportedProducts.jsp"
				style="margin-bottom: 10px;">View Reported Products</a>
		</nav>
	</div>

	<!-- Main Content -->
	<div class="content" id="content">
		<div class="container products-container">
			<div class="card shadow-lg rounded-3">
				<div class="card-body" style="color: white;">
					<center>
						<h2>All Orders</h2>
					</center>
					<%
					// Instantiate ViewAllOrdersDao to fetch orders from the database
					ViewAllOrdersDao dao = new ViewAllOrdersDao();
					List<Orders> ordersList = dao.viewAllOrders();

					// Create a date format for displaying the order date
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

					if (ordersList != null && !ordersList.isEmpty()) {
					%>

					<table class="table align-middle text-center">
						<thead class="table-light">
							<tr>
								<th>Order ID</th>
								<th>Product ID</th>
								<th>Consumer Port ID</th>
								<th>Quantity</th>
								<th>Order Date</th>
								<th>Order Placed</th>
								<th>Shipped</th>
								<th>Out for Delivery</th>
								<th>Delivered</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							// Iterate through the orders and display each in a table row
							for (Orders order : ordersList) {
								// Format the order date to yyyy-MM-dd
								dateFormat.applyPattern("dd-MM-yyyy");
								String formattedOrderDate = dateFormat.format(order.getOrderDate());
							%>
							<tr>
								<td><%=order.getOrderID()%></td>
								<td><%=order.getProductID()%></td>
								<td><%=order.getConsumerPortID()%></td>
								<td><%=order.getQuantity()%></td>
								<td><%=formattedOrderDate%></td>
								<td><span
									class="<%=order.isOrderPlaced() ? "tick" : "cross"%>"><%=order.isOrderPlaced() ? "✓" : "✗"%></span></td>
								<td><span class="<%=order.isShipped() ? "tick" : "cross"%>"><%=order.isShipped() ? "✓" : "✗"%></span></td>
								<td><span
									class="<%=order.isOutForDelivery() ? "tick" : "cross"%>"><%=order.isOutForDelivery() ? "✓" : "✗"%></span></td>
								<td><span
									class="<%=order.isDelivered() ? "tick" : "cross"%>"><%=order.isDelivered() ? "✓" : "✗"%></span></td>
								<td>
									<!-- Update Order Button -->
									<form action="updateOrderStatus.jsp" method="post">
										<input type="hidden" name="orderID"
											value="<%=order.getOrderID()%>">
										<button type="submit" class="btn btn-warning">Update
											Order Status</button>
									</form>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>

					<%
					} else {
					%>
					<div class="no-orders">No orders available.</div>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS (Optional for additional interactive components) -->
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

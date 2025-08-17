
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Pojo.Orders"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%
// Retrieve portID from the session
String portID = (String) session.getAttribute("port_id");
if (portID == null) {
	response.sendRedirect("login.jsp");
	return;
}

// Use the portID as the consumerId
String consumerId = portID;

// Automatically call the action to fetch orders
List<Orders> orders = (List<Orders>) request.getAttribute("consumerOrderSummary");

// Trigger action to retrieve the orders if not already set
if (orders == null) {
	request.setAttribute("consumerId", consumerId); // Set consumerId as an attribute
	request.getRequestDispatcher("OrdersController?action=consumerOrderSummary&consumerId=" + consumerId)
	.forward(request, response);
	return; // Ensure no further processing occurs
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Consumer Order Summary</title>
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

.tick {
	color: #88E788;
	font-size: 20px; /* Adjust size as needed */
}

.cross {
	color: #EE4B2B;
	font-size: 20px; /* Adjust size as needed */
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
			<a class="btn btn-primary" href="viewOrdersByConsumer.jsp"
				style="margin-bottom: 10px;">Track Orders</a> <a
				class="btn btn-primary" href="viewReportedProductsConsumer.jsp"
				style="margin-bottom: 10px;">Reported Product</a>

		</nav>
	</div>

<!-- Main Content -->
<div class="content" id="content">
    <div class="container products-container mt-3">
        <div class="card shadow-lg rounded-3">
            <div class="card-body">
                <center>
                    <h5 style="color: white;">Your Ordered Products</h5>
                </center>
                <%
                if (orders != null && !orders.isEmpty()) {
                %>
                <table class="table align-middle text-center mt-4" style="margin-top: 20px;">
                    <thead class="table-light">
                        <tr>
                            <th>Order ID</th>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Order Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        for (Orders order : orders) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            String formattedOrderDate = dateFormat.format(order.getOrderDate());
                        %>
                        <tr>
                            <td><%=order.getOrderID()%></td>
                            <td><%=order.getProductID()%></td>
                            <td><%=order.getProductName()%></td>
                            <td><%=order.getQuantity()%></td>
                            <td><%=formattedOrderDate%></td>
                            <td>
                                <form action="reportProduct.jsp" method="get">
                                    <input type="hidden" name="productID" value="<%=order.getProductID()%>">
                                    <button type="submit" class="btn btn-success btn-sm">Report Product</button>
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
                <div class="alert alert-warning" role="alert">No orders found for this consumer.</div>
                <%
                }
                %>
            </div>
        </div>
    </div>
</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
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

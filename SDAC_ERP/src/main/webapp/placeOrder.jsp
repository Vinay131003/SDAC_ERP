<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String portID = (String) session.getAttribute("port_id");
    if (portID == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Retrieve product details from request parameters
    String productID = request.getParameter("productID");
    String productName = request.getParameter("productName");
    String price = request.getParameter("price");
    String quantity = request.getParameter("quantity");

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Place Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
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
        td{
        color: white;
        }
        
        .card-body{
         color: white;
         
         }
         .form-container{
         width: 555px;
         }
        
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container-fluid">
            <button class="btn btn-dark" id="sidebarToggle">☰</button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="consumerDashboard.jsp" style="font-weight: 600; font-size: x-large; margin-top: 10px; color: white; ">Consumer Dashboard</a>
                    </li>
                </ul>

                
            </div>
        </div>
    </nav>

    <!-- Sidebar -->
    <div class="sidebar" id="sidebar">
        <div class="profile-section">
            <h5>Welcome, User</h5>
            <p>PortId: <%= portID %></p>
        </div>

        <nav class="nav flex-column">
            <a class="btn btn-primary" href="viewOrdersByConsumer.jsp" style="margin-bottom: 10px;">View Orders</a>
            
            <a class="btn btn-primary" href="reportProduct.jsp" style="margin-bottom: 10px;">Report Product</a>
            
        </nav>
    </div>

   <!-- Main Content -->
<div class="content" id="content">
    <div class="container mt-5" style="max-width: 600px; margin: 0 auto;">
        <div class="card shadow-sm">
            <div class="card-header" >
                <h2 class="text-center" style="color: white;">Place Order</h2>
            </div>
            <div class="card-body">
                <form action="OrdersController" method="post">
                    <input type="hidden" name="action" value="placeOrder">

                    <div class="mb-3">
                        <label for="productID" class="form-label">Product ID</label>
                        <input type="text" id="productID" name="productID" class="form-control" value="<%= productID %>" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="productName" class="form-label">Product Name</label>
                        <input type="text" id="productName" name="productName" class="form-control" value="<%= productName %>" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="text" id="price" name="price" class="form-control" value="<%= price %>" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Quantity</label>
                        <input type="number" id="quantity" name="quantity" class="form-control" min="1" max="<%= quantity %>" required>
                    </div>
                    <button type="submit" class="btn btn-success w-100">Submit Order</button>
                </form>
            </div>
        </div>
    </div>
</div>



   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
     <script>
        document.getElementById('sidebarToggle').addEventListener('click', function () {
            document.getElementById('sidebar').classList.toggle('hidden'); // Toggle sidebar visibility
            document.getElementById('content').classList.toggle('shifted'); // Adjust content position
        });
    </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Pojo.Products" %>
<%@ page import="Products_Dao.ViewProductsDao" %>
<%
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
    <title>Add Product</title>
    <!-- Bootstrap CSS -->
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
            <button class="btn btn-dark" id="sidebarToggle">☰</button> <!-- Sidebar toggle button -->
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="sellerDashboard.jsp" style="font-weight: 600; margin-top:10px; font-size: x-large; color: white; ">Seller Dashboard</a>
                </li>
            </ul>
            <!-- <form action="login.jsp" method="post">
                <button type="submit" class="btn btn-danger btn-logout">Logout</button>
            </form> -->
           <%--  <div class="dropdown">
                <a href="#" id="profileDropdown" data-bs-toggle="dropdown">
                    <div class="profile-circle">S</div>
                </a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileDropdown">
                    <li><h6 class="dropdown-header">Seller Profile</h6></li>
                    <li><span class="dropdown-item-text">SellerID: <%= portID %></span></li>
                    <li><a class="dropdown-item" href="profile.jsp">View Profile</a></li>
                </ul>
            </div> --%>
        </div>
    </nav>

	

    <!-- Sidebar -->
    <div class="sidebar" id="sidebar">
        
        <div class="profile-section">
            <h5>Welcome, Seller</h5>
            <p>SellerID: <%= portID %></p>
        </div>
        
        <nav class="nav flex-column">
            
            
            <a class="btn btn-primary mb-2" href="viewAllOrders.jsp">View Orders</a>
            
            <a class="btn btn-primary mb-2" href="viewReportedProducts.jsp">View Reported Products</a>
        </nav>
    </div>

    <!-- Main Content -->
    <div class="content" id="content">
    <div class="container form-container">
        <div class="card">
            <div class="card-header">
                <h2 class="text-center" style="color: white;">Add New Product</h2>
            </div>
            <div class="card-body">
                <form action="ProductsController" method="post">
                    <input type="hidden" name="action" value="addProduct">
                    <div class="mb-3">
                        <label for="productID" class="form-label">Product ID:</label>
                        <input type="number" id="productID" name="productID" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="productName" class="form-label">Product Name:</label>
                        <input type="text" id="productName" name="productName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="quantity" class="form-label">Quantity:</label>
                        <input type="number" id="quantity" name="quantity" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label">Price:</label>
                        <input type="text" id="price" name="price" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-success">Add Product</button>
                </form>
            </div>
        </div>
    </div>
</div>


    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
     <script>
        document.getElementById('sidebarToggle').addEventListener('click', function () {
            document.getElementById('sidebar').classList.toggle('hidden'); // Toggle sidebar visibility
            document.getElementById('content').classList.toggle('shifted'); // Adjust content position
        });
    </script>
</body>
</html>

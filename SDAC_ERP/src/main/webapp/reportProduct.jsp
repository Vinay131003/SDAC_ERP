<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
    // Retrieve port_id from the session
    String portID = (String) session.getAttribute("port_id");
    if (portID == null) {
        // If portID is not found in session, redirect to login page
        response.sendRedirect("login.jsp");
        return;
    }

    // Retrieve productID from request parameters
    String productID = request.getParameter("productID");
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Report a Product</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Add the CSS from the first page */
       
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
        .container {
            margin: 50px auto;
            padding: 20px;
            max-width: 600px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
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
        .card {
        	background-color: #495057;
            border-radius: 15px; /* Rounded edges for card */
            margin-top:18px;
        }
        .card-body{
         color: white;
         }
        .form-label{
        color:white;
        
        }
        input[type="text"], input[type="number"], select {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            margin-bottom: 5px;
        }
        input[type="submit"] {
        	margin-bottom:10px;
            padding-top: 17px;
            font-weight: 600;
            background-color: green;
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 7px;
            box-sizing: border-box;
        }
        
        #issueType{
        margin-bottom: 15px;
        }
        .alert {
            margin-top: 20px;
        }
    </style>
</head>
<body>

 <!-- Navbar -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container-fluid">
            <button class="btn btn-dark" id="sidebarToggle" style="margin-right:5px;">☰</button>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="consumerDashboard.jsp" style="font-weight: 600; margin-top:10px; font-size: x-large ;color:white;">Consumer Dashboard</a>
                    </li>
                
                </div>
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
            <a class="btn btn-primary" href="viewReportedProducts.jsp" style="margin-bottom: 10px;">View Reported Products</a>
        </nav>
    </div>
    
    
    <div class="content" id="content">
    <div class="container card">
        <h2 style="text-align: center; color:white">Report a Product</h2>

        <% if (successMessage != null) { %>
            <div class="alert alert-success" role="alert">
                <%= successMessage %>
            </div>
        <% } %>

        <% if (errorMessage != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= errorMessage %>
            </div>
        <% } %>

        <form action="Reported_productsController" method="POST">
            <input type="hidden" name="action" value="reportProduct">
            <label for="consumerPortId" class="form-label">Consumer Port ID:</label>
            <input type="text" id="consumerPortId" name="consumerPortId" value="<%= portID %>" readonly class="readonly-input">

            <label for="productId" class="form-label">Product ID:</label>
            <input type="number" id="productId" name="productId" value="<%= productID %>" required readonly>

            <label for="issueType" class="form-label">Issue Type:</label>
            <select id="issueType" name="issueType" required>
                <option value="damage">Damage</option>
                <option value="wrong product">Wrong Product</option>
                <option value="delayed">Delayed</option>
                <option value="missing">Missing</option>
            </select>
            <br>

            <button type="submit" class="btn btn-danger btn-confirm">Report Product</button>
        </form>
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

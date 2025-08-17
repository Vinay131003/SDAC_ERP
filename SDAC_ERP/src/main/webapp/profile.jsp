<%@ page import="java.util.*" %>
<%
    // Retrieve port_id from session
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
    <title>Profile Page</title>
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
    <script>
    function validateForm() {
        const locationInput = document.getElementById('location');
        // Remove the name attribute if the location input is empty
        if (locationInput.value.trim() === '') {
            locationInput.removeAttribute('name');
        }
        return true; // Allow form submission
    }
    </script>
</head>
<body>

<!-- Profile Container -->
<div class="container form-container mt-5" style="max-width: 600px; margin: 0 auto;">
    <div class="row">
        <!-- Update Profile Card -->
        <div class="col-md-12">
            <div class="card shadow-sm">
                <div class="card-header">
                    <h4 class="text-center" style="color: white;">Update Profile</h4>
                </div>
                <div class="card-body">
                    <form action="ConsumerController" method="post" onsubmit="return validateForm()">
                        <input type="hidden" name="action" value="update">
                        
                        <div class="mb-3">
                            <label for="portId" class="form-label">Port ID</label>
                            <input type="text" class="form-control" id="portId" name="portID" value="<%= portID %>" readonly>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Enter New Password">
                        </div>
                        
                        <div class="mb-3">
                            <label for="location" class="form-label">Location</label>
                            <input type="text" class="form-control" id="location" name="location" placeholder="Enter New Location">
                        </div>

                        <div class="mb-3 text-center">
                            <button type="submit" class="btn btn-primary w-100">Update Profile</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Delete Profile Section -->
    <div class="row mt-3">
        <div class="col-md-12 text-center">
            <button class="btn btn-secondary" onclick="window.location.href='consumerDashboard.jsp'">Back to Dashboard</button>
        </div>
    </div>
</div>


    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
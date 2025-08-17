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
    <title>Delete Profile</title>
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
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding-top: 50px;
        }
        .card {
            background-color: #495057;
            border-radius: 15px;
            margin-top: 20px;
        }
        .card-header, .card-body {
            color: white;
        }
        .btn-confirm, .btn-cancel {
            width: 100%;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<!-- Delete Profile Container -->
<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-header text-center">
            <h4>Delete Profile</h4>
        </div>
        <div class="card-body text-center">
            <p>Are you sure you want to delete your profile? This action is irreversible.</p>
            <form action="ConsumerController" method="post">
                <input type="hidden" name="portID" value="<%= portID %>">
                <input type="hidden" name="action" value="delete">
                <button type="submit" class="btn btn-danger btn-confirm">Yes, Delete My Profile</button>
            </form>
            <a href="consumerDashboard.jsp" class="btn btn-secondary btn-cancel">Cancel</a>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

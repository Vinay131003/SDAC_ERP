<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .card-body {
        background-color: #495057;
        width: 450px; /* Adjust width to change card size */
        border-radius: 10px;
        padding: 10px; /* Adjust padding to make the card larger */
        margin: 0 auto; /* Center the card horizontally */
        margin-top: 70px;
        
    }

    .card {
        width: 450px; /* Adjust width to change card size */
        margin: 0 auto; /* Center the card horizontally */
    }

    .form-label {
        color: white;
    }

    h1 {
        margin-top: 0px;
        text-align: center;
        padding-bottom: 5px;
        font-size: 24px; /* Adjust size for a balanced look */
        color: #fff; /* Add contrast for the text */
    }

    .container h1 {
        font-size: 32px; /* Adjust size of the main title */
        color: #007bff; /* Change color to a Bootstrap primary color */
    }
</style>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
             
                <div class="card-body">
                    <h2 class="text-center mb-4" style="color:white;"> Login </h2>
                    <% String error = request.getParameter("error"); %>
                    <% if ("invalid".equals(error)) { %>
                        <div class="alert alert-danger text-center">Invalid credentials, please try again.</div>
                    <% } %>
                    <form action="LoginController" method="post">
                        <div class="mb-3">
                            <label for="portID" class="form-label emoji-label" style="color:white;">Port ID</label>
                            <input type="text" id="portID" name="portID" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label emoji-label" style="color:white;">Password</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="role" class="form-label emoji-label" style="color:white;">Role</label>
                            <select id="role" name="role" class="form-select" required>
                                <option value="">Select Role</option>
                                <option value="consumer">Consumer</option>
                                <option value="seller">Seller</option>
                            </select>
                        </div>
                        <input type="hidden" name="action" value="login">
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <p style="color:white;">Don't have an account? <a href="register.jsp" style="color:yellow;">Register here</a></p>
                    </div>
                </div>
            </div>
        </div>
        </div>
   
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
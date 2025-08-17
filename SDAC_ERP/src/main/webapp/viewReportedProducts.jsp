<%@ page import="java.util.List" %>
<%@ page import="Reported_products_Dao.ViewReportedProductsDao" %>
<%@ page import="Pojo.ReportedProducts" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    // Create an instance of the DAO
    ViewReportedProductsDao dao = new ViewReportedProductsDao();
    // Call the method to fetch reported products
    List<ReportedProducts> reportedProductsList = dao.viewReportedProducts();
    
    // Get any success message from the request attributes
    String successMessage = (String) request.getAttribute("successMessage");

    // Date formatter for dd-MM-yyyy format
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reported Products</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Existing CSS styles */
        body {
            background: #FAF9F6;
            min-height: 100vh;
            padding-top: 60px;
        }
        .navbar {
            background-color: #343a40;
            height: 60px;
            padding: 10px 15px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        }
        .sidebar {
            width: 250px;
            height: calc(100vh - 60px);
            position: fixed;
            top: 60px;
            left: 0;
            background-color: #495057;
            box-shadow: 5px 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            z-index: 1000;
            transition: left 0.3s ease;
        }
        .sidebar.hidden {
            left: -250px;
        }
        .profile-section {
            margin-bottom: 20px;
            padding: 15px;
            border-radius: 10px;
            background-color: #f7f7f7;
            text-align: center;
        }
        .nav-link {
            color: #ffffff;
            margin-bottom: 10px;
        }
        .nav-link:hover {
            background-color: #6c757d;
            border-radius: 5px;
        }
        .content {
            margin-left: 270px;
            transition: margin-left 0.3s ease;
            padding: 20px;
        }
        .content.shifted {
            margin-left: 20px;
        }
        .profile-circle {
            display: inline-block;
            width: 35px;
            height: 35px;
            border-radius: 50%;
            background-color: #007bff;
            color: #fff;
            font-weight: bold;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .btn-logout {
            margin-right: 10px;
        }
        .card {
            background-color: #495057;
            border-radius: 15px;
            margin-top: 18px;
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
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="sellerDashboard.jsp" style="font-weight: 600; font-size: x-large; color: white; margin-top: 10px;">Seller Dashboard</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <!-- Sidebar -->
    <div class="sidebar" id="sidebar">
        <div class="profile-section">
            <h5>Welcome, Seller</h5>
            <p>SellerID: <%= portID %></p>
        </div>
        <nav class="nav flex-column">
            <a class="btn btn-primary" href="addProduct.jsp" style="margin-bottom: 10px;">Add Product</a>
            <a class="btn btn-primary" href="viewAllOrders.jsp" style="margin-bottom: 10px;">View Orders</a>
        </nav>
    </div>

    <!-- Main Content -->
    <div class="content" id="content">
        <div class="container products-container">
            <div class="card shadow-lg rounded-3">
                <div class="card-body" style="color: white;">
                    <center><h5>Reported Products</h5></center>
                    
                    <%-- Display success message if available --%>
                    <% if (successMessage != null) { %>
                        <div class="alert alert-success"><%= successMessage %></div>
                    <% } %>

                    <table class="table align-middle text-center">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">Report ID</th>
                                <th scope="col">Consumer Port ID</th>
                                <th scope="col">Product ID</th>
                                <th scope="col">Issue Type</th>
                                <th scope="col">Solution</th>
                                <th scope="col">Report Date</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (ReportedProducts product : reportedProductsList) {
                            %>
                            <tr>
                                <td><%= product.getReportId() %></td>
                                <td><%= product.getConsumerPortId() %></td>
                                <td><%= product.getProductId() %></td>
                                <td><%= product.getIssueType() %></td>
                                <td><%= product.getSolution() %></td>
                                <td><%= dateFormatter.format(product.getReportDate()) %></td>
                                <td>
                                    <form action="Reported_productsController" method="post">
                                        <input type="hidden" name="reportId" value="<%= product.getReportId() %>">
                                        <input type="hidden" name="action" value="markAsSolved">
                                        <button type="submit" class="btn btn-success">Mark as Solved</button>
                                    </form>
                                </td>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.getElementById('sidebarToggle').addEventListener('click', function () {
            document.getElementById('sidebar').classList.toggle('hidden'); // Toggle sidebar visibility
            document.getElementById('content').classList.toggle('shifted'); // Adjust content position
        });
    </script>
</body>
</html>

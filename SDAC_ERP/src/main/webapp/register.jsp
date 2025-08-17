<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<!-- Bootstrap CSS -->


<style>
    .card-body {
        background-color: #495057;
        border-radius: 10px;
        padding: 10px; /* Adjust padding to make the card larger */
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

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
	
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow-lg">
					<div class="card-body">
						<h1 class="text-center mb-4" style="color:white;">Register</h1>

						<!-- Add 'id' to form for easy reference in JS -->
						<form id="registerForm" action="LoginController" method="post"
							onsubmit="return validatePasswords()">
							<div class="mb-3">
								<label for="portID" class="form-label">Port ID</label> <input
									type="text" id="portID" name="portID" class="form-control"
									required>
									<i class="fas fa-check-circle tick-icon" id="portIDTick"></i>
							</div>

							<div class="mb-3">
								<label for="password" class="form-label">Password</label> <input
									type="password" id="password" name="password"
									class="form-control" required>
									<i class="fas fa-check-circle tick-icon" id="portIDTick"></i>
							</div>

							<div class="mb-3">
							<label for="confirmPassword" class="form-label">Confirm
									Password</label> 
									<input type="password" id="confirmPassword"
									name="confirmPassword" class="form-control" required
									oninput="validatePasswords()">
								<!-- Error message -->
								<div id="passwordError" class="text-danger mt-1"
									style="display: none;">Passwords do not match.</div>
									<i class="fas fa-check-circle tick-icon" id="portIDTick"></i>
							</div>

							<div class="mb-3">
								<label for="location" class="form-label">Location</label> <input
									type="text" id="location" name="location" class="form-control" required
									>
									<i class="fas fa-check-circle tick-icon" id="portIDTick"></i>
							</div>

							<div class="mb-3">
								<label for="role" class="form-label">Role</label> <select
									id="role" name="role" class="form-select" required>
									<option value="">Select Role</option>
									<option value="consumer">Consumer</option>
									<option value="seller">Seller</option>
								</select>
							</div>

							<input type="hidden" name="action" value="register">
							<div class="d-grid">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</form>

						<div class="text-center mt-3">
							<p style="color:white;">
								Already have an account? <a href="login.jsp" style="color:yellow;">Login here</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS and Popper.js -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

	
	
	<script>
    function validatePasswords() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const errorDiv = document.getElementById('passwordError');

        if (password !== confirmPassword) {
            errorDiv.style.display = 'block'; // Show error message
        } else {
            errorDiv.style.display = 'none'; // Hide error message
        }
    }
</script>
	
</body>
</html>

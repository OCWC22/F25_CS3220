<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login - SessionHandler</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap Icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
  <!-- Custom CSS -->
  <link rel="stylesheet" href="assets/css/Styles.css" />
</head>
<body>
  <div class="login-container">
    <div class="login-form-container text-center">
      <h2 class="mb-2">Login</h2>
      
      <!-- Error message display (regular CSS version) -->
      <% if (request.getAttribute("error") != null) { %>
        <div class="error-message">
          <%= request.getAttribute("error") %>
        </div>
      <% } %>
      
      <!-- Bootstrap alert version for error -->
      <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger" role="alert">
          <%= request.getAttribute("error") %>
        </div>
      <% } %>
      
      <!-- Logout message display -->
      <% if (request.getAttribute("logoutMessage") != null) { %>
        <div class="logout-message">
          <%= request.getAttribute("logoutMessage") %>
        </div>
      <% } %>
      
      <form method="post" action="Login">
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" name="email" 
                 placeholder="Please enter your email" required>
        </div>
        
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input type="password" class="form-control" id="password" name="password" 
                 placeholder="Please enter Password" required>
        </div>
        
        <button type="submit" class="btn btn-primary w-100 mb-3">Login</button>
      </form>
    </div>
  </div>

  <!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

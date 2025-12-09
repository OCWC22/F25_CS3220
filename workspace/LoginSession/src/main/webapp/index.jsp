<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Styles.css">
</head>
<body class="auth-body">
  <main class="auth-card" role="main">
    <h1>LOGIN</h1>
    <p>Please enter your login and password!</p>

    <% 
      String error = (String) request.getAttribute("error"); 
      String message = (String) request.getAttribute("message");
    %>
    <% if (message != null) { %>
      <div class="success-message"><%= message %></div>
    <% } %>
    <% if (error != null) { %>
      <div class="auth-error"><%= error %></div>
    <% } %>

    <form method="post" action="<%=request.getContextPath()%>/login" class="auth-form" novalidate>
      <input type="email" class="auth-input" name="email" required>
      <label class="input-label">Email</label>
      
      <input type="password" class="auth-input" name="password" required>
      <label class="input-label">Password</label>
      
      <button class="login-button" type="submit">Login</button>
    </form>

    <div class="social-bar" aria-label="Social login options">
      <a class="social-link" href="#" aria-label="Continue with Facebook">
        <span class="social-icon">f</span>
      </a>
      <a class="social-link" href="#" aria-label="Continue with X">
        <span class="social-icon">X</span>
      </a>
      <a class="social-link" href="#" aria-label="Continue with Google">
        <span class="social-icon">G</span>
      </a>
    </div>

    <div class="signup-cta">
      Don't have an account? <a href="<%=request.getContextPath()%>/register">Sign Up</a>
    </div>
  </main>
</body>
</html>


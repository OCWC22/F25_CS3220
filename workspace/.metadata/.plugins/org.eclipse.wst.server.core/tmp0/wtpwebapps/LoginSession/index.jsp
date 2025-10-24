<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-M09S+Fpg9+1rssZCvGSqtEtFPi0P5xGX2YeliYg+6TS//25kB4ezgmO6ijLJMEVN6M8tnR16vuMJ6xY0pDm1Dw==" crossorigin="anonymous" referrerpolicy="no-referrer">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
</head>
<body class="auth-body">
  <main class="auth-card" role="main">
    <h1>LOGIN</h1>
    <p>Please enter your login and password!</p>

    <% String error = (String) request.getAttribute("error"); %>
    <% String message = (String) request.getAttribute("message"); %>
    <% if (message != null) { %>
      <div class="feedback-message success"><%= message %></div>
    <% } %>
    <% if (error != null) { %>
      <div class="feedback-message error"><%= error %></div>
    <% } %>

    <form method="post" action="<%=request.getContextPath()%>/login" class="auth-form" novalidate>
      <input type="email" class="auth-input" name="email" placeholder="Email" required>
      <input type="password" class="auth-input" name="password" placeholder="Password" required>
      <div class="helper-links">
        <a href="<%=request.getContextPath()%>/register.jsp">Create New Account</a>
      </div>
      <button class="primary-button" type="submit">Login</button>
    </form>

    <div class="social-bar" aria-label="Social login options">
      <a class="social-link" href="#" aria-label="Continue with Facebook">
        <i class="fa-brands fa-facebook-f" aria-hidden="true"></i>
      </a>
      <a class="social-link" href="#" aria-label="Continue with X">
        <i class="fa-brands fa-x-twitter" aria-hidden="true"></i>
      </a>
      <a class="social-link" href="#" aria-label="Continue with Google">
        <i class="fa-brands fa-google" aria-hidden="true"></i>
      </a>
    </div>

    <div class="signup-cta">
      Don't have an account? <a href="<%=request.getContextPath()%>/register.jsp">Sign Up</a>
    </div>
  </main>
</body>
</html>


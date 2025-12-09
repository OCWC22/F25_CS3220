<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Create Account</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Styles.css">
</head>
<body class="auth-body">
  <main class="auth-card" role="main">
    <h1>REGISTER</h1>
    <p>Fill in every field to create your account.</p>

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
      <div class="auth-error"><%= error %></div>
    <% } %>

    <form method="post" action="<%=request.getContextPath()%>/register" class="auth-form" novalidate>
      <input type="text" class="auth-input" name="name" required>
      <label class="input-label">Full Name</label>

      <input type="email" class="auth-input" name="email" required>
      <label class="input-label">Email</label>

      <input type="password" class="auth-input" name="password" required>
      <label class="input-label">Password</label>

      <p class="required-fields">All fields are required.</p>
      <button class="register-button" type="submit">Create Account</button>
    </form>

    <div class="signup-cta">
      Already have an account? <a href="<%=request.getContextPath()%>/index.jsp">Back to Login</a>
    </div>
  </main>
</body>
</html>

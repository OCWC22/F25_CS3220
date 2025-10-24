<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Register</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-M09S+Fpg9+1rssZCvGSqtEtFPi0P5xGX2YeliYg+6TS//25kB4ezgmO6ijLJMEVN6M8tnR16vuMJ6xY0pDm1Dw==" crossorigin="anonymous" referrerpolicy="no-referrer">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
</head>
<body class="auth-body">
  <main class="auth-card" role="main">
    <h1>REGISTER</h1>

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
      <div class="feedback-message error"><%= error %></div>
    <% } %>

    <form method="post" action="<%=request.getContextPath()%>/register" class="auth-form" novalidate>
      <input type="email" class="auth-input" name="email" placeholder="Email" required>
      <input type="text" class="auth-input" name="name" placeholder="Name" required>
      <input type="password" class="auth-input" name="password" placeholder="Password" required>
      <button class="primary-button" type="submit">Register</button>
    </form>

    <div class="signup-cta">
      Already have an account? <a href="<%=request.getContextPath()%>/index.jsp">Login</a>
    </div>
  </main>
</body>
</html>


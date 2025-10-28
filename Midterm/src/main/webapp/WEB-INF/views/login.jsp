<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>User Login</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet">
</head>
<body class="app-bg">
  <div class="container py-5">
    <div class="app-card mx-auto">
      <h3 class="app-title">User Login</h3>

      <form method="post" action="${pageContext.request.contextPath}/Login">
        <table class="login-table">
          <tbody>
            <tr>
              <td class="label-cell">Email:</td>
              <td>
                <input
                  name="email"
                  type="email"
                  class="table-input"
                  placeholder="Please enter your email"
                  required
                  value="${requestScope.emailValue != null ? requestScope.emailValue : ''}">
              </td>
            </tr>
            <tr>
              <td class="label-cell">Password:</td>
              <td>
                <input
                  name="password"
                  type="password"
                  class="table-input"
                  placeholder="Please enter your passw"
                  required>
              </td>
            </tr>
          </tbody>
        </table>

        <div class="login-error">
          ${requestScope.error != null ? requestScope.error : ''}
        </div>
        <div class="login-success">
          ${requestScope.logoutMsg != null ? requestScope.logoutMsg : ''}
        </div>

        <div class="btn-row" role="group" aria-label="Login and Register actions">
          <button class="btn btn-primary px-4">Login</button>
          <a href="${pageContext.request.contextPath}/Register" class="btn btn-primary px-4">Register</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>

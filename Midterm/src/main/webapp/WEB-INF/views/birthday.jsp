<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, cs3220.model.BirthdayEntity" %>
<!DOCTYPE html>
<html>
<head>
  <title>Birthday</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet">
</head>
<body class="app-bg">
  <div class="container py-5">
    <div class="app-card mx-auto">
      <h3 class="app-title">Welcome ${requestScope.user.name}</h3>

      <table class="table app-table mb-3">
        <thead>
          <tr>
            <th class="w-50">Name</th>
            <th>Birthday Date</th>
            <th class="text-center">Edit | Delete</th>
          </tr>
        </thead>
        <tbody>
        <%
          List<BirthdayEntity> list = (List<BirthdayEntity>) request.getAttribute("list");
          if (list != null) {
            for (BirthdayEntity b : list) {
        %>
          <tr>
            <td><%= b.getFriendName() %></td>
            <td><%= b.getFormatted() %></td>
            <td class="text-center">
              <a href="${pageContext.request.contextPath}/UpdateBirthday?id=<%= b.getId() %>">Edit</a> |
              <a href="${pageContext.request.contextPath}/DeleteBirthday?id=<%= b.getId() %>">Delete</a>
            </td>
          </tr>
        <%
            }
          }
        %>
        </tbody>
      </table>

      <div class="d-flex gap-2">
        <a href="${pageContext.request.contextPath}/AddBirthday" class="btn btn-primary">Add New Birthday</a>
        <form method="post" action="${pageContext.request.contextPath}/Logout">
          <button class="btn btn-outline-primary">Logout</button>
        </form>
      </div>
    </div>
  </div>
</body>
</html>

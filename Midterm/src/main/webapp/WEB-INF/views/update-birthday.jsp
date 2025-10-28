<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cs3220.model.BirthdayEntity" %>
<!DOCTYPE html>
<html>
<head>
  <title>Update Birthday</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet">
</head>
<body class="app-bg">
  <div class="container py-5">
    <div class="app-card mx-auto">
      <h3 class="app-title">Update Birthday</h3>

      <%
        BirthdayEntity b = (BirthdayEntity) request.getAttribute("b");
        int curDay = b.getDay();
        int curMonth = b.getMonth();
      %>
      <form method="post" action="${pageContext.request.contextPath}/UpdateBirthday">
        <input type="hidden" name="id" value="<%= b.getId() %>"/>

        <table class="login-table">
          <tbody>
            <tr>
              <td class="label-cell">Name:</td>
              <td>
                <input name="name" type="text" class="table-input" required value="<%= b.getFriendName() %>">
              </td>
            </tr>
            <tr>
              <td class="label-cell">Birthday:</td>
              <td>
                <select name="day" class="table-input" style="max-width:100px; height:28px;">
                  <% for (int d = 1; d <= 31; d++) { %>
                    <option value="<%= d %>" <%= d==curDay ? "selected" : "" %>><%= d %></option>
                  <% } %>
                </select>
                <select name="month" class="table-input" style="max-width:180px; height:28px;">
                  <% String[] m = {"January","February","March","April","May","June","July","August","September","October","November","December"};
                     for (int i=0;i<m.length;i++){ int mm=i+1; %>
                    <option value="<%= mm %>" <%= mm==curMonth ? "selected" : "" %>><%= m[i] %></option>
                  <% } %>
                </select>
              </td>
            </tr>
          </tbody>
        </table>

        <div class="btn-row">
          <button class="btn btn-primary">Update Birthday</button>
          <a href="${pageContext.request.contextPath}/Birthday" class="btn btn-primary">Back to Birthday</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>

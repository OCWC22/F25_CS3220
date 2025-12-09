<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cs3220.model.UserEntry" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Download Center</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/Styles.css">
</head>
<body class="download-body">
  <%
    UserEntry user = (UserEntry) session.getAttribute("user");
    String[][] files = new String[][] {
      {"Apples Showcase", "apples.jpg", "Glossy honeycrisp apples ready for download."},
      {"Oranges Showcase", "oranges.jpg", "Sun-kissed oranges with vibrant highlights."},
      {"Bananas Showcase", "bananas.jpg", "Perfectly ripe bananas captured in studio light."}
    };
  %>

  <div class="logout-container">
    <form action="<%=request.getContextPath()%>/logout" method="post">
      <button type="submit" class="logout-button">Logout</button>
    </form>
  </div>

  <section class="download-container" aria-labelledby="download-heading">
    <header class="mb-4 text-center">
      <h1 id="download-heading">Download Portal</h1>
      <% if (user != null) { %>
        <p class="lead mb-0">Welcome, <strong><%= user.getName() %></strong>! Grab the high-res assets below.</p>
      <% } %>
    </header>

    <table class="download-table" aria-describedby="download-heading">
      <thead>
        <tr>
          <th scope="col" class="col-number">#</th>
          <th scope="col" class="col-filename">Image</th>
          <th scope="col" class="col-preview">Preview</th>
          <th scope="col" class="col-download">Download</th>
        </tr>
      </thead>
      <tbody>
        <% for (int i = 0; i < files.length; i++) {
             String title = files[i][0];
             String fileName = files[i][1];
             String description = files[i][2];
        %>
          <tr class="highlighted-row">
            <td class="col-number"><%= i + 1 %></td>
            <td class="col-filename">
              <div><strong><%= title %></strong></div>
              <small class="text-muted"><%= description %></small>
            </td>
            <td class="col-preview">
              <img class="preview-image" src="<%=request.getContextPath()%>/assets/images/<%= fileName %>" alt="<%= title %> preview">
            </td>
            <td class="col-download">
              <a class="download-button" href="<%=request.getContextPath()%>/download?file=<%= fileName %>">Download</a>
            </td>
          </tr>
        <% } %>
      </tbody>
    </table>
  </section>
</body>
</html>

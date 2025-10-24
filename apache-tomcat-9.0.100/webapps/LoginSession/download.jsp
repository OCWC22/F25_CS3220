<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cs3220.model.UserEntry" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Download images</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
</head>
<body class="container py-5">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h1 class="m-0">Download</h1>
    <form method="post" action="<%=request.getContextPath()%>/logout">
      <button class="btn btn-outline-danger" type="submit">Logout</button>
    </form>
  </div>

  <div class="mb-3">
    <% UserEntry u = (UserEntry) session.getAttribute("user"); %>
    <p class="lead">Welcome, <strong><%= u != null ? u.getName() : "" %></strong></p>
  </div>

  <div class="row g-4">
    <div class="col-md-4">
      <div class="card h-100 shadow-sm">
        <img src="<%=request.getContextPath()%>/images/sample1.jpg" class="card-img-top" alt="Sample 1">
        <div class="card-body">
          <h5 class="card-title">Sample 1</h5>
          <a class="btn btn-primary" href="<%=request.getContextPath()%>/download?file=sample1.jpg">Download</a>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card h-100 shadow-sm">
        <img src="<%=request.getContextPath()%>/images/sample2.jpg" class="card-img-top" alt="Sample 2">
        <div class="card-body">
          <h5 class="card-title">Sample 2</h5>
          <a class="btn btn-primary" href="<%=request.getContextPath()%>/download?file=sample2.jpg">Download</a>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card h-100 shadow-sm">
        <img src="<%=request.getContextPath()%>/images/sample3.jpg" class="card-img-top" alt="Sample 3">
        <div class="card-body">
          <h5 class="card-title">Sample 3</h5>
          <a class="btn btn-primary" href="<%=request.getContextPath()%>/download?file=sample3.jpg">Download</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>

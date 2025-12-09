<#ftl output_format="HTML">
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>US Holidays</title>
  <link rel="stylesheet" href="/css/Styles.css">
</head>
<body class="bg">
<div class="page-wrapper">
  <h1>US Holidays</h1>

  <table class="holiday-table">
    <thead>
    <tr>
      <th>Date</th>
      <th>Holiday</th>
      <th>Update / Delete</th>
    </tr>
    </thead>
    <tbody>
    <#list holidays as h>
      <tr>
        <td>${h.displayDate}</td>
        <td>${h.holiday}</td>
        <td>
          <a href="/holidays/${h.id}/edit">Update</a> |
          <a href="/holidays/${h.id}/delete">Delete</a>
        </td>
      </tr>
    </#list>
    </tbody>
  </table>

  <div class="actions">
    <a class="action-button" href="/holidays/add">Add Holiday</a>
  </div>
</div>
</body>
</html>

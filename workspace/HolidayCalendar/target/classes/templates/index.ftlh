<#ftl output_format="HTML">
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>US Holidays</title>
  <link rel="stylesheet" href="/css/Styles.css">
</head>
<body class="bg">
<div class="container">
  <h1>US Holidays</h1>

  <table class="holidays">
    <thead>
    <tr>
      <th>Date</th>
      <th>Holiday</th>
      <th>Update / Delete</th>
    </tr>
    </thead>
    <tbody>
    <#list holidays as h>
      <tr class="rowHover">
        <td>${h.holidayDate?string["dd MMMM yyyy"]}</td>
        <td>${h.holiday}</td>
        <td>
          <a href="/holidays/${h.id}/edit">Update</a> | 
          <a href="/holidays/${h.id}/delete">Delete</a>
        </td>
      </tr>
    </#list>
    </tbody>
  </table>

  <div class="center">
    <a class="btn" href="/holidays/add">Add Holiday</a>
  </div>
</div>
</body>
</html>

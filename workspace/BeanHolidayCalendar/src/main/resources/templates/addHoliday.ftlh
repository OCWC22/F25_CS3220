<#ftl output_format="HTML">
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Holiday</title>
  <link rel="stylesheet" href="/css/Styles.css">
</head>
<body class="bg">
<div class="form-wrapper">
  <h1>Add Holiday</h1>

  <form action="/holidays/add" method="post" class="holiday-form">
    <div class="form-row">
      <label>Holiday Date:</label>

      <select name="day">
        <#list days as d>
          <option value="${d}" <#if d == (day!0)>selected</#if>>${d}</option>
        </#list>
      </select>

      <select name="month">
        <#list months as m>
          <#assign mName = m?string?lower_case?cap_first>
          <option value="${mName}" <#if (month!"")?lower_case == mName?lower_case>selected</#if>>${mName}</option>
        </#list>
      </select>

      <select name="year">
        <#list years as y>
          <option value="${y}" <#if y == (year!0)>selected</#if>>${y}</option>
        </#list>
      </select>
    </div>

    <div class="form-row">
      <label>Holiday Name:</label>
      <input type="text" name="name" value="${name!}" />
    </div>

    <#if error??>
      <div class="error-message">${error}</div>
    </#if>

    <div class="form-actions">
      <button class="action-button" type="submit">Add Holiday</button>
    </div>
  </form>
</div>
</body>
</html>

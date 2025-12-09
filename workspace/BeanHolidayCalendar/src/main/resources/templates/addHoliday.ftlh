<#ftl output_format="HTML">
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Holiday</title>
  <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="/css/Styles.css">
</head>
<body class="bg">
<div class="form-wrapper">
  <h1>Add Holiday</h1>

  <form action="/holidays/add" method="post" class="holiday-form needs-validation">
    <div class="form-row flex-wrap gap-3">
      <label>Holiday Date:</label>

      <select name="day" class="form-select form-select-lg date-select">
        <#list days as d>
          <option value="${d}" <#if (holidayForm.day!0) == d>selected</#if>>${d}</option>
        </#list>
      </select>

      <select name="month" class="form-select form-select-lg date-select">
        <#list months as m>
          <#assign mName = m?string?lower_case?cap_first>
          <option value="${mName}" <#if (holidayForm.month!"")?lower_case == mName?lower_case>selected</#if>>${mName}</option>
        </#list>
      </select>

      <select name="year" class="form-select form-select-lg date-select">
        <#list years as y>
          <option value="${y?c}" <#if (holidayForm.year!0) == y>selected</#if>>${y?c}</option>
        </#list>
      </select>
    </div>


    <div class="form-row flex-column align-items-start">
      <label>Holiday Name:</label>
      <input type="text"
             class="form-control form-control-lg"
             name="name"
             value="${holidayForm.name!""}" />
    </div>

    <div class="form-actions">
      <button class="action-button" type="submit">Add Holiday</button>
    </div>
  </form>
</div>
</body>
</html>

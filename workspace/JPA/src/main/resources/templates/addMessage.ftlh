<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Message</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <h1 class="text-center">${user.name}<#if user.name?ends_with("s")>'<#else>'s</#if> New Message</h1>

    <form action="/addMessage" method="post">
        <textarea name="message" class="form-control message-textarea" rows="10"></textarea>

        <#if messageError??>
            <div class="error text-center">${messageError}</div>
        </#if>

        <div class="button-row">
            <a href="/guestbook" class="btn btn-primary">Back</a>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>
</body>
</html>

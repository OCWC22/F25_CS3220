<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Message</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <h1 class="text-center">Edit ${entry.user.name}<#if entry.user.name?ends_with("s")>'<#else>'s</#if> Message</h1>

    <form action="/editMessage" method="post">
        <input type="hidden" name="id" value="${entry.id}">
        <textarea name="message" class="form-control message-textarea" rows="10">${entry.message}</textarea>

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

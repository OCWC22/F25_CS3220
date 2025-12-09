<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Guest Book</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <h1 class="text-center">Welcome ${user.name}</h1>

    <div class="button-row">
        <a href="/addMessage" class="btn btn-primary">New Message</a>
        <a href="/logout" class="btn btn-primary">Logout</a>
    </div>

    <#if messages?size == 0>
        <p class="text-center no-message">No Message</p>
    <#else>
        <table class="message-table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Message</th>
                    <th>Date</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <#list messages as msg>
                    <tr class="<#if msg?index % 2 == 0>row-even<#else>row-odd</#if>">
                        <td>${msg.user.name}</td>
                        <td>${msg.message}</td>
                        <td>${msg.formattedDate}</td>
                        <td>
                            <a href="/editMessage?id=${msg.id}" class="btn btn-primary btn-sm">Edit</a>
                        </td>
                        <td>
                            <a href="/deleteMessage?id=${msg.id}" class="btn btn-primary btn-sm">Delete</a>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </#if>
</div>
</body>
</html>

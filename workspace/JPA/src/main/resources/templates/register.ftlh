<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <h1 class="text-center">Register</h1>

    <form action="/register" method="post">
        <table class="form-table">
            <tr>
                <td><label>Email</label></td>
                <td>
                    <input type="text" name="email" value="${email!''}" class="form-control">
                    <#if emailError??><span class="error">${emailError}</span></#if>
                </td>
            </tr>
            <tr>
                <td><label>Name</label></td>
                <td>
                    <input type="text" name="name" value="${name!''}" class="form-control">
                </td>
            </tr>
            <tr>
                <td><label>Password</label></td>
                <td>
                    <input type="password" name="password" class="form-control">
                    <#if passwordError??><span class="error">${passwordError}</span></#if>
                </td>
            </tr>
        </table>

        <#if duplicateError??>
            <div class="error text-center">${duplicateError}</div>
        </#if>

        <div class="button-row">
            <a href="/" class="btn btn-primary">Login</a>
            <button type="submit" class="btn btn-primary">Register</button>
        </div>
    </form>
</div>
</body>
</html>

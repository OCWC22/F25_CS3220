<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <h1 class="text-center">Login</h1>

    <form action="/login" method="post">
        <table class="form-table">
            <tr>
                <td><label>Email</label></td>
                <td>
                    <input type="text" name="email" value="${email!''}" class="form-control">
                    <#if emailError??><span class="error">${emailError}</span></#if>
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

        <#if authError??>
            <div class="error text-center">${authError}</div>
        </#if>

        <div class="button-row">
            <button type="submit" class="btn btn-primary">Login</button>
            <a href="/register" class="btn btn-primary">Register</a>
        </div>
    </form>
</div>
</body>
</html>

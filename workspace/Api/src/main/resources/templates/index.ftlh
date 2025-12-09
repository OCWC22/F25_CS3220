<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab 13 – Login & Registration</title>
    <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="bg-light">
<section class="auth-shell container py-5">
    <h1 class="text-center mb-4">CS3220 Message Board</h1>
    <div class="row g-4">
        <div class="col-md-6">
            <div class="card shadow-sm h-100">
                <div class="card-body">
                    <h2 class="card-title h4">Register</h2>
                    <form id="register-form" class="mt-3" novalidate>
                        <div class="mb-3">
                            <label class="form-label" for="register-full-name">Full Name</label>
                            <input class="form-control" type="text" id="register-full-name" required minlength="2">
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="register-username">Username</label>
                            <input class="form-control" type="text" id="register-username" required minlength="3">
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="register-password">Password</label>
                            <input class="form-control" type="password" id="register-password" required minlength="6">
                        </div>
                        <button class="btn btn-primary w-100" type="submit">Create Account</button>
                        <div class="form-text mt-2" id="register-feedback"></div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card shadow-sm h-100">
                <div class="card-body">
                    <h2 class="card-title h4">Login</h2>
                    <form id="login-form" class="mt-3" novalidate>
                        <div class="mb-3">
                            <label class="form-label" for="login-username">Username</label>
                            <input class="form-control" type="text" id="login-username" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="login-password">Password</label>
                            <input class="form-control" type="password" id="login-password" required>
                        </div>
                        <button class="btn btn-success w-100" type="submit">Login</button>
                        <div class="form-text mt-2" id="login-feedback"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="/javascript/LoginAndRegistrationAndMessageBoard.js"></script>
</body>
</html>

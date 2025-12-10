<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Final</title>
    <link rel="stylesheet" href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/Styles.css">
</head>
<body>
    <div class="auth-container">
        <!-- Login Form -->
        <div id="login-view" class="auth-card">
            <h1 class="auth-title">Login here</h1>
            <p class="auth-subtitle">Welcome back, let's get to work!</p>

            <form id="login-form" novalidate>
                <div class="mb-3">
                    <input type="email" class="form-control auth-input" id="login-email" placeholder="Email" required>
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control auth-input" id="login-password" placeholder="Password" required>
                </div>
                <div class="auth-feedback" id="login-feedback"></div>
                <button type="submit" class="btn auth-btn-primary">Login</button>
            </form>

            <p class="auth-link" id="show-register">Create new account</p>
        </div>

        <!-- Register Form -->
        <div id="register-view" class="auth-card" style="display: none;">
            <h1 class="auth-title">Create Account</h1>
            <p class="auth-subtitle">Create an account so you can start working!</p>

            <form id="register-form" novalidate>
                <div class="mb-3">
                    <input type="email" class="form-control auth-input" id="register-email" placeholder="Email" required>
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control auth-input" id="register-name" placeholder="Name" required>
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control auth-input" id="register-password" placeholder="Password" required>
                </div>
                <div class="auth-feedback" id="register-feedback"></div>
                <button type="submit" class="btn auth-btn-primary">Register</button>
            </form>

            <p class="auth-link" id="show-login">Already have an account</p>
        </div>
    </div>

    <script src="/scripts/index.js"></script>
</body>
</html>

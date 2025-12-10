/**
 * CS3220 Final - Login & Register SPA
 * Handles form switching and API calls for authentication.
 */

document.addEventListener('DOMContentLoaded', () => {
    // Elements
    const loginView = document.getElementById('login-view');
    const registerView = document.getElementById('register-view');
    const showRegisterBtn = document.getElementById('show-register');
    const showLoginBtn = document.getElementById('show-login');
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');
    const loginFeedback = document.getElementById('login-feedback');
    const registerFeedback = document.getElementById('register-feedback');

    // If user is already logged in, redirect to Clocked page
    const existingUser = sessionStorage.getItem('user');
    if (existingUser) {
        window.location.href = '/Clocked';
        return;
    }

    // Toggle between login and register views (SPA behavior)
    showRegisterBtn.addEventListener('click', () => {
        loginView.style.display = 'none';
        registerView.style.display = 'block';
        clearFeedback();
    });

    showLoginBtn.addEventListener('click', () => {
        registerView.style.display = 'none';
        loginView.style.display = 'block';
        clearFeedback();
    });

    // Login form submission
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        clearFeedback();

        const email = document.getElementById('login-email').value.trim();
        const password = document.getElementById('login-password').value;

        if (!email || !password) {
            showFeedback(loginFeedback, 'Please fill in all fields', false);
            return;
        }

        try {
            const response = await fetch('/api/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            const data = await response.json();

            if (!response.ok) {
                showFeedback(loginFeedback, data.error || 'Login failed', false);
                return;
            }

            // Store user in session and redirect
            sessionStorage.setItem('user', JSON.stringify(data.user));
            window.location.href = '/Clocked';

        } catch (error) {
            console.error('Login error:', error);
            showFeedback(loginFeedback, 'Network error. Please try again.', false);
        }
    });

    // Register form submission
    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        clearFeedback();

        const email = document.getElementById('register-email').value.trim();
        const name = document.getElementById('register-name').value.trim();
        const password = document.getElementById('register-password').value;

        if (!email || !name || !password) {
            showFeedback(registerFeedback, 'Please fill in all fields', false);
            return;
        }

        try {
            const response = await fetch('/api/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, name, password })
            });

            const data = await response.json();

            if (!response.ok) {
                showFeedback(registerFeedback, data.error || 'Registration failed', false);
                return;
            }

            // Show success and switch to login view
            showFeedback(registerFeedback, 'Account created! Please login.', true);
            
            // Auto switch to login after short delay
            setTimeout(() => {
                registerView.style.display = 'none';
                loginView.style.display = 'block';
                clearFeedback();
            }, 1500);

        } catch (error) {
            console.error('Register error:', error);
            showFeedback(registerFeedback, 'Network error. Please try again.', false);
        }
    });

    // Helper functions
    function showFeedback(element, message, isSuccess) {
        element.textContent = message;
        element.classList.toggle('success', isSuccess);
    }

    function clearFeedback() {
        loginFeedback.textContent = '';
        registerFeedback.textContent = '';
        loginFeedback.classList.remove('success');
        registerFeedback.classList.remove('success');
    }
});

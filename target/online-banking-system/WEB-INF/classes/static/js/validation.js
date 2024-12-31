document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');

    // Login form validation
    if (loginForm) {
        loginForm.addEventListener('submit', function (event) {
            let valid = true;
            const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            if (!emailRegex.test(email.value)) {
                email.classList.add('is-invalid');
                valid = false;
            } else {
                email.classList.remove('is-invalid');
            }

            if (password.value.length < 8) {
                password.classList.add('is-invalid');
                valid = false;
            } else {
                password.classList.remove('is-invalid');
            }

            if (!valid) {
                event.preventDefault();
            }
        });
    }

    // Registration form validation
    if (registerForm) {
        registerForm.addEventListener('submit', function (event) {
            let valid = true;

            // Password confirmation
            if (password.value !== confirmPassword.value) {
                confirmPassword.classList.add('is-invalid');
                valid = false;
            } else {
                confirmPassword.classList.remove('is-invalid');
            }

            if (!valid) {
                event.preventDefault();
            }
        });
    }
});

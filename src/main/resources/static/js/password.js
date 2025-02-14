// Password toggle for the main password
const togglePassword = document.getElementById('toggle-password');
const passwordInput = document.getElementById('password');

// Confirm password toggle
const toggleConfirmPassword = document.getElementById('toggle-confirm-password');
const confirmPasswordInput = document.getElementById('confirm-password');

// Function to toggle password visibility
function togglePasswordVisibility(inputField, toggleIcon) {
    const type = inputField.type === 'password' ? 'text' : 'password';
    inputField.type = type;

    if (type === 'password') {
        toggleIcon.classList.remove('bx-show');
        toggleIcon.classList.add('bx-hide');
    } else {
        toggleIcon.classList.remove('bx-hide');
        toggleIcon.classList.add('bx-show');
    }
}

// Add event listener to toggle the password visibility for both fields
togglePassword.addEventListener('click', function () {
    togglePasswordVisibility(passwordInput, togglePassword);
});

toggleConfirmPassword.addEventListener('click', function () {
    togglePasswordVisibility(confirmPasswordInput, toggleConfirmPassword);
});

// Function to hide a message after a delay
function hideMessage(elementId, delay) {
    setTimeout(function() {
        var messageElement = document.getElementById(elementId);
        if (messageElement) {
            messageElement.style.display = 'none'; // Hide the message
        }
    }, delay);
}

// Check if the success message exists and hide it after 3 seconds
if (document.getElementById('successMessage')) {
    hideMessage('successMessage', 3000); // 3000 milliseconds = 3 seconds
}

// Check if the error message exists and hide it after 3 seconds
if (document.getElementById('errorMessage')) {
    hideMessage('errorMessage', 3000); // 3000 milliseconds = 3 seconds
}

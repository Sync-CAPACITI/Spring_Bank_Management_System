let toastBox = document.getElementById('toastBox');

function showToast(msg, type) {
    let toast = document.createElement('div');
    toast.classList.add('toast');

    // Add icons based on type
    let icon;
    if (type === 'error') {
        icon = '<i class="bx bxs-x-circle"></i>';
    } else {
        icon = '<i class="bx bxs-badge-check"></i>';
    }

    toast.innerHTML = `${icon} ${msg}`;
    toastBox.appendChild(toast);

    if (type === 'error') {
        toast.classList.add('error');
    } else if (type === 'invalid') {
        toast.classList.add('invalid');
    }

    setTimeout(() => {
        toast.remove();
    }, 8000);
}

// Check if backend has provided a toast message
document.addEventListener("DOMContentLoaded", function () {
    const toastMessage = document.body.dataset.toastMessage;
    const toastType = document.body.dataset.toastType;

    if (toastMessage) {
        showToast(toastMessage, toastType);
    }
});

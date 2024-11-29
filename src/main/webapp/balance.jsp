<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Balance</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Link to your CSS if needed -->
</head>
<body>
    <h1>Current Balance</h1>

    <div id="balance-container">
        <p>Your current balance is:</p>
        <h2 id="balance-amount">Loading...</h2>
    </div>

    <script>
        // Fetch the balance from the backend
        fetch('/api/balance')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch balance.');
                }
                return response.text(); // Balance will be returned as plain text
            })
            .then(data => {
                document.getElementById('balance-amount').textContent = `₹ ${data}`;
            })
            .catch(error => {
                document.getElementById('balance-amount').textContent = 'Error fetching balance.';
                console.error(error);
            });
    </script>
</body>
</html>

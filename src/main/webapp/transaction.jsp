<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Transactions</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Update with your styles -->
</head>
<body>
    <h1>Bank Transactions</h1>

    <!-- Display Current Balance -->
    <h2>Current Balance: <span id="currentBalance">Loading...</span></h2>

    <!-- Deposit Form -->
    <h3>Deposit</h3>
    <form action="/deposit" method="post" id="depositForm">
        <label for="depositAmount">Enter Amount:</label>
        <input type="number" id="depositAmount" name="amount" step="0.01" required>
        <button type="submit">Deposit</button>
    </form>

    <!-- Withdrawal Form -->
    <h3>Withdrawal</h3>
    <form action="/withdraw" method="post" id="withdrawForm">
        <label for="withdrawAmount">Enter Amount:</label>
        <input type="number" id="withdrawAmount" name="amount" step="0.01" required>
        <button type="submit">Withdraw</button>
    </form>

    <!-- Transaction History -->
    <h3>Transaction History</h3>
    <table>
        <thead>
            <tr>
                <th>Transaction ID</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody id="transactionHistory">
            <!-- Transactions will be loaded here via JavaScript -->
        </tbody>
    </table>

    <script>
        // Fetch balance and transactions on page load
        document.addEventListener('DOMContentLoaded', function () {
            fetch('/api/balance')
                .then(response => response.text())
                .then(balance => {
                    document.getElementById('currentBalance').textContent = balance;
                });

            fetch('/api/transactions')
                .then(response => response.json())
                .then(transactions => {
                    const tbody = document.getElementById('transactionHistory');
                    tbody.innerHTML = transactions.map(txn => `
                        <tr>
                            <td>${txn.transaction_id}</td>
                            <td>${txn.transaction_type}</td>
                            <td>${txn.amount}</td>
                            <td>${txn.created_at}</td>
                        </tr>
                    `).join('');
                });
        });
    </script>
</body>
</html>

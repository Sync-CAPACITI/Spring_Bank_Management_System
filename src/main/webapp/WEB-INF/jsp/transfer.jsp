<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Transfer Funds</title>
</head>
<body>
    <h1>Transfer Funds</h1>
    <form action="/transfer" method="post">
        <label for="fromAccount">From Account:</label>
        <select name="fromAccount" id="fromAccount" required>
            <c:forEach var="account" items="${accounts}">
                <option value="${account.account_id}">${account.account_name} (R${account.balance})</option>
            </c:forEach>
        </select>
        <br><br>
        <label for="toAccount">To Account:</label>
        <select name="toAccount" id="toAccount" required>
            <c:forEach var="account" items="${accounts}">
                <option value="${account.account_id}">${account.account_name} (R${account.balance})</option>
            </c:forEach>
        </select>
        <br><br>
        <label for="amount">Amount:</label>
        <input type="number" name="amount" step="0.01" required>
        <br><br>
        <button type="submit">Transfer</button>
    </form>

    <p>${message}</p>
</body>
</html>

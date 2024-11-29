<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Transaction</title>
</head>
<body>
    <h2>New Transaction</h2>
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div style="color: green;">${success}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/transactions/add" method="post">
        <label for="from_account_id">From Account:</label><br>
        <select id="from_account_id" name="from_account_id" required>
            <c:forEach items="${accounts}" var="account">
                <option value="${account.account_id}">${account.account_number} - ${account.account_name}</option>
            </c:forEach>
        </select><br><br>

        <label for="transaction_type">Transaction Type:</label><br>
        <select id="transaction_type" name="transaction_type" required>
            <option value="DEPOSIT">Deposit</option>
            <option value="WITHDRAWAL">Withdrawal</option>
            <option value="TRANSFER">Transfer</option>
        </select><br><br>

        <label for="amount">Amount:</label><br>
        <input type="number" id="amount" name="amount" step="0.01" required><br><br>

        <label for="description">Description:</label><br>
        <input type="text" id="description" name="description"><br><br>

        <label for="to_account_id">To Account (for Transfer):</label><br>
        <select id="to_account_id" name="to_account_id">
            <c:forEach items="${accounts}" var="account">
                <option value="${account.account_id}">${account.account_number} - ${account.account_name}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>

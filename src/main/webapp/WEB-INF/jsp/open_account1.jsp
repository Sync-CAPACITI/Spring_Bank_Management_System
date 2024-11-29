<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Open Account</title>
</head>
<body>
    <h2>Open a New Bank Account</h2>
    <form action="${pageContext.request.contextPath}/api/accounts/create" method="post">
        <label for="user_id">User ID:</label><br>
        <input type="text" id="user_id" name="user_id" required><br><br>

        <label for="account_name">Account Name:</label><br>
        <input type="text" id="account_name" name="account_name" required><br><br>

        <label for="account_type">Account Type:</label><br>
        <input type="text" id="account_type" name="account_type" required><br><br>

        <input type="submit" value="Create Account">
    </form>
</body>
</html>

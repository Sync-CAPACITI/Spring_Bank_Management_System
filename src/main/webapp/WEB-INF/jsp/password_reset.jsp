<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Reset Your Password</title>
    <link rel="stylesheet" href="css/styles.css" />
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
  </head>

  <body>
    <div class="wrapper">
      <div class="form-box">
        <h2>Reset Your Password</h2>
        
        <!-- Display Message -->
        <c:if test="${requestScope.success != null}">
          <div class="alert alert-success text-center border border-success">
            <b>${requestScope.success}</b>
          </div>
        </c:if>

        <c:if test="${requestScope.error != null}">
          <div class="alert alert-danger text-center border border-danger">
            <b>${requestScope.error}</b>
          </div>
        </c:if>

        <!-- Password Reset Form -->
        <form action="/password_reset" method="POST">
          <input type="hidden" name="token" value="${token}" />

          <div class="input-box animation" >
            <form:input type="password" path="password" id="password"  required="true" />
            <label>New Password</label>
            <i id="toggle-password" class='bx bx-hide'></i>
            <form:errors path="password" class="text-white bg-danger" />

        </div>

        <div class="input-box animation">
            <input type="password" name="confirm_password" id="confirm-password" />
            <label>Confirm Password</label>
            <i id="toggle-confirm-password" class='bx bx-hide'></i>
            <small class="text-white bg-danger">${confirm_pass}</small>                        

        </div>

          <button type="submit" class="btn">Reset Password</button>
        </form>
      </div>
    </div>
    <script src="js/password.js"></script>

    <script src="js/main.js"></script>
  </body>
</html>

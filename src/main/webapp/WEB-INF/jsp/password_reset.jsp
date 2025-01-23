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
    <link rel="stylesheet" href="css/login.css" />
     <!-- PNG version for higher resolution -->
     <link rel="icon" href="images/bank.png" type="image/png">
     <link rel="stylesheet" href="../css/toast.css" />
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
  </head>

  <body data-toast-message="${toastMessage}" data-toast-type="${toastType}">
    <div class="wrapper">
        <span class="bg-animate"></span>
        <span class="bg-animate2"></span>
        <div class="form-box login">
            <h2 class="animation" style="--i:0; --j:21;">Password Reset</h2>

            
            <!-- End Of Display Message -->

            <!-- Password Reset Form -->
        <form:form action="/password_reset" method="POST" modelAttribute="passwordResetForm">
          <input type="hidden" name="token" value="${token}" />
      
          <div class="input-box animation">
              <form:input type="password" path="password" id="password" required="true" />
              <label>New Password</label>
              <i id="toggle-password" class='bx bx-hide'></i>
              <form:errors path="password" class="text-white bg-danger" />
          </div>
      
          <div class="input-box animation">
              <form:input type="password" path="confirmPassword" id="confirm-password" required="true" />
              <label>Confirm Password</label>
              <i id="toggle-confirm-password" class='bx bx-hide'></i>
              <form:errors path="confirmPassword" class="text-white bg-danger" />
          </div>
      
          <button type="submit" class="btn">Reset Password</button>
      </form:form>
        </div>

        <div class="info-text login">
            <h2 class="animation" style="--i:0; --j:20;">Profile Update</h2>
            <p class="animation" style="--i:1; --j:21;">
                Reseting your Account Login Password. Please be sure to remember it this time.
            </p>
        </div>
    </div>
    <div id="toastBox"></div>

    <script src="../js/toast.js"></script>
    <script src="js/login.js"></script>

    <!-- External JS for password toggle -->
    <script src="js/password.js"></script>

    <!-- message js-->
    <script src="/js/messages.js"></script>
  </body>
</html>

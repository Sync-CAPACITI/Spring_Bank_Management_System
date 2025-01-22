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
    <title>Request Password Reset</title>
    <link rel="stylesheet" href="css/login.css" />
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
     <!-- PNG version for higher resolution -->
     <link rel="icon" href="images/bank.png" type="image/png">
  </head>

  <body>
    <div class="wrapper">
        <span class="bg-animate"></span>
        <span class="bg-animate2"></span>
        <div class="form-box login">
            <h2 class="animation" style="--i:2; --j:23;">Requesting for a Password Reset</h2>

            <!-- Display Message -->
            <c:if test="${requestScope.success != null}">
                <div class="alert alert-success text-center border border-success">
                    <b>${requestScope.success}</b>
                </div>
            </c:if>
            <!-- End Of Display Message -->

            <!-- Display Message -->
            <c:if test="${requestScope.error != null}">
                <div class="alert alert-danger text-center border border-danger">
                    <b>${requestScope.error}</b>
                </div>
            </c:if>
            <!-- End Of Display Message -->

            <!-- Display Message -->
            <c:if test="${logged_out != null}">
                <div class="alert alert-info text-center border border-info">
                    <b>${logged_out}</b>
                </div>
            </c:if>
            <!-- End Of Display Message -->

            <form action="/password_reset_request" method="POST">
            <div class="input-box animation" style="--i:1; --j:22;">
                <input type="email" name="email" required />
                <label>Email</label>
                <i class="bx bxs-user"></i>
            </div>
            <div class="form-group col">
              <input type="hidden" name="token" value="${token}"/>
          </div>

            <button type="submit" class="btn animation">Request Password Reset</button>
            <div class="logreg-link animation" style="--i:3; --j:24;">
                 <!-- Forgot Password Link -->
            <p>
                <a href="/login" class="forgot-password-link animation" style="--i:5; --j:26;">Back to Login</a>
            </p>
            </div>
           
            </form>
        </div>

        <div class="info-text login">
            <h2 class="animation" style="--i:0; --j:20;">Requesting Password Reset</h2>
            <p class="animation" style="--i:1; --j:21;">
                Bank Better with PEAK, made for the people. By the people you can trust.
            </p>
        </div>
    </div>
    <script src="js/login.js"></script>
    <script src="js/password.js"></script>

    <!-- message js-->
    <script src="/js/messages.js"></script>
  </body>
</html>



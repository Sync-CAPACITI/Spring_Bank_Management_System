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
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css" />
    <link rel="stylesheet" href="css/toast.css" />
     <!-- PNG version for higher resolution -->
     <link rel="icon" href="images/bank.png" type="image/png">
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
            <h2 class="animation" style="--i:0; --j:21;">Login</h2>

            <form action="/login" method="POST">
            <div class="input-box animation" style="--i:1; --j:22;">
                <input type="email" name="email" required />
                <label>Email</label>
                <i class="bx bxs-user"></i>
            </div>
            <div class="input-box animation" style="--i:2; --j:23;">
                <input type="password" name="password" id="password" required />
                <label>Password</label>
                <i id="toggle-password" class='bx bx-hide'></i>
                <!-- Password toggle icon -->
            </div>

            <div class="form-group col">
              <input type="hidden" name="_token" value="${token}"/>
          </div>

            <button type="submit" class="btn animation">Login</button>
            <div class="logreg-link animation" style="--i:3; --j:24;">
                <p>
                Don't have an account?
                <a href="/register" class="register-link animation" style="--i:4; --j:25;">Sign Up</a>
                </p>

                <!-- Forgot Password Link -->
                <p>
                    <a href="/password_reset_request" class="forgot-password-link animation" style="--i:5; --j:26;">Forgot Password?</a>
                </p>
            </div>
            </form>
        </div>

        <div class="info-text login">
            <h2 class="animation" style="--i:0; --j:20;">Welcome Back</h2>
            <p class="animation" style="--i:1; --j:21;">
                Bank Better with PEAK, made for the people. By the people you can trust.
            </p>
        </div>
    </div>
    <div id="toastBox"></div>
    <script src="js/login.js"></script>

    <script src="js/toast.js"></script>

    <!-- External JS for password toggle -->
    <script src="js/password.js"></script>

    <!-- message js-->
    <script src="/js/messages.js"></script>
  </body>
</html>



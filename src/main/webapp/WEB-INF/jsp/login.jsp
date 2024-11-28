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
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
  </head>

  <body>
    <div class="wrapper">
        <span class="bg-animate"></span>
        <span class="bg-animate2"></span>
        <div class="form-box login">
            <h2 class="animation" style="--i:0; --j:21;">Login</h2>

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

            <form action="/login" method="POST">
            <div class="input-box animation" style="--i:1; --j:22;">
                <input type="email" name="email" required />
                <label>Email</label>
                <i class="bx bxs-user"></i>
            </div>
            <div class="input-box animation" style="--i:2; --j:23;">
                <input type="password" name="password" required />
                <label>Password</label>
                <i class="bx bxs-lock-alt"></i>
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
    <script src="js/login.js"></script>
  </body>
</html>



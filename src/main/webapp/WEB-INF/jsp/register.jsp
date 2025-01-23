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
        <title>Register</title>
        <link rel="stylesheet" href="css/login.css" />
        <link rel="stylesheet" href="css/toast.css" />
        <link
          href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
          rel="stylesheet"
        />
         <!-- PNG version for higher resolution -->
        <link rel="icon" href="images/bank.png" type="image/png">
      </head>
 
  <body data-toast-message="${toastMessage}" data-toast-type="${toastType}">
    <div class="wrapper">
        <span class="bg-animate"></span>
        <span class="bg-animate2"></span>
        <div class="form-box login reg-box">
            <h2 class="animation" style="--i:0; --j:21;">Register</h2>

            <form:form action="/register"  method="POST" modelAttribute="registerUser">


                <div class="input-box animation">
                    <form:input type="text" path="firstName" required="true" />
                    <label>First Name</label>
                    <i class="fa-duotone fa-light fa-input-text"></i>
                    <form:errors path="firstName" class="text-white bg-danger" />
                </div>

                <div class="input-box animation" >
                    <form:input type="text" path="lastName"  required="true" />
                    <label>Last Name</label>
                    <i class="fa-duotone fa-light fa-input-text"></i>
                    <form:errors path="lastName" class="text-white bg-danger" />
                </div>

            <div class="input-box animation" >
                <form:input type="email" path="email"  required="true" />
                <label>Email</label>
                <i class='bx bxs-envelope'></i>
                <form:errors path="email" class="text-white bg-danger" />
            </div>

            <div class="input-box animation" >
                <form:input type="number" path="idNum"  required="true" />
                <label>ID Number</label>
                <i class="fa-solid fa-book"></i>
                <form:errors path="idNum" class="text-white bg-danger" />                    

            </div>

            <div class="input-box animation" >
                <form:input type="password" path="password" id="password"  required="true" />
                <label>Password</label>
                <i id="toggle-password" class='bx bx-hide'></i>
                <form:errors path="password" class="text-white bg-danger" />

            </div>

            <div class="input-box animation">
                <input type="password" name="confirm_password" id="confirm-password" />
                <label>Confirm Password</label>
                <i id="toggle-confirm-password" class='bx bx-hide'></i>
                <small class="text-white bg-danger">${confirm_pass}</small>                        

            </div>
            

            <button type="submit" class="btn animation" style="--i:24; --j:7;">Sign Up</button>
            <div class="logreg-link animation" >
                <p>
                Already have an account?
                <a href="/login" class="login-link">Login</a>
                </p>
            </div>
        </form:form>
        </div>
 
        <div class="info-text login">
          <h2>Bank Better with PEAK</h2>
          <p>
            Join and move to the future with <br />
            PEAK for the people, made by <br />the people.
          </p>
        </div>
       
 
    </div>
    <div id="toastBox"></div>
    <script src="js/login.js"></script>

    <script src="js/toast.js"></script>

    <script src="js/password.js"></script>

    <!-- message js-->
    <script src="/js/messages.js"></script>
  </body>
</html>

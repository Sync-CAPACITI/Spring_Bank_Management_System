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
        <link
          href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
          rel="stylesheet"
        />
      </head>
 
  <body>
    <div class="wrapper">
        <span class="bg-animate"></span>
        <span class="bg-animate2"></span>
        <div class="form-box login reg-box">
            <h2 class="animation" style="--i:0; --j:21;">Register</h2>
            <!-- Display Message -->
            <c:if test="${requestScope.passwordMisMatch != null}">
                <div class="alert alert-danger text-center border border-danger">
                    <b>${requestScope.passwordMisMatch}</b>
                </div>
            </c:if>
            <!-- End Of Display Message -->

            <!-- Display Message -->
            <c:if test="${requestScope.success != null}">
                <div class="alert alert-success text-center border border-success">
                    <b>${requestScope.success}</b>
                </div>
            </c:if>
            <!-- End Of Display Message -->

            <form:form action="/register"  method="POST" modelAttribute="registerUser">
            <div class="input-box animation" >
                    <form:input type="text" path="first_name" required="true" />
                    <label> First name </label>
                    <i class="fa-duotone fa-light fa-input-text"></i>
                    <form:errors path="first_name" class="text-white bg-danger" />                        
                </div>

                <div class="input-box animation" >
                    <form:input type="text" path="last_name"  required="true" />
                    <label>Last name</label>
                    <i class="fa-duotone fa-light fa-input-text"></i>
                    <form:errors path="last_name" class="text-white bg-danger" />
                </div>



            <div class="input-box animation" >
                <form:input type="email" path="email"  required="true" />
                <label>Email</label>
                <i class='bx bxs-envelope'></i>
                <form:errors path="email" class="text-white bg-danger" />
            </div>


            <div class="input-box animation" >
                <form:input type="number" path="id_num"  required="true" />
                <label>ID Number</label>
                <i class="fa-solid fa-book"></i>
                <form:errors path="id_num" class="text-white bg-danger" />                    


            </div>

            <div class="input-box animation" >
                <form:input type="password" path="password"  required="true" />
                <label>Password</label>
                <i class="bx bxs-lock-alt"></i>
                <form:errors path="password" class="text-white bg-danger" />

            </div>

            <div class="input-box animation">
                <input type="password" name="confirm_password" />
                <label>Confirm Password</label>
                <i class="bx bxs-lock-alt"></i>
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
    <script src="js/login.js"></script>
  </body>
</html>
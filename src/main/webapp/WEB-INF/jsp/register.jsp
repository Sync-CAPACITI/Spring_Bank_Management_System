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
        <div class="form-box register">
            <h2 class="animation" style="--i:17; --j:0;">Sign Up</h2>
          
            <form:form action="/register"  method="POST" modelAttribute="registerUser">
              
                <div class="input-box animation" style="--i:18; --j:1;">
                    <form:input type="text" path="first_name" placeholder="First Name" required />
                    <label> First name </label>
                    <i class='bx bxs-envelope'></i>
                </div>

                <div class="input-box animation" style="--i:19; --j:2;">
                    <form:input type="text" path="last_name" placeholder="Last Name" required />
                    <label>Last name</label>
                    <i class='bx bxs-envelope'></i>
                </div>



            <div class="input-box animation" style="--i:20; --j:3;">
                <form:input type="email" path="email" placeholder="Email" required />
                <label>Email</label>
                <i class='bx bxs-envelope'></i>
            </div>


            <div class="input-box animation" style="--i:21; --j:4;">
                <form:input type="number" path="id_num"   placeholder="ID Number" required />
                <label>ID Number</label>
                <!-- Don't forget the get a correct icon  -->
                <i class='bx bxs-envelope'></i>

            </div>

            <div class="input-box animation" style="--i:22; --j:5;">
                <form:input type="password" path="password" placeholder="Password" required />
                <label>Password</label>
                <i class="bx bxs-lock-alt"></i>
            </div>

            <div class="input-box animation" style="--i:23; --j:6;">
                <form:input type="password" name="confirm_password" placeholder="Confirm Password" required/>
                <label>Confirm Password</label>
                <i class="bx bxs-lock-alt"></i>
            </div>
            

            <button type="submit" class="btn animation" style="--i:24; --j:7;">Sign Up</button>
            <div class="logreg-link animation" style="--i:25; --j:8;">
                <p>
                Already have an account?
                <a href="#" class="login-link">Login</a>
                </p>
            </div>
            </form>
        </div>
        <div class="info-text register">
            <h2 class="animation" style="--i:17; --j:0;">Bank Better with PEAK</h2>
            <p class="animation" style="--i:18; --j:1;" >
                Join and move to the future with <br> PEAK for the people, made by <br>the people.
            </p>
        </div>
    </div>
    <script src="js/login.js"></script>
  </body>
</html>

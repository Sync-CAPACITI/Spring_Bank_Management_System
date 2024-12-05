<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transfer Cash</title>

    <!-- material icon -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
    <!-- google fonts poppins -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

    <!-- Dasboards style -->
     <link rel="stylesheet" href="../css/home.css">
     <link rel="stylesheet" href="../css/universal.css">
     <link rel="stylesheet" href="../css/open_account.css">

     <!--clock ui-->
     <link rel="stylesheet" href="../css/clock.css">

     <!-- calenda css -->
     <link rel="stylesheet" href="../css/calenda.css">

</head>
<body>

    <c:import url="components/dashboard_components/nav.jsp"/>


   
    <!-- nav bar ends -->
    <main>
        <aside>
            <button id="close-btn">
                <span class="material-icons-sharp">close</span>
            </button>
            <c:import url="components/dashboard_components/side_bar.jsp"/>
            <!-- sidebar end -->

            <div class="updates">
                <span class="material-icons-sharp">update</span>
                <h4>Updates Available</h4>
                <p>Security Updates</p>
                <p>General Updates</p>
                <a href="">Update Now</a>
            </div>
        </aside>
        <!-- aside end -->
         
        <section class="middle">
             <!-- Display Message -->
             <c:if test="${success != null}">
                <div class="alert alert-info text-center border border-info">
                    <b>${success}</b>
                </div>
            </c:if>
            <!-- End Of Display Message -->

            <!-- Display Message -->
            <c:if test="${error!= null}">
                <div class="alert alert-danger text-center border border-danger">
                    <b>${error}</b>
                </div>
            </c:if>
            <!-- End Of Display Message -->
            <h1> Transfer Cash</h1>
            <c:import url="components/dashboard_components/transfer_component.jsp"/>

  

        </section>
        <!-- section end mid -->

        <section class="right">
            <c:import url="components/dashboard_components/clock.jsp"/>

                <!-- clock end -->
                <c:import url="components/dashboard_components/calendar.jsp"/>

        </section>
        <!-- right end -->
    </main>
    <!-- Clock JS -->
    <script src="../js/clock.js"></script>

    <!-- calendar js -->
     <script src="../js/calenda.js"></script>
    
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>

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
     
     <!-- The Data Tables -->

        
        <link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />  
        <script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>

         <meta charset="UTF-8">
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <title>Transactions</title>

         <style>
            /* Glassmorphism styles go here */

            .middle {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px;
    padding: 20px;
    backdrop-filter: blur(10px);
    background: rgba(255, 255, 255, 0.1); /* Transparent white */
    border-radius: 15px;
    box-shadow: 0 8px 32px rgba(0, 0, 0, 0.25);
}

.middle p {
    font-size: 18px;
    font-weight: 500;
    color: #000000; /* Black text for the title */
    margin-bottom: 20px;
}

#myTable {
    width: 100%;
    border-collapse: collapse;
    backdrop-filter: blur(10px);
    background: rgba(255, 255, 255, 0.2); /* Transparent white */
    border-radius: 15px;
    overflow: hidden; /* Rounded corners for table */
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
    color: #000000; /* Black text for the table */
}

#myTable thead {
    background: rgba(255, 255, 255, 0.25); /* Slightly opaque header */
    font-weight: bold;
}

#myTable thead th {
    padding: 12px;
    text-align: left;
    color: #000000; /* Black text for the table header */
    text-shadow: none; /* Remove shadow for clarity */
}

#myTable tbody tr {
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    transition: background 0.3s ease-in-out;
}

#myTable tbody tr:hover {
    background: rgba(0, 0, 0, 0.05); /* Slightly darker on hover */
}

#myTable tbody td {
    padding: 12px;
    text-align: left;
    color: #000000; /* Black text for table body */
    text-shadow: none; /* Remove shadow for clarity */
}

#myTable tbody td:first-child {
    font-weight: bold;
}

#myTable tbody tr:last-child {
    border-bottom: none;
}

        </style>
        

     
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
              <p>Showing Transactions for ${user.firstName}  ${user.lastName}</p>
            
            
                <table id="myTable" class="display" style="width: 100%; border-collapse: collapse; margin-top: 20px;">
                    <thead>
                        <tr>
                            <th>Transaction ID</th>
                            <th>Account Number</th>
                            <th>Transaction Type</th>
                            <th>Amount</th>
                            <th>Transaction Date</th>
                            <th>Description</th>
                            <th>Destination Account</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="transaction" items="${transactions}">
                            <tr>
                                <td>${transaction.transaction_id}</td>
                                <td>${transaction.account_number}</td>
                                <td>${transaction.transaction_type}</td>
                                <td>${transaction.amount}</td>
                                <td>
                                ${transaction.transaction_date}
                                </td>
                                <td>${transaction.description}</td>
                                <td>${transaction.destination_account_number}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
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
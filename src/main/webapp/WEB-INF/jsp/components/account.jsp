<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="components/no_account.jsp"/>
<br>
<!-- Check if account exists -->
 <div class="account-display">
    <c:choose>
    <c:when test="${accountType == 'savings'}">
        <c:import url="components/account_types/savings.jsp" />
        <br>
    </c:when>
    <c:when test="${accountType == 'cheque'}">
        <c:import url="components/account_types/cheque.jsp" />
        <br>
    </c:when>
    <c:when test="${accountType == 'credit'}">
        <c:import url="components/account_types/credit.jsp" />
        
    </c:when>
    <c:otherwise>
        <p>${message}</p>
    </c:otherwise>
</c:choose>

<!-- Optionally display all accounts -->
<c:if test="${not empty accounts}">
    <h2>All Accounts</h2>
    <ul>
        <c:forEach var="account" items="${accounts}">
            <c:import url="components/account_types/savings.jsp" />
            <c:import url="components/account_types/cheque.jsp" />
            <c:import url="components/account_types/credit.jsp" />
            <c:import url="components/no_account.jsp"/>
        </c:forEach>
    </ul>
</c:if>
 </div>

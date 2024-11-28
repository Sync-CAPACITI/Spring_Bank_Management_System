<c:choose>
    <c:when test="${accountType == 'savings'}">
        <c:import url="components/account_types/savings.jsp"/>
    </c:when>
    <c:when test="${accountType == 'cheque'}">
        <c:import url="components/account_types/cheque.jsp"/>
    </c:when>
    <c:when test="${accountType == 'credit'}">
        <c:import url="components/account_types/credit.jsp"/>
    </c:when>
</c:choose>

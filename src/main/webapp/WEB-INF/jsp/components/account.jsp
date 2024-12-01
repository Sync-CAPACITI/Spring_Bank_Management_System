

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!-- Check if account exists -->
<c:forEach var="account" items="${userAccounts}">
    <c:choose>
        <c:when test="${account.accountType == 'savings'}">
            <jsp:include page="/WEB-INF/jsp/components/account_types/savings.jsp">
                <jsp:param name="accountType" value="${account.accountType}" />
                <jsp:param name="balance" value="${account.balance}" />
                <jsp:param name="accountName" value="${account.accountName}" />
            </jsp:include>
        </c:when>
        <c:when test="${account.accountType == 'cheque'}">
            <jsp:include page="/WEB-INF/jsp/components/account_types/cheque.jsp">
                <jsp:param name="accountType" value="${account.accountType}" />
                <jsp:param name="balance" value="${account.balance}" />
                <jsp:param name="accountName" value="${account.accountName}" />
            </jsp:include>
        </c:when>
        <c:when test="${account.accountType == 'credit'}">
            <jsp:include page="/WEB-INF/jsp/components/account_types/credit.jsp">
                <jsp:param name="accountType" value="${account.accountType}" />
                <jsp:param name="balance" value="${account.balance}" />
                <jsp:param name="accountName" value="${account.accountName}" />
            </jsp:include>
        </c:when>
        <c:otherwise>
            <p>Unknown account type: ${account.accountType}</p>
        </c:otherwise>
    </c:choose>
</c:forEach>

<!-- Allow the user to open new account  -->
<c:if test="${not empty userAccounts}">
    <c:import url="components/no_account.jsp"/>
    <!-- <ul>
        <c:forEach var="account" items="${userAccounts}">
            <c:import url="components/account_types/savings.jsp" />
            <c:import url="components/account_types/cheque.jsp" />
            <c:import url="components/account_types/credit.jsp" />
        </c:forEach>
    </ul> -->
</c:if>
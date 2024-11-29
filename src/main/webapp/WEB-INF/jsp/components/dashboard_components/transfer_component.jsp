

<div class="wrapper" id="app">
    <div class="card-form">
  
      <c:choose>
        <c:when test="${accountType == 'savings'}">
          <c:import url="components/account_types/savings.jsp" />
        </c:when>
        <c:when test="${accountType == 'cheque'}">
          <c:import url="components/account_types/cheque.jsp" />
        </c:when>
        <c:when test="${accountType == 'credit'}">
          <c:import url="components/account_types/credit.jsp" />
        </c:when>
      </c:choose>
  
      

<div class="wrapper" id="app">
    <div class="card-form">
  
      <c:choose>
        <c:when test="${accountType == 'savings'}">
          <c:import url="components/account_types/savings.jsp" />
        </c:when>
        <c:when test="${accountType == 'cheque'}">
          <c:import url="components/account_types/cheque.jsp" />
        </c:when>
        <c:when test="${accountType == 'credit'}">
          <c:import url="components/account_types/credit.jsp" />
        </c:when>
      </c:choose>
      <form action="/transact/transfer" method="POST">
      <div class="card-form__inner">
        <div class="card-input">
          <label class="card-input__label">From</label>
          <div>
            <select name="account_id" id="account-type">
                <option value="">-- Select Account --</option>
                <c:if test="${userAccounts != null}">
                    <c:forEach items="${userAccounts}" var="selectAccount">
                        <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                </c:if>
            </select>
          </div>
        </div>
        <div class="card-input">
          <label class="card-input__label">To</label>
          <div>
            <select name="account_id" id="account-type">
                <option value="">-- Select Account --</option>
                <c:if test="${userAccounts != null}">
                    <c:forEach items="${userAccounts}" var="selectAccount">
                        <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                    </c:forEach>
                </c:if>
            </select>
          </div>
        </div>
        <div class="card-input">
          <label for="cardName" class="card-input__label">Amount</label>
          <input
            type="number"
            maxlength="10"
            class="card-input__input"
            placeholder="Enter amount"
            autocomplete="off"
          />
        </div>
  
        <button class="card-form__button">Transfer</button>
      </div>
      </form>
    </div>
    </div>
    </div>
  </div>
  
<div class="wrapper" id="app">
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


        <div class="card-form">
          <c:import url="components/dashboard_components/normal_card.jsp" />
                <form action="/api/transactions/transfer" method="POST">
                  <div class="card-form__inner">

                    <div class="card-input">
                      <label class="card-input__label">From</label>
                      <div>
                          <select name="transfer_from" id="account-type">
                                <option value="">-- Select Account --</option>
                                <c:if test="${userAccounts != null}">
                                      <c:forEach items="${userAccounts}" var="selectAccount">
                                        <option value="${selectAccount.accountId}">${selectAccount.accountName}</option>
                                      </c:forEach>
                                </c:if>
                          </select>
                      </div>
                      
                    </div>
                    <div class="card-input">
                      <label maxlength="50" class="card-input__input">To</label>
                      <div>
                        <select name="transfer_to" id="account-type">
                          <option value="">-- Select Account --</option>
                          <c:if test="${userAccounts != null}">
                            <c:forEach items="${userAccounts}" var="selectAccount">
                              <option value="${selectAccount.accountId}">${selectAccount.accountName}</option>
                            </c:forEach>
                          </c:if>
                        </select>
                      </div>
                    </div>
                    <div class="card-input">
                      <label for="cardName" class="card-input__label">Amount</label>
                      <input type="number" maxlength="50" class="card-input__input" name="transfer_amount"
                        class="card-input__input" placeholder="Enter amount to transfer" autocomplete="off" />
                    </div>

                    <div class="card-input">
                      <label for="cardName" class="card-input__label">Description</label>
                      <input type="text" maxlength="50" name="description" class="card-input__input"
                        placeholder="Enter a transfer Description" autocomplete="off" />
                    </div>

                    <button class="card-form__button">Transfer</button>
                  </div>
                </form>
        </div>
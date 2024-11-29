


    <div class="wrapper" id="app">
        <div class="card-form">
            <c:import url="components/dashboard_components/normal_card.jsp"/>

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
              <label for="cardName" class="card-input__label">Amount</label>
              <input
                type="number"
                maxlength="10"
                class="card-input__input"
                placeholder="Enter amount"
                autocomplete="off"
              />
            </div>
      
            <button class="card-form__button">Withdraw</button>
          </div>
    
          </form>
        </div>
        </div>
        </div>
      
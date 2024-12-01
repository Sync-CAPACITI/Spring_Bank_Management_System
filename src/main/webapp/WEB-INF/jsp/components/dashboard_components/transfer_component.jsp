<div class="wrapper" id="app">

  <div class="wrapper" id="app">
    <div class="card-form">

      <form action="/api/transactions/transfer" method="POST">
        <div class="card-form__inner">

          <!-- From Account -->
          <div class="card-input">
            <label class="card-input__label">From</label>
            <div>
              <!-- Debugging line to check userAccounts list -->
              <p>User Accounts: ${userAccounts}</p> <!-- This will print the userAccounts list -->

              <select  name="from_account"id="from-account">
                <option value="">-- Select Account --</option>

                <!-- Manually add options for each account if needed -->
                <c:if test="${not empty userAccounts}">
                  <c:choose>
                    <c:when test="${userAccounts.size() > 0}">
                          <!-- Thid is the value we sent to the databse SELECT * FROM accounts WHERE account_number = :account_number"-->
                          <option value="${userAccounts[0].accountNumber}">
                              ${userAccounts[0].accountNumber} ${userAccounts[0].accountType}
                          </option>
                    </c:when>

                    <c:when test="${userAccounts.size() > 1}">
                        <option value="${userAccounts[0].accountNumber}">
                              ${userAccounts[0].accountNumber} 
                        </option>

                        <option value="${userAccounts[0].accountNumber}">
                              AC162147
                        </option>
                    </c:when>

                    <!-- Continue for more accounts as needed -->
                  </c:choose>
                </c:if>

              </select>
            </div>
          </div>



          <!-- To Account -->
          <div class="card-input">
            <label class="card-input__label">To</label>
            <div>
              <select name="to_account" id="to-account">
                <option value="">-- Select Account --</option>
                <c:if test="${userAccounts != null}">
                  <c:forEach var="account" items="${userAccounts}">
                    ${account.accountName}

                    <option value="1">Account 1</option>
                    <option value="2">AC862202</option>
                    <option value="${userAccounts[1].accountNumber}">
                      ${userAccounts[1].accountNumber} 
                    </option>

                  </c:forEach>
                </c:if>
              </select>
            </div>
          </div>

          <!-- Amount -->
          <div class="card-input">
            <label class="card-input__label">Amount</label>
            <input type="number" name="amount" class="card-input__input" placeholder="Enter amount" autocomplete="off"
              required />
          </div>

          <!-- Description -->
          <div class="card-input">
            <label class="card-input__label">Description</label>
            <input type="text" name="description" class="card-input__input" placeholder="Enter description"
              autocomplete="off" />
          </div>

          <button class="card-form__button">Transfer</button>
        </div>

      </form>
    </div>
  </div>
</div>
</div>
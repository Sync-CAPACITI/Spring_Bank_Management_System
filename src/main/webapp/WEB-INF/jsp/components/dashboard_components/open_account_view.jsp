<form  action="/api/accounts/create"  method="POST" >
    <label for="card-holder">Card Holder:</label>
    <input type="text"  name="account_name" id="card-name-input" placeholder="Your Name" maxlength="30"/>
    <div class="date-cvv">
      <div>
        <label for="">Account Type</label>
        <select name="account_type" id="account-type">
            <option value="">Choose Account</option>
          <option value="savings">Savings</option>
          <option value="cheque">Cheque</option>
          <option value="credit">Credit</option>
        </select>
      </div>
    </div>
    <button class="card-form__button">
        Open Account
      </button>
  </form>       

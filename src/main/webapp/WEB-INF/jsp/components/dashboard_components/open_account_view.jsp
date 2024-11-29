<form  action="/account/create_account"  method="POST" >
    <label for="card-holder">Card Holder:</label>
    <input type="text" id="card-name-input" placeholder="Your Name" maxlength="30"/>
    <div class="date-cvv">
      <div>
        <label for="">Account Type</label>
        <select name="account_type" id="account-type">
          <option value="savings">Savings</option>
          <option value="cheque">Cheque</option>
          <option value="credit">Credit</option>
        </select>
      </div>
    </div>
  </form>       
<button class="card-form__button">
  Open Account
</button>
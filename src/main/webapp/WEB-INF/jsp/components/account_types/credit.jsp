<p>Account type C: ${account.accountType}</p>
<div class="card">
    <div class="top">
        <div class="left">
            <!-- Display account type (e.g., savings, cheque, credit) -->
            <img src="../images/BTC.png" alt="">
            <h2>${account.accountType}</h2>
        </div>
        <img src="../images/visa.png" alt="" class="right">
    </div>
    <div class="middle">
        <!-- Display account balance -->
        <h1 class="balance">R ${account.balance}</h1> <!-- Ensure account.balance is in the correct format -->
        <div class="chip">
            <img src="../images/chip.png" alt="">
        </div>
    </div>
    <div class="bottom">
        <div class="left">
            <!-- Display account holder name -->
            <small class="holder-name">Card Holder</small>
            <h5>${account.accountName}</h5> <!-- Display the account name -->
        </div>
        <div class="right">
            <!-- Hardcoded expiry and CVV for the sake of example -->
            <div class="expiry">
                <small>Expiry</small>
                <h5 class="expiry-date">08/29</h5> <!-- Example expiry date -->
            </div>
            <div class="cvv">
                <small>CVV</small>
                <h5 class="cvv-num">896</h5> <!-- Example CVV number -->
            </div>
        </div>
    </div>
</div>

<p>Account type Sav: ${account.accountType}</p>
<div class="card">
    <div class="top">
        <div class="left">
            <img src="../images/BTC.png" alt="">
            <h2>${account.accountType}</h2>
        </div>
        <img src="../images/visa.png" alt="" class="right">
    </div>
    <div class="middle">
        <h1 class="balance">R ${account.Balance} </h1>
        <div class="chip">
            <img src="../images/chip.png" alt="">
        </div>
    </div>
    <div class="bottom">
        <div class="left">
            <small class="holder-name">Card Holder</small>
            <h5> ${account.accountName}</h5>
        </div>
        <div class="right">
            <div class="expiry">
                <small>Expiry</small>
                <h5 class="expiry-date">08/29</h5>
            </div>
            <div class="cvv">
                <small>CVV</small>
                <h5 class="cvv-num">896</h5>
            </div>
        </div>
    </div>
</div>
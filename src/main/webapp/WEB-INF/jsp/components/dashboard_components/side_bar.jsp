<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="sidebar">
    <a href="/app/home" class="active">
        <span class="material-icons-sharp">dashboard</span>
        <h4>Dashboard</h4>
         
    </a>
    <a class="show_transactions" href="/app/transactionsList">
        <span class="material-icons-sharp">payment</span>
        <h4>Transactions</h4>
    </a>
    <a href="/deposit">
        <span class="material-symbols-outlined">
            savings
            </span>
        <h4>Deposit</h4>
    </a>
    <a href="/withdraw">
        <span class="material-symbols-outlined">
            payments
            </span>
        <h4>Withdraw</h4>
    </a>
    <a href="/app/transfer">
        <span class="material-symbols-outlined">
            send_money
        </span>                    
        <h4>Transfer</h4>
    </a>
    <a href="/help_center">
        <span class="material-icons-sharp">help_center</span>
        <h4>Help Center</h4>
    </a>
    <a href="/settings">
        <span class="material-icons-sharp">settings</span>
        <h4>Settings</h4>
    </a>
</div>

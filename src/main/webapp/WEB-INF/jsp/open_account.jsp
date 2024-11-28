<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>open_account</title>

      <!-- material icon -->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
      <!-- google fonts poppins -->
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
  
      <!-- Dasboards style -->
       <link rel="stylesheet" href="css/open_account.css">
       <link rel="stylesheet" href="css/universal.css">
  
       <!--clock ui-->
       <link rel="stylesheet" href="css/clock.css">
       <!-- calenda css -->
       <link rel="stylesheet" href="css/calenda.css">
</head>
<body>
    <nav>
        <div class="container">
            <img src="images/logo.png" class="logo" alt="logo">
            <div class="search-bar">
                <span class="material-icons-sharp">search</span>
                <input type="search" placeholder="Search">
            </div>
            <div class="profile-area">
                <div class="theme-btn">
                    <span class="material-icons-sharp active">light_mode</span>
                    <span class="material-icons-sharp">dark_mode</span>
                </div>
                <div class="profile">
                    <div class="profile-photo">
                        <img src="images/user(2)" alt="">
                    </div>
                    <h5>Zolwethu</h5>
                    <span class="material-icons-sharp">expand_more</span>
                </div>
                <button id="menu-btn">
                    <span class="material-icons-sharp">menu</span>
                </button>
            </div>
        </div>
    </nav>
    <!-- nav bar ends -->

    <main>
        <aside>
            <button id="close-btn">
                <span class="material-icons-sharp">close</span>
            </button>

            <div class="sidebar">
                <a href="" class="active">
                    <span class="material-icons-sharp">dashboard</span>
                    <h4>Dashboard</h4>
                </a>
                <a href="">
                    <span class="material-icons-sharp">payment</span>
                    <h4>Transactions</h4>
                </a>
                <a href="">
                    <span class="material-icons-sharp">help_center</span>
                    <h4>Deposit</h4>
                </a>
                <a href="">
                    <span class="material-icons-sharp">help_center</span>
                    <h4>Withdraw</h4>
                </a>
                <a href="">
                    <span class="material-icons-sharp">help_center</span>
                    <h4>Transfer</h4>
                </a>
                <a href="">
                    <span class="material-icons-sharp">message</span>
                    <h4>Messages</h4>
                </a>
                <a href="">
                    <span class="material-icons-sharp">help_center</span>
                    <h4>Help Center</h4>
                </a>
                <a href="">
                    <span class="material-icons-sharp">settings</span>
                    <h4>Settings</h4>
                </a>
            </div>
            <!-- sidebar end -->

            <div class="updates">
                <span class="material-icons-sharp">update</span>
                <h4>Updates Available</h4>
                <p>Security Updates</p>
                <p>General Updates</p>
                <a href="">Update Nou</a>
            </div>
        </aside>
        <!-- aside end -->
         
        <section class="middle">
            <h1>Open Account</h1>
        </div>
        <div class="wrapper">
          <div class="credit-card" id="card">
            <div class="card-front">
              <div class="branding">
                <img src="images/chip.png" class="chip-img" />
                <img src="images/visa.png" class="visa-logo" />
              </div>
              <div class="card-number">
                <div>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                </div>
                <div>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                </div>
                <div>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                </div>
                <div>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                  <span class="card-number-display">X</span>
                </div>
              </div>
              <div class="details">
                <div>
                  <span>Card Holder</span>
                  <span id="card-holder-name">Your Name Here</span>
                </div>
                <div>
                  <span>Expires</span>
                  <span id="validity">06/28</span>
                </div>
              </div>
            </div>
            <div class="card-back">
              <div class="black-strip"></div>
              <div class="white-strip">
                <span>CVV</span>
                <div id="cvv-display"></div>
              </div>
              <img src="visa.png" class="visa-logo" />
            </div>
          </div>
          <form>
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
          Submit
        </button>
        <!-- <button class="card-form__button_back">
          Back
        </button> -->
        </div>
          </section>
        <!-- section end mid -->

        <section class="right">
                <section class="clock container">
                    <div class="clock__container grid">
                        <div class="clock__content grid">
                            <div class="clock__circle">
                                <span class="clock__twelve"></span>
                                <span class="clock__three"></span>
                                <span class="clock__six"></span>
                                <span class="clock__nine"></span>
            
                                <div class="clock__rounder"></div>
                                <div class="clock__hour" id="clock-hour"></div>
                                <div class="clock__minutes" id="clock-minutes"></div>
                                <div class="clock__seconds" id="clock-seconds"></div>
                            </div>
        
                            <div>
                                <div class="clock__text">
                                    <div class="clock__text-hour" id="text-hour"></div>
                                    <div class="clock__text-minutes" id="text-minutes"></div>
                                    <div class="clock__text-ampm" id="text-ampm"></div>
                                </div>
            
                                <div class="clock__date">
                                    <!-- <span id="date-day-week"></span> -->
                                    <span id="date-day"></span>
                                    <span id="date-month"></span>
                                    <span id="date-year"></span>
                                </div>
                            </div>
                        </div>                        
                    </div>
                </section>
                <!-- clock end -->
                <div class="container">
                    <div class="calendar">
                        <div class="header">
                            <div class="month"></div>
                            <div class="btns">
                                <div class="btn today-btn">
                                    <i class="fas fa-calendar-day"></i>
                                </div>
                                <div class="btn prev-btn">
                                    <i class="fas fa-chevron-left"></i>
                                </div>
                                <div class="btn next-btn">
                                    <i class="keyboard_arrow_right"></i>
                                </div>
                            </div>
                        </div>
                        <div class="weekdays">
                            <div class="day">Sun</div>
                            <div class="day">Mon</div>
                            <div class="day">Tue</div>
                            <div class="day">Wed</div>
                            <div class="day">Thu</div>
                            <div class="day">Fri</div>
                            <div class="day">Sat</div>
                        </div>
                        <div class="days">
                            <!-- lets add days using js -->
                        </div>
                    </div>
                </div>
        </section>
        <!-- right end -->
    </main>
    <!--==================== Clock JS ====================-->
    <script src="js/clock.js"></script>

    <!-- calendar js -->
     <script src="js/calenda.js"></script>
    
</body>
</html>
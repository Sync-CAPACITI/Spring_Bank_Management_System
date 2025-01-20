package com.example.Spring_Bank_Management_System.helpers;

import java.time.Year;

public class HTML {

    public static String htmlEmailTemplate(String token, int code){

        int currentYear = Year.now().getValue();
        // Verify Account URL:
        String url = "https://bank-system-enre.onrender.com/verify?token=" + token +"&code=" + code;

        String emailTemplate = "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "    <!-- <link rel='stylesheet' href='css/email.css'> -->\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        *{\n" +
                "            box-sizing: border-box;\n" +
                "            font-family: Comfortaa;\n" +
                "        }\n" +
                "\n" +
                "        /* Main Body Styling */\n" +
                "        body{\n" +
                "            height: 100vh;\n" +
                "            background-color: rgb(212, 222, 230);\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "\n" +
                "        /* <!-- Wrapper -->*/\n" +
                "        .wrapper{\n" +
                "            width: 550px;\n" +
                "            height: auto;\n" +
                "            padding: 15px;\n" +
                "            background-color: white;\n" +
                "            border-radius: 7px;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        /* <!-- Email MSG Header --> */\n" +
                "        .email-msg-header{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        /* <!-- Company Name --> */\n" +
                "        .company-name{\n" +
                "            width: 100%;\n" +
                "            font-size: 35px;\n" +
                "            color: gray;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        /* <!-- Welcome Text --> */\n" +
                "        .welcome-text{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        /* <!-- Verify Account Button --> */\n" +
                "        .verify-account-btn{\n" +
                "            padding: 15px;\n" +
                "            background-color: rgb(0, 109, 252);\n" +
                "            text-decoration: none;\n" +
                "            color: white;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "\n" +
                "        /* <!-- Copy Right Wrapper --> */\n" +
                "        .copy-right{\n" +
                "            padding: 15px;\n" +
                "            color: gray;\n" +
                "            font-size: 14px;\n" +
                "            margin: 20px 0px;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "    <!-- Wrapper -->\n" +
                "    <div class='wrapper'>\n" +
                "        <!-- Email MSG Header -->\n" +
                "        <h2 class='email-msg-header'>\n" +
                "            Welcome and Thank You for Choosing  \n" +
                "        </h2>\n" +
                "        <!-- End Of Email MSG Header -->\n" +
                "\n" +
                "        <!-- Company Name -->\n" +
                "        <div class='company-name'>PEAK Next-Gen Banking</div>\n" +
                "        <!-- End Of Company Name -->\n" +
                "        <hr>\n" +
                "\n" +
                "        <!-- Welcome Text -->\n" +
                "        <p class='welcome-text'>\n" +
                "            Your Account has been successfully registered, please click below to verify your account \n" +
                "        </p>\n" +
                "        <!-- End Of Welcome Text -->\n" +
                "        <br>\n" +
                "        <br>\n" +
                "\n" +
                "        <!-- Verify Account Button -->\n" +
                "        <center><a href='"+ url +"' class='verify-account-btn' role='button'>Verify Account</a></center>\n" +
                "        <!-- End Of Verify Account Button -->\n" +
                "\n" +
                "        <!-- Copy Right Wrapper -->\n" +
                "        <div class='copy-right'>\n" +
                "            &copy; Copy Right " + currentYear + ". All Rights Reserved.\n" +
                "        </div>\n" +
                "        <!-- End Of Copy Right Wrapper -->\n" +
                "\n" +
                "    </div>\n" +
                "    <!-- End Of Wrapper -->\n" +
                "\n" +
                "    \n" +
                "</body>\n" +
                "</html>";
        
        return emailTemplate;
    }

    public static String passwordResetEmailTemplate(String token, int code){

        int currentYear = Year.now().getValue();
        // Password Reset URL:
        String url = "https://bank-system-enre.onrender.com/password_reset?token=" + token;
    
        String passwordTemplate = "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "    <title>Password Reset</title>\n" +
                "    <style>\n" +
                "        *{\n" +
                "            box-sizing: border-box;\n" +
                "            font-family: Comfortaa;\n" +
                "        }\n" +
                "\n" +
                "        /* Main Body Styling */\n" +
                "        body{\n" +
                "            height: 100vh;\n" +
                "            background-color: rgb(212, 222, 230);\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "\n" +
                "        /* Wrapper */\n" +
                "        .wrapper{\n" +
                "            width: 550px;\n" +
                "            padding: 15px;\n" +
                "            background-color: white;\n" +
                "            border-radius: 7px;\n" +
                "        }\n" +
                "\n" +
                "        /* Email Header */\n" +
                "        .email-msg-header{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        /* Company Name */\n" +
                "        .company-name{\n" +
                "            font-size: 35px;\n" +
                "            color: gray;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        /* Welcome Text */\n" +
                "        .welcome-text{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        /* Reset Password Button */\n" +
                "        .reset-password-btn{\n" +
                "            padding: 15px;\n" +
                "            background-color: rgb(0, 109, 252);\n" +
                "            text-decoration: none;\n" +
                "            color: white;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "\n" +
                "        /* Footer */\n" +
                "        .copy-right{\n" +
                "            padding: 15px;\n" +
                "            color: gray;\n" +
                "            font-size: 14px;\n" +
                "            margin: 20px 0px;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <!-- Wrapper -->\n" +
                "    <div class='wrapper'>\n" +
                "        <!-- Email Header -->\n" +
                "        <h2 class='email-msg-header'>\n" +
                "            Password Reset Request\n" +
                "        </h2>\n" +
                "        <!-- End of Email Header -->\n" +
                "\n" +
                "        <!-- Company Name -->\n" +
                "        <div class='company-name'>PEAK Next-Gen Banking</div>\n" +
                "        <!-- End of Company Name -->\n" +
                "        <hr>\n" +
                "\n" +
                "        <!-- Welcome Text -->\n" +
                "        <p class='welcome-text'>\n" +
                "            We received a request to reset your password. Please click the button below to reset it.\n" +
                "        </p>\n" +
                "        <!-- End of Welcome Text -->\n" +
                "        <br>\n" +
                "        <br>\n" +
                "\n" +
                "        <!-- Reset Password Button -->\n" +
                "        <center><a href='"+ url +"' class='reset-password-btn' role='button'>Reset Password</a></center>\n" +
                "        <!-- End of Reset Password Button -->\n" +
                "\n" +
                "        <!-- Footer -->\n" +
                "        <div class='copy-right'>\n" +
                "            &copy; " + currentYear + " PEAK Next-Gen Banking. All Rights Reserved.\n" +
                "        </div>\n" +
                "        <!-- End of Footer -->\n" +
                "    </div>\n" +
                "    <!-- End of Wrapper -->\n" +
                "</body>\n" +
                "</html>";
    
        return passwordTemplate;
    }
    
}

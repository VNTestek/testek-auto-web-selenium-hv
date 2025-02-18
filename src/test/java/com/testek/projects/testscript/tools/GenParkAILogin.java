package com.testek.projects.testscript.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.testek.annotations.FrameAnnotation;
import com.testek.consts.AuthorType;
import com.testek.consts.FrameConst;
import com.testek.driver.BrowserFactory;
import com.testek.driver.DriverManager;
import com.testek.projects.common.TestBase;
import com.testek.projects.page.tools.GenParkAIPage;
import com.testek.projects.testscript.tools.mails.DongVanMail;
import com.testek.projects.testscript.tools.otp.DaiLyOTP;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//mvn clean test -DcusName=khanh -Dmonth=1 -DaiURL='https://www.genspark.ai/invite?invite_code=OTA3YzA3MWZMMGU2ZUw4ZTA0TGUyNWFMZmVhZmI5ZjM5YjQ0'
//Vincent@1234
public class GenParkAILogin extends TestBase {
    String genURL = "https://www.genspark.ai/invite?invite_code=NWYxZjgwOTlMZmJkNExhYzE0TDQ2MThMMjIwNjY2NTRkMzRi";
    private WebDriver webDriver;
    private String mailHost = "https://api.mail.tm";
    private String mailDomain = "edny.net";
    private String mailPrefix = "khanh";

    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Subcribe to GenParkAI")
    public void Testek_Login_002_Valid() {
//        Properties properties = System.getProperties();
//        genURL = properties.getProperty("aiURL");
//        mailPrefix = properties.getProperty("cusName");
//        int count = Integer.parseInt(properties.getProperty("month"));
        String mailPath = "/Users/vincent/Work/testek/workspace/training/auto/testek-auto-web-selenium/src/test/java/com/testek/projects/testscript/tools/mails/vincent-genspark.txt";
        List<String> mails = DongVanMail.getInstance().generateMail(mailPath);


        StringBuffer sb = new StringBuffer();
        sb.append("Invite code: ").append(genURL);
        int successCount = 0;
        for (String mailData : mails) {
            try {
                webDriver = DriverManager.getDriver();
                webDriver.get(genURL);
                String res = registerToGenParkAI(mailData);
                if (res != null) {
                    sb.append("\n").append(res);
                    successCount++;
                    System.out.println("Vincent: " + successCount + " : " + res);
                }
            } catch (Exception e) {
                System.out.println("Failed to subcribe to GenParkAI " + e.getMessage());
            } finally {
                DriverManager.quitDriver();
                BrowserFactory.initWebDriver("chrome");
            }
        }
        System.out.println(sb);
    }


    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(description = "Subcribe to GenParkAI", enabled = false)
    public void Testek_Login_001_Valid() {
//        Properties properties = System.getProperties();
//        genURL = properties.getProperty("aiURL");
//        mailPrefix = properties.getProperty("cusName");
//        int count = Integer.parseInt(properties.getProperty("month"));
        int count = 1;
        StringBuffer sb = new StringBuffer();
        sb.append("Invite code: ").append(genURL);
        int index = 0;
        while (count > 0) {
            System.out.println("Execute: " + count + " : Index: " + index++);
            try {
                webDriver = DriverManager.getDriver();
                webDriver.get(genURL);
                String res = subcribeToGenParkAI();
                if (res != null) {
                    sb.append("\n").append(res);
                    count--;
                }
            } catch (Exception e) {
                System.out.println("Failed to subcribe to GenParkAI " + e.getMessage());
            } finally {
                DriverManager.quitDriver();
                BrowserFactory.initWebDriver("chrome");
            }
        }
        System.out.println(sb);

    }

    void waitForDebug(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String registerToGenParkAI(String mailData) {
        String[] arr = mailData.split("\\|");
        String email = arr[0];
        String pass = arr[1];

        GenParkAIPage genParkAIPage = new GenParkAIPage();


        genParkAIPage.clickInviteButton();
        waitForDebug(3);
        genParkAIPage.clickMicrosoftAccountExchange();
        waitForDebug(4);
        genParkAIPage.enterLoginOut(email)
                .clickNext();
        waitForDebug(5);
        genParkAIPage.enterPassword(pass).clickNext();
        waitForDebug(5);
        genParkAIPage.clickSkipForNow();
        waitForDebug(5);
        genParkAIPage.clickAcceptButton();
        waitForDebug(5);
        genParkAIPage.clickUcaccept();
        waitForDebug(15);

        genParkAIPage.clickFlag();
        waitForDebug(3);
        genParkAIPage.enterSearchCountry("57");

        waitForDebug(3);
        genParkAIPage.clickColombia();

        //String email = addOutlookDongVan(mailData, genParkAIPage);

        waitForDebug(3);
        Map<String, String> phoneMap;
        String phoneOTP = null;
        for (int i = 1; i <= 5; i++) {
            phoneMap = DaiLyOTP.getInstance()
                    .callAPIRentPhoneNumber("Genspark", "CO", "Server 5");
            int count = 0;

            genParkAIPage.enterPhoneNumber(phoneMap.get("phoneNumber"));
            waitForDebug(3);

            genParkAIPage.clickGetVerificationCode();
            do {
                phoneOTP = DaiLyOTP.getInstance().callAPIGetOTPCode(phoneMap);
                if (phoneOTP != null) {
                    break;
                }
                count++;
                waitForDebug(10);
            } while (count < 7);

            if (phoneOTP != null) {
                break;
            }
            webDriver.navigate().refresh();
        }

        genParkAIPage.enterPhoneVerificationCode(phoneOTP)
                .clickClaimMembershipBenefits();

        waitForDebug(3);
        genParkAIPage.captureScreen(email, mailPrefix);
        String result = MessageFormat.format("Email: {0}, Pass {1}", email, "Vincent@1234");
        System.out.println(result);
        return result;
    }

    private void accessEmailRegistration(GenParkAIPage genParkAIPage) {

    }

    public String subcribeToGenParkAI() {
        GenParkAIPage genParkAIPage = new GenParkAIPage();
        genParkAIPage.clickInviteButton();
        waitForDebug(3);
        genParkAIPage.clickLoginWithEmail();
        waitForDebug(4);
        genParkAIPage.clickCreateAccount();


        String email = generateEmail(genParkAIPage);

        waitForDebug(10);
        genParkAIPage.enterNewPassword("Vincent@1234").enterReenterPassword("Vincent@1234").clickContinue();
        waitForDebug(10);

        // Get Phone number & SOTP
        String sotp = null, phone = null;
        for (int i = 0; i <= 5; i++) {
            Response resOTP = RestAssured.given().log().all().get("https://api.viotp.com/request/getv2?" + "token=7130d6ccb4ed459dadd174a67be55907" + "&serviceId=1413" + "&network=VINAPHONE");
            resOTP.prettyPrint();
            phone = resOTP.getBody().jsonPath().get("data.phone_number");
            String seqId = resOTP.getBody().jsonPath().get("data.request_id").toString();
            webDriver.navigate().refresh();
            genParkAIPage.enterPhoneNumber(phone).clickGetVerificationCode();

            // Get OTP
            int count = 0;
            do {
                waitForDebug(10);
                String path = String.format("https://api.viotp.com/session/getv2?requestId=%s&token=7130d6ccb4ed459dadd174a67be55907", seqId);
                resOTP = RestAssured.given().log().all().get(path);
                resOTP.prettyPrint();
                sotp = resOTP.getBody().jsonPath().get("data.Code");
                count++;
            } while (sotp == null & count < 7);
            System.out.println("OTP: " + sotp);
            if (sotp != null) {
                break;
            }
        }
        System.out.println("OTP: " + sotp);


        waitForDebug(3);

        genParkAIPage.enterPhoneVerificationCode(sotp).clickClaimMembershipBenefits();

        waitForDebug(3);
        genParkAIPage.captureScreen(phone, email, mailPrefix);
        String result = MessageFormat.format("Email: {0}, Pass {1}, Phone: {2}", email, "Vincent@1234", phone);
        System.out.println(result);
        return result;
    }

    private String addOutlookDongVan(String mailData, GenParkAIPage genParkAIPage) {
        String[] arr = mailData.split("\\|");
        String email = arr[0];
        String token = arr[2];
        String clientId = arr[3];

        //waitForDebug(10);
        genParkAIPage.enterEmail(email).clickSendCode();

        waitForDebug(15);
        String verificationCode = DongVanMail.getInstance().callAPIGetOTPFromMail(email, token, clientId, "Genspark account email verification code");
        System.out.println("Verification Code: " + verificationCode);
        //endregion

        genParkAIPage.enterVerificationCode(verificationCode).clickVerifyCode();
        return email;
    }

    private String generateEmail(GenParkAIPage genParkAIPage) {
        String email, verificationCode = null;
        //region temp mail
        /*// Create temp email
        email = mailPrefix + System.currentTimeMillis() + "@" + mailDomain;
        String emailBody = String.format("{\n" + "    \"address\": \"%s\",\n" + "    \"password\": \"Vincent@1234'\"\n" + "}", email);

        Response resEmail = RestAssured.given().header("Content-Type", "application/json").body(emailBody).log().all().post(mailHost + "/accounts");
        resEmail.prettyPrint();
        if (resEmail.statusCode() != 201) {
            System.out.println("Failed to create temp email");
            return null;
        }
        // Get Token
        resEmail = RestAssured.given().header("Content-Type", "application/json").body(emailBody).log().all().post(mailHost + "/token");
        resEmail.prettyPrint();
        if (resEmail.statusCode() != 200) {
            System.out.println("Failed to create temp email");
            return null;
        }
        String accessToken = resEmail.getBody().jsonPath().get("token");*/
        //endregion

        // region outlook
        String str = "lemuel18naderiga@outlook.com|Rrdne91qp|M.C537_SN1.0.U.-CmXYQXuq!5xgApn7aGwYDwMTZ0JyzU3AvZEHje8rl9R3rOu58E2HbaWOWaAe6Hg1F!J1vCwf!zxsHw81IF*EfGScoMdOorutrOXG31R4rQxlDwMHvkL!!RkQkXNhOkCyjCNellSMImjb66xMCGspC8JO7cd7iyVIJjVGd!lRy2*QVmddrMWlPyzYeoQVyeGmWDfwiVq00VDecv6jg2bombFROrPDPS5YkMCHqnODQVAfZ1Lutc5v*y73M23RaOq0YNKiywh7fJT91TZKwNdrCeH!8VVTe6lrhcEQZolN31fVFdeMiMIA7BM3pyXDZ8IysjGmN7kjuzIraPjCjQL1cdc3iEzi9MFnVjRRkT2ACvfMvmU0HeQl4mJsrB8HoA1TfDQgyAmFQY0eka0AXhQzU48$|9e5f94bc-e8a4-4e73-b8be-63364c29d753";
        String[] arr = str.split("\\|");
        email = arr[0];
        String password = arr[1];
        String token = arr[2];
        String clientId = arr[3];
        // endregion

        waitForDebug(10);
        genParkAIPage.enterEmail(email).clickSendCode();

        waitForDebug(15);

        //region get code from temp mail
        /*// Get email
        String verificationCode = null;
        resEmail = RestAssured.given().header("Authorization", "Bearer " + accessToken).log().all().get(mailHost + "/messages?page=1");
        resEmail.prettyPrint();
        JsonObject jsonObject = JsonParser.parseString(resEmail.getBody().prettyPrint()).getAsJsonObject();
        JsonArray messages = jsonObject.getAsJsonArray("hydra:member");
        // Find the email with the specified name
        for (int i = 0; i < messages.size(); i++) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String fromName = message.getAsJsonObject("from").get("name").getAsString();
            if ("Microsoft on behalf of Genspark" .equals(fromName)) {
                // Extract the intro field
                String intro = message.get("intro").getAsString();

                // Use regex to extract the verification code
                Pattern pattern = Pattern.compile("Your code is: (\\d+)");
                Matcher matcher = pattern.matcher(intro);
                if (matcher.find()) {
                    verificationCode = matcher.group(1);

                } else {
                    System.out.println("Verification code not found.");
                }
                break;
            }
        }
        System.out.println("Verification Code: " + verificationCode);
*/

        //endregion

        //region get code from outlook
        String body = String.format("{\n" + "    \"email\": \"%s\",\n" + "    \"refresh_token\": \"%s\",\n" + "    \"client_id\": \"%s\"\n" + "}", email, token, clientId);

        Response resEmail = RestAssured.given().header("Content-Type", "application/json").body(body).log().all().post("https://tools.dongvanfb.net/api/get_messages_oauth2");

        JsonObject jsonObject = JsonParser.parseString(resEmail.getBody().prettyPrint()).getAsJsonObject();
        JsonArray messages = jsonObject.getAsJsonArray("messages");
        // Find the email with the specified name
        for (int i = 0; i < messages.size(); i++) {
            JsonObject message = messages.get(i).getAsJsonObject();
            String subject = message.get("subject").getAsString();
            if ("Genspark account email verification code" .equals(subject)) {
                // Extract the intro field
                String intro = message.get("message").getAsString();

                // Use regex to extract the verification code
                Pattern pattern = Pattern.compile("Your code is: (\\d+)");
                Matcher matcher = pattern.matcher(intro);
                if (matcher.find()) {
                    verificationCode = matcher.group(1);

                } else {
                    System.out.println("Verification code not found.");
                }
                break;
            }
        }
        System.out.println("Verification Code: " + verificationCode);
        //endregion

        genParkAIPage.enterVerificationCode(verificationCode).clickVerifyCode();

        return email;
    }
}

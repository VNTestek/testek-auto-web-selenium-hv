package com.testek.projects.testscript.tools.mails;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DongVanMail {
    final String BASE_URL = "https://tools.dongvanfb.net";

    @Getter
    public static DongVanMail instance = new DongVanMail();

    private DongVanMail(){}

    public static void main(String[] args) {
        DongVanMail dongVanMail = new DongVanMail();
        dongVanMail.generateMail("");
        /*dongVanMail.callAPIGetOTPFromMail("thantsejuk@outlook.com",
                "M.C528_BAY.0.U.-CoDw8WLIVEIpgEfG57OoGpF4AtjVcinyJKz*Jxx1cNKpN0s3De14xShzVl4A2NmeWsSHPd7!i!PUmRahz*cJKLZanw4l9E3gEl1kB75aTitE9Dx4lME!*8zU9iCaeRwpIPEvhyemTfKYJvzyyfftIzNqPQa!4w99A9GaFpA*jLvbblXU9ShRs0DlWTUdRNcYHfKm4COHfz7Ary3Yw7j6U*AR6gn71vSSWxJoM!R5fMYCEdBeUQPbdB9GYC0fTGn2mbFuvjPK704MuWCmrH*yFPq0SvrLNCTPr05ugUamiKnX4LmbE7CtlJPGI3pk3PkMN4dU*4*Vpp!RuuUmJyHr5Gbv5AtyA!FNSsOhPKVnNYATDFYnfVutMIR1Gwf*I!BpE7kgUYFXadYFN7mMBKQw481dz*WPiojWX!JiiKoKgjou!tOccI!c1s0bpTYQPTP7MA$$",
                "8b4ba9dd-3ea5-4e5f-86f1-ddba2230dcf2",
                "Genspark account email verification code"
        );*/
    }

    public String callAPIGetOTPFromMail(String email, String token, String clientId, String subject) {
        String url = MessageFormat.format("{0}/api/get_messages_oauth2", BASE_URL);
        String body = """
                {
                    "email": "%s",
                    "refresh_token": "%s",
                    "client_id": "%s"
                }""".formatted(email, token, clientId);
        Response resEmail;
        int count = 0;
        do {
            try {
                resEmail = generateRequest(false)
                        .body(body)
                        .post(url);
            } catch (Exception e) {
                resEmail = null;
            }
            count++;
            if (count > 4) {
                break;
            }
        } while (resEmail == null || resEmail.getStatusCode() != 200);


        if (resEmail == null || resEmail.getStatusCode() != 200) {
            System.out.println("Error when calling API to get otp code from email.");
            return null;
        }

        String verificationCode = null;
        try {
            JsonObject jsonObject = JsonParser.parseString(resEmail.getBody().prettyPrint()).getAsJsonObject();
            JsonArray messages = jsonObject.getAsJsonArray("messages");
            // Find the email with the specified name
            for (int i = 0; i < messages.size(); i++) {
                JsonObject message = messages.get(i).getAsJsonObject();
                String actSubject = message.get("subject").getAsString();
                if (subject.equals(actSubject)) {
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
        } catch (Exception e) {
            System.out.println("Error when parsing response from API to get otp code from email.");
        }

        return verificationCode;
    }

    private RequestSpecification generateRequest(boolean isSecure) {
        RequestSpecification requestSpec = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all();
        return isSecure ? requestSpec.relaxedHTTPSValidation() : requestSpec;
    }

    public List<String> generateMail(String mailPath) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(mailPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error when reading file: " + e.getMessage());
        }
        System.out.println(list);
        return list;
    }
}

///Users/vincent/Work/testek/workspace/training/auto/testek-java-basic-training/src/test/java/com/testek/projects/testscript/tools/mails/vincent-genspark.txt
///Users/vincent/Work/testek/workspace/training/auto/testek-auto-web-selenium/src/test/java/com/testek/projects/testscript/tools/mails/vincent-genspark.txt

package com.testek.projects.testscript.tools.otp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.testek.clients.api.APIBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

public class DaiLyOTP extends APIBase {
    final String API_KEY = "19e5a8e0e5eaeee1052582eafe93f1e2dc0SzvZnG5fv7jhGj75f";
    final String BASE_URL = "https://dailyotp.com";
    @Getter
    public static DaiLyOTP instance = new DaiLyOTP();

    private DaiLyOTP(){}

    public Response callAPICountry() {
        String url = String.format("%s/api/countries?api_key=%s", BASE_URL, API_KEY);
        Response res = generateRequest(false)
                .get(url);
        res.prettyPrint();
        return res;
    }

    public Response callAPIGetServices(String countryCode, String serverName) {
        String url = String.format("%s/api/services?countryCode=%s&serverName=%s&api_key=%s",
                BASE_URL, countryCode, serverName, API_KEY);
        Response res = generateRequest(false)
                .get(url);
        res.prettyPrint();
        return res;
    }

    public Response callAPICheckBalance() {
        String url = String.format("%s/api/me?api_key=%s", BASE_URL, API_KEY);
        Response res = generateRequest(false)
                .get(url);
        res.prettyPrint();
        return res;
    }

    public Map<String, String> callAPIRentPhoneNumber(String appBrand, String countryCode, String serverName) {
        Map<String, String> resMap = new HashMap<>();
        try {
            String url = String.format("%s/api/rent-number?appBrand=%s&countryCode=%s&serverName=%s&api_key=%s",
                    BASE_URL, appBrand, countryCode, serverName, API_KEY);
            Response res = generateRequest(false)
                    .get(url);
            res.prettyPrint();
            JsonObject jsonObject = JsonParser.parseString(res.getBody().prettyPrint()).getAsJsonObject();

            JsonObject jsonOb = jsonObject.get("data").getAsJsonObject();
            resMap.put("phoneNumber", jsonOb.get("phoneNumber").getAsString());
            resMap.put("transId", jsonOb.get("transId").getAsString());
        } catch (Exception e) {
            System.out.println("Error - callAPIRentPhoneNumber: " + e.getMessage());
        }
        return resMap;
    }

    public String callAPIGetOTPCode(Map<String, String> transMap) {
        try {
            String transId = transMap.get("transId");
            if (transId == null) {
                System.out.println("transId is null");
                return null;
            }
            String url = String.format("%s/api/get-messages?transId=%s&api_key=%s",
                    BASE_URL, transId, API_KEY);
            Response res = generateRequest(false)
                    .get(url);
            res.prettyPrint();
            JsonObject jsonObject = JsonParser.parseString(res.getBody().prettyPrint()).getAsJsonObject();

            JsonObject jsonOb = jsonObject.get("data").getAsJsonObject();
            return jsonOb.get("code").getAsString();
        } catch (Exception e) {
            System.out.println("Error - callAPIGetOTPCode: " + e.getMessage());
        }
        return null;
    }

    private RequestSpecification generateRequest(boolean isSecure) {
        RequestSpecification requestSpec = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all();
        return isSecure ? requestSpec.relaxedHTTPSValidation() : requestSpec;
    }



}

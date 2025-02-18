package com.testek.projects.common.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.testek.consts.FrameConst;
import com.testek.datadriven.DataModel;
import com.testek.report.ExtentReportManager;
import com.testek.report.ExtentTestManager;
import com.testek.utils.Log;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.*;

import static com.testek.consts.FrameConst.HTTPMethod.*;
import static com.testek.consts.FrameConst.ReportConst.*;
import static com.testek.consts.FrameConst.*;


public class APIBase {
    /**
     * Assert Element Objects.
     * Support 3 type of Failure Handling
     */
    public static void assertTrueCondition(boolean isResult, FailureHandling failureHandling, @Nullable String errMsg) {
        if (Objects.isNull(errMsg) || errMsg.isEmpty()) {
            errMsg = "Verify TRUE object : ";
        }

        if (!isResult) {
            Log.info(String.format("%s -> VERIFY : %s", errMsg, isResult));
            //AllureManager.saveTextLog(String.format("%s -> VERIFY : %s", errMsg, isResult));
        }
        switch (failureHandling) {
            case STOP_ON_FAILURE:
                if (!isResult) {
                    ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, FAIL));
                }
                Assert.assertTrue(isResult);
                ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, PASS));
                break;
            case CONTINUE_ON_FAILURE:
                if (!isResult) {
                    Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                    ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, FAIL));
                } else {
                    ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, PASS));
                }
                break;
            default:
                break;
        }
    }

    /**
     * Assert Fail
     */
    public static void assertFalseCondition(boolean isResult, FailureHandling failureHandling, String errMsg) {
        if (Objects.isNull(errMsg) || errMsg.isEmpty()) {
            errMsg = "Verify FALSE object: ";
        }
        if (isResult) {
            Log.info(String.format("%s -> VERIFY : %s", errMsg, !isResult));
            ExtentReportManager.logMessage(errMsg);
            //AllureManager.saveTextLog(String.format("%s -> VERIFY : %s", errMsg + "\n" + apiLog, !isResult));
        }
        switch (failureHandling) {
            case STOP_ON_FAILURE:
                if (isResult) {
                    ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, FAIL));
                }
                ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, PASS));
                Assert.assertFalse(isResult);
                break;
            case CONTINUE_ON_FAILURE:
                if (isResult) {
                    Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                    ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, FAIL));
                } else {
                    ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, PASS));
                }
                break;
        }
    }

    /**
     * Assert Equal
     */
    public static void assertEqualCondition(Object actual, Object expected, FailureHandling failureHandling, String errMsg) {
        boolean isResult = Objects.equals(actual, expected);

        if (Objects.isNull(errMsg) || errMsg.isEmpty()) {
            errMsg = "Verify equal object: ";
        }

        errMsg = String.format("%s - Actual: %s ; Expected: %s", errMsg, actual.toString(), expected.toString());

        if (!isResult) {
            Log.info(String.format("%s -> VERIFY : %s", errMsg, isResult));
        }

        switch (failureHandling) {
            case STOP_ON_FAILURE:
                if (!isResult) {
                    ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, FAIL));
                }
                Assert.assertEquals(actual, expected);
                ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, PASS));
                break;
            case CONTINUE_ON_FAILURE:
                if (!isResult) {
                    Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
                    ExtentReportManager.fail(String.format("%s -> VERIFY : %s", errMsg, FAIL));
                } else {
                    ExtentReportManager.pass(String.format("%s -> VERIFY : %s", errMsg, PASS));
                }
                break;
        }
    }

    /**
     * Assert Equal
     */
    public static <T> void assertThatCondition(T actual, Matcher<? super T> matcher, String errMsg) {
        try {
            MatcherAssert.assertThat(actual, matcher);
        } catch (AssertionError e) {
            Log.error(e.getMessage());
            Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
            ExtentReportManager.fail(String.format("%s -> \nVERIFY : %s", e.getMessage(), FAIL));
        }
    }

    /**
     * Add more information for Report: Including Extent and Allure.
     * You can add more report at this function.
     *
     * @param extMsg : The message to show on report
     */
    public static void addReportInfo(FrameConst.LogType logType, String extMsg, boolean isResult, String responseCodeBlock) {
        ExtentTestManager.addReportInfo(logType, extMsg, isResult, responseCodeBlock);
    }



    //endregion
    //region Rest API
    /**
     * Create request default for RequestSpecification
     *
     * @param apiPath  : The path of api
     * @param isSecure : use secure or not
     */
    public RequestSpecification createDefaultRequest(String apiPath, boolean isSecure, String... uris) {
        String endPoint = uris.length > 0 ? uris[0] : "BASE_URL";
        RequestSpecification requestSpec = RestAssured.given()
                .baseUri(endPoint)
                .basePath(apiPath);
        return isSecure ? requestSpec.relaxedHTTPSValidation() : requestSpec;
    }

    //endregion


    /**
     * Call Rest API
     *
     * @param method  : The method type
     * @param request : The request
     * @return API response
     */
    public Response callRestAPI(HTTPMethod method, RequestSpecification request) {
        Response response = null;
        switch (method) {
            case POST:
                ExtentReportManager.logRequest(request, POST);
                response = request.post();
                ExtentReportManager.logResponse(response);
                break;
            case PUT:
                ExtentReportManager.logRequest(request, PUT);
                response = request.put();
                ExtentReportManager.logResponse(response);
                break;
            case GET:
                ExtentReportManager.logRequest(request, GET);
                response = request.get();
                ExtentReportManager.logResponse(response);
                break;
            case DELETE:
                ExtentReportManager.logRequest(request, DELETE);
                response = request.delete();
                ExtentReportManager.logResponse(response);
                break;
            case PATCH:
                ExtentReportManager.logRequest(request, PATCH);
                response = request.patch();
                ExtentReportManager.logResponse(response);
                break;
        }
        return response;
    }

//region Extract data from Response

    /**
     * Convert json to Object
     *
     * @param jsonData : Json Data
     * @return : T Object
     */
    public <T> T convertJsonToObject(String jsonData, Type type) {
        //Type productConfigType =new TypeReference<Map<String, StrapiProductConfigModel>>(){}.getType();
        return new Gson().fromJson(String.valueOf(jsonData), type);
    }

    public HashMap<String, Object> convertJsonToHashMap(String jsonData) {
        Type type = new TypeReference<Map<String, HashMap>>() {
        }.getType();
        return new Gson().fromJson(String.valueOf(jsonData), type);
    }


    /**
     * Convert a map to an object
     *
     * @return : T Object
     */
    public <T> T convertObjectToObject(Object mapData, Class<T> cls) {
        return (T) new ObjectMapper().convertValue(mapData, cls);
    }

    public <T> T extractDataFromResponse(Response response, String jsonPath) {
        return response.jsonPath().get(jsonPath);
    }

    public String extractResponseDataToString(Response response, String jsonPath) {
        var res = extractDataFromResponse(response, jsonPath);
        if (Objects.nonNull(res)) {
            return String.valueOf(res);
        }
        return "NOT_FOUND";
    }

    public JSONObject extractResponseDataToJSON(Response response, String jsonPath) {
        JSONObject jsonObject = new JSONObject(response.prettyPrint());
        String[] pathArr = jsonPath.split("\\.");
        for (String curPath : pathArr) {
            if (jsonObject != null && jsonObject.keySet().contains(curPath)) {
                jsonObject = jsonObject.getJSONObject(curPath);
            } else {
                return null;
            }
        }
        return jsonObject;
    }

    //region new
    public HashMap<String, Object> responseToHashMap(Response response) {
        try {
            return new ObjectMapper().readValue(response.asString(), HashMap.class);
        } catch (JsonProcessingException e) {
            Log.info("VDebug: Convert the response to HashMap: FAILED");
            return new HashMap<>();
        }

    }

    public HashMap<String, Object> extractDataFromResToHashMap(Response response) {
        HashMap<String, Object> resMap = responseToHashMap(response);
        if (resMap.containsKey("data")) {
            return (HashMap<String, Object>) resMap.get("data");
        }
        return new HashMap<>();
    }

    public HashMap<String, Object> extractMetaFromResToHashMap(Response response) {
        HashMap<String, Object> resMap = responseToHashMap(response);
        if (resMap.containsKey("meta")) {
            return (HashMap<String, Object>) resMap.get("meta");
        }
        return new HashMap<>();
    }
//endregion
// endregion


//region Support


    /* Get default value*/

    /**
     * Get default value
     */
    public String getStringValueOrDefault(Object object, String defVal) {
        return Objects.isNull(object) ? defVal : object.toString();
    }

//endregion

}
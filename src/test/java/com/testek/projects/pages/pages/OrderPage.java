package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst.FailureHandling;
import com.testek.projects.common.BasePage;
import com.testek.projects.dataprovider.model.CreateOrderModel;
import com.testek.projects.pages.objects.OrderObjects;
import com.testek.projects.pages.objects.ProductObjects;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.Objects;

/**
 * Implement the functions of the Product page
 */
public class OrderPage extends BasePage {
    private final OrderObjects orderObjects;

    public OrderPage() {
        super();
        PageFactory.initElements(webDriver, this);
        orderObjects = OrderObjects.getInstance();
    }

    public OrderPage clickToCreateOrder() {
        orderObjects.clickAddMore();
        return this;
    }

    public OrderPage clickIconAdd() {
        orderObjects.clickIconAddOrder();
        return this;
    }

    public OrderPage clickMnuOrder() {
        orderObjects.clickMenuOrder();
        return this;
    }

    //region Integration Functions
    public OrderPage fillOrderInfo(CreateOrderModel model) {

        /* Update the random Price & Quantity */
        long currentTimeMillis = System.nanoTime();
        model.getShippingPhone().setValue(model.getShippingPhone().getValue());
        model.getPhoneNum().setValue(model.getPhoneNum().getValue());
        model.getEmail().setValue(model.getEmail().getValue());
        model.getAddress().setValue(model.getAddress().getValue() + currentTimeMillis);
        model.getAddressOrder().setValue(model.getAddressOrder().getValue() + currentTimeMillis);

        orderObjects.selectCustomer(model.getCustomer().getValue())
                .selectEmployee(model.getEmployee().getValue())
                .inputShippingPhone(model.getShippingPhone().getValue())
                .inputPhoneNum(model.getPhoneNum().getValue())
                .inputEmail(model.getEmail().getValue())
                .inputAddress(model.getAddress().getValue())
                .inputAddressOrder(model.getAddressOrder().getValue());
        return this;
    }

    /* Update the random Price & Quantity */
    long currentTimeMillis = System.nanoTime();
    String customerName = "LINH CHI";
    String employeeName = "Nguyễn Nhân Viên";
    String shippingPhone = "0123456789";
    String phoneNumber = "0374975401";
    String email = "vukimchi023@gmail.com";
    String address = "Auto_Hai Ba Trung, Ha Noi_" + currentTimeMillis;
    String addressOrder = "Auto_Nguyen Luong Bang, Ha Noi_" + currentTimeMillis;

    /**
     * Fill the product information with random data
     */
    public OrderPage fillOrderInfo() {
        orderObjects.selectCustomer(customerName)
                .selectEmployee(employeeName)
                .inputShippingPhone(shippingPhone)
                .inputPhoneNum(phoneNumber)
                .inputEmail(email)
                .inputAddress(address)
                .inputAddressOrder(addressOrder);
        return this;
    }


    /* Verify the order page display */
    public void verifyOrderPageDisplay() {
        WebElement element = waitForElementVisible(orderObjects.findBtnAddMore());
        assertTrueCondition(element, Objects.nonNull(element), FailureHandling.CONTINUE_ON_FAILURE, "Verify the Order page is displayed");
    }

    /**
     * Verify the product creation
     */
    public void verifyOrderCreation() {
        orderObjects.verifySuccessMessageDisplayed();

        WebElement orderIdEle = orderObjects.findOrderIdRes();
        assertTrueCondition(orderIdEle, Objects.nonNull(orderIdEle), FailureHandling.CONTINUE_ON_FAILURE, "Verify the order ID is displayed after creating a order");

        WebElement orderResultEle = orderObjects.findOrderResult();
        String resultText = getValueOfElement(orderResultEle);
        JSONObject orderJson = new JSONObject(resultText);
        assertEqualCondition(orderResultEle, orderJson.getJSONObject("data").get("id"), getValueOfElement(orderIdEle),
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the order code matches the input value");

        // TODO: Verify the product info
        JSONObject response = new JSONObject(resultText);
        JSONObject data = response.getJSONObject("data");
        JSONObject customer = data.getJSONObject("customerId"); // LẤY CUSTOMER OBJECT

        assertEqualCondition(orderResultEle, data.get("id"), getValueOfElement(orderIdEle),
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the order id NOT matches the input value");

        assertFalseCondition(orderResultEle, data.get("shippingPhoneNum").equals(shippingPhone),
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the order shipping phone matches the input value");

        assertTrueCondition(orderResultEle, response.get("statusCode").equals(201),
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the status code NOT matches the input value");

        assertEqualCondition(orderResultEle, customer.get("name"), customerName,
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the order customer NOT matches the input value");

        assertFalseCondition(orderResultEle, customer.get("phoneNum").equals(phoneNumber),
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the order phone number matches the input value");

        assertFalseCondition(orderResultEle, customer.get("email").equals(email),
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the order email matches the input value");

        assertFalseCondition(orderResultEle, customer.get("address").equals(address),
                FailureHandling.CONTINUE_ON_FAILURE, "Verify the order address matches the input value" );
    }
}

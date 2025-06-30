package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst.FailureHandling;
import com.testek.projects.common.BasePage;
import com.testek.projects.dataprovider.model.CreateOrderModel;
import com.testek.projects.pages.objects.OrderObjects;
import com.testek.projects.pages.objects.ProductObjects;
import org.json.JSONObject;
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

    /**
     * Fill the product information with random data
     */
    public OrderPage fillOrderInfo() {
        /* Update the random Price & Quantity */
        long currentTimeMillis = System.nanoTime();
        String customer = "LINH CHI";
        String employee = "Nguyễn Nhân Viên";
        String shippingPhone = "0123456789";
        String phoneNumber = "0374975401";
        String email = "vukimchi023@gmail.com";
        String address = "Auto_Hai Ba Trung, Ha Noi_" + currentTimeMillis;
        String addressOrder = "Auto_Nguyen Luong Bang, Ha Noi_" + currentTimeMillis;

        orderObjects.selectCustomer(customer)
                .selectEmployee(employee)
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
    }

    //endregion
}

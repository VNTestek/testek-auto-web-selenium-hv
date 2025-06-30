package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst;
import com.testek.projects.common.BasePage;
import com.testek.projects.pages.objects.OrderObjects;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.Objects;

@Slf4j
public class OrderPage extends BasePage {
    private final OrderObjects orderObjects;

    public OrderPage() {
        super();
        PageFactory.initElements(webDriver, this);
        orderObjects = OrderObjects.getInstance();
    }


    public OrderPage fillOrderInfo() {

        String phone = "0" + System.currentTimeMillis();
        String email = "phamhuong@gmail.com";
        String address = "Auto_HUONGPHAM_" + System.currentTimeMillis();
        orderObjects.selectCustomer()
                .inputPhone(phone)
                .inputShipperPhone(phone)
                .inputEmail(email)
                .inputAddress(address)
                .selectCheckboxAddress()
                .selectEmployee();
        return this;
    }

    /* Verify the order page display */
    public void verifyOrderPageDisplay() {
        WebElement element = waitForElementVisible(orderObjects.findAddEle());
        assertTrueCondition(element, Objects.nonNull(element), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "Verify the Order page is displayed");
    }

    public OrderPage clickToCreateOrder() {
        orderObjects.clickAddButton();
        return this;
    }

    /**
     * Verify the order creation
     */
    public void verifyOrderCreation() {
        getWaitDriver().until(ExpectedConditions.attributeToBeNotEmpty(orderObjects.findOrderCodeEle(), "value"));
        String codeOrder = orderObjects.findOrderCodeEle().getAttribute("value");
        getWaitDriver().until(ExpectedConditions.attributeToBeNotEmpty(orderObjects.findErrorMessageEle(), "value"));
        String messageOrder = orderObjects.findErrorMessageEle().getAttribute("value");
        log.info("Code order: [{}]", codeOrder);
        log.info("Message order: [{}]", messageOrder);
        String phone = orderObjects.findPhoneEle().getAttribute("value");

        Assert.assertTrue(messageOrder.contains("\"name\":\"" + "LINH CHI"), "Customer name is not match!");
        Assert.assertTrue(messageOrder.contains("\"shippingPhoneNum\":\"" + phone), "Shipping phone number is not match");

    }

}

package com.testek.study.lesson13.exercise.pages.pages;

import com.testek.consts.FrameConst;
import com.testek.projects.common.BasePage;
import com.testek.projects.dataprovider.model.CreateOrderModel;
import com.testek.study.lesson13.exercise.pages.objects.OrderObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;


public class OrderPage extends BasePage {
    private final OrderObjects orderObjects;


    public OrderPage() {
        super();
        PageFactory.initElements(webDriver, this);
        orderObjects = OrderObjects.getInstance();
    }

    public OrderPage fillOrderInfo(CreateOrderModel model){
        long currentTimeMillis = System.nanoTime();

        orderObjects.selectCustomer(model.getCustomer().getValue())
                .inputPhoneNumber(model.getPhoneNum().getValue())
                .inputShipperPhoneNumber(model.getShipperPhone().getValue())
                .inputEmail(model.getEmail().getValue())
                .inputShipAddress(model.getOderAddress().getValue())
                .selectEmployee(model.getEmployee().getValue());
        return this;
    }

    public void verifyOrderPageDisplay() {
        WebElement element = waitForElementVisible(orderObjects.findBtnAddMore());
        assertTrueCondition(element, Objects.nonNull(element), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "Verify the Order page is displayed");
    }

}

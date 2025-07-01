package com.testek.study.lesson13.exercise.pages.objects;

import com.testek.consts.FrameConst;
import com.testek.study.lesson13.exercise.pages.locator.OrderLocator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class OrderObjects extends BaseObjects{
    @Getter
    public static OrderObjects instance = new OrderObjects();

    private final OrderLocator orderLocator;

    private OrderObjects(){orderLocator = OrderLocator.getInstance();}

    public WebElement findCustomerForm() {return findWebElement(orderLocator.getFormCustomer());}

    public WebElement findPhoneNumber() {return findWebElement(orderLocator.getTxtPhoneNumber());}

    public WebElement findShipperPhoneNumber() {return findWebElement(orderLocator.getTxtShipperPhoneNumber());}

    public WebElement findEmail() {return findWebElement(orderLocator.getTxtEmail());}

    public WebElement findShipAddress() {return findWebElement(orderLocator.getTxtShipAddress());}

    public WebElement findBillAddress() {return findWebElement(orderLocator.getChkBillAddress());}

    public WebElement findEmployeeForm(){return findWebElement(orderLocator.getFormEmployee());}

    public WebElement findBtnAddMore() {return  findWebElement(orderLocator.getBtnAddMore());}

    public WebElement findAddOrderResult() {return findWebElement(orderLocator.getPopUpAddOrderResult());}


    public OrderObjects inputPhoneNumber(String phoneNumber){
        this.inputText(findPhoneNumber(), "Phone Number", phoneNumber);
        return this;
    }

    public OrderObjects inputShipperPhoneNumber (String shipperPhoneNumber){
        this.inputText(findShipperPhoneNumber(), "Shipper Phone Number", shipperPhoneNumber);
        return this;
    }

    public OrderObjects inputEmail (String email){
        this.inputText(findEmail(), "Email", email);
        return this;
    }

    public OrderObjects inputShipAddress (String shipAddress) {
        this.inputText(findShipAddress(), "Shipp Address", shipAddress);
        return this;
    }

    public OrderObjects clickBillAddress () {
        clickTo(findBillAddress(), "Bill Address");
        return this;
    }

    public OrderObjects clickAddOrderButton() {
        clickTo(findBtnAddMore(), "Add Order");
        return this;
    }

    public void verifySuccessMessage() {
        WebElement lblMessage = findAddOrderResult();
        assertTrueCondition(lblMessage, lblMessage.isDisplayed(), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "Verify the success message is displayed after adding a order");
        verifyElementTextEqual(lblMessage, getLanguageValue("Thêm đơn hàng thành công"));
    }

    public WebElement findDropContent (String type) {
        return waitForElementVisible(By.xpath(String.format(orderLocator.getLblRowContent(), type)));
    }

    public OrderObjects selectCustomer (String expRowContent){
        return selectDropdownContent(findCustomerForm(), expRowContent, "Customer");
    }

    public OrderObjects selectEmployee (String expRowContent) {
        return selectDropdownContent(findEmployeeForm(), expRowContent, "Employee");
    }

    private OrderObjects selectDropdownContent(WebElement element, String expRowContent, String title) {
        clickElementViaJs(element, "Select " + title);
        inputText(element, title, expRowContent);

        clickTo(findDropContent(expRowContent), "Select " + title + " Type");
        return this;
    }
}

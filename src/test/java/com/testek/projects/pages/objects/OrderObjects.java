package com.testek.projects.pages.objects;

import com.testek.consts.FrameConst.FailureHandling;
import com.testek.projects.pages.locator.OrderLocator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class OrderObjects extends BaseObjects {

    @Getter
    public static OrderObjects instance = new OrderObjects();

    private final OrderLocator orderLocator;

    private OrderObjects() {
        orderLocator = OrderLocator.getInstance();
    }

    public WebElement findBtnAddMore() {
        return findWebElement(orderLocator.getBtnAddMore());
    }

    public WebElement findCustomerForm() {
        return findWebElement(orderLocator.getFormItemCustomer());
    }

    public WebElement findEmployeeForm() {
        return findWebElement(orderLocator.getFormItemEmployee());
    }

    public WebElement findShippingPhone() {
        return findWebElement(orderLocator.getEdtShippingPhone());
    }

    public WebElement findPhoneNum() {
        return findWebElement(orderLocator.getEdtPhoneNum());
    }

    public WebElement findEmail() {
        return findWebElement(orderLocator.getEdtEmail());
    }

    public WebElement findAddress() {
        return findWebElement(orderLocator.getEdtAddress());
    }

    public WebElement findAddressOrder() {
        return findWebElement(orderLocator.getEdtAddressOrder());
    }

    public WebElement findMnuOrder() {
        return findWebElement(orderLocator.getMnuOrder());
    }

    public WebElement findBtnSearch() {
        return findWebElement(orderLocator.getBtnSearchXpath());
    }

    public WebElement findBtnAdd() {
        return findWebElement(orderLocator.getBtnAdd());
    }

    public WebElement findMsgCreateOrder() {
        return findWebElement(orderLocator.getPopUpAddOrderSuccess());
    }

    public WebElement findOrderIdRes() {
        return findWebElement(orderLocator.getTxtOrderIdResult());
    }

    public WebElement findOrderResult() {
        return findWebElement(orderLocator.getTxtAreaResult());
    }

    public WebElement findIconAdd() {
        return findWebElement(orderLocator.getIconAdd());
    }

    public WebElement findOptionOrder() {
        return findWebElement(orderLocator.getOptionOrder());
    }

    public WebElement findKeyword() {
        return findWebElement(orderLocator.getKeywordXpath());
    }

    public OrderObjects clickIconAddOrder() {
        clickTo(findIconAdd(), "Icon Add");
        return this;
    }

    public OrderObjects clickOptionOrder() {
        clickTo(findOptionOrder(), "Option Product");
        return this;
    }

    public OrderObjects clickMnuOrder() {
        clickTo(findMnuOrder(), "Menu Product");
        return this;
    }

    public OrderObjects clickBtnSearch() {
        clickTo(findBtnSearch(), "Button Search");
        return this;
    }

    public OrderObjects inputKeyword(String keyword) {
        this.inputText(findKeyword(), "Keyword", keyword);
        return this;
    }

    public OrderObjects inputShippingPhone(String shippingPhone) {
        this.inputText(findShippingPhone(), "Shipping Phone", shippingPhone);
        return this;
    }

    public OrderObjects inputPhoneNum(String phoneNum) {
        this.inputText(findPhoneNum(), "Phone Number", phoneNum);
        return this;
    }

    public OrderObjects inputEmail(String email) {
        this.inputText(findEmail(), "Email", email);
        return this;
    }

    public OrderObjects inputAddress(String address) {
        this.inputText(findAddress(), "Address", address);
        return this;
    }

    public OrderObjects inputAddressOrder(String addressOrder) {
        this.inputText(findAddressOrder(), "Address Order", addressOrder);
        return this;
    }

    public OrderObjects clickAddMore() {
        clickTo(findBtnAddMore(), "Add More Order");
        return this;
    }

    public OrderObjects clickAdd() {
        clickTo(findBtnAdd(), "Add Order Button");
        return this;
    }

    public OrderObjects clearShippingPhone() {
        clearText(findShippingPhone());
        return this;
    }

    public OrderObjects clearPhoneNum() {
        clearText(findPhoneNum());
        return this;
    }

    public OrderObjects clearEmail() {
        clearText(findEmail());
        return this;
    }

    public OrderObjects clearAddress() {
        clearText(findAddress());
        return this;
    }

    public OrderObjects clearAddressOrder() {
        clearText(findAddressOrder());
        return this;
    }


    public void verifySuccessMessageDisplayed() {
        WebElement lblMessage = findMsgCreateOrder();
        assertTrueCondition(lblMessage, lblMessage.isDisplayed(), FailureHandling.CONTINUE_ON_FAILURE, "Verify the success message is displayed after adding a product");
        verifyElementTextEqual(lblMessage, getLanguageValue("OrderCreateSuccessMessage"));
    }

    /**
     * Get the row content element by type
     *
     * @param type : Type of product (e.g., "Category", "Supplier")
     * @return : WebElement representing the row content for the specified type
     */
    public WebElement findDropContent(String type) {
        return waitForElementVisible(By.xpath(String.format(orderLocator.getLblRowContent(), type)));
    }

    /* Select category product */
    public OrderObjects selectCustomer(String expRowContent) {
        return selectDropdownContent(findCustomerForm(), expRowContent, "Customer Drop list");
    }

    public OrderObjects selectEmployee(String expRowContent) {
        return selectDropdownContent(findEmployeeForm(), expRowContent, "Employee Drop list");
    }

    /**
     * Select the dropdown content based on the provided element and expected row content.
     *
     * @param element       : WebElement representing the dropdown element
     * @param expRowContent : Expected content of the row to select
     * @param title         : Title for the dropdown selection action, used for logging
     * @return : ProductObjects instance for method chaining
     */
    private OrderObjects selectDropdownContent(WebElement element, String expRowContent, String title) {
        clickElementViaJs(element, "Select " + title);
        inputText(element, title, expRowContent);

        clickTo(findDropContent(expRowContent), "Select " + title + " Type");
        return this;
    }


}

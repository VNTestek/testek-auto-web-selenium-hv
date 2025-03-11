package com.testek.projects.common;

import com.testek.consts.FrameConst;
import com.testek.consts.FrameConst.LogType;
import com.testek.datadriven.DataModel;
import com.testek.driver.DriverManager;
import com.testek.projects.enums.ProjectConst;
import com.testek.projects.page.pages.ProductPage;
import com.testek.utils.LogUtils;
import com.testek.utils.WebUI;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

import static com.testek.consts.FrameConst.FailureHandling.CONTINUE_ON_FAILURE;

/**
 * Create a base methods used for subpage to interact with elements
 */
@Getter
@Setter
public class BasePage extends WebUI {
    public WebDriver webDriver;

    /**
     * Initial a new instance
     */
    public BasePage() {
        webDriver = DriverManager.getDriver();
    }


    // region User Actions: Web Element Interaction


    //region ComboBox

    /**
     * Input text and select from combobox
     *
     * @param comboBoxTitle The title of Combo Box
     * @param comboBoxValue The value which you want to insert
     */
    public void inputTextToComboBoxBySibling(String comboBoxValue, String comboBoxTitle, String... xpathPages) {
    }

    /**
     * Input text and select from combobox
     */
    public void inputTextToComboBoxBySibling(DataModel model, String... xpathPages) {
    }

    /**
     * Input text and select from combobox
     *
     * @param comboBoxElement Combo Box Element (By or Web Element)
     * @param comboBoxTitle   The title of Combo Box
     * @param comboBoxValue   The value which you want to insert
     */
    public void inputTextToComboBox(Object comboBoxElement, String comboBoxTitle, String comboBoxValue, String... xpathDropdownDetail) {
        WebElement element = inputTextTo(comboBoxElement, comboBoxTitle, comboBoxValue);
        if (Objects.isNull(element)) return;
        chooseDropdownItemWithoutScroll(comboBoxValue, xpathDropdownDetail);
    }

    /**
     * Input text and select from combo box
     *
     * @param comboBoxElement Combo Box Element (By or Web Element)
     * @param data            The data to insert
     */
    public void inputTextToComboBox(Object comboBoxElement, DataModel data) {
    }

    /**
     * Input a text to Combo box
     *
     * @param comboBoxValue : Giá trị user nhập
     * @param comboBoxTitle : Tiêu đề của object cần nhập
     * @param xpathPages    : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToComboBoxByTitle(String comboBoxValue, String comboBoxTitle, String... xpathPages) {
    }

    /**
     * Input a text to Combo box with Model Mapper
     *
     * @param data       The data to insert
     * @param xpathPages The page xpath
     */
    public void inputTextToComboBoxByTitle(DataModel data, String... xpathPages) {
    }

    /**
     * Input a text to Combo box with Model Mapper
     */
    public void inputTextToComboBoxWithOutClearData(Object element, String value, String title, String... dropDownXPath) {
        WebElement inputElement = inputTextTo(element, title, value, false);
        if (Objects.isNull(inputElement)) return;

        chooseDropdownItemWithoutScroll(value, dropDownXPath);
    }

    /**
     * Verify a text to Combo box
     *
     * @param comboBoxValue : Giá trị user nhập
     * @param comboBoxTitle : Tiêu đề của object cần nhập
     * @param xpathPages    : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyComboBoxValueByTitle(String comboBoxValue, String comboBoxTitle, String... xpathPages) {
    }

    /**
     * Verify a text to Combo box
     *
     * @param model      : User Data
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyComboBoxValueByTitle(DataModel model, String... xpathPages) {
    }

    /**
     * Lựa chọn element tại Dropwdown thông qua click button và tìm trong list
     *
     * @param comboBoxValue : Giá trị user nhập
     * @param comboBoxTitle : Tiêu đề của object cần nhập
     */
    public void inputTextToComboBoxByTitleUseBtnDropdown(String comboBoxTitle, String comboBoxValue, String... xpathPages) {

    }


    // endregion

    // region ComboBox - PlaceHolder Value

    /**
     * Nhập text với element dạng placeholder and select value from dropdown
     *
     * @param comboBoxValue : Giá trị user nhập
     * @param comboBoxTitle : Tiêu đề của object cần nhập
     * @param xpathPages    : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToPlaceHolderAndComboBox(String comboBoxValue, String comboBoxTitle, String... xpathPages) {
    }

    /**
     * Nhập text với element dạng placeholder and select value from dropdown
     *
     * @param model      The data to insert
     * @param xpathPages The page xpath
     */
    public void inputTextToPlaceHolderAndComboBox(DataModel model, String... xpathPages) {
    }
    // endregion

    //region TextBox

    /**
     * Input text and select from combobox
     */
    public void inputTextToTextBoxBySibling(String textBoxValue, String textBoxTitle, String... xpathPages) {
        String xpath = "%s//*[contains(text(),'%s')]//following-sibling::div//input";
        String finalXPath = getStringXPathDynamic(xpath, xpathPages.length > 0 ? xpathPages[0] : Strings.EMPTY, textBoxTitle);
        inputTextTo(By.xpath(finalXPath), textBoxTitle, textBoxValue);
    }

    /**
     * Input text and select from combobox
     */
    public void inputTextToTextBoxBySibling(DataModel model, String... xpathPages) {
    }

    /**
     * Input a text to text box
     *
     * @param textBoxValue : Giá trị user nhập
     * @param textBoxTitle : Tiêu đề của object cần nhập
     * @param xpathPages   : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToTextBoxByTitle(String textBoxValue, String textBoxTitle, String... xpathPages) {
    }

    /**
     * Input a text to text box
     *
     * @param model      : Giá trị user nhập
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToTextBoxByTitle(DataModel model, String... xpathPages) {
    }

    /**
     * Verify a text in textbox
     *
     * @param textBoxValue : Giá trị user nhập
     * @param textBoxTitle : Tiêu đề của object cần nhập
     * @param xpathPages   : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */

    public void verifyTextBoxValueByTitle(String textBoxValue, String textBoxTitle, String... xpathPages) {
    }

    /**
     * Verify a text in textbox
     *
     * @param model      : Giá trị user nhập
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */

    public void verifyTextBoxValueByTitle(DataModel model, String... xpathPages) {
    }
    // endregion

    // region TextBox - PlaceHolder Value

    /**
     * Input a text to text box with placeholder
     *
     * @param textBoxValue : Giá trị user nhập
     * @param textBoxTitle : Tiêu đề của object cần nhập
     * @param xpathPages   : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToPlaceHolderByTitle(String textBoxValue, String textBoxTitle, String... xpathPages) {
    }

    /**
     * Input a text to text box with placeholder
     *
     * @param model      : Giá trị user nhập
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToPlaceHolderByTitle(DataModel model, String... xpathPages) {
    }

    /**
     * Verify text in text box with placeholder
     *
     * @param textBoxValue : Giá trị user nhập
     * @param textBoxTitle : Tiêu đề của object cần nhập
     * @param xpathPages   : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyTextBoxValueWithPlaceHolderByTitle(String textBoxValue, String textBoxTitle, String... xpathPages) {
    }

    /**
     * Verify text in text box with placeholder
     *
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyTextBoxValueWithPlaceHolderByTitle(DataModel model, String... xpathPages) {
    }
    // endregion

    //region TextArea

    /**
     * Input a text to area
     *
     * @param textAreaValue : Giá trị user nhập
     * @param textAreaTitle : Tiêu đề của object cần nhập
     * @param xpathPages    : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToAreaByTitle(String textAreaValue, String textAreaTitle, String... xpathPages) {
    }

    /**
     * Input a text to area
     *
     * @param model      : Data object off Element
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToAreaByTitle(DataModel model, String... xpathPages) {
    }

    /**
     * Verify text in area
     *
     * @param textAreaValue : Giá trị user nhập
     * @param textAreaTitle : Tiêu đề của object cần nhập
     * @param xpathPages    : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyTextAreaValueByTitle(String textAreaValue, String textAreaTitle, String... xpathPages) {
    }

    /**
     * Verify text in area
     *
     * @param model      : Giá trị user nhập
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyTextAreaValueByTitle(DataModel model, String... xpathPages) {
    }
    // endregion

    //region TextBox - DatePicker Type

    /**
     * Input a text to datepicker
     *
     * @param datePickerValue : Giá trị user nhập
     * @param datePickerTitle : Tiêu đề của object cần nhập
     * @param xpathPages      : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToDatePickerByTitle(String datePickerValue, String datePickerTitle, String... xpathPages) {
    }

    /**
     * Input a text to datepicker
     *
     * @param model      : User Data
     * @param xpathPages : The page xpath
     */
    public void inputTextToDatePickerByTitle(DataModel model, String... xpathPages) {
    }

    /**
     * Verify a text in DatePicker
     *
     * @param datePickerValue : Giá trị user nhập
     * @param datePickerTitle : Tiêu đề của object cần nhập
     * @param xpathPages      : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyDatePickerValueByTitle(String datePickerValue, String datePickerTitle, String... xpathPages) {
    }

    /**
     * Verify a text in DatePicker
     *
     * @param model      : User Data
     * @param xpathPages : The page xpath
     */
    public void verifyDatePickerValueByTitle(DataModel model, String... xpathPages) {
    }

    /**
     * Input a text to datepicker
     */
    public void inputTextToDatePickerByTitleSibling(DataModel model, String... xpathPages) {
    }

    /**
     * Input a text to datepicker
     *
     * @param datePickerValue : Giá trị user nhập
     * @param datePickerTitle : Tiêu đề của object cần nhập
     * @param xpathPages      : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToDatePickerByTitleSibling(String datePickerValue, String datePickerTitle, String... xpathPages) {
    }

    /**
     * Click to datePicker icon
     *
     * @param datePickerTitle : Tiêu đề của object cần nhập
     * @param xpathPages      : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void clickToDatePickerIconByTitle(String datePickerTitle, String... xpathPages) {
    }

    // endregion

    //region TextBox - Number Type

    /**
     * Input text and select from combobox
     */
    public WebElement inputTextToTextBoxNumberBySibling(String textBoxNumValue, String textBoxNumTitle, String... xpathPages) {
        return null;
    }

    /**
     * Input text and select from combobox
     */
    public WebElement inputTextToTextBoxNumberBySibling(DataModel model, String... xpathPages) {
        return null;
    }

    /**
     * Input a text to text box number type
     *
     * @param textBoxNumValue : Giá trị user nhập
     * @param textBoxNumTitle : Tiêu đề của object cần nhập
     * @param xpathPages      : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public WebElement inputTextToTextBoxNumberByTitle(String textBoxNumValue, String textBoxNumTitle, String... xpathPages) {
        return null;
    }

    /**
     * Input a text to text box number type
     *
     * @param textBoxNumValue : Giá trị user nhập
     * @param textBoxNumTitle : Tiêu đề của object cần nhập
     * @param xpathPages      : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public WebElement inputTextToTextBoxNumberByTitleWithSelectAllText(String textBoxNumValue, String textBoxNumTitle, String... xpathPages) {
        return null;
    }

    /**
     * Input a text to text box number type
     *
     * @param model     : Giá trị user nhập
     * @param xpathPage : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public WebElement inputTextToTextBoxNumberByTitle(DataModel model, String... xpathPage) {
        return null;
    }

    /**
     * Input a text to text box number type
     *
     * @param model     : Giá trị user nhập
     * @param xpathPage : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public WebElement inputTextToTextBoxNumberByTitleWithSelectAllText(DataModel model, String... xpathPage) {
        return null;
    }

    /**
     * Verify a number in textbox number
     *
     * @param textBoxNumValue : Giá trị user nhập
     * @param textBoxNumTitle : Tiêu đề của object cần nhập
     * @param xpathPages      : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyTextBoxNumberByTitle(String textBoxNumValue, String textBoxNumTitle, String... xpathPages) {
    }


    /**
     * Verify a number in textbox number
     *
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyTextBoxNumberByTitle(DataModel model, String... xpathPages) {
    }

    /**
     * Input a text to text box number type
     *
     * @param textBoxNumValue : Giá trị user nhập
     * @param textBoxNumTitle : Tiêu đề của object cần nhập
     * @param xpathPages      : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToTextBoxNumberWithOutMSNumberByTitle(String textBoxNumValue, String textBoxNumTitle, String... xpathPages) {
    }

    /**
     * Input a text to text box number type
     *
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void inputTextToTextBoxNumberWithOutMSNumberByTitle(DataModel model, String... xpathPages) {
    }
    // endregion

    //region Scroll

    /**
     * Scroll left with offset value
     *
     * @param scrollObj scroll Object (By or WebElement)
     * @param offset    The offset value
     */
    public void scrollLeft(Object scrollObj, int offset) {
        if (Objects.isNull(scrollObj)) scrollObj = By.xpath("//div[@id='body-layout']");
        var scrollElement = getWebElement(scrollObj);
        getJsExecutor().executeScript(String.format("arguments[0].scrollLeft=%s", offset), scrollElement);
    }
    //endregion

    // region CheckBox

    /**
     * Choose checkbox via Object
     *
     * @param checkBoxTitle The title of checkbox. The system will use this data to find and click the element
     * @param xpathPages    The page xpath
     */
    public void chooseCheckBoxViaElementObj(Object chkElement, String checkBoxValue, String checkBoxTitle, String... xpathPages) {
    }

    /**
     * Choose checkbox via text
     *
     * @param checkBoxValue The value of checkbox
     * @param checkBoxTitle The title of checkbox. The system will use this data to find and click the element
     * @param xpathPages    The page xpath
     */
    public void chooseCheckBoxByText(String checkBoxValue, String checkBoxTitle, String... xpathPages) {
        String finalXPath = getStringXPathDynamic(BaseConst.XPATH_DYNAMIC_FORM_CHECKBOX_INPUT, xpathPages.length > 0 ? xpathPages[0] : Strings.EMPTY, checkBoxTitle);
        waitFor(1);

        try {
            var checkBoxs = webDriver.findElements(By.xpath(finalXPath));
            if (checkBoxs.isEmpty()) return;

            var checkBox = checkBoxs.get(0);
            chooseCheckBox(checkBox, checkBoxValue);
        } catch (Exception error) {
            LogUtils.error("chooseCheckBoxByText: Normal text - Press Enter", error);
        }

        String msg = String.format("Insert text [%s] to [%s]  <br/> <span style='font-size: 0.75em'>(Element's locator:  %s)</span>", FrameConst.ReportConst.BOLD_START + checkBoxValue + FrameConst.ReportConst.BOLD_END, checkBoxTitle, finalXPath);
        addReportInfo(LogType.INFO, msg, "setText_" + checkBoxValue, finalXPath);
    }

    /**
     * Choose checkbox via Model Mapper
     *
     * @param model      The user data
     * @param xpathPages The page xpath
     */
    public void chooseCheckBoxByText(DataModel model, String... xpathPages) {
    }

    /**
     * Check to checkbox
     *
     * @param checkBoxElement : WebElement
     * @param checkBoxValue   : Value expected (O: Tick/ Other: UnTick)
     */
    private void chooseCheckBox(WebElement checkBoxElement, String checkBoxValue) {
        boolean currentStatus = checkBoxValue.equalsIgnoreCase(BaseConst.CHECKBOX_OK);
        // Uncheck : Checkbox was selected and input False
        if (checkBoxElement.isSelected() && !currentStatus) {
            getActions().click(checkBoxElement).perform();
        }
        // Check : Checkbox don't tick and input true
        else if (!checkBoxElement.isSelected() && currentStatus) {
            getActions().click(checkBoxElement).perform();
        }
    }

    /**
     * Select a checkbox
     *
     * @param checkBoxTitle : Tiêu đề của object cần nhập
     * @param xpathPages    : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void selectCheckBoxByTitle(String checkBoxValue, String checkBoxTitle, String... xpathPages) {
        boolean isCheck = false;
        try {
            isCheck = Objects.nonNull(checkBoxValue) & checkBoxValue.equalsIgnoreCase(BaseConst.CHECKBOX_OK);
        } catch (Exception e) {
            isCheck = false;
        }
        String finalXPath = getStringXPathDynamic(BaseConst.XPATH_DYNAMIC_FORM_CHECKBOX_INPUT, xpathPages.length > 0 ? xpathPages[0] : Strings.EMPTY, checkBoxTitle);
        selectItemFromCheckBox(By.xpath(finalXPath), isCheck, checkBoxTitle);
    }

    /**
     * Select a checkbox
     *
     * @param isCheck       : giá trị expect
     * @param checkBoxTitle : Tiêu đề của object cần nhập
     * @param xpathPage     : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyCheckBoxByTitle(boolean isCheck, String checkBoxTitle, String... xpathPage) {
        String finalXPath = getStringXPathDynamic(BaseConst.XPATH_DYNAMIC_FORM_CHECKBOX_INPUT, xpathPage.length > 0 ? xpathPage[0] : Strings.EMPTY, checkBoxTitle);
        var chkElement = getWebElement(getByXpathDynamic(finalXPath));
        assertEqualCondition(chkElement, chkElement.isSelected(), isCheck, CONTINUE_ON_FAILURE, checkBoxTitle);
        try {
            var parent = chkElement.findElement(By.xpath("./.."));
            var spnElement = parent.findElement(By.xpath("./span"));
            var classValue = getAttributeOfElement(spnElement, "class");
            if (isCheck)
                assertTrueCondition(chkElement, classValue.contains("ms-checkbox-border-checked-true"), CONTINUE_ON_FAILURE, "Kiểm tra checkbox ticked");
            else
                assertTrueCondition(chkElement, classValue.contains("ms-checkbox-border-checked-false"), CONTINUE_ON_FAILURE, "Kiểm tra checkbox unticked");
        } catch (Exception e) {
            LogUtils.info("verifyCheckBoxByTitle - Exception : " + e.getMessage());
        }
    }

    /**
     * Choose checkbox via Object
     *
     * @param checkBoxTitle The title of checkbox. The system will use this data to find and click the element
     * @param xpathPages    The page xpath
     */
    public void verifyCheckBoxViaElementObj(Object chkElementObj, String checkBoxValue, String checkBoxTitle, String... xpathPages) {
        WebElement chkElement = getWebElement(chkElementObj);
        boolean isCheck = Objects.nonNull(checkBoxValue) & checkBoxValue.equalsIgnoreCase(BaseConst.CHECKBOX_OK);
        //assertEqualCondition(chkElement, chkElement.isSelected(), isCheck, CONTINUE_ON_FAILURE, checkBoxTitle);
        try {
            var parent = chkElement.findElement(By.xpath("./.."));
            var spnElement = parent.findElement(By.xpath("./span"));
            var classValue = getAttributeOfElement(spnElement, "class");
            if (isCheck)
                assertTrueCondition(chkElement, classValue.contains("ms-checkbox-border-checked-true"), CONTINUE_ON_FAILURE, "Kiểm tra checkbox ticked");
            else
                assertTrueCondition(chkElement, classValue.contains("ms-checkbox-border-checked-false"), CONTINUE_ON_FAILURE, "Kiểm tra checkbox unticked");
        } catch (Exception e) {
            LogUtils.info("verifyCheckBoxByTitle - Exception : " + e.getMessage());
        }
    }
    // endregion

    // region Radio button

    /**
     * Select radio button
     *
     * @param radioTitle : Tiêu đề của object cần nhập
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void selectRadioButtonByTitle(String radioTitle, String... xpathPages) {
    }

    /**
     * Select radio button
     *
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void selectRadioButtonByTitle(DataModel DataModel, String... xpathPages) {
    }

    /**
     * Verify radio button selected or not
     *
     * @param isSelected : giá trị expect
     * @param radioTitle : Tiêu đề của object cần nhập
     * @param xpathPages : Parent or Page locator (Required: Khi tìm thấy nhiều object có chung postfix locator)
     */
    public void verifyRadioButtonByTitle(boolean isSelected, String radioTitle, String... xpathPages) {
    }

    // endregion

    // region File Attachment

    /**
     * Upload file
     *
     * @param fileUploadElement The web element for uploading file
     * @param filePaths         The file paths
     */
    public void uploadFile(Object fileUploadElement, String... filePaths) {
        WebElement element = getWebElement(fileUploadElement);
        uploadFileSendKeys(element, filePaths);
    }
    // endregion

    // region Dropdown Panel


    public boolean chooseDropdownItemWithClickButton(Object element, String value, String... resultXPaths) {
        try {
            clickElement(element);
        } catch (Exception e) {
            clickElementViaJs(element);
        }
        return chooseDropdownItemWithoutScroll(value, resultXPaths);
    }

    /**
     * Select an item into the dropdown list.
     * Sử dụng cho hàm click dropdown để chọn value
     *
     * @param itemName The item name
     */
    protected boolean chooseDropdownItemWithoutScroll(String itemName, String... resultXPaths) {
        return false;
    }

    /**
     * Scroll drop-down util discover drop-down item then select that one
     *
     * @param itemName text content of item that need to find
     * @return web-element of discovered item
     */
    public WebElement getDropdownItemWithScroll(String itemName) {

        return null;
    }

    /**
     * Scroll in loop until discovers the item on the By, It takes too many times to loop
     *
     * @param scroller      drop-down list scroller
     * @param viewHeight    current height of view
     * @param scrollWrapper drop-down wrapper, it in need to capture the height of view
     * @param itemBy        item by
     * @return web element of the item one
     */
    private WebElement findFirstUntilScroll(WebElement scroller, WebElement scrollWrapper, By itemBy, Integer viewHeight, Integer times) {
        return null;
    }


    // endregion

    // region Dropdown Menu
    private String XPATH_MENU_IN_DROPDOWN_MENU = "//div[contains(@class,'ms-component') and not(contains(@style, 'display: none;'))]//ul[@class='ms-component ms-dropdown--menu']//li[normalize-space()='%s']";

    /**
     * Choose menu in dropdown menu by menu name (text shown)
     * Note: Only apply for menu under 'ul' tag with class 'ms-dropdown--menu'
     *
     * @param menuName
     */
    public void selectMenuItemInDropdownMenu(String menuName) {
        WebElement element = waitForElementVisible(getByXpathDynamic(XPATH_MENU_IN_DROPDOWN_MENU, menuName));
        clickElement(element);
    }


    /**
     * Hover to a button then Choose menu in dropdown menu by menu name (text shown)
     * Note: Only apply for menu under 'ul' tag with class 'ms-dropdown--menu'
     *
     * @param menuName
     */
    protected void selectMenuFromHoverButton(Object elementObj, String menuName, boolean... isJavaScripts) {
        WebElement element = waitForElementVisible(getWebElement(elementObj));
        hoverElement(element, isJavaScripts);
        selectMenuItemInDropdownMenu(menuName);
    }


    // endregion

    // region Toast Message

    /**
     * Verify a toast message (ở giữa màn hình) is shown
     *
     * @param message
     */
    public void verifyToastNotificationShown(String message) {

    }
    // endregion

    // region Button

    /**
     * Click to Button theo text content [Button có hoặc không có short-key đều dùng được]
     *
     * @param isVerify: tùy chọn có/không verify button còn xuất hiện sau khi click
     */
    public void pressToButtonByText(String btnTitle, boolean isVerify, String... xpathPages) {

    }

    /**
     * Click button Save and Add ở footer của detail page
     */
    public void pressToSaveAddButton(String... xpathPages) {
        pressToButtonByText(getLanguageValue("SaveAdd"), false, xpathPages);
    }

    /**
     * Click button Edit ở footer của detail page
     */
    public void pressToEditButton(boolean isVerify, String... xpathPages) {
        pressToButtonByText(getLanguageValue("menu_edit"), isVerify, xpathPages);
    }

    /**
     * Click button Save ở footer của detail page
     */
    public void pressToSaveButton(boolean isVerify, String... xpathPages) {
        pressToButtonByText(getLanguageValue("Save"), isVerify, xpathPages);
    }

    /**
     * Click button Close ở footer của detail page
     */
    public void pressToCloseButton(boolean isVerify, String... xpathPages) {
        pressToButtonByText(getLanguageValue("Close"), isVerify, xpathPages);
    }

    /**
     * Click button 'Ghi doanh số' ở footer của detail page
     */
    public void pressToRecogniseRevenueButton(boolean isVerify, String... xpathPages) {
        pressToButtonByText(getLanguageValue("menu_recognise_revenue"), isVerify, xpathPages);
        verifyToastNotificationShown(getLanguageValue("PostRevenueSuccessNoDot"));
    }
    // endregion

    // region Tab

    /**
     * Choose the tab by name
     *
     * @param tabName
     * @param xpathPage
     */
    public void selectTab(String tabName, String... xpathPage) {

    }
    // endregion

    //endregion

    //region Redirect to Page

    /**
     * Go to specific URL
     *
     * @param URL       : URL Page
     * @param pageTitle : Page title
     */
    protected void goToSpecificURL(String URL, String pageTitle) {
        goToURL(URL);
        assertTrueCondition(null, verifyPageUrl(URL), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, String.format("Verify the '%s' page", pageTitle));
        String msg = FrameConst.ReportConst.BOLD_START + FrameConst.Icon.ICON_NAVIGATE_RIGHT + " Go to URL : " + FrameConst.ReportConst.BOLD_END + DriverManager.getDriver().getCurrentUrl();
        addReportInfo(LogType.INFO, msg, null, null);
    }

    /**
     * Truy cap Banking Home Page
     */
    @Step("Go to 'Product' Page")
    public ProductPage gotoProductPage() {
        ProjectConst.ModuleURL module = ProjectConst.ModuleURL.PRODUCT;
        goToSpecificURL(module.getPath(), module.getName());
        return new ProductPage();
    }
    //endregion

}

package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst;
import com.testek.projects.common.BasePage;
import com.testek.projects.dataprovider.model.CreateCusModel;
import com.testek.projects.dataprovider.model.CreateSupModel;
import com.testek.projects.pages.objects.CreateCustomerObjects;
import com.testek.projects.pages.objects.CreateSupplierObjects;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;
import java.util.UUID;

import static com.testek.utils.FakerUtils.getRandomQuantity;

public class CreateCustomerPage extends BasePage {
    private final CreateCustomerObjects createCustomerObjects;

    public CreateCustomerPage() {
        super();
        PageFactory.initElements(webDriver, this);
        createCustomerObjects = CreateCustomerObjects.getInstance();
    }

    //Kiểm tra truy cập man them moi khach hang đúng không ?
    public void verifyAccessCreateCustomer() {
        createCustomerObjects.verifyTitleCreateCustomer();
    }

    //Nhập thông tin cho khach hang
//    public CreateCustomerPage fillCusInfo(CreateCusModel model) {
//        long currentTimeMillis = System.nanoTime();
//        model.getName().setValue(model.getName().getValue());
//        model.getPhone().setValue(model.getPhone().getValue());
//        model.getEmail().setValue(model.getEmail().getValue());
//        model.getContact().setValue(model.getContact().getValue());
//        model.getCountry().setValue(model.getCountry().getValue());
//        model.getCity().setValue(model.getCity().getValue());
//        model.getAddress().setValue(model.getAddress().getValue() + currentTimeMillis);
//        model.getPostalCode().setValue(String.valueOf(getRandomQuantity()));
//
//        createCustomerObjects.inputName(model.getName().getValue())
//                .inputPhone(model.getPhone().getValue())
//                .inputContact(model.getContact().getValue())
//                .inputEmail(model.getEmail().getValue())
//                .inputCountry(model.getCountry().getValue())
//                .inputCity(model.getCity().getValue())
//                .inputAddress(model.getAddress().getValue())
//                .inputPostalCode(model.getPostalCode().getValue());
//        return this;
//    }

    String name;
    String phone;
    String email;
    String contact;
    String country;
    String city;
    String address;
    String postalCode;

    /**
     * Fill the supplier information with random data
     */
    public CreateCustomerPage fillCusInfo() {
        long currentTimeMillis = System.nanoTime();
        name = "CHI TEST1_" + currentTimeMillis;
        phone = "0374975417";
        email = String.format("vukimchi%s@gmail.com", currentTimeMillis);
        contact = "0374975457";
        country = "Việt Nam";
        city = "Hà Nội";
        address = "Auto_Test_Hai Ba Trung, Ha Noi_" + currentTimeMillis;
        postalCode = "1000";

        createCustomerObjects.inputName(name)
                .inputPhone(phone)
                .inputEmail(email)
                .inputContact(contact)
                .inputCountry(country)
                .inputCity(city)
                .inputAddress(address)
                .inputPostalCode(postalCode);
        return this;
    }

    public CreateCustomerPage clickAddCus() {
        createCustomerObjects.clickBtnAdd();
        return this;
    }

    /**
     * Verify the product creation
     */
    public void verifyCusCreation() {
        createCustomerObjects.verifySuccessMessageDisplayed();

        WebElement cusIdEle = createCustomerObjects.findTxtCusId();
        assertTrueCondition(cusIdEle, Objects.nonNull(cusIdEle), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Customer ID is not displayed after creating a customer");

        WebElement cusResultEle = createCustomerObjects.findTextAreaError();
        String result = getValueOfElement(cusResultEle);

        // TODO: Verify the product info
        JSONObject response = new JSONObject(result);
        JSONObject data = response.getJSONObject("data");

        assertEqualCondition(cusResultEle, data.get("id"), getValueOfElement(cusIdEle),
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The customer id NOT matches the input value");

        assertTrueCondition(cusResultEle, data.get("name").equals(name),
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Customer Name NOT matches the input value");

        assertFalseCondition(cusResultEle, response.get("code").equals(201),
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The code matches the input value");

        assertEqualCondition(cusResultEle, data.get("phoneNum"), phone,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Customer phone NOT matches the input value");

        assertEqualCondition(cusResultEle, data.get("email"), email,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Customer email NOT matches the input value");

        assertEqualCondition(cusResultEle, data.get("country"), country,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Customer country NOT matches the input value");

        assertEqualCondition(cusResultEle, data.get("city"), city,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Customer city NOT matches the input value");

        assertEqualCondition(cusResultEle, data.get("address"), address,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Customer address NOT matches the input value");

        assertEqualCondition(cusResultEle, data.get("postalCode"), postalCode,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Customer PostalCode NOT matches the input value");

        assertEqualCondition(cusResultEle, data.get("contact"), contact,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Customer contact NOT matches the input value");
    }
}

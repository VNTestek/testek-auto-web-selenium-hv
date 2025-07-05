package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst;
import com.testek.projects.common.BasePage;
import com.testek.projects.dataprovider.model.CreateSupModel;
import com.testek.projects.pages.objects.CreateSupplierObjects;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

import static com.testek.utils.FakerUtils.getRandomQuantity;


public class CreateSupplierPage extends BasePage {
    private final CreateSupplierObjects createSupplierObjects;

    public CreateSupplierPage() {
        super();
        PageFactory.initElements(webDriver, this);
        createSupplierObjects = CreateSupplierObjects.getInstance();
    }

    //Kiểm tra truy cập Nhà cung cấp đúng không ?
    public void verifyAccessCreateSupplier() {
        createSupplierObjects.verifyTitleCreateSupplier();
    }

    //Nhập thông tin cho Nhà cung cấp
    public CreateSupplierPage fillSupInfo(CreateSupModel model) {
        long currentTimeMillis = System.nanoTime();
        model.getSupName().setValue(model.getSupName().getValue());
        model.getSupPhone().setValue(model.getSupPhone().getValue());
        model.getSupContact().setValue(model.getSupContact().getValue());
        model.getSupCountry().setValue(model.getSupCountry().getValue());
        model.getSupCity().setValue(model.getSupCity().getValue());
        model.getSupAddress().setValue(model.getSupAddress().getValue() + currentTimeMillis);
        model.getSupPostalCode().setValue(String.valueOf(getRandomQuantity()));

        createSupplierObjects.inputSupName(model.getSupName().getValue())
                .inputSupPhone(model.getSupPhone().getValue())
                .inputSupContact(model.getSupContact().getValue())
                .inputSupCountry(model.getSupCountry().getValue())
                .inputSupCity(model.getSupCity().getValue())
                .inputSupAddress(model.getSupAddress().getValue())
                .inputSupPostalCode(model.getSupPostalCode().getValue());
        return this;
    }

    String supName;
    String supPhone;
    String supContact;
    String supCountry;
    String supCity;
    String supAddress;
    String supPostalCode;

    /**
     * Fill the supplier information with random data
     */
    public CreateSupplierPage fillSupInfo() {
        long currentTimeMillis = System.nanoTime();
        supName = String.format("KIM CHI_%s", currentTimeMillis);
        supPhone = "0374975400";
        supContact = String.format("vukimchi%s@gmail.com", currentTimeMillis);
        supCountry = "Việt Nam";
        supCity = "Hà Nội";
        supAddress = "Auto_Hai Ba Trung, Ha Noi_" + currentTimeMillis;
        supPostalCode = "1000";

        createSupplierObjects.inputSupName(supName)
                .inputSupPhone(supPhone)
                .inputSupContact(supContact)
                .inputSupCountry(supCountry)
                .inputSupCity(supCity)
                .inputSupAddress(supAddress)
                .inputSupPostalCode(supPostalCode);
        return this;
    }

    public CreateSupplierPage clickAddMoreSup() {
        createSupplierObjects.clickBtnAddMore();
        return this;
    }

    /**
     * Verify the product creation
     */
    public void verifySupCreation() {
        createSupplierObjects.verifySuccessMessageDisplayed();

        WebElement supIdEle = createSupplierObjects.findTxtSupId();
        assertTrueCondition(supIdEle, Objects.nonNull(supIdEle), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The supplier ID is not displayed after creating a supplier");

        WebElement supResultEle = createSupplierObjects.findTextAreaError();
        String result = getValueOfElement(supResultEle);

        // TODO: Verify the product info
        JSONObject response = new JSONObject(result);
        JSONObject data = response.getJSONObject("data");

        assertEqualCondition(supResultEle, data.get("id"), getValueOfElement(supIdEle),
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The supplier id NOT matches the input value");

        assertTrueCondition(supResultEle, data.get("supName").equals(supName),
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Supplier Name NOT matches the input value");

        assertFalseCondition(supResultEle, response.get("statusCode").equals(200),
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The status code matches the input value");

        assertEqualCondition(supResultEle, data.get("supContactName"), supContact,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Supplier Contact Name NOT matches the input value");

        assertEqualCondition(supResultEle, data.get("supAddress"), supAddress,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Supplier Address NOT matches the input value");

        assertEqualCondition(supResultEle, data.get("supCity"), supCity,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Supplier City NOT matches the input value");

        assertEqualCondition(supResultEle, data.get("supPostalCode"), supPostalCode,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Supplier PostalCode NOT matches the input value");

        assertEqualCondition(supResultEle, data.get("supCountry"), supCountry,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Supplier Country NOT matches the input value");

        assertEqualCondition(supResultEle, data.get("supPhone"), supPhone,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The Supplier Phone NOT matches the input value");
    }

    public void clearData() {
        createSupplierObjects.clearSupName()
                .clearSupPhone()
                .clearSupContact()
                .clearSupCountry()
                .clearSupCity()
                .clearSupAddress()
                .clearSupPostalCode();
    }
}

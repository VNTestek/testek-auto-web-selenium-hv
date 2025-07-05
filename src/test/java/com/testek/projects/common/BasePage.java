package com.testek.projects.common;

import com.testek.consts.FrameConst;
import com.testek.consts.FrameConst.LogType;
import com.testek.controller.WebUI;
import com.testek.driver.DriverManager;
import com.testek.consts.ProjectConst;
import com.testek.projects.pages.pages.CreateSupplierPage;
import com.testek.projects.pages.pages.OrderPage;
import com.testek.projects.pages.pages.ProductPage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static com.testek.report.ReportConfig.*;

/**
 * Create a base methods used for subpage to interact with elements
 */
@Getter
@Setter
@Slf4j
public class BasePage extends WebUI {
    public WebDriver webDriver;

    /**
     * Initial a new instance
     */
    public BasePage() {
        webDriver = DriverManager.getDriver();
    }

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
        String msg = BOLD_START + Icon.ICON_NAVIGATE_RIGHT + " Go to URL : " + BOLD_END + DriverManager.getDriver().getCurrentUrl();
        addReportInfo(LogType.INFO, msg, null, null);
    }

    /**
     * Access the 'Product' page
     */
    @Step("Go to 'Product' Page")
    public ProductPage gotoProductPage() {
        ProjectConst.ModuleURL module = ProjectConst.ModuleURL.PRODUCT;
        goToSpecificURL(module.getPath(), module.getName());
        return new ProductPage();
    }

    /**
     * Access the 'Order' page
     */
    @Step("Go to 'Order' Page")
    public OrderPage gotoOrderPage() {
        ProjectConst.ModuleURL module = ProjectConst.ModuleURL.ORDER;
        goToSpecificURL(module.getPath(), module.getName());
        return new OrderPage();
    }

    /**
     * Access the 'Create Supplier' page
     */
    @Step("Go to 'Create' Supplier")
    public CreateSupplierPage gotoCreateSupplierPage() {
        ProjectConst.ModuleURL module = ProjectConst.ModuleURL.CREATESUPPLIER;
        goToSpecificURL(module.getPath(), module.getName());
        return new CreateSupplierPage();
    }

    //endregion

}

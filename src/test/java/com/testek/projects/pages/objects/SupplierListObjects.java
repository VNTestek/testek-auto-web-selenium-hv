package com.testek.projects.pages.objects;

import com.testek.projects.pages.PageManagement;
import com.testek.projects.pages.locator.SupplierLocator;
import com.testek.projects.pages.pages.SupplierListPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SupplierListObjects extends BaseObjects {
    @Getter
    public static SupplierListObjects instance = new SupplierListObjects();

    private final SupplierLocator supplierLocator;

    private SupplierListObjects() {
        supplierLocator = SupplierLocator.getInstance();
    }

    public WebElement findMnuSupplier() {
        return findWebElement(supplierLocator.getMnuSupplier());
    }

    public WebElement findKeyword() {
        return findWebElement(supplierLocator.getKeyword());
    }

    public WebElement findBtnSearch() {
        return findWebElement(supplierLocator.getBtnSearch());
    }

    public List<WebElement> findSearchName() {
        return this.webDriver.findElements(By.xpath(supplierLocator.getSearchName()));
    }

    public SupplierListObjects clickMnuSup () {
        this.clickTo(findMnuSupplier(), "Menu Supplier");
        return this;
    }

    public SupplierListObjects inputKeyword (String keyword) {
        this.inputText(findKeyword(), "Keyword", keyword);
        return this;
    }

    public SupplierListPage clickBtnSearch () {
        this.clickTo(findBtnSearch(), "Button Search");
        return PageManagement.gotoSupplierListPage();
    }

}

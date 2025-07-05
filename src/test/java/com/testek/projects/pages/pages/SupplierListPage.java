package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst;
import com.testek.projects.common.BasePage;
import com.testek.projects.pages.objects.SupplierListObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class SupplierListPage extends BasePage {
    private final SupplierListObjects supplierListObjects;

    public SupplierListPage() {
        super();
        PageFactory.initElements(webDriver, this);
        supplierListObjects = SupplierListObjects.getInstance();
    }

    String keyword = "KIM CHI";
    public SupplierListPage searchInfoSup() {
        return supplierListObjects.inputKeyword(keyword)
                .clickBtnSearch();
    }

    // Assert that search results are not empty
    public void verifySearchResult() {
        String supName = "KIM CHI";
        String supPhone = "0374975400";
        String supContact = "vukimchi";
        String supCountry = "Việt Nam";
        String supCity = "Hà Nội";
        String supAddress = "Auto_Hai Ba Trung, Ha Noi";
        String supPostalCode = "1000";

        List<WebElement> searchNameEles = supplierListObjects.findSearchName();

//        String[] keywords = {"Tivi", "TV"};
//        for (int i = 0; i < searchResultEles.size(); i++) {
//            // Get the Name and Description columns
//            WebElement searchResultEle = searchResultEles.get(i);
//            WebElement tdNameColEle = searchResultEle.findElement(By.xpath(".//td[2]"));
//            String tdNameColText = tdNameColEle.getText();
//            WebElement tdDescColEle = searchResultEle.findElement(By.xpath(".//td[3]"));
//            String tdDescColText = tdDescColEle.getText();
//
//            boolean isMatch = false;
//            for (String key : keywords) {
//                // Check if either Name or Description contains the keyword
//                if (tdNameColText.toLowerCase().contains(key.toLowerCase()) || tdDescColText.toLowerCase().contains(key.toLowerCase())) {
//                    isMatch = true;
//                    break;
//                }
//            }
//            log.info("Row " + (i + 1) + ": Name: " + tdNameColText + ", Description: " + tdDescColText + " => Match: " + isMatch);
//            // Assert that the row contains the keyword "Tivi" in Name or Description
//            mSoftAssert.assertTrue(isMatch, "Result return in row " + (i + 1) + "does not contain 'Tivi' in Name or Description");
//        }

    }

}

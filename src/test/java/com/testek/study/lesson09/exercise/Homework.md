### Homework: TƯƠNG TÁC PHẦN TỬ

1. Tương tác với toàn bộ các phần tử trong page sau (sử dụng Action, Wait, JavaScript):
   1. Truy cập: https://testek.vn/lab/auto/web-elements/
   2. Truy cập Form > StudentForm
   3. Tìm kiếm toàn bộ các phần tử trong page

   ![homepage.png](../../../../../../resources/data/study/studentForm.png)



### Hướng dẫn
1. Tìm kiếm toàn bộ các phần tử trong screen
2. Sử dụng Selenium WebDriver để thực hiện tìm kiếm các phần tử
3. Xây dựng các method tương tác với các phần tử
4. Sử dụng Action, Wait, JavaScript để thực hiện tương tác với các phần tử

Lưu ý: Hãy viết thành các base method, ví dụ:
- clickButton (By by); 
- sendKeys (By by, String text); 
- chooseOption (By by, String text);
- ... để có thể sử dụng lại cho các bài sau.


Ví dụ: Sử dụng các loại wait & method support để thực hiện phát triển script
```java
   /**
    * Wait for element clickable
    */
    private WebElement waitForElementClickable(By by) {
        log.info("Waiting for element clickable: {}", by);
        return mWebDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }


   /**
    * Send keys to element
    */
   private void sendKeysToElement(By by, String text, boolean isClear) {
      log.info("Send keys to element: {}", by);
      WebElement element = waitForElement(by);
      if (element == null) {
         log.error("Element not found: {}", by);
         return;
      }
   
      // Clear the text if isClear = true
      if (isClear) {
         element.clear();
      }
      element.sendKeys(text);
      log.info("Send keys to element: {}", text);
   }


   /**
    * Click on element
    */
   private void clickOnElement(By by) {
       log.info("Click on element: {}", by);
       WebElement element = waitForElementClickable(by);
       if (element == null) {
           log.error("Element not found: {}", by);
           return;
       }
       element.click();
       // Can add JS click if needed
       log.info("Click on element: {}", by);
   }
```


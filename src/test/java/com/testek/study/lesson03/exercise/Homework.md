### Homework: XÁC ĐỊNH LOCATOR NÂNG CAO

1. Tìm kiếm toàn bộ phần tử trong page sau:
   Truy cập: https://testek.vn/lab/auto/web-elements/
   2. Truy cập domain
   3. Tìm kiếm toàn bộ các phần tử trong page
   ![homepage.png](../../../../../../resources/data/study/homepage.png)
# Hướng dẫn
1. Sử dụng các locator sau:
   - CSS Selector (với các loại contains, starts-with)
   - XPath (Full, bao gồm: contains, start-with, text(), ancestor, descendant, sibling, following, preceding...) <br/>
   Yêu cầu: Thực hiện xác định phần tử với cả 02 loại locator và các loại support của nó (nếu có, ví dụ id trong CSS Selector dùng #; class trong CSS Selector dùng .)
   
2. Sử dụng DevTools để xác định các locator
3. Sử dụng Selenium WebDriver để thực hiện tìm kiếm các phần tử
   1. Khai báo các biến là các locator cho từng phần tử
   2. Sử dụng mWebDriver để tìm kiếm các phần tử
   ```java
    String lblTitleXpath = "//h4[@test-id='db-website']/following-sibling::h4[@test-id='db-facebook']";
    WebElement lblTitleElement = mWebDriver.findElement(By.xpath(lblTitleXpath));
    ```
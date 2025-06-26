### Homework: XÁC ĐỊNH LOCATOR NÂNG CAO

1. Tìm kiếm toàn bộ phần tử trong page sau:
   1. Truy cập: https://testek.vn/lab/auto/web-elements/
   2. Truy cập Form > StudentForm
   3. Tìm kiếm toàn bộ các phần tử trong page
   
   ![homepage.png](../../../../../../resources/data/study/studentForm.png)
# Hướng dẫn
1. Tìm kiếm locator cơ bản với toàn bộ các phần tử trong screen
2. Nghiên cứu và tìm ra các đặc điểm chung cho các phần tử đó
3. Xây dựng FORMAT chung -> Config lại toàn bộ các elements đáp ứng form 
4. Sử dụng Selenium WebDriver để thực hiện tìm kiếm các phần tử
   1. Khai báo các biến là các locator cho từng phần tử
   2. Sử dụng mWebDriver để tìm kiếm các phần tử
   ```java
    String lblTitleXpath = "//h4[@test-id='db-website']/following-sibling::h4[@test-id='db-facebook']";
    WebElement lblTitleElement = mWebDriver.findElement(By.xpath(lblTitleXpath));
    ```
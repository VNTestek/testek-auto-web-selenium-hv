### Homework: TƯƠNG TÁC PHẦN TỬ

1. Tương tác với toàn bộ các phần tử trong page sau (sử dụng Action):
   1. Truy cập: https://testek.vn/lab/auto/web-elements/
   2. Truy cập Form > StudentForm
   3. Tìm kiếm toàn bộ các phần tử trong page

   ![homepage.png](../../../../../../resources/data/study/studentForm.png)

2. Thực hiện phát triển script tự động cho kịch bản manual như sau:
   1. Truy cập: https://testek.vn/lab/auto/login
   2. Thưc hiện đăng nhập với tài khoản:
      - Tài khoản: admin_com_role
      - Mật khẩu: aA12345678@
   3. Thực hiện truy cập [Sản phẩm]
   4. Thực hiện tìm kiếm sản phẩm với từ khóa: "Tivi"

3. Thực hiện tương tác Action (Click, Double Click) với các phần tử trong page sau:
   1. Truy cập: https://testek.vn/lab/auto/web-elements/
   2. Truy cập Widget > Accordion
   3. Thực hiện mỗi section một lần click và double click, tại mỗi lần đó hãy in ra thông tin được hiển thị (nếu có)

4. Thực hiện tương tác Action (Hover) với các phần tử trong page sau:
   1. Truy cập: https://testek.vn/lab/auto/web-elements/
   2. Truy cập Widget > Menu
   3. Thực hiện hover vào các menu và in ra thông tin được hiển thị (nếu có)
   Ví dụ: Services: [Web Design, Web Development, SEO, etc.]

### Hướng dẫn
1. Tìm kiếm toàn bộ các phần tử trong screen
2. Sử dụng Selenium WebDriver để thực hiện tìm kiếm các phần tử
   1. Khai báo các biến là các locator cho từng phần tử
   2. Sử dụng mWebDriver để tìm kiếm các phần tử và tương tác
   ```java
    String lblTitleXpath = "//h4[@test-id='db-website']/following-sibling::h4[@test-id='db-facebook']";
    WebElement lblTitleElement = mWebDriver.findElement(By.xpath(lblTitleXpath));
    ```


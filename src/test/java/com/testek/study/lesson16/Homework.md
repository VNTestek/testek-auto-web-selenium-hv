### Homework: TRIỂN KHAI POM VỚI TESTNG FRAMEWORK

1. Thực hiện triển khai bài kiểm tra module 02 áp dụng Page Object Model (POM) & Data Driven
Thực hiện tạo dữ liệu test (tối thiểu 03 bộ dữ liệu) và sử dụng TestNG Data Provider để thực hiện kiểm tra với các bộ dữ liệu này.
   1. Truy cập: https://testek.vn/lab/auto/login
      1. Kiểm tra có truy cập đúng website không ?
   2. Login với tài khoản Admin (admin_com_role / aA12345678@)
      1. Kiểm tra sau khi login thành công hay không ? 
   3. Truy cập add button > Lựa chọn Đơn hàng
      1. Kiểm tra truy cập đơn hàng đúng không ? 
   4. Nhập thông tin cho Đơn hàng 
      1. Các thông tin này do bạn tự tạo với thông tin yêu cầu như sau: Auto_[YOUR NAME]_[timestamp]
      2. Ví dụ Địa chỉ: Auto_Yên Hoà, Cầu Giấy_1750731826 
      3. Nhập đầy đủ thông tin cho các trường khác, nếu là dropdown cần sử dụng dữ liệu có sẵn thì vui lòng chọn 01 dữ liệu trong danh sách 
   5. Press [Thêm nhiều hơn]
      1. Verify kết quả sau khi nhấn button [Thêm nhiều hơn]
   6. Lặp lại bước 4,5 cho 01 Đơn hàng nữa (Tổng: Cần add 02 Đơn hàng)
   7. Thực hiện verify lại đơn hàng đó tại màn hình Danh sách 
      1. Truy cập menu “Đơn hàng” > Tìm kiếm trong danh sách và verify

2. (Optional) Thực hiện phát triển POM & tích hợp Data Driven với luồng nghiệp vụ tạo mới sản phẩm như sau:
   1. Truy cập: https://testek.vn/lab/auto/login
   2. Login với tài khoản Admin (admin_com_role / aA12345678@)
   3. Truy cập add button > Lựa chọn Sản phẩm
   4. Nhập thông tin cho Sản phẩm
      1. Các thông tin này do bạn tự tạo với thông tin yêu cầu như sau: Auto_[YOUR NAME]_[timestamp]
      2. Ví dụ Tên sản phẩm: Auto_Yên Hoà, Cầu Giấy_1750731826 
      3. Nhập đầy đủ thông tin cho các trường khác, nếu là dropdown cần sử dụng dữ liệu có sẵn thì vui lòng chọn 01 dữ liệu trong danh sách
   5. Press [Thêm] và verify kết quả sau khi nhấn button [Thêm]
Lưu ý: Bạn cân thực hiện tạo dữ liệu test (tối thiểu 03 bộ dữ liệu) và sử dụng TestNG Data Provider để thực hiện kiểm tra với các bộ dữ liệu này.
   

### Hướng dẫn
1. Triển khai với 03 levels:
   1. Locator: Chứa các locator của các phần tử trong trang web
   2. Object: Chứa các phương thức tương tác với các phần tử trong trang web
   3. Page: Chứa các phương thức thực hiện các hành động trên trang web
2. Phát triển trực tiếp trong các package tại project
   3. Sử dụng TestNG Data Provider để thực hiện kiểm tra với các bộ dữ liệu đã tạo. Lưu ý: Các ví dụ source code trong hướng dẫn là ví dụ để apply cho bài tập thực hành, chúng không phải là dữ liệu cho nghiệp vụ đang phát triển. Bạn cần thực hiện các bước sau để hoàn thành bài tập:
      1. Nghiên cứu nghiệp vụ manual --> Đánh giá và xác định các dữ liệu cần thiết để thực hiện nhập/kiểm tra
      2. Xây dựng Model dữ liệu cho các bộ dữ liệu này, đảm bảo mỗi field trong Model tương ứng với các trường cần nhập/kiểm tra trên trang web
      ```java
      public class LoginModel extends BaseModel {
      public DataModel userName;
      public DataModel password;
   
          public LoginModel() {
              super();
              userName = createDataModelObj("UserName");
              password = createDataModelObj("Password");
          }
      }
      ```
      3. Xây dựng Data Provider trong TestNG để cung cấp/tích hợp và sử dụng các bộ dữ liệu này (lưu ý name của Data Provider)
      ```java
      public class LoginProvider extends BaseProvider {
      JsonUtils jsonUtils = JsonUtils.getInstance();
   
          @DataProvider(name = "Testek_Login_001_Valid")
          public  Object[][] TK_Login_001_Valid(Method method) {
              var dataList = jsonUtils.readDataTestFromJSON(DataPath.DATA_LOGIN, method.getName());
   
              // Using Model Class and Data From Json file
              LoginModel templateModel = new LoginModel();
              return updateDataModel(templateModel, dataList);
          }
      }
      ```
      4. Tích hợp vào test script, sử dụng annotations @Test và @DataProvider để thực hiện tương tác với các bộ dữ liệu đã tạo
      Lưu ý: dataProvider trong @Test phải trùng với tên đã định nghĩa trong phương thức @DataProvider (tại bước 03); dataProviderClass cũng phải trùng với tên class đã định nghĩa trong phương thức @DataProvider
      ```java
      @FrameAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
      @Test(description = "Verify the login function", dataProvider = "Testek_Login_001_Valid", dataProviderClass = LoginProvider.class)
      public void Testek_Login_001_Valid(LoginModel data) {
            // Access to the login page
            LoginPage loginPage = PageManagement.accessWebPage();
   
            // Verify the login page is displayed
             loginPage.verifyLoginPageDisplay();
            
            // Perform login with valid credentials
            HomePage homePage = loginPage.login(data);
   
            // Verify the home page is displayed after login
            homePage.verifyHomePage();
          }
      ```
      5. Tối ưu các method và sử dụng data đã tạo để thực hiện
      Lưu ý: Các phương thức test có sử dụng Data Provider sẽ sử dụng model để làm dữ liệu đầu vào cho các method, như LoginModel; Khi tương tác, lấy value bạn sẽ lấy như sau:
      ```java
      /* Login  with userName and password */
      public HomePage login(LoginModel data) {
           return login(data.getUserName().getValue(), data.getPassword().getValue());
      }
      ```
4. Sử dụng các phương thức Assert để kiểm tra kết quả trả về từ các hành động trên trang web



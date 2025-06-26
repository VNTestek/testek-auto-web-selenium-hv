### Homework: TRIỂN KHAI POM VỚI TESTNG FRAMEWORK

1. Thực hiện triển khai bài kiểm tra module 02 áp dụng Page Object Model (POM) và TestNG Framework.
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

2. (Optional) Thực hiện phát triển POM với luồng nghiệp vụ tạo mới sản phẩm như sau:
   1. Truy cập: https://testek.vn/lab/auto/login
   2. Login với tài khoản Admin (admin_com_role / aA12345678@)
   3. Truy cập add button > Lựa chọn Sản phẩm
   4. Nhập thông tin cho Sản phẩm
      1. Các thông tin này do bạn tự tạo với thông tin yêu cầu như sau: Auto_[YOUR NAME]_[timestamp]
      2. Ví dụ Tên sản phẩm: Auto_Yên Hoà, Cầu Giấy_1750731826 
      3. Nhập đầy đủ thông tin cho các trường khác, nếu là dropdown cần sử dụng dữ liệu có sẵn thì vui lòng chọn 01 dữ liệu trong danh sách
   5. Press [Thêm] và verify kết quả sau khi nhấn button [Thêm]
   

### Hướng dẫn
1. Triển khai với 03 levels:
   1. Locator: Chứa các locator của các phần tử trong trang web
   2. Object: Chứa các phương thức tương tác với các phần tử trong trang web
   3. Page: Chứa các phương thức thực hiện các hành động trên trang web
2. Phát triển trực tiếp trong các package tại project



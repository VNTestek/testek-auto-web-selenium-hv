### Homework: TRIỂN KHAI POM VỚI TESTNG FRAMEWORK

1. Thực hiện triển khai kiểm tra Nhà cung cấp áp dụng Page Object Model (POM) và TestNG Framework.
   1. Truy cập: https://testek.vn/lab/auto/login
      1. Kiểm tra có truy cập đúng website không ?
   2. Login với tài khoản Admin (admin_com_role / aA12345678@)
      1. Kiểm tra sau khi login thành công hay không ? 
   3. Truy cập add button > Lựa chọn Nhà cung cấp
      1. Kiểm tra truy cập Nhà cung cấp đúng không ? 
   4. Nhập thông tin cho Nhà cung cấp 
   5. Press [Thêm nhiều hơn]
      1. Verify kết quả sau khi nhấn button [Thêm nhiều hơn]
   6. Lặp lại bước 4,5 cho 01 Nhà cung cấp nữa (Tổng: Cần add 02 Nhà cung cấp)
   7. Thực hiện verify lại Nhà cung cấp đó tại màn hình Danh sách 
      1. Truy cập menu “Nhà cung cấp” > Tìm kiếm trong danh sách và verify

2. (Optional) Thực hiện phát triển POM với luồng nghiệp vụ tạo mới khách hàng như sau:
   1. Truy cập: https://testek.vn/lab/auto/login
   2. Login với tài khoản Admin (admin_com_role / aA12345678@)
   3. Truy cập add button > Lựa chọn Khách hàng
   4. Nhập thông tin cho Khách hàng
      1. Nhập đầy đủ thông tin cho các trường khác, nếu là dropdown cần sử dụng dữ liệu có sẵn thì vui lòng chọn 01 dữ liệu trong danh sách
   5. Press [Thêm] và verify kết quả sau khi nhấn button [Thêm]
   

### Hướng dẫn
1. Triển khai với 03 levels:
   1. Locator: Chứa các locator của các phần tử trong trang web
   2. Object: Chứa các phương thức tương tác với các phần tử trong trang web
   3. Page: Chứa các phương thức thực hiện các hành động trên trang web
2. Phát triển trực tiếp trong các package tại project



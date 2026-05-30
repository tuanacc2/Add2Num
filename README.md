# Module Core: MyBigNumber (Lõi xử lý số nguyên lớn)

Module này chịu trách nhiệm xử lý thuật toán cộng hai chuỗi số nguyên có độ dài bất kỳ (vượt qua giới hạn lưu trữ dữ liệu thông thường của các kiểu dữ liệu nguyên thủy như `long` hay `double`). Đồng thời, module hỗ trợ cơ chế ghi nhận luồng dữ liệu I/O (Log Console) phục vụ mô phỏng thuật toán trực quan.

---

## Nguyên lý hoạt động & Thuật toán

Thuật toán mô phỏng lại chính xác phương pháp **cộng toán học tiểu học (cộng từ phải sang trái có nhớ)**.

1. **Vòng lặp quét ngược:** Duyệt từ cuối chuỗi (hàng đơn vị) về đầu chuỗi:
   - Tách lần lượt hai đơn vị cuối của chuỗi.
   - Tính tổng: `Tổng = Số_thứ_nhất + Số_thứ_hai + Số_nhớ`.
   - Ghi nhớ `Số_nhớ` ra một biến phụ `(flag)`.
   - Cập nhật lại `Số_nhớ` (`Tổng / 10`) cho lượt tính tiếp theo.
3. **Xử lý số dư cuối (Leftover):** Khi vòng lặp kết thúc, nếu `Số_nhớ > 0`, hạ số nhớ cuối cùng này xuống vị trí ngoài cùng bên trái của chuỗi kết quả.

---

## Đặc tả kỹ thuật API

### Phương thức chính
```java
public static String sum(String num1, String num2)
```
Tham số đầu vào:
```text
num1 (String): Chuỗi ký tự số nguyên thứ nhất (Chỉ chứa các ký tự từ 0-9).

num2 (String): Chuỗi ký tự số nguyên thứ hai (Chỉ chứa các ký tự từ 0-9).
```
Dữ liệu phản hồi:
```text
String: Chuỗi kết quả của phép toán cộng.
```
## Định dạng Log Stream đầu ra (Standard Output)

Trong quá trình thực thi hàm sum, hệ thống sẽ tự động in dữ liệu log tiến trình ra System.out để các ứng dụng tầng trên (như Spring Boot Controller) có thể hứng luồng dữ liệu I/O và chuyển đổi thành cấu trúc JSON.

## Định dạng log chuẩn được in ra:

```text
Thực hiện cộng hàng đơn vị: lấy [X] cộng [Y], cộng số nhớ [Z]. Kết quả = [T], ghi nhớ [K].
```
# Hướng dẫn tích hợp vào dự án Spring Boot (Maven)

Để tích hợp file mybignumber.jar đã đóng gói này vào một dự án Spring Boot khác, thực hiện theo các bước sau:

1. Đặt file vào thư mục nội bộ
Tạo thư mục libs/ ở gốc dự án Spring Boot và sao chép file .jar vào đó:

```text
project
 ├── libs
 │   └──  mybignumber-core-0.0.1.jar
 └── pom.xml
```

2. Khai báo dependency trong pom.xml

Thêm cấu hình nạp thư viện từ hệ thống cục bộ:

```XML
<dependency>
    <groupId>com.mybignumber</groupId>
    <artifactId>lib</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/mybignumber.jar</systemPath>
</dependency>
```

Đồng thời, cập nhật spring-boot-maven-plugin để cho phép đóng gói thư viện hệ thống khi deploy lên các nền tảng đám mây (như Render/Docker):

```XML
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <includeSystemScope>true</includeSystemScope>
    </configuration>
</plugin>
```

# Tự biên dịch file jar 

Mở Terminal tại thư mục gốc của dự án và chạy lệnh sau để tự biên dịch.

```bash
./mvnw clean package -DskipTests
```

File `mybignumber-core-0.0.1.jar` sẽ xuất hiện trong thư mục `\target`.

# Tài liệu hướng dẫn MySQL cơ bản

## 1. Mục tiêu

Sau tài liệu này, bạn sẽ làm được:
1. Tạo **database** và **bảng** trong MySQL.
2. Sử dụng các câu lệnh SQL cơ bản:
   - `CREATE DATABASE`, `CREATE TABLE`
   - `INSERT`
   - `SELECT` (kèm `WHERE`, `ORDER BY`, `LIKE`, `LIMIT`)
   - `UPDATE`
   - `DELETE`
3. Thực hành trên ví dụ bảng **students** để dùng cho Java/JDBC hoặc Spring MVC.

---

## 2. Kết nối MySQL

### 2.1. Dùng terminal (mysql client)

```bash
mysql -u root -p
```

- `-u root`: đăng nhập với user root (hoặc user khác nếu có).
- `-p`: MySQL sẽ hỏi mật khẩu.

Sau khi đăng nhập thành công, bạn sẽ thấy prompt:

```text
mysql>
```

### 2.2. Dùng MySQL Workbench

- Mở MySQL Workbench.
- Tạo 1 Connection (ví dụ: `Local instance MySQL`).
- Bấm **Test Connection** → OK → **Connect**.
- Mở tab **Query** để chạy lệnh SQL.

---

## 3. Tạo database và chọn database

### 3.1. Tạo database

```sql
CREATE DATABASE examdb;
```

Hoặc an toàn hơn (nếu đã tồn tại thì không lỗi):

```sql
CREATE DATABASE IF NOT EXISTS examdb;
```

### 3.2. Chọn database để làm việc

```sql
USE examdb;
```

Lệnh này giúp tất cả câu lệnh sau đó (CREATE TABLE, INSERT, SELECT, …) sẽ áp dụng lên database `examdb`.

---

## 4. Tạo bảng từ yêu cầu bài toán

Ví dụ muốn quản lý sinh viên với thông tin:

- `id` (INT) – khóa chính.
- `name` (VARCHAR) – tên sinh viên.
- `email` (VARCHAR) – email.
- `score` (DOUBLE) – điểm.

Ta tạo bảng:

```sql
USE examdb;

CREATE TABLE students (
    id    INT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    score DOUBLE
);
```

### 4.1. Xem danh sách bảng

```sql
SHOW TABLES;
```

### 4.2. Xem cấu trúc bảng

```sql
DESCRIBE students;
```

---

## 5. Lệnh INSERT – Thêm dữ liệu

### 5.1. Thêm một bản ghi

```sql
INSERT INTO students (id, name, email, score)
VALUES (1, 'Nguyen Van A', 'a@example.com', 8.5);
```

### 5.2. Thêm nhiều bản ghi cùng lúc

```sql
INSERT INTO students (id, name, email, score)
VALUES
(2, 'Tran Thi B', 'b@example.com', 7.0),
(3, 'Le Van C',   'c@example.com', 9.0);
```

### 5.3. Kiểm tra kết quả

```sql
SELECT * FROM students;
```

---

## 6. Lệnh SELECT – Đọc dữ liệu

### 6.1. Lấy toàn bộ dữ liệu

```sql
SELECT * FROM students;
```

### 6.2. Lấy một vài cột

```sql
SELECT id, name, score FROM students;
```

### 6.3. Dùng WHERE để lọc

```sql
-- Sinh viên có điểm >= 8
SELECT * FROM students
WHERE score >= 8;

-- Sinh viên có email cụ thể
SELECT * FROM students
WHERE email = 'b@example.com';
```

### 6.4. Dùng LIKE để tìm gần đúng

```sql
-- Tên bắt đầu bằng chữ 'N'
SELECT * FROM students
WHERE name LIKE 'N%';

-- Tên chứa chuỗi 'an'
SELECT * FROM students
WHERE name LIKE '%an%';
```

### 6.5. ORDER BY – Sắp xếp kết quả

```sql
-- Sắp xếp theo điểm tăng dần
SELECT * FROM students
ORDER BY score ASC;

-- Sắp xếp theo điểm giảm dần
SELECT * FROM students
ORDER BY score DESC;
```

### 6.6. LIMIT – Giới hạn số dòng

```sql
-- Lấy 5 sinh viên đầu tiên
SELECT * FROM students
ORDER BY id
LIMIT 5;
```

---

## 7. Lệnh UPDATE – Cập nhật dữ liệu

> LUÔN luôn cẩn thận với mệnh đề `WHERE`. Nếu bỏ `WHERE` là cập nhật toàn bảng.

### 7.1. Cập nhật điểm cho 1 sinh viên

```sql
UPDATE students
SET score = 9.5
WHERE id = 1;
```

### 7.2. Cập nhật nhiều cột cùng lúc

```sql
UPDATE students
SET name = 'Nguyen Van A Updated',
    email = 'a_new@example.com'
WHERE id = 1;
```

Kiểm tra lại:

```sql
SELECT * FROM students WHERE id = 1;
```

---

## 8. Lệnh DELETE – Xóa dữ liệu

### 8.1. Xóa 1 bản ghi theo id

```sql
DELETE FROM students
WHERE id = 2;
```

### 8.2. Xóa nhiều bản ghi theo điều kiện

```sql
-- Xóa sinh viên có điểm < 5
DELETE FROM students
WHERE score < 5;
```

### 8.3. Xóa toàn bộ dữ liệu trong bảng

```sql
-- Cực kỳ cẩn thận: lệnh này xóa hết dữ liệu
DELETE FROM students;
```

Hoặc dùng lệnh nhanh:

```sql
TRUNCATE TABLE students;
```

`TRUNCATE` cũng xóa hết dữ liệu, nhưng reset luôn auto-increment (nếu có).

---

## 9. Một số lệnh quản lý khác

### 9.1. Xóa bảng

```sql
DROP TABLE students;
```

### 9.2. Xóa database

```sql
DROP DATABASE examdb;
```

> Chỉ dùng khi bạn chắc chắn không cần dữ liệu nữa.

---

## 10. Liên hệ với Java/JDBC hoặc Spring MVC

Khi viết Java/JDBC:

- Các lệnh SQL bạn dùng trong code **chính là** các câu lệnh ở trên.
- Ví dụ trong Java:

```java
String sqlInsert = "INSERT INTO students(id, name, email, score) VALUES (?,?,?,?)";
String sqlSelect = "SELECT id, name, email, score FROM students";
```

Quy trình gợi ý:

1. Viết và **test câu lệnh SQL trong MySQL** (CLI hoặc Workbench).
2. Khi chắc chắn chạy đúng → copy vào code Java, thay giá trị cụ thể bằng `?` và set bằng `PreparedStatement`.
3. Dùng `ResultSet` để đọc kết quả `SELECT` và map về model `Student`.

---

## 11. Gợi ý luyện thi

1. Tự tay:
   - Tạo 1 database khác (ví dụ `demo_exam`).
   - Tạo 1 bảng khác (vd `products`, `books`, `orders`).
2. Viết đủ:
   - INSERT vài dòng.
   - SELECT với WHERE / LIKE / ORDER BY / LIMIT.
   - UPDATE 1-2 dòng.
   - DELETE theo điều kiện.
3. Thử chạy trên Workbench và trên terminal để quen cả hai môi trường.

Chỉ cần quen được mấy lệnh này là bạn đã đủ nền tảng SQL để làm bài Java + Spring MVC + JDBC trong đề thi.

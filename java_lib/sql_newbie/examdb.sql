-- examdb.sql
-- Script tạo database và bảng students để dùng cho bài Spring MVC không Maven

-- 1. Tạo database (nếu chưa có)
CREATE DATABASE IF NOT EXISTS examdb
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- 2. Chọn database
USE examdb;

-- 3. Xóa bảng cũ nếu tồn tại (cẩn thận khi dùng trên môi trường thật)
DROP TABLE IF EXISTS students;

-- 4. Tạo bảng students
CREATE TABLE students (
    id    INT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    score DOUBLE
);

-- 5. Thêm dữ liệu mẫu
INSERT INTO students (id, name, email, score) VALUES
(1, 'Nguyen Van A', 'a@example.com', 8.5),
(2, 'Tran Thi B',   'b@example.com', 7.0),
(3, 'Le Van C',     'c@example.com', 9.0);

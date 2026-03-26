# 🎬 Movie Booking System

A web-based movie ticket booking system built with **Spring Boot**, **MySQL**, and **Docker**.
This project allows users to browse movies, view showtimes, and book tickets online.

---

## 🚀 Features

* 🎥 View movie list and details
* 🕒 Showtimes management
* 🎟️ Book tickets online
* 👤 User management (login/register - optional)
* 🗂️ MVC architecture (Controller - Service - Repository)
* 🐳 Docker support for easy deployment

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot
* **Database:** MySQL
* **ORM:** Spring Data JPA / Hibernate
* **Frontend:** Thymeleaf (HTML, CSS)
* **Build Tool:** Maven
* **Containerization:** Docker

---

## 📁 Project Structure

```
rapchieuphim/
│── src/
│   ├── main/
│   │   ├── java/com/example/rapchieuphim/
│   │   │   ├── config/
│   │   │   ├── controllers/
│   │   │   ├── model/
│   │   │   ├── repositories/
│   │   │   └── RapchieuphimApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       └── application.properties
│
│── docker-compose.yml
│── pom.xml
│── README.md
```

---

## ⚙️ Installation & Run

### 🔹 1. Clone repository

```bash
git clone https://github.com/tanint06/rapchieuphim.git
cd rapchieuphim
```

---

### 🔹 2. Run with Docker (recommended)

```bash
docker-compose up
```

👉 This will:

* Start MySQL database
* Run Spring Boot application

---

### 🔹 3. Run manually (without Docker)

#### Start MySQL first

Update your database config in:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rapchieuphim
spring.datasource.username=root
spring.datasource.password=your_password
```

#### Run project

```bash
mvn spring-boot:run
```

---

## 🌐 Access Application

Open browser:

```
http://localhost:8080
```

---

## 📌 Notes

* ❌ Do NOT commit:

  * `mysql_data/`
  * `target/`
* ✔️ Use `.gitignore` to exclude unnecessary files
* 🔐 Do not expose real database passwords

---

## 📸 Demo (Optional)

*Add screenshots of your UI here*

---

## 🤝 Contribution

Contributions are welcome!

1. Fork this repository
2. Create new branch (`git checkout -b feature/your-feature`)
3. Commit changes
4. Push and create Pull Request

---

## 📄 License

This project is for educational purposes.

---

## 👨‍💻 Author

* GitHub:   https://github.com/Developer0630 __ Phan Văn Nhật Hiếu
            https://github.com/tanint06      __ Phạm Kỳ Tân
            ...                              __ Phan Ngọc Hoàng Sơn
            ...                              __ Trần Viết Nhật

---

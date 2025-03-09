# Hotel Servlet App

## 📌 Project Overview
The **Hotel Servlet App** is a web-based application built using Java Servlets, JSP, and JDBC. It enables users to book hotel rooms, manage reservations, and provides an admin panel for hotel staff to oversee bookings and customer details.

## 🚀 Features
### 🔹 User Features
- View available hotel rooms
- Book a room
- Cancel reservations
- User authentication (Login/Signup)

### 🔹 Admin Features
- Manage room availability
- View all bookings
- User management

## 🛠️ Tech Stack
- **Frontend:** HTML, CSS, JSP
- **Backend:** Java Servlets
- **Database:** PostgreSQL/MySQL (JDBC Connection)
- **Server:** Apache Tomcat
- **Build Tool:** Maven

## 📂 Project Structure
```
HotelServletApp/
│── src/main/java/
│   ├── com.hotel.servlets/        # Servlet classes
│   ├── com.hotel.dao/             # Data Access Objects (DAO)
│   ├── com.hotel.models/          # Model classes (Entities)
│
│── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── web.xml                # Deployment Descriptor
│   ├── views/
│   │   ├── index.jsp              # Homepage
│   │   ├── login.jsp              # Login Page
│   │   ├── register.jsp           # Signup Page
│   │   ├── dashboard.jsp          # User Dashboard
│
│── pom.xml                        # Maven Dependencies
│── README.md                      # Project Documentation
```

## 🔧 Installation & Setup
### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/mahingaRodin/HotelServletApp.git
cd HotelServletApp
```

### **2️⃣ Configure Database**
- Create a database in PostgreSQL/MySQL
- Import the provided SQL script

### **3️⃣ Update Database Configuration**
Modify `db.properties` in `src/main/resources/`:
```properties
db.url=jdbc:postgresql://localhost:5432/hotel_db
db.user=your_db_username
db.password=your_db_password
```

### **4️⃣ Build & Run the Project**
#### Using Maven:
```sh
mvn clean install
```
#### Deploy on Tomcat:
1. Open IntelliJ IDEA and **import the project as a Maven project**.
2. Go to `Run` > `Edit Configurations` > `Tomcat Server`.
3. Add an artifact (`hotel-servlet:war exploded`).
4. Click **Run** and access it at:
```
http://localhost:8080/HotelServletApp
```

## 🛠️ Future Improvements
- Implement payment gateway integration
- Add email notifications for bookings
- Improve UI/UX with Bootstrap/Tailwind

## 📜 License
This project is licensed under the MIT License.

---
### ✨ Developed by [Mahinga Rodin](https://github.com/mahingaRodin) & Team 🚀


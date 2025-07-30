# Library-managment-system-Swing-

A Java Swing application with MySQL backend for efficient library operations, featuring role-based access control and comprehensive book/member management.

---

## 📌 Table of Contents
- [Project Overview](#-project-overview)
- [Key Features](#-key-features)
- [Database Scehema](#-database-schema)
- [Tech Stack](#-tech-stack)
- [How It Works](#-how-it-works)
- [Installation](#-installation)
- [Usage](#-usage)
- [Credentials](#-credentials)

---

## 🌟 Project Overview
Traditional library systems rely on manual record-keeping. This automated solution streamlines:
   - Book tracking (Check-in/Check-out)
   - Member management
   - Admin analytics
   - Transaction history

---

## ✨ Key Features
- **Role-Based Access**: Separate interfaces for admins/librarians
- **Real-Time Inventory**: Live updates on book availability
- **Search & Filter**: Multi-criteria book/member search
- **Transaction Logs**: Detailed history with timestamps 
- **Responsive UI**: Swing-based intuitive interface

---

## 📂 Database Schema
Tables structure:  
| Table | Key Fields | Purpose |  
|--------|-------------|------|  
| `books` | ISBN, Title, Author, Status | Track inventory |  
| `members` | MemberID, Name, Contact | User management |  
| `transactions` | TransactionID, BookID, Date | Record checkouts/returns |   
| ... | (See full list in code) | ... |  

---

## 🛠 Tech Stack
1. Core:
   - Java 8+
   - Swing (GUI)
   - MySQL (Database)
2. Libraries:
   - mysql-connector-j (Database connectivity)
3. Architecture:
   - MVC Pattern
   - JDBC for data persistence

---

## 🔍 How It Works
1. Authentication:
    - Role-based login (Admin/User)
2. Data Flow:
    - Swing UI → JDBC → MySQL
3. Operations:
    - CRUD for books/members
    - Transaction processing with validation

---

## ⚙️ Installation
1. Prerequisites:
MySQL Workbench  
JDK 8+  
2. Setup:
    # Clone repository
    git clone https://github.com/Omkarjha31/Library-managment-system-Swing-.git

    # Compile (from project root)
    javac -cp "lib\mysql-connector-j-8.0.23.jar" -sourcepath src -d out src\librarymanagement\Main.java

---

## 🚀 Usage
1. Run:
java -cp "out;lib\mysql-connector-j-8.0.23.jar" librarymanagement.Main

2. Navigation:
    - Admins: Full system control
    - Users: Book search/checkout

---

## 🔑 Credentials
Default admin access:
- Username: admin
- Password: admin

---
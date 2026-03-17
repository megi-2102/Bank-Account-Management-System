# 🏦 Banking System – Java (TDD, Design Patterns, Multithreading)

## 📖 Overview
This project is a Java-based banking system developed using **Test-Driven Development (TDD)** principles.  
It implements core banking functionalities such as account management, fee calculation, and safety deposit box allocation.

The project demonstrates strong understanding of **object-oriented programming, clean code practices, design patterns, and concurrency**.

---

## 🚀 Features

### 💰 Fee Calculation
Calculates account fees based on balance ranges:
- Balance ≤ 100 → $20 fee  
- Balance ≤ 500 → $15 fee  
- Balance ≤ 1000 → $10 fee  
- Balance ≤ 2000 → $5 fee  
- Balance > 2000 → $0 fee  

---

### 🏦 Account Management
- Create new accounts  
- Delete existing accounts  
- Retrieve list of accounts  
- Uses **DAO pattern** for data handling  

---

### 🔐 Safety Deposit Box System
- Allocate and release safety deposit boxes  
- Implements:
  - **Singleton pattern**
  - **Object Pool pattern**
  - **Thread-safe operations (synchronization)**
- Supports concurrent access using multithreading  
- Handles waiting when no boxes are available  

---

## 🧪 Testing
This project follows **Test-Driven Development (TDD)**:

- Unit tests written using:
  - **JUnit**
  - **Mockito**
- Covers:
  - Fee calculation logic  
  - Account service behaviour  
  - Multithreading scenarios  

---

## 🛠️ Technologies Used
- Java  
- JUnit  
- Mockito  
- Maven (if applicable)  

---

## 🧠 Key Concepts Demonstrated
- Object-Oriented Programming (OOP)  
- SOLID Principles  
- Design Patterns (Singleton, Object Pool)  
- Multithreading & Synchronization  
- Clean Code Practices  
- Test-Driven Development (TDD)  

---

## 📂 Project Structure

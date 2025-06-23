# 💸 JavaBudgetApp

A simple console-based budget tracking application written in Java.  
This project uses Java **records**, **ArrayLists**, and **basic CLI interaction** to let users add, view, and delete expenses by category — now with persistent file storage.

---

## ✨ Features

- Add new expenses and dynamically create categories
- View all expenses grouped by category
- Delete expenses by category and amount
- Automatically removes empty categories when unused
- **Saves all data to a file and loads it on startup**
- Uses Java `record` for clean data structure

---

## 📦 Technologies

- Java 17+
- Console Input via `Scanner`
- File I/O (`FileWriter`, `Scanner`)
- `record` for modeling expense entries

---

## 🧠 Example Output
[1] new Expense
[2] see all Expenses
[3] delete Expense

---

## 🙋‍♂️ About

Built by **Jason Belzek** as a personal learning project to explore Java records, I/O, and basic data structures.  
Feel free to fork, explore, or suggest improvements!

---

## 🚀 Getting Started

1. Clone the repository
2. Compile and run `Main.java`:
```bash
javac Main.java
java Main

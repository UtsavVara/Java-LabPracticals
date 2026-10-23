# MiniBank — Java Console Banking Project

A semester-style Java mini project demonstrating object-oriented programming, collections, validation, exceptions, synchronization, file persistence, transaction logs, and reporting.

## Features

- Menu-driven console interface: open accounts, deposit, withdraw, transfer, list accounts, view transaction history, generate report, estimate interest.
- Account types: Savings (minimum balance ₹500), Current (overdraft limit ₹5,000), and Fixed Deposit (withdrawals locked in this demo).
- OOP: encapsulation, inheritance, abstraction through shared account behavior, runtime polymorphism, interfaces, records, enums, nested classes, cloning, `equals`, `hashCode`, `toString`, and `Comparable`.
- Input validation and custom checked exceptions.
- Thread-safe account balance operations and serialized transfer operations.
- Collections: `ConcurrentHashMap`, synchronized transaction history, sorting by balance.
- Persistence in `data/accounts.bin`; append-only log in `data/transactions.log`; report in `data/daily-report.txt`.
- Runtime annotations and reflection validation utilities.
- Executor-based transaction processor utility.

## Requirements

- JDK 17 or later (records and switch expressions are used).
- No third-party dependencies and no database setup required.

## Run

From the project root:

### Linux / macOS
```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out minibank.MiniBank
```

### Windows PowerShell
```powershell
New-Item -ItemType Directory -Force out
$files = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out minibank.MiniBank
```

On first run, three demo accounts are created if no saved data exists. Account numbers are printed in the list. The program creates `data/` files as needed. Keep the working directory at the project root so persistence files remain in the expected location.

## Menu

```text
1. Open account
2. Deposit
3. Withdraw
4. Transfer
5. List accounts
6. Transaction history
7. End-of-day report
8. Interest estimate
0. Exit
```

## Sample session

```text
=================================
       MINIBANK CONSOLE
=================================
1. Open account
2. Deposit
3. Withdraw
4. Transfer
5. List accounts
6. Transaction history
7. End-of-day report
8. Interest estimate
0. Exit
Choose an option: 5
AC0001 | Aarav Patel          | Balance: ₹5000 | ACTIVE
AC0002 | Diya Shah            | Balance: ₹10000 | ACTIVE
AC0003 | Riya Desai            | Balance: ₹15000 | ACTIVE

Choose an option: 2
Account number: AC0001
Deposit amount: 500
Deposit successful. New balance: ₹5500
```

Exact demo account balances may differ after previous runs because state is persisted.

## Test checklist

1. List accounts and note an account number.
2. Deposit a positive amount; confirm balance increases.
3. Try depositing `0` or a negative amount; expect a friendly validation message.
4. Withdraw an amount within the allowed limit; confirm balance decreases.
5. Try withdrawing too much from a Savings Account; expect an insufficient-funds message.
6. Try withdrawing from a Fixed Deposit Account; expect a locked-account message.
7. Transfer money between two different valid accounts and confirm both balances.
8. Enter an unknown account number; expect an account-not-found message.
9. Generate the report and inspect `data/daily-report.txt`.
10. Exit and restart; confirm account balances persist.

## Project layout

```text
MiniBank/
├── README.md
├── src/
│   └── minibank/
│       ├── MiniBank.java
│       ├── BankService.java
│       ├── Account.java
│       ├── SavingsAccount.java
│       ├── CurrentAccount.java
│       ├── FixedDepositAccount.java
│       ├── Customer.java
│       ├── AccountStore.java
│       ├── StatePersister.java
│       ├── TransactionLog.java
│       ├── ReportGenerator.java
│       ├── TransactionProcessor.java
│       ├── Command.java
│       ├── CommandParser.java
│       ├── Validator.java
│       ├── AnnotationValidator.java
│       ├── Transactable.java
│       ├── InterestBearing.java
│       ├── WithdrawRule.java
│       ├── annotation/
│       └── exception/
└── data/  (created at runtime)
```

## Notes and scope

- This is an educational console simulation, not a production banking system. It does not connect to a real bank, authenticate users, encrypt data, or implement regulatory controls.
- File serialization is used for simple local persistence. Do not store real financial or personal information in this demo.
- Java `long` represents whole rupees; fractional currency is not supported.
- Fixed deposits are locked in this educational model. Interest is an estimate and is not automatically credited.
- Transaction history displayed in the menu is the current session's in-memory history; the append-only transaction log persists on disk.

# 🤖 SauceDemo UI Test Automation

## 📌 Project Overview
This project is a UI test automation framework built for the SauceDemo e-commerce demo application. It automates important user journeys such as login, inventory validation, product sorting, cart management, and checkout confirmation.

The framework is designed using Selenium WebDriver, TestNG, Maven, Page Object Model, WebDriverManager, and ExtentReports. The goal is to provide a clean, maintainable, and professional automation structure that can run locally or inside CI/CD pipelines.

---

## 🎥 Project Demo
A project demo video is not currently included in this repository.

After running the tests, you can review the generated HTML execution report under the `reports` folder.

---

## 🧪 Test Coverage
The framework covers key user journeys across the following SauceDemo modules:

- **User Login:** Valid login flow and inventory page access validation
- **Locked User Validation:** Error message validation for blocked users
- **Inventory Page:** Product listing page loading validation
- **Product Sorting:** Price low-to-high sorting validation
- **Shopping Cart:** Adding and removing products from the cart
- **Cart Badge:** Cart item count validation after add/remove actions
- **Checkout Flow:** End-to-end checkout flow from product selection to order confirmation
- **Reporting:** HTML report generation with screenshots captured on failures

---

## 🎯 Project Goal
This project was created to practice and demonstrate real-world QA automation engineering skills, including:

- Building a reusable Selenium automation framework
- Applying the Page Object Model design pattern
- Managing browser lifecycle safely
- Supporting configurable browser execution
- Supporting headless execution for CI environments
- Organizing tests into smoke and regression groups
- Generating professional HTML test reports
- Capturing screenshots on test failures
- Preparing the framework for Jenkins CI/CD execution
- Keeping test data centralized and maintainable

---

## 🛠️ Technologies Used
- Java 21 LTS
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- ExtentReports
- PageFactory
- Jenkins Pipeline
- JMeter performance test file
- Git / GitHub

---

## ✨ Key Features
This framework supports:

- Automated UI testing for SauceDemo
- Page Object Model implementation
- Reusable base test lifecycle through `BaseTest`
- Common page initialization through `PageBase`
- Centralized WebDriver creation through `DriverFactory`
- Chrome, Edge, and Firefox browser support
- Headless execution support for CI environments
- Configurable browser using Maven system properties
- Configurable application base URL
- Smoke and regression execution using TestNG groups
- TestNG suite configuration
- Parallel suite configuration through `ParallelTesting.xml`
- ExtentReports HTML reporting
- Screenshot capture on test failure
- Jenkins pipeline support
- Centralized test data through `TestData`

---

## 🧠 Development Process
The project was built with a framework-first mindset instead of writing isolated Selenium scripts. The main focus was to create a structure that is easy to maintain, extend, debug, and execute in different environments.

The development process included:

- Analyzing the main business flows of the SauceDemo application
- Identifying core test scenarios such as login, sorting, cart, and checkout
- Designing page object classes to separate locators and actions from test logic
- Creating a base test class to control setup and teardown consistently
- Building a driver factory to centralize browser creation and browser options
- Adding runtime configuration for browser, base URL, and headless mode
- Organizing tests using TestNG smoke and regression groups
- Adding ExtentReports for professional HTML reporting
- Capturing screenshots automatically when tests fail
- Preparing Jenkins execution with headless mode and CI-safe report handling
- Centralizing repeated test data to reduce duplication in test classes

---

## 📈 Personal Growth From This Project
This project helps build practical automation framework design skills, including:

- Structuring Selenium projects professionally
- Applying Page Object Model in a real UI automation project
- Separating test logic from UI interaction logic
- Managing WebDriver lifecycle safely
- Creating reusable framework utilities
- Handling browser configuration cleanly
- Organizing smoke and regression test execution
- Improving CI/CD readiness for automated test execution
- Generating and reviewing automation reports
- Thinking about long-term maintainability and scalability

---

## 🚀 Future Improvements
This framework can be improved further by adding more advanced engineering and DevOps practices, such as:

- Adding a Maven wrapper so the project can run without installing Maven globally
- Adding GitHub Actions pipeline for CI execution
- Running smoke tests automatically on every pull request
- Running regression tests automatically on scheduled builds
- Adding retry logic for flaky test handling
- Adding explicit wait utility methods for dynamic elements
- Adding Allure reports for advanced reporting and visualization
- Adding Docker support for consistent browser execution
- Adding Selenium Grid support for distributed execution
- Adding cross-browser execution matrix for Chrome, Firefox, and Edge
- Adding more negative test scenarios
- Adding external test data files or data providers
- Adding API testing coverage for backend validation
- Adding architecture diagrams and extended technical documentation

---

## 📥 Download and Run the Project

### Prerequisites
Before running the project, make sure the following tools are installed:

- Java 21 LTS
- Maven
- Git
- Google Chrome, Microsoft Edge, or Mozilla Firefox

The default application URL used by the framework is:

```text
https://www.saucedemo.com/
```

You can override the browser, base URL, and headless mode from the Maven command line.

## 📦 Clone the Repository
```bash
git clone <repository-url>
cd Suace_Demo_Automation
```

## ⚙️ Install Dependencies
Maven will automatically download the required dependencies from `pom.xml` when you run the tests.

You can validate the project setup using:

```bash
mvn clean compile
```

### Run Default Suite
The default Maven suite is `testing.xml`.

```bash
mvn clean test
```

### Run Tests in Headless Mode
```bash
mvn clean test -Dbrowser.headless=true -Dci.environment=true
```

### Run with a Specific Browser
```bash
mvn clean test -Dbrowser.name=chrome
mvn clean test -Dbrowser.name=edge
mvn clean test -Dbrowser.name=firefox
```

### Run with a Custom Base URL
```bash
mvn clean test -Dapp.baseUrl=https://www.saucedemo.com/
```

### Run Parallel Suite
```bash
mvn clean test -Dsuite=ParallelTesting.xml -Dbrowser.headless=true -Dci.environment=true
```

### Run a Specific Test Class
```bash
mvn test -Dtest=InventoryTest
```

### Run Smoke Tests
```bash
mvn test -Dgroups=smoke
```

### Run Regression Tests
```bash
mvn test -Dgroups=regression
```

### View Test Reports
After execution, reports are generated under:

```text
reports/
```

The main HTML report uses this naming pattern:

```text
ExtentReport_yyyyMMdd_HHmmss.html
```

Screenshots for failed tests are saved under:

```text
reports/screenshots/
```

---

## 📁 Project Structure
```text
Suace_Demo_Automation
├── src
│   ├── main
│   │   └── java
│   │       └── mostafa
│   │           └── qa
│   │               ├── core
│   │               │   ├── BaseTest.java
│   │               │   ├── DriverFactory.java
│   │               │   └── PageBase.java
│   │               ├── listeners
│   │               │   └── ExtentTestNGListener.java
│   │               ├── pages
│   │               │   ├── CartPage.java
│   │               │   ├── CheckoutPage.java
│   │               │   ├── InventoryPage.java
│   │               │   └── LoginPage.java
│   │               └── utils
│   │                   └── ExtentManager.java
│   │
│   └── test
│       └── java
│           └── tests
│               ├── CartTest.java
│               ├── CheckoutTest.java
│               ├── InventoryTest.java
│               ├── LoginTest.java
│               └── TestData.java
│ 
├── pom.xml
├── testing.xml
└── README.md
```

---

## 🗂️ Folder Responsibilities

| Folder / File | Responsibility |
| :--- | :--- |
| `src/main/java/mostafa/qa/core` | Stores base framework classes for driver lifecycle and page initialization |
| `src/main/java/mostafa/qa/pages` | Stores page objects, locators, and page actions |
| `src/main/java/mostafa/qa/listeners` | Handles TestNG execution events, reporting, and screenshots |
| `src/main/java/mostafa/qa/utils` | Stores reusable utility classes such as report management |
| `src/test/java/tests` | Stores TestNG test classes and centralized test data |
| `performance` | Stores JMeter performance testing files |
| `reports` | Stores generated ExtentReports HTML files and screenshots |
| `testing.xml` | Default TestNG suite configuration |
| `ParallelTesting.xml` | Parallel TestNG suite configuration |
| `oldtesting.xml` | Legacy TestNG suite configuration |
| `Jenkinsfile` | Jenkins CI/CD pipeline configuration |
| `pom.xml` | Maven dependencies, Java version, and Surefire test execution configuration |

---

## 🏗️ Design Patterns & Architecture

### 📄 Page Object Model (POM)
Each web page is represented by a dedicated Java class that encapsulates its locators and actions, such as `LoginPage.java`, `InventoryPage.java`, `CartPage.java`, and `CheckoutPage.java`.

**Benefit:** Centralizes UI interactions, keeps tests clean, and reduces maintenance effort when UI elements change.

### 🧱 Base Test Layer
`BaseTest.java` is the parent class for all test classes. It creates a fresh browser before each test, navigates to the configured application URL, stores browser information for reporting, and closes the browser after each test.

**Benefit:** Keeps setup and teardown consistent across the whole framework.

### 🧱 Base Page Abstraction
`PageBase.java` is the parent class for all page objects. It stores the WebDriver instance and initializes elements using Selenium PageFactory.

**Benefit:** Removes duplicate page initialization code from every page object.

### 🏭 Factory Pattern
`DriverFactory.java` centralizes WebDriver creation for Chrome, Edge, and Firefox. It also applies common browser options such as incognito/private mode, notification disabling, and headless execution.

**Benefit:** Eliminates duplicate driver setup logic and makes browser support easier to extend.

### ⚙️ Configuration Management
The framework reads runtime configuration using Maven system properties:

- `browser.name`
- `app.baseUrl`
- `browser.headless`
- `ci.environment`
- `suite`

**Benefit:** Allows the same framework to run locally, in headless mode, against different environments, and inside CI/CD pipelines without changing source code.

### 📊 Reporting Architecture
`ExtentManager.java` creates a singleton ExtentReports instance, while `ExtentTestNGListener.java` listens to TestNG events and records test results.

**Benefit:** Produces readable HTML reports and captures screenshots automatically on failures.

### 🧪 Test Organization
Tests are grouped with TestNG groups such as `smoke` and `regression`, and suite XML files control how tests are executed.

**Benefit:** Supports flexible execution strategies for quick validation, full regression, and parallel execution.

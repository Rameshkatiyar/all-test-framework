# all-test-framework (Web+App+API)
### This framework is a generic custom test automation framework. It can be used to automate API, Web UI, and mobile app testing tests.

## Features of the framework:
- Support the Web UI automation using Selenium. 
- Provide API automation support using Rest Assured.
- Provide App automation support using Appium.
- We can run the tests on local, Browserstack, and devicefarm.
- No need to maintain any TestNG XML files. It will be created automatically at runtime.
- Implemented the Page object model.
- Very strong filters are provided to run a specific group of tests.
- Filters based on test categories: SMOKE, ACCEPTANCE, and REGRESSION. API, WEB, and APP platforms are also used.
- Highly customizable and configurable for any organization.
- Take screenshots, compare them with expected screenshots, and show the difference in report.
- Implemented proper selenium wait for element loading.
- Generate the Extent report as a testing summary report.
- Support the data-driven testing and load data from CSVs.
- No need to download and manage any external browser's driver.
- Simple commands to run the test. Example: `make test`


## TechStack Used:
**Main:**
- Java
- TestNg
- Maven
- Selenium
- Appium
- Rest Assured

**Support Library:**
- Lombok
- Guava
- Hamcrest
- WebDriverManager
- Univocity Parsers
- Extent Report

## Types of testing supported
- Acceptance testing
- Regression end-to-end testing
- Integration testing
- System testing
- Smoke testing

## Types of framework used:
- The Hybrid Test Automation Framework
- The Test Library Architecture Framework
- The Data-Driven Testing Framework
- Test Modularity Framework
- Business Process Testing (BPT)

Link: https://www.guru99.com/test-automation-framework.html

## How to set up framework?
- TDOD

## How to run the test with filters?
Go inside the `all-test-framework` directory and run the following command.
- To run all tests: `make test`
- To run only API tests: `make test platform=api`
- To run only Web tests: `make test platform=web`
- To run only App tests: `make test platform=app`
- To run only smoke tests: `make test group=smoke`
- To run only acceptance tests: `make test group=acceptance`
- To run only APIs smoke tests: `make test platform=api group=acceptance`
- To run tests on browserstack: `make test env=browserstack`
- To run a tests of a specific class: `make test class=LoginPageTest`

## How to automate Web UI tests?
- TDOD

## How to automate API tests?
- TDOD

## How to use data-driven tests?
- TDOD

## How to automate the Mobile App tests?
- TDOD
# Demo test automation framework

Created for example, and also for fun

Used stack:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Cucumber](https://cucumber.io/)
- [Selenium](https://www.selenium.dev/) / [Selenide](https://selenide.org/)
- [Allure](https://qameta.io/allure-report/)
- [Rest Assured](https://rest-assured.io/)

Start:

1. Install browser and copy webdriver to *src/test/resources/drivers*
2. Install Allure reports if needed
3. Execute:

```
mvn clean test -D"cucumber.filter.tags=not @skip"; allure serve .\target\allure-results\ --host localhost 
```

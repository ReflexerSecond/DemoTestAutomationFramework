# Demo test automation framework

Created for example, and also for fun

Used stack:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Cucumber](https://cucumber.io/)
- [Selenium](https://www.selenium.dev/)
- [Allure](https://qameta.io/allure-report/)
- [Rest Assured](https://rest-assured.io/)

Start:

1. Install browser and copy webdriver to *src/test/resources/drivers*
2. Install Allure reports if needed
3. Execute:

```
mvn clean test; allure serve .\target\allure-results\ --host localhost
```

### TODO:

1. move versions to properties
2. add Classes for webDriver creation or use Manager
3. [&check;] add API calls support
4. Add suites
5. add PO classes and selenium steps
6. add Api steps
7. add db connection
8. add web,api,db test in different steps
9. add appium support
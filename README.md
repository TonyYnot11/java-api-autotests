# Java API AutoTests

[![Run API Tests](https://github.com/TonyYnot11/java-api-autotests/actions/workflows/ci.yml/badge.svg)](https://github.com/TonyYnot11/java-api-autotests/actions/workflows/ci.yml)

***

## О проекте
Учимся писать автотесты для REST API (JSONPlaceholder) на Java с использованием RestAssured, JUnit 4 и GitHub Actions.

***

## Что умеют тесты
- API тесты ресурса [jsonplaceholder](https://jsonplaceholder.typicode.com)
   * GET, POST, PUT, DELETE запросы
   * Параметризованные тесты (проверка разных пользователей)
   * Проверка схемы JSON
   * Негативные сценарии (404)

***

- UI тесты для [saucedemo](https://www.saucedemo.com/)
    * Проверка авторизации пользователя
    * Проверка авторизации заблокированного пользователя
    * Проверка добавления товара в корзину

***

## Технологии
- Java 17
- RestAssured 5.5.0
- JUnit 4
- Maven
- GitHub Actions (CI/CD)

***

## Allure отчеты доступны по ссылке
 ###  [Отчет последнего прогона](https://tonyynot11.github.io/java-api-autotests/)

***

## Запуск тестов локально
```bash
mvn clean test

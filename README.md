# Java API AutoTests

[![Run API Tests](https://github.com/TonyYnot11/java-api-autotests/actions/workflows/ci.yml/badge.svg)](https://github.com/TonyYnot11/java-api-autotests/actions/workflows/ci.yml)

## О проекте
Автотесты для REST API (JSONPlaceholder) на Java с использованием RestAssured, JUnit 4 и GitHub Actions.

## Что умеют тесты
- GET, POST, PUT, DELETE запросы
- Параметризованные тесты (проверка разных пользователей)
- Проверка схемы JSON
- Негативные сценарии (404)

## Технологии
- Java 17
- RestAssured 5.5.0
- JUnit 4
- Maven
- GitHub Actions (CI/CD)

## Запуск тестов локально
```bash
mvn clean test
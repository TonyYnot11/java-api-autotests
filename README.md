# Java API + UI AutoTests

[![CI](https://github.com/TonyYnot11/java-api-autotests/actions/workflows/ci.yml/badge.svg)](https://github.com/TonyYnot11/java-api-autotests/actions/workflows/ci.yml)
[![Allure Report](https://img.shields.io/badge/Allure%20Report-latest-brightgreen)](https://tonyynot11.github.io/java-api-autotests/)

## О проекте

Учебный проект с автотестами для:
- **REST API** ([JSONPlaceholder](https://jsonplaceholder.typicode.com))
- **Web UI** ([SauceDemo](https://www.saucedemo.com/))

## Что проверяется

### API
- GET, POST, PUT, DELETE запросы
- Параметризованные тесты
- Проверка JSON Schema
- Негативные сценарии (404)

### UI
- Авторизация (успешная и заблокированный пользователь)
- Добавление товаров в корзину
- Оформление заказа

## Технологии

- Java 17, Maven, JUnit 4/5
- RestAssured, Selenium WebDriver
- Allure Report, GitHub Actions

## Allure отчеты доступны по ссылке или через бейдж в шапке
###  [Отчет последнего прогона](https://tonyynot11.github.io/java-api-autotests/)

## Локальный запуск

```bash
mvn clean test     # запуск тестов
----------------------------------
mvn allure:serve   # открыть отчёт
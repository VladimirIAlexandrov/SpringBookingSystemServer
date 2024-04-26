# Event Booking Server

Данный проект представляет собой серверное приложение для управления мероприятиями и клиентами. Он предоставляет API для создания, обновления, удаления и поиска мероприятий, а также управления клиентской базой данных.

## Функциональности

- Создание, обновление, удаление и поиск мероприятий.
- Поиск мероприятий по имени.
- Работа с базой данных клиентов: поиск клиентов по email и имени пользователя.
- Интеграция с базой данных PostgreSQL.

## Технологии

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Установка и запуск

1. Установить PostgreSQL и создайть базу данных для приложения.
2. Изменить конфигурацию базы данных в файле `application.properties` в соответствии с данными:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. Клонировать репозиторий:

    ```
    git clone https://github.com/VladimirIAlexandrov/SpringBookingSystemServer.git
    ```

4. Перейти в каталог проекта:

    ```
    cd event-booking-server
    ```

5. Соберать проект с помощью Maven:

    ```
    mvn clean install
    ```

6. Запустить сервер:

    ```
    mvn spring-boot:run
    ```




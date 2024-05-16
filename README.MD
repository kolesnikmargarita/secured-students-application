# Secured students application

Приложение хранит данные о пользователях и книгах в защищенном виде. Для выполнения каких либо действий приложение приложение запрашивает данные пользователя для выполнения аутентификации и авторизации. В зависимости от роли авторизованного пользователя он может выполнять следующие действия:

НЕ АВТОРИЗОВАННЫЙ ПОЛЬЗОВАТЕЛЬ

    1. регистрация
USER

    1. просмотр списка всех книг
    2. просмотр списка книг своего wishlist
    3. добавление книги в свой wishlist
    4. удаление книги из своего wishlist
ADMIN

    1. все действия, доступные USER
    2. добавление книг в общий список
    3. удаление книги из общего списка

## Список эндпоинтов

http:/localhost:8080/auth/login - аутентификация, авторизация
http:/localhost:8080/registration - регистрация
http:/localhost:8080/books - список всех книг из блока (не из таблицы)
http:/localhost:8080/wishlist - список книг из wishlist авторизованного пользователя

## Необходимая конфигурация

application.yml -> исправить username и password
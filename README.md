# Social Network Application

## Технологии
- Spring Boot 3+
- Spring Security для аутентификации
- PostgreSQL для хранения данных
- Docker для контейнеризации приложения

## Запуск приложения

Для запуска приложения и его зависимостей (например, базы данных) используйте Docker Compose.

### Предварительные требования

Убедитесь, что у вас установлены:
- Docker
- Docker Compose

### Инструкции по запуску

1. Создайте файл `docker-compose.yml` на вашем компьютере и вставьте в него следующее содержание:

```yaml
version: '3.8'
services:
  app:
    image: ibodmer/socialnetwork
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/socialnetwork
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres
    depends_on:
      - db
  db:
    image: postgres:latest
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: socialnetwork
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    volumes:
      - db_data:/var/lib/postgresql/data
volumes:
  db_data:
```
2. Теперь для того, чтобы запустить приложение и базу данных. Откройте терминал в папке, в которой лежит файл **docker-compose.yml** и запустите команду `docker compose up` 
3. В Postman Коллекции открываем реквест `login` Для того, чтобы залогиниться в приложении
4. Пользуемся доступными методами в постман коллекциях

**Postman Коллекции** находятся в `~/resources/collections`
# --- Stage 1: Сборка ---
    FROM maven:3.8.8-eclipse-temurin-17 AS build

    # Создаем рабочую директорию
    WORKDIR /app
    
    # Копируем описание проекта
    COPY pom.xml .
    
    # Загружаем зависимости (чтобы закэшировать этот слой)
    RUN mvn dependency:go-offline
    
    # Копируем исходный код
    COPY src ./src
    
    # Собираем проект и создаем fat-jar
    RUN mvn clean package
    
    # --- Stage 2: Запуск ---
    FROM eclipse-temurin:17-jre-alpine
    
    WORKDIR /app
    
    # Копируем собранный JAR файл из предыдущего этапа
    COPY --from=build /app/target/telegram-bot-docker-1.0-SNAPSHOT.jar app.jar
    
    # Команда запуска
    CMD ["java", "-jar", "app.jar"]
    
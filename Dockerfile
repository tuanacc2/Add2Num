FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Bước 2: Chạy file JAR với môi trường JRE tinh gọn
FROM eclipse-temurin:21-jre
WORKDIR /app
# Thay đổi tên file mvc-0.0.1-SNAPSHOT.jar thành tên file jar thực tế trong target của bạn
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
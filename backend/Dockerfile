# Stage 1: Build ứng dụng với Maven
FROM maven:3.8.7-openjdk-17 AS builder

# Set thư mục làm việc trong container
WORKDIR /app

# Copy toàn bộ source code vào container
COPY backend/ /app/

# Cấp quyền thực thi cho mvnw
RUN chmod +x mvnw

# Build ứng dụng mà không chạy test
RUN ./mvnw clean package -DskipTests

# Stage 2: Deploy lên Tomcat
FROM tomcat:11.0

# Copy file WAR đã build vào thư mục webapps của Tomcat
COPY --from=builder /app/target/backend-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Mở cổng 8080
EXPOSE 8080

# Chạy Tomcat khi container khởi động
CMD ["catalina.sh", "run"]

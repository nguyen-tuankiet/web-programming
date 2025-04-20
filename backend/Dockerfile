# ======== Stage 1: Build (Compile Java + build WAR) ========
FROM maven:3.9.9-amazoncorretto-21-al2023 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# ======== Stage 2: Runtime (Run with Tomcat) ========
FROM tomcat:10.1-jdk21

# Xóa ứng dụng mặc định của Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file WAR đã build từ stage 1
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]

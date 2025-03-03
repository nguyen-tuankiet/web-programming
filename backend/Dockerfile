# Sử dụng Tomcat 9 làm base image
FROM tomcat:11.0

# Copy file WAR vào thư mục webapps của Tomcat
COPY target/backend-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Mở cổng 8080
EXPOSE 8080

# Chạy Tomcat khi container khởi động
CMD ["catalina.sh", "run"]

RUN chmod +x mvnw
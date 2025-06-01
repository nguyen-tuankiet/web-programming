FROM tomcat:jdk21-temurin-noble


# Xoá ứng dụng mặc định
RUN rm -rf /usr/local/tomcat/webapps/*


# Copy war vào webapps
COPY /target/backend-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]
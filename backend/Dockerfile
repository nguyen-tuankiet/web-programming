FROM tomcat:jdk21-temurin-noble

# Copy war vào webapps
COPY /target/backend-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080


#Bước 1: Copy source code từ máy thật sang máy ảo CentOS
#1. Máy ảo CentOS phải caài sẵn:
#
#sudo yum install -y openssh-server
#sudo systemctl enable sshd --now


#2. Lấy IP máy ảo:nasna
#ip a   # Tìm dòng có inet, ví dụ: 192.168.153.128


#3. Trên máy thật - nhớ build cho chạy dưới local trươc
#scp -r "D:\NLU\deploy\web-programming" qhung@192.168.153.128:/home/qhung/
#    qhung là user trên CentOS
#    /home/qhung/ là thư mục đích
#    Nếu chưa set password cho user: sudo passwd qhung





# Bước 2: Cài Docker trên máy ảo CentOS


#sudo yum install -y yum-utils
#sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
#sudo yum install -y docker-ce docker-ce-cli containerd.io
#sudo systemctl enable docker --now



#Bước 3: Tạo Dockerfile cho project JSP/Servlet
#FROM tomcat:9.0-jdk11
#
#COPY target/yourproject.war /usr/local/tomcat/webapps/
#
#EXPOSE 8080



# Bước 4: Build và Run Docker container

#cd /home/qhung/web-programming/backend

## Nếu chưa có .war file, build bằng Maven


## Build Docker image
#docker build -t jsp-app .

## Chạy container
#docker run -d -p 8080:8080 jsp-app

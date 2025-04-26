FROM tomcat:jdk21-temurin-noble

# Copy war vào webapps
COPY /target/backend-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080

#Bước 1: Cài đặt máy ảo

#1.1. Máy ảo CentOS phải caài sẵn:
#sudo dnf install -y openssh-server
#sudo systemctl enable sshd --now
#sudo vi /etc/ssh/sshd_config
# Bấm i để bật chế dộ insert
# Tìm đoạn PermitRootLogin rồi đổi thành => "PermitRootLogin yes"
# Bấm esc để thoát insert, sau đó bấm :wq để lưu


#1.2. Lấy IP máy ảo:
#ip a
# Tìm dòng có inet, ví dụ: 192.168.153.128



#1.3: Cài Docker trên máy ảo CentOS
#sudo dnf -y install dnf-plugins-core
#sudo dnf config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
#sudo dnf install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
#sudo systemctl enable --now docker



#Bước 2: Copy source code từ máy thật sang máy ảo CentOS

#Trên máy thật - nhớ build cho chạy dưới local trưoc
#scp -r "D:\NLU\deploy\web-programming" qhung@192.168.153.128:/home/qhung/
#    qhung là user trên CentOS, nhớ tạo folder này trong home truocws để tránh lỗi
#    /home/qhung/ là thư mục đích
#    Nếu chưa set password cho user: sudo passwd qhung
# Lúc này nó sẽ đẩy thieeus thư mục target lên máy ảo ( kiểm tra bằng cách cd vào thư trong máy ảo xem có chưa)
# Sau đó tiếp tục đẩy folder target lên máy ảo
# scp -r "D:\NLU\deploy\web-programming\backend\target" qhung@192.168.153.128:/home/qhung/web-programming/backend
# Nhớ đẩy đúng câấu truc, đẩy sai thi sẽ ko build được




# Bước 3: Build và Run Docker container
# BẮC BUỘC PHẢI ĐANG Ở TRONG THƯ MUC backend
# BẮC BUỘC PHẢI ĐANG Ở TRONG THƯ MUC backend
# BẮC BUỘC PHẢI ĐANG Ở TRONG THƯ MUC backend

#cd /home/qhung/web-programming/backend
# Nếu chưa có .war file, phải về máy thật đẩy lên lại


## Build Docker image
#docker build -t appname .
# PHẢI CÓ DẤU CHẤM Ở CUỐI CÙNG
# PHẢI CÓ DẤU CHẤM Ở CUỐI CÙNG
# PHẢI CÓ DẤU CHẤM Ở CUỐI CÙNG

## Chạy container
#docker run -d -p 8080:8080 appname

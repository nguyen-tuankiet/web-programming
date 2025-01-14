package com.example.backend.service;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {

    // Hàm gửi email chứa mã OTP
    public void sendEmailWithOTP(String toEmail, String otp) {
        // Cấu hình thông tin kết nối với SMTP server
        String host = "smtp.gmail.com";
        String fromEmail = "22130136@st.hcmuaf.edu.vn";
        String password = "paej hzcy qwml bwoc";

        // Cấu hình các thuộc tính SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Tạo một session email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));  // Địa chỉ người gửi
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));  // Địa chỉ người nhận
            message.setSubject("Mã OTP xác thực");  // Tiêu đề email

            // Nội dung email
            String emailContent = "<h3>Ma OTP cua ban la: " + otp + "</h3>"
                    + "<p>Vui lòng không chia sẻ mã OTP này với bất kỳ ai.</p>";
            message.setContent(emailContent, "text/html");

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công đến " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Có lỗi xảy ra khi gửi email");
        }
    }

    public String generateOTP() {
        int otp = (int) (Math.random() * 90000) + 10000;  // Tạo OTP 5 chữ số
        return String.valueOf(otp);
    }
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        emailService.sendEmailWithOTP("22130136@st.hcmuaf.edu.vn", "12345");
    }

}

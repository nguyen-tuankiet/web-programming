package com.example.backend.service;
import org.jdbi.v3.core.Jdbi;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class EmailService {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String USERNAME = "22130136@st.hcmuaf.edu.vn"; // Thay bằng email của bạn
    private static final String PASSWORD = "paej hzcy qwml bwoc"; // Thay bằng mật khẩu ứng dụng của bạn
    private static final Properties properties = new Properties();
    private static Session session;

    public EmailService() {
        try {
            // Load email configuration
            InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
            if (input != null) {
                properties.load(input);
            }

            // Configure JavaMail
            Properties mailProps = new Properties();
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.smtp.starttls.enable", "true");
            mailProps.put("mail.smtp.host", properties.getProperty("mail.smtp.host", "smtp.gmail.com"));
            mailProps.put("mail.smtp.port", properties.getProperty("mail.smtp.port", "587"));

            final String username = properties.getProperty("mail.username");
            final String password = properties.getProperty("mail.password");

            session = Session.getInstance(mailProps, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hàm gửi email chứa mã OTP
    public void sendEmailWithOTP(String toEmail, String otp) {
        // Cấu hình thông tin kết nối với SMTP server


        // Cấu hình các thuộc tính SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        // Tạo một session email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));  // Địa chỉ người gửi
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));  // Địa chỉ người nhận
            message.setSubject("Mã OTP xác thực");  // Tiêu đề email

            // Nội dung email
            String emailContent = "<h3>Kiet da gui ma OTP cho ban. Ma OTP cua ban la: " + otp + "</h3>"
                    + "<p>Vui lòng không chia sẻ mã OTP này voi bat ki ai.</p>";
            message.setContent(emailContent, "text/html");

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công đến " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Có lỗi xảy ra khi gửi email");
        }
    }

    public void sendConfirmationEmail(String toEmail, String sessionId) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Xác nhận đăng ký tài khoản");

            String confirmLink = "http://localhost:8080/backend_war/confirm?sessionId=" + sessionId;
            String emailContent = "<h3>Xin Chào!,</h3>"
                    + "<p>Vui lòng nhap vào liên ket duoi dây de xac nhan tai khoan cua ban:</p>"
                    + "<a href=\"" + confirmLink + "\">Xác nhận</a>";
            message.setContent(emailContent, "text/html");

            Transport.send(message);
            System.out.println("Email xác nhận đã được gửi đến " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Có lỗi xảy ra khi gửi email xác nhận");
        }
    }

    public static void sendRegistrationEmail(String toEmail, String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Xác nhận đăng ký tài khoản");

            String content = "Xin chào,\n\n" +
                    "Cảm ơn bạn đã đăng ký tài khoản với chúng tôi.\n\n" +
                    "Thông tin đăng nhập của bạn:\n" +
                    "Email: " + toEmail + "\n" +
                    "Mật khẩu: " + password + "\n\n" +
                    "Vui lòng đăng nhập và đổi mật khẩu ngay sau khi đăng nhập lần đầu.\n\n" +
                    "Trân trọng,\n" +
                    "Đội ngũ hỗ trợ";

            message.setText(content);

            Transport.send(message);
            System.out.println("Email xác nhận đã được gửi đến " + toEmail);
        } catch (MessagingException e) {
            System.err.println("Lỗi khi gửi email: " + e.getMessage());
            throw new RuntimeException("Không thể gửi email xác nhận", e);
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

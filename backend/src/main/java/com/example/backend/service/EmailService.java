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
    private static final String USERNAME = "22130136@st.hcmuaf.edu.vn";
    private static final String PASSWORD = "paej hzcy qwml bwoc";
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

            session = Session.getInstance(mailProps, new jakarta.mail.Authenticator() {
                @Override
                protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new jakarta.mail.PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hàm gửi email chứa mã OTP
    public void sendEmailWithOTP(String toEmail, String otp) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Mã OTP xác thực");

            String emailContent = "<h3>Modern Home da gui ma OTP cho ban. Ma OTP cua ban la: " + otp + "</h3>"
                    + "<p>Vui lòng không chia sẻ mã OTP này voi bat ki ai.</p>";
            message.setContent(emailContent, "text/html");

            Transport.send(message);
            System.out.println("Email đã được gửi thành công đến " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Có lỗi xảy ra khi gửi email");
        }
    }

    public static void sendRegistrationEmail(String toEmail, String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(USERNAME, PASSWORD);
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

    public void sendEmail(String to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Không thể gửi email: " + e.getMessage());
        }
    }


    public  void sendInviteEmail(String toEmail,String name, String roleName, String url) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Thư mời tham gia cộng tác từ đội ngũ cửa hàng");


            String content = "Xin chào " +name + ",\n\n" +
                    "Bạn vừa được mời gia nhập đội ngũ quản lý trên nền tảng của chúng tôi.\n\n" +

                    "Thông tin lời mời:\n" +
                    "- Email: " + toEmail + "\n" +
                    "- Vị trí: " + roleName + "\n\n" +
                    "Vui lòng nhấn vào liên kết dưới đây để chấp nhận lời mời và bắt đầu quản lý shop:\n" +
                    url + "\n\n" +
                    "Lưu ý: Lời mời sẽ hết hạn sau 24 giờ.\n\n" +
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

    public void sendAccountCreationEmailPlain(String toEmail, String userName, String roleName, String temporaryPassword, String loginUrl) {
        String subject = "Tài khoản của bạn đã được tạo - Mời tham gia hệ thống";

        String emailContent = String.format("""
                Xin chào %s,
                
                Tài khoản của bạn đã được tạo thành công trong hệ thống với vai trò: %s
                
                THÔNG TIN ĐĂNG NHẬP:
                - Email: %s
                - Mật khẩu tạm thời: %s
                
                CÁC BƯỚC TIẾP THEO:
                1. Đăng nhập vào hệ thống tại: %s
                2. Xác nhận tài khoản của bạn qua email xác nhận (nếu có)
                3. Đổi mật khẩu sau khi đăng nhập lần đầu
                
                ⚠️ LÚU Ý QUAN TRỌNG:
                - Mật khẩu này chỉ là tạm thời
                - Vui lòng đổi mật khẩu ngay sau khi đăng nhập để bảo mật tài khoản
                - Không chia sẻ thông tin đăng nhập với người khác
                
                Nếu bạn cần hỗ trợ, vui lòng liên hệ với đội ngũ quản trị.
                
                Trân trọng,
                Đội ngũ hỗ trợ
                """, userName, roleName, toEmail, temporaryPassword, loginUrl);

        sendEmail(toEmail, subject, emailContent);
    }

        public static void main(String[] args) {
        EmailService emailService = new EmailService();
//        emailService.sendEmailWithOTP("22130136@st.hcmuaf.edu.vn", "12345");
         emailService.sendInviteEmail("tranquochung0404@gmail.com", "Quốc Hưng","Admin", "https://www.google.com");
    }
}

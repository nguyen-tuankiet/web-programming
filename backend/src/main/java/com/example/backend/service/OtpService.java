package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.DAO.OtpDao;
import com.example.backend.model.UserOTP;
import org.jdbi.v3.core.Jdbi;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OtpService {
    private final OtpDao otpDao;
    private final EmailService emailService;

    public OtpService(Jdbi jdbi) {
        this.otpDao = jdbi.onDemand(OtpDao.class);
        this.emailService = new EmailService();
    }

    /**
     * Tạo và gửi OTP cho email
     * @param email Email người dùng
     * @return true nếu gửi thành công, false nếu có lỗi
     */
    public boolean generateAndSendOTP(String email) {
        try {
            String otp = emailService.generateOTP();
            // Set thời gian hết hạn là 5 phút sau thời điểm hiện tại
            LocalDateTime now = LocalDateTime.now();
            Timestamp expiresAt = Timestamp.valueOf(now.plusMinutes(5));
            
            // Lưu OTP vào database
            otpDao.saveOrUpdateOTP(email, otp, expiresAt);
            
            // Gửi OTP qua email
            emailService.sendEmailWithOTP(email, otp);
            
            System.out.println("Generated OTP for " + email + ": " + otp + ", expires at: " + expiresAt);
            return true;
        } catch (Exception e) {
            System.err.println("Error generating OTP for " + email + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Xác thực OTP
     * @param email Email người dùng
     * @param enteredOtp OTP người dùng nhập vào
     * @return true nếu OTP hợp lệ, false nếu không hợp lệ
     */
    public boolean verifyOTP(String email, String enteredOtp) {
        try {
            System.out.println("Verifying OTP for " + email + ". Entered OTP: " + enteredOtp);
            
            UserOTP userOTP = otpDao.getValidOTPByEmail(email);
            
            if (userOTP == null) {
                System.out.println("No valid OTP found for " + email);
                return false;
            }

            // Kiểm tra xem OTP có hết hạn chưa
            LocalDateTime now = LocalDateTime.now();
            if (userOTP.getExpiresAt().before(Timestamp.valueOf(now))) {
                System.out.println("OTP has expired for " + email);
                return false;
            }

            System.out.println("Stored OTP: " + userOTP.getOtpCode());
            System.out.println("Expires at: " + userOTP.getExpiresAt());
            
            boolean isValid = enteredOtp.equals(userOTP.getOtpCode());
            
            if (isValid) {
                System.out.println("OTP verified successfully for " + email);
                otpDao.deleteOTP(email);
            } else {
                System.out.println("OTP verification failed for " + email);
            }
            
            return isValid;
        } catch (Exception e) {
            System.err.println("Error verifying OTP for " + email + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Xóa OTP của email
     * @param email Email cần xóa OTP
     */
    public void deleteOTP(String email) {
        try {
            otpDao.deleteOTP(email);
            System.out.println("Deleted OTP for " + email);
        } catch (Exception e) {
            System.err.println("Error deleting OTP for " + email + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
} 
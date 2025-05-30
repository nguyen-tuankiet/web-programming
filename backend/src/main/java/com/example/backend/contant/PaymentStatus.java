package com.example.backend.contant;

public enum PaymentStatus {
    PENDING,       // Chưa thanh toán
    PAID,          // Đã thanh toán
    FAILED,        // Thanh toán thất bại
    REFUNDED,      // Đã hoàn tiền
    CANCELLED      // Đã hủy thanh toán
}

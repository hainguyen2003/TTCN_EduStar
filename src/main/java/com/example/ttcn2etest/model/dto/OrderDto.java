package com.example.ttcn2etest.model.dto;

import com.example.ttcn2etest.model.etity.Order;
import com.example.ttcn2etest.model.etity.Service;
import com.example.ttcn2etest.model.etity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.example.ttcn2etest.model.etity.Order}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    Long id;
    String orderId;
    Long userId;
    Long serviceManagerId;
    String amount;
    String paymentMethod;
    String status;
    Instant paymentDate;
    Instant updatedAt;
    String  image;
    String email;
    String fullName;
    String phone;
    String address;
}
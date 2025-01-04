package com.example.ttcn2etest.model.dto;

import com.example.ttcn2etest.model.etity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.ttcn2etest.model.etity.ClassUser}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassUserDto implements Serializable {
    Long id;
    String classId;
    Long idUser;
}
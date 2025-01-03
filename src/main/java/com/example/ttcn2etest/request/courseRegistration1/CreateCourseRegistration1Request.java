package com.example.ttcn2etest.request.courseRegistration1;



import com.example.ttcn2etest.model.etity.CourseRegistration1;
import com.example.ttcn2etest.validator.EmailAnnotation;
import com.example.ttcn2etest.validator.NameAnnotation;
import com.example.ttcn2etest.validator.PhoneNumber;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.example.ttcn2etest.model.etity.CourseRegistration1.Status.WAITING_FOR_APPROVEDED;


@Data
public class CreateCourseRegistration1Request {
    @NotBlank(message = "Họ và tên không được để trống!")
    @Size(min = 6, max = 100, message = "Họ và tên phải có ít nhất 6, nhiều nhất 100 kí tự!")
    @NameAnnotation
    private String name;

    //    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không hợp lệ!")
    @NotBlank(message = "Email không được để trống!")
    @EmailAnnotation
    private String email;
    @NotBlank(message = "Số điện thoại không được để trống!")
    @PhoneNumber
    private String phone;
    @NotBlank(message = " không để trống địa chỉ")
    private String address;

    @NotBlank(message = "Nội dung dang ki không được để trống!")
    private String information;

    @NotNull(message = "Trạng thái không được để trống!")
    @Enumerated(EnumType.STRING)
    private CourseRegistration1.Status status = WAITING_FOR_APPROVEDED;
}

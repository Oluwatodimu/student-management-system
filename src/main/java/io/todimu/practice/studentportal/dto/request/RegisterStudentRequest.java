package io.todimu.practice.studentportal.dto.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;

@Data
public class RegisterStudentRequest {

    @NonNull private String firstName;

    @NonNull private String lastName;

    @NonNull private String phoneNumber;

    @NonNull private String email;

    @Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$")
    @NonNull private String password;
}
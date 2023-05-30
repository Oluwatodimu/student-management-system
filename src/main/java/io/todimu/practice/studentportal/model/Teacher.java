package io.todimu.practice.studentportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.todimu.practice.studentportal.enumeration.TeacherStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
@EqualsAndHashCode(callSuper = true)
public class Teacher extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_status")
    private TeacherStatus teacherStatus;


    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "teacher", allowSetters = true)
    private Set<CourseTeacher> courseTeachers;
}

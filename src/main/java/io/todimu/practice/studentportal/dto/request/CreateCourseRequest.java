package io.todimu.practice.studentportal.dto.request;

import io.todimu.practice.studentportal.dto.CourseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class CreateCourseRequest {

    @NotNull
    @NotEmpty
    private Set<CourseDto> courses;
}

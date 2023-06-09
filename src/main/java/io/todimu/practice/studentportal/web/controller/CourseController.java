package io.todimu.practice.studentportal.web.controller;

import io.todimu.practice.studentportal.annotation.RateLimited;
import io.todimu.practice.studentportal.dto.CourseDto;
import io.todimu.practice.studentportal.dto.request.CreateCourseRequest;
import io.todimu.practice.studentportal.service.CourseService;
import io.todimu.practice.studentportal.utils.MethodAuthorityConstants;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import io.todimu.practice.studentportal.web.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/course")
public class CourseController extends BaseController {

    private final CourseService courseService;

    @RateLimited
    @PostMapping
    @PreAuthorize(MethodAuthorityConstants.ADMIN_ROLE)
    public ResponseEntity<?> createCourses(@RequestBody @Valid CreateCourseRequest createCourseRequest) {
        log.info("creating courses: {}", createCourseRequest.getCourses());
        List<CourseDto> response = courseService.createCourse(createCourseRequest);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.CREATED);
    }

    @RateLimited
    @GetMapping(value = "/retrieve")
    public ResponseEntity<BaseResponse> getAllCourses(@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        log.info("getting all courses");
        Pageable pageable = createPageableObject(pageNumber, pageSize);
        Page<CourseDto> response = courseService.findAllCourses(pageable);
        return new ResponseEntity<>(new BaseResponse(response, ResponseConstants.SUCCESS, false), HttpStatus.OK);
    }
}

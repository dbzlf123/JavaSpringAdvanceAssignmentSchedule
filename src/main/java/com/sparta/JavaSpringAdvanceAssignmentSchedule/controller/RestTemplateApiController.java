package com.sparta.JavaSpringAdvanceAssignmentSchedule.controller;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.resttemplate.RestTemplateItemRequestDto;
import com.sparta.JavaSpringAdvanceAssignmentSchedule.service.RestTemplateApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestTemplateApiController {

    private final RestTemplateApiService restTemplateApiService;

    public RestTemplateApiController(RestTemplateApiService restTemplateApiService) {
        this.restTemplateApiService = restTemplateApiService;
    }

}

package com.sparta.JavaSpringAdvanceAssignmentSchedule.service;

import com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.resttemplate.RestTemplateItemResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Slf4j(topic = "RestTemplate API")
@Service
public class RestTemplateApiService {

    private final RestTemplate restTemplate;
    private static final String WEATHER_API_URL = "https://f-api.github.io/f-api/weather.json";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");

    public RestTemplateApiService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String getWeather() {
        try {
            // 외부 API 호출
            ResponseEntity<RestTemplateItemResponseDto[]> response = restTemplate.getForEntity(WEATHER_API_URL, RestTemplateItemResponseDto[].class);
            RestTemplateItemResponseDto[] restTemplateItemResponseDtoList = response.getBody();
            if(restTemplateItemResponseDtoList == null) return "Shit";

            String todayDate = LocalDate.now().format(DATE_FORMATTER);
            Optional<RestTemplateItemResponseDto> todayWeather = Arrays.stream(restTemplateItemResponseDtoList)
                    .filter(w -> w.getDate().equals(todayDate))
                    .findFirst();

            return todayWeather.map(RestTemplateItemResponseDto::getWeather).orElse("Shit");
        } catch (Exception e) {
            // 예외 처리 및 로그 기록
            log.error("날씨 정보를 가져오는 중 오류 발생: {}", e.getMessage());
            return "Failed to retrieve weather information";
        }
    }

}

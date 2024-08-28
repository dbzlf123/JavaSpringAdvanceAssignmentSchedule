package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.resttemplate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class RestTemplateItemRequestDto {
    private String date;
    private String weather;

    public RestTemplateItemRequestDto(JSONObject itemJson) {
        this.date = itemJson.getString("date");
        this.weather = itemJson.getString("weather");
    }
}

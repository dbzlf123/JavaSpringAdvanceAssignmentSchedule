package com.sparta.JavaSpringAdvanceAssignmentSchedule.dto.resttemplate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class RestTemplateItemResponseDto {

    private String date;
    private String weather;

}

package com.activitytracker.demo.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString

public class ActivityResponse {


    @JsonProperty("activity_statistics_for_month")
    private List<MonthlyActivity> monthlyActivity;

    @JsonProperty("activity_statistics_yesterday_vs_today")
    private List<YerterdayVsTodayActivity> yerterdayVsTodayActivity;
}

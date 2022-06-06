package com.activitytracker.demo. pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class YerterdayVsTodayActivity {

  @JsonProperty("activity_name")
  private String name;
  @JsonProperty("yesterday_occurrences")
  private Long yesterdayOccurrence;
  @JsonProperty("today_occurrences")
  private Long todayOccurrence;
  @JsonProperty("status")
  private String status;

}

package com.activitytracker.demo. pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class MonthlyActivity {
  public MonthlyActivity(String activityName, Long activityOccurrence) {
    this.activityName = activityName;
    this.activityOccurrence = activityOccurrence;
  }

  @JsonProperty("activity_name")
  private String activityName;

  @JsonProperty("occurrences")
  private Long activityOccurrence;

}

package com.activitytracker.demo.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Table(name = "activity_tracker")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Activities {
    @JsonProperty("name")
    private String name;

    @JsonProperty("time")
    private Long time;

    @JsonProperty("duration")
    private int duration;


}

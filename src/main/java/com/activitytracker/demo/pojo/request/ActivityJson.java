package com.activitytracker.demo.pojo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityJson  implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("unique_id")
    /*name explicitly kept just like mentioned in JSON, else GSON was unable to read the values*/
    private Long unique_id;
    @JsonProperty("activities")
    private List<Activities> activities;


}

package com.activitytracker.demo. service;

import com.activitytracker.demo.pojo.response.ActivityResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public interface ActivityService {
    ActivityResponse getTrakerDetails() throws IOException;
}

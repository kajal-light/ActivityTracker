package com.activitytracker.demo.controller;


import com.activitytracker.demo.exception.BusinessEception;
import com.activitytracker.demo.exception.ControllerException;
import com.activitytracker.demo.pojo.response.ActivityResponse;
import com.activitytracker.demo.service.ActivityService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class ActivityController {

  @Autowired
  ActivityService activityService;


  @GetMapping("/kk")
  public ResponseEntity<?>  getDetails() throws IOException {
try{
  ActivityResponse activityResponse= new ActivityResponse();
  activityResponse= activityService.getTrakerDetails();
return new ResponseEntity<ActivityResponse>(activityResponse, HttpStatus.CREATED);
}


catch (BusinessEception e){

  ControllerException ex=new ControllerException(e.getErrorMeaasage(),e.getErrorCode());
  return new ResponseEntity<ControllerException>(ex,HttpStatus.NOT_FOUND);
}catch (Exception e){

      ControllerException ex=new ControllerException(" Something went wroung in controller",404);
      return new ResponseEntity<ControllerException>(ex,HttpStatus.BAD_REQUEST);
    }

  }


}

package com.activitytracker.demo. service;

import com.activitytracker.demo.entity.ActivityEntity;
import com.activitytracker.demo.exception.BusinessEception;
import com.activitytracker.demo.pojo.request.Activities;
import com.activitytracker.demo.pojo.request.ActivityJson;
import com.activitytracker.demo.pojo.response.ActivityResponse;
import com.activitytracker.demo.pojo.response.MonthlyActivity;
import com.activitytracker.demo.pojo.response.YerterdayVsTodayActivity;
import com.activitytracker.demo.repository.ActivityRepo;
import com.activitytracker.demo.utils.ErrorMessage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.LongSerializationPolicy;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.util.ResourceUtils;
import utils.ActivityTrakerNmae;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ActivityServiceImpl implements ActivityService {

@Autowired
ActivityRepo activityRepo;

@Value("${file.path}")
private String filepath;

private final String[] activityName={ActivityTrakerNmae.DOUBLE_TAP,ActivityTrakerNmae.ANR,ActivityTrakerNmae.CRASH,ActivityTrakerNmae.SINGLE_TAP};
private final String[] statusName={ActivityTrakerNmae.POSITIVE,ActivityTrakerNmae.NEGATIVE,ActivityTrakerNmae.UNALTERED};

    @Override
    public ActivityResponse getTrakerDetails() throws IOException {
        ActivityResponse activityResponse=new ActivityResponse();
        List<ActivityJson> ActivityJson=readFile();

        saveToDb(ActivityJson);
        activityResponse.setMonthlyActivity(getMonthlyActivity());
       List<YerterdayVsTodayActivity> as=getTwoDayActivityVsYesterday();
        activityResponse.setYerterdayVsTodayActivity(as);
        return activityResponse;
    }

    private List<YerterdayVsTodayActivity> getTwoDayActivityVsYesterday() {
Date today=new Date(System.currentTimeMillis());
int num=-1;
Date yesterday=getDateFromToday(today,num);
        List<YerterdayVsTodayActivity> yesterdayList=new ArrayList<>();
        YerterdayVsTodayActivity doubleTapActivity=YerterdayVsTodayActivity.builder().name(activityName[0]).yesterdayOccurrence(0l).todayOccurrence(0l).build();
        YerterdayVsTodayActivity anrActivity=YerterdayVsTodayActivity.builder().name(activityName[1]).yesterdayOccurrence(0l).todayOccurrence(0l).build();
        YerterdayVsTodayActivity crashActivity=YerterdayVsTodayActivity.builder().name(activityName[2]).yesterdayOccurrence(0l).todayOccurrence(0l).build();
        YerterdayVsTodayActivity singleTapActivity=YerterdayVsTodayActivity.builder().name(activityName[3]).yesterdayOccurrence(0l).todayOccurrence(0l).build();

List<MonthlyActivity> yesterdayOcuurenceandNameList=activityRepo.getActivityOcurrencebyNameAndDate(yesterday);
List<MonthlyActivity> todayOcuurenceandNameList=activityRepo.getActivityOcurrencebyNameAndDate(today);

for(MonthlyActivity s:yesterdayOcuurenceandNameList){
    setYesterdayOcurrence(doubleTapActivity,anrActivity,singleTapActivity,crashActivity,s);
}

for(MonthlyActivity todayActivity:todayOcuurenceandNameList){
            setTodayOcurrence(doubleTapActivity,anrActivity,singleTapActivity,crashActivity,todayActivity);
}
setActivityStatusDubleTap(doubleTapActivity);
        setActivityStatusDubleTap(doubleTapActivity);
        setActivityStatusDubleTap(anrActivity);
        setActivityStatusDubleTap(crashActivity);
        setActivityStatusDubleTap(singleTapActivity);
        yesterdayList.add(doubleTapActivity);
        yesterdayList.add(anrActivity);
        yesterdayList.add(crashActivity);
        yesterdayList.add(singleTapActivity);

        return yesterdayList;
    }

    private void setActivityStatusDubleTap(YerterdayVsTodayActivity activity) {

        long x = activity.getTodayOccurrence();
        long y = activity.getYesterdayOccurrence();
        if (x > y) {

            activity.setStatus(statusName[0]);
        } else if (y > x) {

            activity.setStatus(statusName[1]);
        } else {

            activity.setStatus(statusName[2]);

        }
    }
    private void setTodayOcurrence(YerterdayVsTodayActivity doubleTapActivity, YerterdayVsTodayActivity anrActivity, YerterdayVsTodayActivity singleTapActivity, YerterdayVsTodayActivity crashActivity, MonthlyActivity todayActivity) {
   if(Objects.equals(todayActivity.getActivityName(),doubleTapActivity.getName())){

       doubleTapActivity.setTodayOccurrence(todayActivity.getActivityOccurrence());
   }
        else if(Objects.equals(todayActivity.getActivityName(),anrActivity.getName())){
       anrActivity.setTodayOccurrence(todayActivity.getActivityOccurrence());
        }
        else if(Objects.equals(todayActivity.getActivityName(),singleTapActivity.getName())){
       singleTapActivity.setTodayOccurrence(todayActivity.getActivityOccurrence());
        }
       else if(Objects.equals(todayActivity.getActivityName(),crashActivity.getName())){
       crashActivity.setTodayOccurrence(todayActivity.getActivityOccurrence());
        }

    }

    private void setYesterdayOcurrence(YerterdayVsTodayActivity doubleTapActivity, YerterdayVsTodayActivity anrActivity, YerterdayVsTodayActivity singleTapActivity, YerterdayVsTodayActivity crashActivity, MonthlyActivity s) {

        if(Objects.equals(s.getActivityName(),doubleTapActivity.getName())){
            doubleTapActivity.setYesterdayOccurrence(s.getActivityOccurrence());
        }
        else if(Objects.equals(s.getActivityName(),anrActivity.getName())){
            anrActivity.setYesterdayOccurrence(s.getActivityOccurrence());
        }
        else if(Objects.equals(s.getActivityName(),singleTapActivity.getName())){
            singleTapActivity.setYesterdayOccurrence(s.getActivityOccurrence());
        }
        else if(Objects.equals(s.getActivityName(),crashActivity.getName())){
            crashActivity.setYesterdayOccurrence(s.getActivityOccurrence());
        }


    }

    private List<MonthlyActivity> getMonthlyActivity() {
Date date=new Date(System.currentTimeMillis());
int numDays=-30;
Date lastMonthDate=getDateFromToday(date,numDays);

return activityRepo.getActivityStatusByActivityDate(date,lastMonthDate);
    }

    private Date getDateFromToday(Date date, int numDays) {
       Calendar cal=new GregorianCalendar();
       cal.setTime(date);
       cal.add(Calendar.DAY_OF_MONTH,numDays);


return  new Date(cal.getTime().getTime());

    }

    private void saveToDb(List<ActivityJson> activityJson) {
       List<ActivityEntity> activityEntityList=new ArrayList<>();

for(ActivityJson s:activityJson){
for(Activities d:s.getActivities()){
    ActivityEntity activityEntity1=new ActivityEntity();
    activityEntity1.setActivityName(d.getName());
    activityEntity1.setUniqueId(s.getUnique_id());
    activityEntity1.setStartTime(d.getTime());
    activityEntity1.setActivityDate(new Date(d.getTime()));
    activityEntity1.setActivityDuration(d.getDuration());
    activityEntityList.add(activityEntity1);
}

}
activityRepo.saveAll(activityEntityList);


    }

    private List<ActivityJson> readFile() throws IOException {
        List<ActivityJson> ActivityJsonList=new ArrayList<>();

Gson gson=new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create();

Path folder= Paths.get(filepath);

try(DirectoryStream<Path> stream= Files.newDirectoryStream(folder)){
    for(Path entry:stream){
     String file=folder + "\\" + entry.getFileName().toString();
        FileReader jsonFile= new FileReader(ResourceUtils.getFile(file));
        ActivityJson activityJson=gson.fromJson(jsonFile,ActivityJson.class);
        removeInvalidActivityJson(activityJson);
        ActivityJsonList.add(activityJson);
    }

}catch(JsonSyntaxException e){
throw new BusinessEception(ErrorMessage.FILE_READER_ISSUE.getErrorMessage(),ErrorMessage.FILE_READER_ISSUE.getErrorCode());

}catch(Exception e){
    throw new BusinessEception(ErrorMessage.NOT_FOUND.getErrorMessage(),ErrorMessage.NOT_FOUND.getErrorCode());

}

return ActivityJsonList;

    }

    private void removeInvalidActivityJson(ActivityJson sample) {
List<Activities> activities=sample.getActivities();

        activities=activities.stream().filter(activity -> Arrays.asList(activityName).contains(activity.getName())).collect(Collectors.toList());

        sample.setActivities(activities);

    }
}

package  com.activitytracker.demo.repository;


import com.activitytracker.demo.entity.ActivityEntity;
import com.activitytracker.demo.pojo.response.MonthlyActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utils.ActivityTrakerNmae;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepo extends CrudRepository<ActivityEntity,Long> {

@Query(ActivityTrakerNmae.ACTIVITY_STATS_BY_ACTIVITY_DATE_QUERY)
List<MonthlyActivity> getActivityStatusByActivityDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query(ActivityTrakerNmae.ACTIVITY_STATS_BY_ACTIVITY_DATE_AND_NAME_QUERY)
    List<MonthlyActivity> getActivityOcurrencebyNameAndDate(@Param("startDate") Date startDate);
}
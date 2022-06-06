package utils;

public class ActivityTrakerNmae {

  public static final String POSITIVE = "Positive";
  public static final String NEGATIVE = "Negative";
  public static final String UNALTERED = "Unaltered";
  public static final String DOUBLE_TAP = "doubletap";
  public static final String SINGLE_TAP = "singleTap";
  public static final String CRASH = "crash";
  public static final String ANR = "anr";
  public static final String ACTIVITY_STATS_BY_ACTIVITY_DATE_QUERY = "select distinct new com.activitytracker.demo.pojo.response.MonthlyActivity(a.activityName,COUNT(*) as total) from ActivityEntity a where a.activityDate >=:endDate and a.activityDate <=:startDate GROUP BY activity_name ORDER BY total DESC";
 public static final String ACTIVITY_STATS_BY_ACTIVITY_DATE_AND_NAME_QUERY = "select distinct new com.activitytracker.demo.pojo.response.MonthlyActivity(a.activityName, COUNT(*) as total) from ActivityEntity a where a.activityDate = :startDate GROUP BY activity_name";


  public ActivityTrakerNmae() {


  }

}
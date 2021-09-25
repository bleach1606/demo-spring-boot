package com.example.demo.constant;

public class DemoConstant {

  public interface Paging {

    String DEFAULT_PAGE = "1";
    String DEFAULT_SIZE = "10";

  }

  public interface ApiResponseStatus {

    String SUCCESS = "success";
    String FAIL = "fail";
    String ERROR = "error";
    String INVALID = "invalid";
    String NOTFOUND = "notfound";
  }

  public interface ApiResponseCode {

    Integer  SUCCESSFULLY = 0;
    Integer  UNKNOWN_ERROR = 1000;

  }

  public interface DurationLimit {
     int LIMIT_WEEK_MAX = 5200;
     int LIMIT_MONTH_MAX = 1200;
  }

  public interface Tracing {
    String TRACE_ID = "traceId";
    String TIME_REQUEST = "time-request";
  }
}

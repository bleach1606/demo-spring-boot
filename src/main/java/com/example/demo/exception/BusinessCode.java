package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class BusinessCode {

    private BusinessCode() {
    }

    public static final ErrorResponse SUCCESS =
            new ErrorResponse("SUCCESS-01", "SUCCESS", HttpStatus.OK);

    public static final ErrorResponse INTERNAL_SERVER =
            new ErrorResponse("INTERNAL-SERVER", "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ErrorResponse NOT_FOUND_USER =
            new ErrorResponse("NOT-FOUND-USER", "Not found user with id", HttpStatus.BAD_REQUEST);

    public static final ErrorResponse NOT_FOUND_NOTIFICATION =
            new ErrorResponse("NOT-FOUND-NOTIFICATION", "Not found notification with id", HttpStatus.BAD_REQUEST);


}

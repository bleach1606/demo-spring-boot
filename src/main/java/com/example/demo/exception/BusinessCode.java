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
    public static final ErrorResponse NOT_FOUND_CATEGORY =
            new ErrorResponse("NOT_FOUND_CATEGORY", "Not found category with id", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse NOT_FOUND_SUB_CATEGORY =
            new ErrorResponse("NOT_FOUND_SUB_CATEGORY", "Not found sub category with id", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse NOT_FOUND_ITEM =
            new ErrorResponse("NOT_FOUND_ITEM", "Not found item with id", HttpStatus.BAD_REQUEST);

}

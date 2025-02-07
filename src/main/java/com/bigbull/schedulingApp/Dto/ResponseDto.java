package com.bigbull.schedulingApp.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<T> {
    private String message;
    private String status;
    private String email;
    private T data;
}

package com.stripeboilerplate.stripeboilerplate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {
    private String sessionId;
    private String sessionUrl;
    private String status;
    private String message;
}

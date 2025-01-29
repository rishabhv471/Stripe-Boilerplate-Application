package com.stripeboilerplate.stripeboilerplate.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Request {
    private String productName;
    private long qty;
    private long price;
    private String currency;
}

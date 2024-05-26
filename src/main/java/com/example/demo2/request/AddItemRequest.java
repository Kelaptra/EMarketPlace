package com.example.demo2.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddItemRequest {
    private String name;
    private Double price;
    private String description;
    private String photoUrl;
}

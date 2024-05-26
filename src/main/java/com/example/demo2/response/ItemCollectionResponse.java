package com.example.demo2.response;

import com.example.demo2.dtos.ItemCollectionDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemCollectionResponse {
    private ItemCollectionDto itemCollectionDto;
    private String errorMessage;
}

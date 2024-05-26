package com.example.demo2.response;

import com.example.demo2.dtos.ItemDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
    private ItemDto itemDto;
    private String errorMessage;
}

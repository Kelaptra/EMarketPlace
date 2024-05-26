package com.example.demo2.dtos;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ItemCollectionDto {
    private Integer totalRecords;
    private List<ItemDto> itemDtoList;
}

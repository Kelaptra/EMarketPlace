package com.example.demo2.util;

import com.example.demo2.dtos.ItemCollectionDto;
import com.example.demo2.dtos.ItemDto;
import com.example.demo2.model.Item;
import com.example.demo2.request.AddItemRequest;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class ModelConverter {
    public ItemDto itemToDto(Item item) {
        return ItemDto.builder()
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .submissionTime(item.getSubmissionTime())
                .photoUrl(item.getPhotoUrl())
                .build();
    }

    public ItemCollectionDto itemListToDto(List<Item> items) {
        return ItemCollectionDto.builder()
                .totalRecords(items.size())
                .itemDtoList(items.stream().map(this::itemToDto).toList())
                .build();
    }

    public Item generateItem(AddItemRequest addItemRequest) {
        return Item.builder()
                .name(addItemRequest.getName())
                .description(addItemRequest.getDescription())
                .price(addItemRequest.getPrice())
                .submissionTime(Instant.now())
                .photoUrl(addItemRequest.getPhotoUrl())
                .build();
    }
}

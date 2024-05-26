package com.example.demo2.dtos;

import com.example.demo2.util.InstantConverter;
import jakarta.persistence.Convert;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ItemDto {
    private String name;
    private Double price;
    private String description;
    @Convert(converter = InstantConverter.class)
    private Instant submissionTime;
    private String photoUrl;
}

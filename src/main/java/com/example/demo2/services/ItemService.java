package com.example.demo2.services;

import com.example.demo2.dtos.ItemCollectionDto;
import com.example.demo2.dtos.ItemDto;
import com.example.demo2.model.Item;
import com.example.demo2.repository.ItemRepository;
import com.example.demo2.request.AddItemRequest;
import com.example.demo2.util.ModelConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelConverter modelConverter;
    @Autowired
    private PhotoService photoService;

    private static final Integer defaultPageSize = 6;
    private static final Sort defaultSort = Sort.by("submissionTime").descending();

    public ItemCollectionDto getItemsByPage(Integer pageNum) {
        List<Item> items = findItemsByPage(pageNum, defaultPageSize, defaultSort);
        return modelConverter.itemListToDto(items);
    }

    public ItemDto getItemById(Integer id) {
        Item item = findItemById(id).orElseThrow(() -> new EntityNotFoundException("No such item for specified id: " + id));
        return modelConverter.itemToDto(item);
    }

    public ItemDto addItem(AddItemRequest addItemRequest, MultipartFile photo) {
        Item item = modelConverter.generateItem(addItemRequest);
        String photoNameUniquePart = String.valueOf(2 * item.hashCode());
        String photoUrl = photoService.storePhoto(photo, photoNameUniquePart);
        item.setPhotoUrl(photoUrl);
        Item savedItem = saveItem(item);
        return modelConverter.itemToDto(savedItem);
    }

    public ItemDto addItem(MultipartFile photo, String name, Double price, String description) {
        Item item = Item.builder()
                .name(name)
                .price(price)
                .description(description)
                .submissionTime(Instant.now())
                .build();
        String photoNameUniquePart = String.valueOf(2 * item.hashCode());
        String photoUrl = photoService.storePhoto(photo, photoNameUniquePart);
        item.setPhotoUrl(photoUrl);
        Item savedItem = saveItem(item);
        return modelConverter.itemToDto(savedItem);
    }


    private List<Item> findItemsByPage(Integer pageNum, Integer pageSize, Sort sort) {
        return itemRepository.findAll(PageRequest.of(pageNum, pageSize, sort)).stream().toList();
    }

    private Optional<Item> findItemById(Integer id) {
        return itemRepository.findById(id);
    }

    private Item saveItem(Item item) {
        return itemRepository.save(item);
    }
}

package com.example.demo2.controllers;

import com.example.demo2.dtos.ItemCollectionDto;
import com.example.demo2.dtos.ItemDto;
import com.example.demo2.services.ItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private ItemService itemService;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<?> getItemsByPage(@RequestParam Integer pageNum) {
        try {
            ItemCollectionDto items = itemService.getItemsByPage(pageNum);
            return ResponseEntity.status(HttpStatus.OK).body(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getItemById(@PathVariable Integer id) {
        try{
            ItemDto item = itemService.getItemById(id);
            return ResponseEntity.status(HttpStatus.OK).body(item);
        } catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addItem(@RequestParam MultipartFile photo,
                                     @RequestParam String name,
                                     @RequestParam Double price,
                                     @RequestParam String description) {
        try {
            ItemDto item = itemService.addItem(photo, name, price, description);
            return ResponseEntity.status(HttpStatus.CREATED).body(item);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

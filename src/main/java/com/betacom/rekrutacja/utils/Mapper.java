package com.betacom.rekrutacja.utils;

import com.betacom.rekrutacja.dto.ItemGetResponse;
import com.betacom.rekrutacja.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<ItemGetResponse> convertToDto(List<Item> items) {
        List<ItemGetResponse> itemsDtoList = new ArrayList<>();
        items.forEach(item -> {
            ItemGetResponse itemDto = new ItemGetResponse(item.getId(), item.getName());
            itemsDtoList.add(itemDto);
        });
        return itemsDtoList;
    }
}

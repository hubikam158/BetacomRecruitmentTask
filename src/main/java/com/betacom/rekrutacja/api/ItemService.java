package com.betacom.rekrutacja.api;

import com.betacom.rekrutacja.dto.ItemGetResponse;
import com.betacom.rekrutacja.entity.Item;
import com.betacom.rekrutacja.entity.User;
import com.betacom.rekrutacja.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    public List<ItemGetResponse> getItemsByUser(User user) {
        return Mapper.convertToDto(itemRepository.findAllByOwner(user));
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }
}

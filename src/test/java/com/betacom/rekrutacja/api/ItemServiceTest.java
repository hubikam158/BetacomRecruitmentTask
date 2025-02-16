package com.betacom.rekrutacja.api;

import com.betacom.rekrutacja.dto.ItemGetResponse;
import com.betacom.rekrutacja.entity.Item;
import com.betacom.rekrutacja.entity.User;
import com.betacom.rekrutacja.utils.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.betacom.rekrutacja.api.DatabaseMock.getItemsForUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetAllUserItems() throws JsonProcessingException {

        // given
        final User user = new User("user1", "$2y$10$05H3rq9iDwZr17lJQkEUae1qCIAKatcuwwurxeziPpfTERiH3K6A2");
        user.setId("d5be5b9d-5a65-4e31-9f5b-5daeff044ae1");
        final ItemRepository itemRepository = mock(ItemRepository.class);
        final ItemService itemService = new ItemService(itemRepository);
        final List<Item> items = objectMapper.readValue(getItemsForUser(), new TypeReference<>() {});
        final List<ItemGetResponse> expectedResponse = Mapper.convertToDto(items);
        final int expectedSize = 3;
        final String expectedItemName = "item1";

        // when
        when(itemService.getItemsByUser(user)).thenReturn(expectedResponse);

        // then
        assertThat(expectedResponse).hasSize(expectedSize);
        assertThat(expectedResponse.getFirst().getName()).isEqualTo(expectedItemName);
    }

    @Test
    void shouldAddItem() {

        // given
        final User user = new User("user1", "$2y$10$05H3rq9iDwZr17lJQkEUae1qCIAKatcuwwurxeziPpfTERiH3K6A2");
        user.setId("d5be5b9d-5a65-4e31-9f5b-5daeff044ae1");
        final Item item = new Item("item", user);
        item.setId("ed74a842-b84c-4b13-b3fa-14126c0937a9");
        final ItemService itemService = mock(ItemService.class);
        final int expectedNumberOfInvocations = 1;

        // when
        doNothing().when(itemService).addItem(item);
        itemService.addItem(item);

        // then
        verify(itemService, times(expectedNumberOfInvocations)).addItem(item);
    }
}

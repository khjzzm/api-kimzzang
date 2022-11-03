package kim.zzang.webservice.api.v1.item.service;


import kim.zzang.webservice.api.v1.item.domain.Item;
import kim.zzang.webservice.api.v1.item.dto.ItemSearchCond;
import kim.zzang.webservice.api.v1.item.dto.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findItems(ItemSearchCond itemSearch);

}
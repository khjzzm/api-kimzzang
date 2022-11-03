package kim.zzang.webservice.api.v1.item.service;


import kim.zzang.webservice.api.v1.item.domain.Item;
import kim.zzang.webservice.api.v1.item.dto.ItemSearchCond;
import kim.zzang.webservice.api.v1.item.dto.ItemUpdateDto;
import kim.zzang.webservice.api.v1.item.repository.ItemQueryRepository;
import kim.zzang.webservice.api.v1.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemQueryRepository itemQueryRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem;
        try {
            findItem = itemRepository.findById(itemId).orElseThrow(() -> new Exception("Item not found - " + itemId));
            findItem.setItemName(updateParam.getItemName());
            findItem.setPrice(updateParam.getPrice());
            findItem.setQuantity(updateParam.getQuantity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        };
    }


    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findItems(ItemSearchCond cond) {
        return itemQueryRepository.findAll(cond);
    }

}

package com.training.springboot.itemstorage.service;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.entity.request.NotificationRequest;
import com.training.springboot.itemstorage.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService implements IItemService {

	private final ItemRepository itemRepository;

	@Override
	public Page<Item> list(int size, int page) {
		// TODO retrieve item page (use repository)
		return null;
	}

	@Override
	public List<Item> list() {
		// TODO list items (use repository)
		return null;
	}

	@Override
	public Item get(Long id) {
		// TODO get item by id (use repository)
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO delete item id (use repository)
	}

	@Override
	public Item update(Item item) {
		// TODO update item (use repository)
		return null;
	}

	@Override
	public Item save(Item item) {
		// TODO save item (use repository)
		return null;
	}

	@Override
	public void notify(NotificationRequest request) {
		//TODO Print request (format email:message)
	}

	@Override
	public void restock(Long id, Integer quantity) {
		//TODO get item's current stock and increase it  by given quantity
	}

	@Override
	public void dispatch(Long id, Integer quantity) {
		//TODO get item's current stock and decrease it  by given quantity
	}
}

package com.training.springboot.itemstorage.service;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.entity.request.NotificationRequest;
import com.training.springboot.itemstorage.enums.EnumEntity;
import com.training.springboot.itemstorage.error.EntityNotFoundException;
import com.training.springboot.itemstorage.repository.ItemRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.training.springboot.itemstorage.utils.constant.ItemStorageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService implements IItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Page<Item> list(int size, int page) {
        Pageable sizePage = PageRequest.of(page,size);

	    return itemRepository.findAll(sizePage);
	}

	@Override
	public List<Item> list() {
		return itemRepository.findAll();
	}

	@Override
	public Item get(Long id) {
		return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EnumEntity.ITEM.name(), id));
	}

	@Override
	public void delete(Long id) {
		itemRepository.deleteById(id);
	}

	@Override
	public Item update(Item item) {
	    Optional<Item> found =itemRepository.findById(item.getItemUid());

	    if (!found.isPresent()){
	        throw new EntityNotFoundException(EnumEntity.ITEM.name(), item.getItemUid());
        }

        Item newItem = found.get();
	    newItem.setName(item.getName());
	    newItem.setDescription(item.getDescription());
	    newItem.setStock(item.getStock());
	    newItem.setMarket(item.getMarket());
	    newItem.setPriceTag(item.getPriceTag());
	    newItem.setState(item.getState());

		return itemRepository.save(newItem);
	}

	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public void notify(NotificationRequest request) {
	    System.out.println(String.format("{0}:{1}", request.getEmail(), request.getMessage()));
	}

	@Override
	public void restock(Long id, Integer quantity) {
	    Item foundItem = get(id);

	    BigInteger newStock = foundItem.getStock().add(BigInteger.valueOf(quantity));
        foundItem.setStock(newStock);

		save(foundItem);
	}

	@Override
	public void dispatch(Long id, Integer quantity) {
        Item foundItem = get(id);

        BigInteger newStock = foundItem.getStock().subtract(BigInteger.valueOf(quantity));
        foundItem.setStock(newStock);

        save(foundItem);
	}
}

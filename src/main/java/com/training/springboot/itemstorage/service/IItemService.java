package com.training.springboot.itemstorage.service;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.entity.request.NotificationRequest;

public interface IItemService extends ICrudService<Item> {

	void notify(NotificationRequest request);

	void restock(Long id, Integer quantity);

	void dispatch(Long id, Integer quantity);

}

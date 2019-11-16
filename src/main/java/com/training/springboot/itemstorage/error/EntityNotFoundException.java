package com.training.springboot.itemstorage.error;

import com.training.springboot.itemstorage.utils.constant.ItemStorageConstant;

public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException(String entity, Long id) {
		this(String.format(ItemStorageConstant.ENTITY_NOT_FOUND_MSG, entity, id));
	}

	private EntityNotFoundException(String message) {
		super(message);
	}
}

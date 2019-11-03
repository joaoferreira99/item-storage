package com.training.springboot.itemstorage.controller;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.entity.request.CreateItemRequestDto;
import com.training.springboot.itemstorage.entity.request.DispatchItemRequestDto;
import com.training.springboot.itemstorage.entity.request.RestockItemRequestDto;
import com.training.springboot.itemstorage.entity.response.CreateItemResponseDto;
import com.training.springboot.itemstorage.entity.response.ErrorMessage;
import com.training.springboot.itemstorage.entity.response.GetItemResponseDto;
import com.training.springboot.itemstorage.entity.response.UpdateItemResponseDto;
import com.training.springboot.itemstorage.enums.EnumOperation;
import com.training.springboot.itemstorage.service.ItemService;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * @JavaDoc ModelMapper is a mapping tool easily configurable to accommodate most application defined entities check
	 * some configuration example at: http://modelmapper.org/user-manual/
	 */
	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<?> createItem(@RequestBody @Valid CreateItemRequestDto request) {
		try {
			Item item = mapper.map(request, Item.class);
			return new ResponseEntity<>(mapper.map(itemService.save(item), CreateItemResponseDto.class), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(
					ErrorMessage.builder().code(HttpStatus.CONFLICT.name()).operation(EnumOperation.CreateItem.name())
							.message(e.getMessage()).build(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getItem(@PathVariable("id") Long id) {
		try {
			Item item = itemService.get(id);
			return new ResponseEntity<>(mapper.map(item, GetItemResponseDto.class), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(
					ErrorMessage.builder().code(HttpStatus.NOT_FOUND.name()).operation(EnumOperation.GetItem.name())
							.message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
		item.setItemUid(id);
		try {
			return new ResponseEntity<>(mapper.map(itemService.update(item), UpdateItemResponseDto.class), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(
					ErrorMessage.builder().code(HttpStatus.NOT_FOUND.name()).operation(EnumOperation.UpdateItem.name())
							.message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(
					ErrorMessage.builder().code(HttpStatus.CONFLICT.name()).operation(EnumOperation.UpdateItem.name())
							.message(e.getMessage()).build(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable("id") Long id) {
		try {
			itemService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(
					ErrorMessage.builder().code(HttpStatus.NOT_FOUND.name()).operation(EnumOperation.DeleteItem.name())
							.message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<GetItemResponseDto>> listItems() {
		return new ResponseEntity<>(itemService.list().stream().map(i -> mapper.map(i, GetItemResponseDto.class)).collect(
				Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping("/{id}/dispatch")
	public ResponseEntity<?> stockItem(@PathVariable("id") Long id,
			@RequestBody DispatchItemRequestDto request) {
		try {
			itemService.dispatch(id, request.getQuantity());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(
					ErrorMessage.builder().code(HttpStatus.NOT_FOUND.name()).operation(EnumOperation.DispatchItem.name())
							.message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/restock")
	public ResponseEntity<?> dispatchItem(@PathVariable("id") Long id,
			@RequestBody RestockItemRequestDto request) {
		try {
			itemService.restock(id, request.getQuantity());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(
					ErrorMessage.builder().code(HttpStatus.NOT_FOUND.name()).operation(EnumOperation.RestockItem.name())
							.message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
		}
	}

}

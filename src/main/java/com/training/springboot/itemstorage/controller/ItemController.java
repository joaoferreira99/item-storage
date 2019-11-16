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
import com.training.springboot.itemstorage.utils.annotation.ServiceOperation;

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
@RequestMapping("/items")
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
	@ServiceOperation("createItem")
	public ResponseEntity<CreateItemResponseDto> createItem(@RequestBody @Valid CreateItemRequestDto request) {
			return new ResponseEntity<>(mapper.map(itemService.save(mapper.map(request, Item.class)), CreateItemResponseDto.class), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@ServiceOperation("getItem")
	public ResponseEntity<GetItemResponseDto> getItem(@PathVariable("id") Long id) {
			return new ResponseEntity<>(mapper.map(itemService.get(id), GetItemResponseDto.class), HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	@ServiceOperation("updateItem")
	public ResponseEntity<UpdateItemResponseDto> updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
		item.setItemUid(id);
			return new ResponseEntity<>(mapper.map(itemService.update(item), UpdateItemResponseDto.class), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ServiceOperation("deleteItem")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id) {
			itemService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	@ServiceOperation("listItems")
	public ResponseEntity<List<GetItemResponseDto>> listItems() {
		return new ResponseEntity<>(itemService.list().stream().map(i -> mapper.map(i, GetItemResponseDto.class)).collect(
				Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping("/{id}/dispatch")
	@ServiceOperation("dispatchItem")
	public ResponseEntity<HttpStatus> dispatchItem(@PathVariable("id") Long id,
			@RequestBody DispatchItemRequestDto request) {
			itemService.dispatch(id, request.getQuantity());
			return new ResponseEntity<>(HttpStatus.OK);
	
	}

	@PostMapping("/{id}/restock")
	@ServiceOperation("restockItem")
	public ResponseEntity<HttpStatus> restockItem(@PathVariable("id") Long id,
			@RequestBody RestockItemRequestDto request) {
			itemService.restock(id, request.getQuantity());
			return new ResponseEntity<>(HttpStatus.OK);
	}

}

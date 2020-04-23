package com.training.springboot.itemstorage.controller;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.entity.request.CreateItemRequestDto;
import com.training.springboot.itemstorage.entity.request.DispatchItemRequestDto;
import com.training.springboot.itemstorage.entity.request.RestockItemRequestDto;
import com.training.springboot.itemstorage.entity.request.UpdateItemRequestDto;
import com.training.springboot.itemstorage.entity.response.CreateItemResponseDto;
import com.training.springboot.itemstorage.entity.response.GetItemResponseDto;
import com.training.springboot.itemstorage.entity.response.UpdateItemResponseDto;
import com.training.springboot.itemstorage.service.ItemService;
import com.training.springboot.itemstorage.utils.annotation.ServiceOperation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

	private final ItemService itemService;

	/**
	 * @JavaDoc ModelMapper is a mapping tool easily configurable to accommodate most application defined entities check
	 * some configuration example at: http://modelmapper.org/user-manual/
	 */
	private final ModelMapper mapper;

	@ServiceOperation("createItem")
	@PostMapping
	public ResponseEntity<CreateItemResponseDto> createItem(@RequestBody @Valid CreateItemRequestDto request) {
		Item item = mapper.map(request, Item.class);
		Item persistedItem = itemService.save(item);
		CreateItemResponseDto responseDto = mapper.map(persistedItem, CreateItemResponseDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@ServiceOperation("getItem")
	@GetMapping("/{id}")
	public ResponseEntity<GetItemResponseDto> getItem(@PathVariable("id") Long id) {
		Item item = itemService.get(id);
		GetItemResponseDto responseDto = mapper.map(item, GetItemResponseDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@ServiceOperation("updateItem")
	@PatchMapping("/{id}")
	public ResponseEntity<UpdateItemResponseDto> updateItem(@PathVariable("id") Long id,
			@RequestBody UpdateItemRequestDto request) {
		Item item = mapper.map(request, Item.class);
		Item persistedItem = itemService.save(item);
		UpdateItemResponseDto responseDto = mapper.map(persistedItem, UpdateItemResponseDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@ServiceOperation("deleteItem")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id) {
		itemService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ServiceOperation("listItems")
	@GetMapping
	public ResponseEntity<List<GetItemResponseDto>> listItems() {
		List<GetItemResponseDto> responseDtoList = new ArrayList<>();
		List<Item> itemList = itemService.list();
		itemList.forEach(item -> responseDtoList.add(mapper.map(item, GetItemResponseDto.class)));
		return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
	}

	@ServiceOperation("dispatchItem")
	@PostMapping("/{id}/dispatch")
	public ResponseEntity<HttpStatus> dispatchItem(@PathVariable("id") Long id,
			@RequestBody DispatchItemRequestDto request) {
		itemService.dispatch(id, request.getQuantity());
		return ResponseEntity.ok().build();
	}

	@ServiceOperation("restockItem")
	@PostMapping("/{id}/restock")
	public ResponseEntity<HttpStatus> restockItem(@PathVariable("id") Long id,
			@RequestBody RestockItemRequestDto request) {
		itemService.restock(id, request.getQuantity());
		return ResponseEntity.ok().build();
	}

}

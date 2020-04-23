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
import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<CreateItemResponseDto> createItem(@RequestBody @Valid CreateItemRequestDto request) {
		Item item = mapper.map(request, Item.class);
		//TODO PERSIST ENTITY (use service)
		CreateItemResponseDto responseDto = mapper.map(persistedItem, CreateItemResponseDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetItemResponseDto> getItem(@PathVariable("id") Long id) {
		//TODO GET ITEM (use service)
		GetItemResponseDto responseDto = mapper.map(item, GetItemResponseDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UpdateItemResponseDto> updateItem(@PathVariable("id") Long id,
			@RequestBody UpdateItemRequestDto request) {
		Item item = mapper.map(request, UpdateItemRequestDto.class);
		//TODO Update item (use service)
		UpdateItemResponseDto responseDto = mapper.map(persistedItem, UpdateItemResponseDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id) {
		// TODO Delete item (use service)
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<GetItemResponseDto>> listItems() {
		List<GetItemResponseDto> responseDtoList;
		// TODO List items (use service)
		// TODO Map each item in the List
		return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
	}

	@PostMapping("/{id}/dispatch")
	public ResponseEntity<HttpStatus> dispatchItem(@PathVariable("id") Long id,
			@RequestBody DispatchItemRequestDto request) {
		// TODO Dispatch item (use service)
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{id}/restock")
	public ResponseEntity<HttpStatus> restockItem(@PathVariable("id") Long id,
			@RequestBody RestockItemRequestDto request) {
		// TODO Restock item (use service)
		return ResponseEntity.ok().build();
	}

}

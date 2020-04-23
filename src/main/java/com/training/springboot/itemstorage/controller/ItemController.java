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
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	/**
	 * @JavaDoc ModelMapper is a mapping tool easily configurable to accommodate most application defined entities check
	 * some configuration example at: http://modelmapper.org/user-manual/
	 */
	private final ModelMapper mapper;

	@PostMapping
	public ResponseEntity<CreateItemResponseDto> createItem(@RequestBody @Valid CreateItemRequestDto request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.map(itemService.save(mapper.map(request, Item.class)), CreateItemResponseDto.class));
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetItemResponseDto> getItem(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(mapper.map(itemService.get(id), GetItemResponseDto.class));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UpdateItemResponseDto> updateItem(@PathVariable("id") Long id,
			@RequestBody UpdateItemRequestDto request) {
		request.setItemUid(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(mapper.map(itemService.update(mapper.map(request, Item.class)), UpdateItemResponseDto.class));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id) {
		itemService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<GetItemResponseDto>> listItems() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(itemService.list().stream().map(i -> mapper.map(i, GetItemResponseDto.class)).collect(
						Collectors.toList()));
	}

	@PostMapping("/{id}/dispatch")
	public ResponseEntity<HttpStatus> dispatchItem(@PathVariable("id") Long id,
			@RequestBody DispatchItemRequestDto request) {
		itemService.dispatch(id, request.getQuantity());
		return ResponseEntity.ok().build();

	}

	@PostMapping("/{id}/restock")
	public ResponseEntity<HttpStatus> restockItem(@PathVariable("id") Long id,
			@RequestBody RestockItemRequestDto request) {
		itemService.restock(id, request.getQuantity());
		return ResponseEntity.ok().build();
	}

}

package com.training.springboot.itemstorage.entity.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemRequestDto {

	@JsonIgnore
	private Long itemUid;
	private String name;
	private String state;
	private String description;
	private String market;
	@PositiveOrZero
	private Integer stock;
	@PositiveOrZero
	private Double priceTag;

}

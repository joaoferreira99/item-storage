package com.training.springboot.itemstorage.entity.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestockItemRequestDto {

	@NotNull
	@PositiveOrZero
	private Integer quantity;
}

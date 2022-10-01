package com.catwalk.test.Catwalk_Test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrder {

	private Integer productId;
	private Integer quantity;
}
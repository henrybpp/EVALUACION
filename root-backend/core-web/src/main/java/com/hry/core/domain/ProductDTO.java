package com.hry.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDTO {
	private String id;
	private String brand;
	private String description;
	private String image;
	private Double price;
}

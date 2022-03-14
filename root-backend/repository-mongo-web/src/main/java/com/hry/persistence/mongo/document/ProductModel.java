package com.hry.persistence.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
@Document(collection = "products")
public class ProductModel {
	
	@Id
	private String id;
	private String brand;
	private String description;
	private String image;
	private Double price;
}

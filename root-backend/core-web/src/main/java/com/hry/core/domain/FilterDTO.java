package com.hry.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FilterDTO {
	private String brand;
	private String description;
}

package com.hry.evaluacion.service;

import java.util.List;

import com.hry.core.common.wrapper.Response;
import com.hry.core.domain.FilterDTO;
import com.hry.persistence.mongo.document.ProductModel;

public interface ProductService {
	Response<ProductModel> getById(String id);
	Response<List<ProductModel>> getAll();
	Response<List<ProductModel>> getByPalindromeCondition(FilterDTO filterDTO);
}

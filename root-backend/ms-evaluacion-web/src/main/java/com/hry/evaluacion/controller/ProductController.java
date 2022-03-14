package com.hry.evaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hry.core.common.wrapper.Response;
import com.hry.core.domain.FilterDTO;
import com.hry.evaluacion.service.ProductService;
import com.hry.persistence.mongo.document.ProductModel;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping(value = "/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String ping(){
		return "ms controller is running..";
	}
	
	@GetMapping(value = "/getall", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<List<ProductModel>> getAll(){
		return productService.getAll();
	}
	
	@GetMapping(value = "/getbyid/{id}", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<ProductModel> getById(@PathVariable String id){
		return productService.getById(id);
	}
	
	@PostMapping(value = "/getbycondition",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Response<List<ProductModel>> getByPalindromeCondition(@RequestBody FilterDTO filterDTO){
		return productService.getByPalindromeCondition(filterDTO);
	}
}

package com.hry.evaluacion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hry.core.common.base.BaseService;
import com.hry.core.common.util.Constantes;
import com.hry.core.common.util.Util;
import com.hry.core.common.wrapper.Response;
import com.hry.core.domain.FilterDTO;
import com.hry.persistence.mongo.document.ProductModel;
import com.hry.persistence.mongo.repository.ProductRepository;

@Service
public class ProductServiceImpl extends BaseService implements ProductService{
	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
		
	@Autowired
	ProductRepository productRepository;

	@Override
	public Response<ProductModel> getById(String id) {
		Response<ProductModel> response = getInitialResponse();
		try {
			Optional<ProductModel> optProduct = productRepository.findById((new ObjectId(id)).toString());
			
			if(!optProduct.isPresent()){
				response.setStatusText(Constantes.STATUS_TEXT_DESCRIPTION);
				response.setStatusCode(Constantes.PRD_NOT_FOUND);
				return response;				
			}
			response.setObjetoRespuesta(optProduct.get());
		}catch(Exception e) {
			logger.info("error find product...",e);
			response.setStatusText(Constantes.STATUS_TEXT_ERROR);
			response.setStatusCode(Constantes.ERR_GENERICO);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<List<ProductModel>> getAll() {
		Response<List<ProductModel>> response = getInitialResponse();
		List<ProductModel> lstProductsModel = new ArrayList<>();
		try {
			lstProductsModel = productRepository.findAll();
			
			if(lstProductsModel.isEmpty()){
				response.setStatusCode(Constantes.PRD_NOT_FOUND);
				response.setStatusText(Constantes.STATUS_TEXT_DESCRIPTION);
				return response;
			}

			response.setObjetoRespuesta(lstProductsModel);
		}catch(Exception e) {
			logger.info("error find product...",e);
			response.setStatusText(Constantes.STATUS_TEXT_ERROR);
			response.setStatusCode(Constantes.ERR_GENERICO);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public Response<List<ProductModel>> getByPalindromeCondition(FilterDTO filterDTO) {
		Response<List<ProductModel>> response = getInitialResponse();
		List<ProductModel> lstProductsModel = new ArrayList<>();
		try {
			
			boolean brandIsPalindrome = Util.isPalindrome(filterDTO.getBrand()) ? Boolean.TRUE : Boolean.FALSE;
			boolean descriptionIsPalindrome = Util.isPalindrome(filterDTO.getDescription()) ? Boolean.TRUE : Boolean.FALSE;
			
			if(!(brandIsPalindrome || descriptionIsPalindrome)) {
				response.setStatusCode(Constantes.PRD_PAL_NOT_FOUND);
				response.setStatusText(Constantes.PAR_NOT_PALINDROME);
				response.setObjetoRespuesta(lstProductsModel);
				return response;
			}
			
			String brand = brandIsPalindrome ? ".*" + filterDTO.getBrand() + ".*" : "~";
			String description = descriptionIsPalindrome ? ".*" + filterDTO.getDescription() + ".*" : "~";			
			
			lstProductsModel = productRepository
				.findByPalindromeCondition(brand, description);
			
			if(lstProductsModel.isEmpty()){
				response.setStatusCode(Constantes.PRD_NOT_FOUND);
				response.setStatusText(Constantes.STATUS_TEXT_DESCRIPTION);
				return response;
			}
			response.setObjetoRespuesta(lstProductsModel);
		}catch(Exception e) {
			logger.info("error find product by palindrome condition...",e);
			response.setStatusText(Constantes.STATUS_TEXT_ERROR);
			response.setStatusCode(Constantes.ERR_GENERICO);
			response.setMessage(e.getMessage());
		}
		return response;
	}
}

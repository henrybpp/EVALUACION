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
	
	private static Double dscTextPalindrome = 0.50;

	@Override
	public Response<ProductModel> getById(String id) {
		Response<ProductModel> simpleResponse = new Response<>();
		try {
			Optional<ProductModel> optProduct = productRepository.findById((new ObjectId(id)).toString());			
			if(!optProduct.isPresent()){
				simpleResponse.setStatusText(Constantes.STATUS_TEXT_DESCRIPTION);
				simpleResponse.setStatusCode(Constantes.PRD_NOT_FOUND);
				return simpleResponse;				
			}
			simpleResponse.setObjetoRespuesta(optProduct.get());
		}catch(Exception e) {
			logger.info("error find product...",e);
			simpleResponse.setStatusText(Constantes.STATUS_TEXT_ERROR);
			simpleResponse.setStatusCode(Constantes.ERR_GENERICO);
			simpleResponse.setMessage(e.getMessage());
		}
		return simpleResponse;
	}

	@Override
	public Response<List<ProductModel>> getAll() {
		Response<List<ProductModel>> lstResponse = getInitialResponse();
		List<ProductModel> lstProductsModel = new ArrayList<>();
		try {
			lstProductsModel = productRepository.findAll();
			
			if(lstProductsModel.isEmpty()){
				lstResponse.setStatusCode(Constantes.PRD_NOT_FOUND);
				lstResponse.setStatusText(Constantes.STATUS_TEXT_DESCRIPTION);
				return lstResponse;
			}

			lstResponse.setObjetoRespuesta(lstProductsModel);
		}catch(Exception e) {
			logger.info("error find product...",e);
			lstResponse.setStatusText(Constantes.STATUS_TEXT_ERROR);
			lstResponse.setStatusCode(Constantes.ERR_GENERICO);
			lstResponse.setMessage(e.getMessage());
		}
		return lstResponse;
	}

	@Override
	public Response<List<ProductModel>> getByPalindromeCondition(FilterDTO filterDTO) {
		Response<List<ProductModel>> lstResponse = getInitialResponse();
		List<ProductModel> lstProductsModel = new ArrayList<>();
		try {
			
			boolean brandIsPalindrome = Util.isPalindrome(filterDTO.getBrand()) ? Boolean.TRUE : Boolean.FALSE;
			boolean descriptionIsPalindrome = Util.isPalindrome(filterDTO.getDescription()) ? Boolean.TRUE : Boolean.FALSE;
			
			if(!(brandIsPalindrome || descriptionIsPalindrome)) {
				lstResponse.setStatusCode(Constantes.PRD_PAL_NOT_FOUND);
				lstResponse.setStatusText(Constantes.PAR_NOT_PALINDROME);
				lstResponse.setObjetoRespuesta(lstProductsModel);
				return lstResponse;
			}
			
			String brand = brandIsPalindrome ? ".*" + filterDTO.getBrand() + ".*" : "~";
			String description = descriptionIsPalindrome ? ".*" + filterDTO.getDescription() + ".*" : "~";			
			
			lstProductsModel = productRepository
				.findByPalindromeCondition(brand, description);
			
			if(lstProductsModel.isEmpty()){
				lstResponse.setStatusCode(Constantes.PRD_NOT_FOUND);
				lstResponse.setStatusText(Constantes.STATUS_TEXT_DESCRIPTION);
				return lstResponse;
			}
			
			lstProductsModel.forEach(x->  
				x.setPrice(x.getPrice()*dscTextPalindrome)
			);
			lstResponse.setObjetoRespuesta(lstProductsModel);
		}catch(Exception e) {
			logger.info("error find product by palindrome condition...",e);
			lstResponse.setStatusText(Constantes.STATUS_TEXT_ERROR);
			lstResponse.setStatusCode(Constantes.ERR_GENERICO);
			lstResponse.setMessage(e.getMessage());
		}
		return lstResponse;
	}
}

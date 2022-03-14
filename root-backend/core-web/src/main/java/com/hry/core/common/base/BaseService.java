package com.hry.core.common.base;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.hry.core.common.util.Constantes;
import com.hry.core.common.wrapper.Response;

public abstract class BaseService {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	private static final Integer STATUS_OK = 200;
	
	@PostConstruct
	void init(){}	
	
	public String getPathApi() {
		return request.getRequestURI();
	}
	
	public Integer getStatusError() {
		return response.getStatus();
	}
	
	public <T> Response<T> getInitialResponse() {
		Response<T> responsex= new Response<>();
		responsex.setPath(getPathApi());
		responsex.setStatus(STATUS_OK);
		responsex.setStatusText(Constantes.STATUS_TEXT_OK);
		responsex.setMessage(Constantes.OPERACION_OK);
		return responsex;
	}
}
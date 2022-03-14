package com.hry.core.common.base;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.hry.core.common.util.Constantes;
import com.hry.core.common.wrapper.Response;

public abstract class BaseService {
	
	@Autowired
	public HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@PostConstruct
	void init(){}	

	public Integer getStatusOk() {
		Integer res = 200;
		return res;
	}
	
	public String getPathApi() {
		return request.getRequestURI();
	}
	
	public Integer getStatusError() {
		return response.getStatus();
	}
	
	public <T> Response<T> getInitialResponse() {
		Response<T> response=new Response<>();
		response.setPath(getPathApi());
		response.setStatus(getStatusOk());
		response.setStatusText(Constantes.STATUS_TEXT_OK);
		response.setMessage(Constantes.OPERACION_OK);
		return response;
	}
}
package com.hry.core.common.wrapper;

import javax.annotation.PostConstruct;

import com.hry.core.common.util.Constantes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
	Integer status;
	String statusText;
	String statusCode;
	String message;
	String path;
	
	@PostConstruct
	public void initIt(){
		this.statusText=Constantes.STATUS_TEXT_OK;
		this.status=200;
	}

	private T objetoRespuesta;	
}

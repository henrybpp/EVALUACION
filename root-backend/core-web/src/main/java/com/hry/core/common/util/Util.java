package com.hry.core.common.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hry.core.common.exception.ProductException;

public final class Util {
	private Util() {
	}

	public static LocalDate generaFecha(String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FORMATO_FECHA_LARGA);
		return LocalDate.parse(fecha, formatter);
	}

	public static String obtenerFechaString() {
		Locale local = new Locale("es-PE");
		DateFormat df = new SimpleDateFormat(Constantes.FORMATO_FECHA_LARGA, local);
		Date timeDate = Calendar.getInstance().getTime();
		return df.format(timeDate);
	}

	public static Date obtenerFechaDate() throws ParseException {
		Locale local = new Locale("es-PE");
		DateFormat df = new SimpleDateFormat(Constantes.FORMATO_FECHA_LARGA, local);
		return df.parse(Calendar.getInstance().getTime().toString());
	}

	public static boolean fechaOk(LocalDate fechaDesde, LocalDate fechaHasta, LocalDate fechaData) {
		boolean ok = false;
		if (fechaData.equals(fechaDesde) || fechaData.equals(fechaHasta)) {
			ok = true;
		}
		if (fechaData.isBefore(fechaHasta) && fechaData.isAfter(fechaDesde)) {
			ok = true;
		}
		return ok;
	}

	public static String objectToJson(Object o) throws ProductException, JsonProcessingException {
		ObjectMapper mapperObj = new ObjectMapper();
		mapperObj.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return mapperObj.writeValueAsString(o);
	}

	public static <T> T objectToObject(Class<T> type, Object o) throws ProductException, IOException {
		ObjectMapper mapperObj = new ObjectMapper();
		mapperObj.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapperObj.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		String jsonString = objectToJson(o);
		return mapperObj.readValue(jsonString, type);
	}

	public static <T> T jsonStringToObject(Class<T> type, String jsonInString) throws IOException {
		ObjectMapper mapperObj = new ObjectMapper();
		return mapperObj.readValue(jsonInString, type);
	}

	public static boolean isPalindrome(String phrase) {
		//String sPalabra = "anita lava la tina";
		//String sPalabra = "anilina";
		//String sPalabra = "dabale arroz a la zorra el abad";
		boolean isPalindrome = Boolean.FALSE;
		int inc = 0;
		int des = phrase.length() - 1;
		boolean bError = false;
		
		if(ObjectUtils.isEmpty(phrase))
			return isPalindrome;

		while ((inc < des) && (!bError)) {
			if (phrase.charAt(inc) == phrase.charAt(des)) {
				inc++;
				des--;
			} else {
				bError = true;
			}
		}

		if (!bError)
			isPalindrome = Boolean.TRUE;
		
		return isPalindrome;
	}
}

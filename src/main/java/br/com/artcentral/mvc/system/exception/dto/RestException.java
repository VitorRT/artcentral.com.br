package br.com.artcentral.mvc.system.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestException {
	protected boolean error;
	protected String message;
	protected int statusCode;
}

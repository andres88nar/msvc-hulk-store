package com.co.iuvity.hulk.store.commons.services;

import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;

import com.co.iuvity.hulk.store.exception.InputValidationException;

import static com.co.iuvity.hulk.store.constant.MessagesConstans.MSN_FIELD_NUNABLE_ERROR;
import static com.co.iuvity.hulk.store.constant.MessagesConstans.REPLACE;

@Service
public class ValidateField {
	
	public void validateFieldNull(String field, Optional<String> value) throws InputValidationException {
		if (value.isEmpty() || value.get().isBlank()) {			
			throw new InputValidationException(MSN_FIELD_NUNABLE_ERROR.replace(REPLACE, field));
		}
	}
	
	public void validateObjectNull (String field , Object object) throws InputValidationException {
		if (object == null) {
			throw new InputValidationException(MSN_FIELD_NUNABLE_ERROR.replace(REPLACE, field));			
		}
	}
	
	public void validateRegEx (String data, String regex, String message) throws InputValidationException {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches()) {			
			throw new InputValidationException(message);
		}
		
	}
}

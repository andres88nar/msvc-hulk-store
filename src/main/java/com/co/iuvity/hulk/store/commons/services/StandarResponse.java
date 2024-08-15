package com.co.iuvity.hulk.store.commons.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.iuvity.hulk.store.models.RestStatus;
import com.co.iuvity.hulk.store.models.Status;
import com.co.iuvity.hulk.store.models.entities.Product;

import static com.co.iuvity.hulk.store.constant.CommonsConstans.SEVERITY_SUCCESS;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.SEVERITY_WARNING;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.SEVERITY_ERROR;

import static com.co.iuvity.hulk.store.constant.CommonsConstans.SUCCESS_STATUS;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.BAD_STATUS;

import static com.co.iuvity.hulk.store.constant.MessagesConstans.MSN_PRODUCT_DELETED;
import static com.co.iuvity.hulk.store.constant.MessagesConstans.REPLACE;

@Service
public class StandarResponse {

	public ResponseEntity<Object> createResponseBuilderNoFound(String message) {
		return new ResponseEntity<>(new RestStatus(new Status(BAD_STATUS, SEVERITY_WARNING, message)),
				HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Object> createResponseBuilderBadRequest(String message) {
		return new ResponseEntity<>(new RestStatus(new Status(BAD_STATUS, SEVERITY_ERROR, message)),
				HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Object> createResponseBuilderDeleted(Long id) {
		return new ResponseEntity<>(new RestStatus(
				new Status(SUCCESS_STATUS, SEVERITY_SUCCESS, MSN_PRODUCT_DELETED.replace(REPLACE, String.valueOf(id)))),
				HttpStatus.OK);
	}

	public ResponseEntity<Object> createResponseBuilderCreate(Product product) {
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> createResponseBuilderConflict(String message) {
		return new ResponseEntity<>(new RestStatus(new Status(BAD_STATUS, SEVERITY_ERROR, message)),
				HttpStatus.CONFLICT);
	}

}

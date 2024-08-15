package com.co.iuvity.hulk.store.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestStatus {

	@JsonProperty("Status")
	Status status;

	public RestStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RestStatus [status=" + status + "]";
	}

}

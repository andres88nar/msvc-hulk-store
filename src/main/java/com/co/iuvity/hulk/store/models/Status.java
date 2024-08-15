package com.co.iuvity.hulk.store.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {

	@JsonProperty("StatusCode")
	private String statusCode;

	@JsonProperty("Severity")
	private String severity;

	@JsonProperty("StatusDesc")
	private String statusDesc;

	public String getStatusCode() {
		return statusCode;
	}

	public Status(String statusCode, String severity, String statusDesc) {
		this.statusCode = statusCode;
		this.severity = severity;
		this.statusDesc = statusDesc;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	@Override
	public String toString() {
		return "Status [statusCode=" + statusCode + ", severity=" + severity + ", statusDesc=" + statusDesc + "]";
	}

}
